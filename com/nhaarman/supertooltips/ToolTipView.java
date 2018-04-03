package com.nhaarman.supertooltips;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.nhaarman.supertooltips.ToolTip.AnimationType;
import com.tado.C0676R;
import com.tado.android.report.ChartUtils;
import java.util.ArrayList;
import java.util.Collection;

public class ToolTipView extends LinearLayout implements OnPreDrawListener, OnClickListener {
    public static final String ALPHA_COMPAT = "alpha";
    public static final String SCALE_X_COMPAT = "scaleX";
    public static final String SCALE_Y_COMPAT = "scaleY";
    public static final String TRANSLATION_X_COMPAT = "translationX";
    public static final String TRANSLATION_Y_COMPAT = "translationY";
    private boolean hasMoreThanMaxLimitItems = false;
    private View mBottomFrame;
    private ImageView mBottomPointerView;
    private ViewGroup mContentHolder;
    private boolean mDimensionsKnown;
    private OnToolTipViewClickedListener mListener;
    private int mRelativeMasterViewX;
    private int mRelativeMasterViewY;
    private View mShadowView;
    private ToolTip mToolTip;
    private TextView mToolTipTV;
    private View mTopFrame;
    private ImageView mTopPointerView;
    private View mView;
    private int mWidth;

    private class AppearanceAnimatorListener extends AnimatorListenerAdapter {
        private final float mToolTipViewX;
        private final float mToolTipViewY;

        AppearanceAnimatorListener(float fToolTipViewX, float fToolTipViewY) {
            this.mToolTipViewX = fToolTipViewX;
            this.mToolTipViewY = fToolTipViewY;
        }

        public void onAnimationStart(Animator animation) {
        }

        @SuppressLint({"NewApi"})
        public void onAnimationEnd(Animator animation) {
            LayoutParams params = (LayoutParams) ToolTipView.this.getLayoutParams();
            params.leftMargin = (int) this.mToolTipViewX;
            params.topMargin = (int) this.mToolTipViewY;
            ToolTipView.this.setX(0.0f);
            ToolTipView.this.setY(0.0f);
            ToolTipView.this.setLayoutParams(params);
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    private class DisappearanceAnimatorListener extends AnimatorListenerAdapter {
        private DisappearanceAnimatorListener() {
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            if (ToolTipView.this.getParent() != null) {
                ((ViewManager) ToolTipView.this.getParent()).removeView(ToolTipView.this);
            }
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    public interface OnToolTipViewClickedListener {
        void onToolTipViewClicked(ToolTipView toolTipView);
    }

    public ToolTipView(Context context) {
        super(context);
        init();
    }

    public ToolTipView(Context context, boolean hasMoreThanMaxLimitItems) {
        super(context);
        this.hasMoreThanMaxLimitItems = hasMoreThanMaxLimitItems;
        init();
    }

    private void init() {
        setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(C0676R.layout.bubble_tooltip, this, true);
        this.mTopPointerView = (ImageView) findViewById(C0676R.id.tooltip_pointer_up);
        this.mTopFrame = findViewById(C0676R.id.tooltip_topframe);
        this.mContentHolder = (ViewGroup) findViewById(C0676R.id.tooltip_contentholder);
        this.mToolTipTV = (TextView) findViewById(C0676R.id.tooltip_contenttv);
        this.mBottomFrame = findViewById(C0676R.id.tooltip_bottomframe);
        this.mBottomPointerView = (ImageView) findViewById(C0676R.id.tooltip_pointer_down);
        this.mShadowView = findViewById(C0676R.id.tooltip_shadow);
        if (this.hasMoreThanMaxLimitItems) {
            this.mContentHolder.getLayoutParams().height = (int) ChartUtils.getDIPValue(BitmapDescriptorFactory.HUE_CYAN);
        }
        setOnClickListener(this);
        getViewTreeObserver().addOnPreDrawListener(this);
    }

    public boolean onPreDraw() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        this.mDimensionsKnown = true;
        this.mWidth = this.mContentHolder.getWidth();
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.width = this.mWidth;
        setLayoutParams(layoutParams);
        if (this.mToolTip != null) {
            applyToolTipPosition();
        }
        return true;
    }

    public void setToolTip(ToolTip toolTip, View view) {
        this.mToolTip = toolTip;
        this.mView = view;
        if (this.mToolTip.getText() != null) {
            this.mToolTipTV.setText(this.mToolTip.getText());
        } else if (this.mToolTip.getTextResId() != 0) {
            this.mToolTipTV.setText(this.mToolTip.getTextResId());
        }
        if (this.mToolTip.getTypeface() != null) {
            this.mToolTipTV.setTypeface(this.mToolTip.getTypeface());
        }
        if (this.mToolTip.getTextColor() != 0) {
            this.mToolTipTV.setTextColor(this.mToolTip.getTextColor());
        }
        if (this.mToolTip.getColor() != 0) {
            setColor(this.mToolTip.getColor());
        }
        if (this.mToolTip.getContentView() != null) {
            setContentView(this.mToolTip.getContentView());
        }
        if (!this.mToolTip.shouldShowShadow()) {
            this.mShadowView.setVisibility(8);
        }
        if (this.mDimensionsKnown) {
            applyToolTipPosition();
        }
    }

    private void applyToolTipPosition() {
        if (getParent() == null) {
            remove();
            return;
        }
        int[] masterViewScreenPosition = new int[2];
        this.mView.getLocationOnScreen(masterViewScreenPosition);
        Rect viewDisplayFrame = new Rect();
        this.mView.getWindowVisibleDisplayFrame(viewDisplayFrame);
        int[] parentViewScreenPosition = new int[2];
        ((View) getParent()).getLocationOnScreen(parentViewScreenPosition);
        int masterViewWidth = this.mView.getWidth();
        int masterViewHeight = this.mView.getHeight();
        this.mRelativeMasterViewX = masterViewScreenPosition[0] - parentViewScreenPosition[0];
        this.mRelativeMasterViewY = masterViewScreenPosition[1] - parentViewScreenPosition[1];
        int relativeMasterViewCenterX = this.mRelativeMasterViewX + (masterViewWidth / 2);
        int toolTipViewAboveY = (this.mRelativeMasterViewY - getHeight()) + ((int) ChartUtils.getDIPValue(8.0f));
        int toolTipViewBelowY = Math.max(0, this.mRelativeMasterViewY + masterViewHeight);
        int toolTipViewX = Math.max(0, relativeMasterViewCenterX - (this.mWidth / 2));
        if (this.mWidth + toolTipViewX > viewDisplayFrame.right) {
            toolTipViewX = viewDisplayFrame.right - this.mWidth;
        }
        setX((float) toolTipViewX);
        setPointerCenterX(relativeMasterViewCenterX);
        if (VERSION.SDK_INT < 11) {
            this.mTopPointerView.setAlpha(0);
            this.mBottomPointerView.setAlpha(1);
        } else {
            this.mTopPointerView.setVisibility(8);
            this.mBottomPointerView.setVisibility(0);
        }
        int toolTipViewY = toolTipViewAboveY;
        if (this.mToolTip.getAnimationType() == AnimationType.NONE) {
            setTranslationY((float) toolTipViewY);
            setTranslationX((float) toolTipViewX);
            return;
        }
        Collection<Animator> animators = new ArrayList(5);
        if (this.mToolTip.getAnimationType() == AnimationType.FROM_MASTER_VIEW) {
            animators.add(ObjectAnimator.ofInt(this, TRANSLATION_Y_COMPAT, new int[]{(this.mRelativeMasterViewY + (this.mView.getHeight() / 2)) - (getHeight() / 2), toolTipViewY}));
            animators.add(ObjectAnimator.ofInt(this, TRANSLATION_X_COMPAT, new int[]{(this.mRelativeMasterViewX + (this.mView.getWidth() / 2)) - (this.mWidth / 2), toolTipViewX}));
        } else {
            if (this.mToolTip.getAnimationType() == AnimationType.FROM_TOP) {
                animators.add(ObjectAnimator.ofFloat(this, TRANSLATION_Y_COMPAT, new float[]{0.0f, (float) toolTipViewY}));
            }
        }
        float[] fArr = new float[2];
        animators.add(ObjectAnimator.ofFloat(this, SCALE_X_COMPAT, new float[]{0.0f, 1.0f}));
        fArr = new float[2];
        animators.add(ObjectAnimator.ofFloat(this, SCALE_Y_COMPAT, new float[]{0.0f, 1.0f}));
        fArr = new float[2];
        animators.add(ObjectAnimator.ofFloat(this, ALPHA_COMPAT, new float[]{0.0f, 1.0f}));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        if (VERSION.SDK_INT < 11) {
            animatorSet.addListener(new AppearanceAnimatorListener((float) toolTipViewX, (float) toolTipViewY));
        }
        animatorSet.start();
    }

    public void setPointerCenterX(int pointerCenterX) {
        int pointerWidth = Math.max(this.mTopPointerView.getMeasuredWidth(), this.mBottomPointerView.getMeasuredWidth());
        this.mTopPointerView.setX((float) ((pointerCenterX - (pointerWidth / 2)) - ((int) getX())));
        this.mBottomPointerView.setX((float) ((pointerCenterX - (pointerWidth / 2)) - ((int) getX())));
    }

    public void setOnToolTipViewClickedListener(OnToolTipViewClickedListener listener) {
        this.mListener = listener;
    }

    public void setColor(int color) {
        this.mTopPointerView.setColorFilter(color, Mode.MULTIPLY);
        this.mTopFrame.getBackground().setColorFilter(color, Mode.MULTIPLY);
        this.mBottomPointerView.setColorFilter(color, Mode.MULTIPLY);
        this.mBottomFrame.getBackground().setColorFilter(color, Mode.MULTIPLY);
        this.mContentHolder.setBackgroundColor(color);
    }

    private void setContentView(View view) {
        this.mContentHolder.removeAllViews();
        this.mContentHolder.addView(view);
    }

    public void remove() {
        if (VERSION.SDK_INT < 11) {
            LayoutParams params = (LayoutParams) getLayoutParams();
            setX((float) params.leftMargin);
            setY((float) params.topMargin);
            params.leftMargin = 0;
            params.topMargin = 0;
            setLayoutParams(params);
        }
        if (this.mToolTip.getAnimationType() != AnimationType.NONE) {
            Collection<Animator> animators = new ArrayList(5);
            if (this.mToolTip.getAnimationType() == AnimationType.FROM_MASTER_VIEW) {
                animators.add(ObjectAnimator.ofInt(this, TRANSLATION_Y_COMPAT, new int[]{(int) getY(), (this.mRelativeMasterViewY + (this.mView.getHeight() / 2)) - (getHeight() / 2)}));
                animators.add(ObjectAnimator.ofInt(this, TRANSLATION_X_COMPAT, new int[]{(int) getX(), (this.mRelativeMasterViewX + (this.mView.getWidth() / 2)) - (this.mWidth / 2)}));
            } else {
                animators.add(ObjectAnimator.ofFloat(this, TRANSLATION_Y_COMPAT, new float[]{getY(), 0.0f}));
            }
            animators.add(ObjectAnimator.ofFloat(this, SCALE_X_COMPAT, new float[]{1.0f, 0.0f}));
            animators.add(ObjectAnimator.ofFloat(this, SCALE_Y_COMPAT, new float[]{1.0f, 0.0f}));
            animators.add(ObjectAnimator.ofFloat(this, ALPHA_COMPAT, new float[]{1.0f, 0.0f}));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animators);
            animatorSet.addListener(new DisappearanceAnimatorListener());
            animatorSet.start();
        } else if (getParent() != null) {
            ((ViewManager) getParent()).removeView(this);
        }
    }

    public void onClick(View view) {
        remove();
        if (this.mListener != null) {
            this.mListener.onToolTipViewClicked(this);
        }
    }

    @SuppressLint({"NewApi"})
    public float getX() {
        if (VERSION.SDK_INT >= 11) {
            return super.getX();
        }
        return getX();
    }

    @SuppressLint({"NewApi"})
    public void setX(float x) {
        if (VERSION.SDK_INT >= 11) {
            super.setX(x);
        } else {
            setX(x);
        }
    }

    @SuppressLint({"NewApi"})
    public float getY() {
        if (VERSION.SDK_INT >= 11) {
            return super.getY();
        }
        return getY();
    }

    @SuppressLint({"NewApi"})
    public void setY(float y) {
        if (VERSION.SDK_INT >= 11) {
            super.setY(y);
        } else {
            setY(y);
        }
    }

    public boolean isHasMoreThanMaxLimitItems() {
        return this.hasMoreThanMaxLimitItems;
    }

    public void setHasMoreThanMaxLimitItems(boolean hasMoreThanMaxLimitItems) {
        this.hasMoreThanMaxLimitItems = hasMoreThanMaxLimitItems;
    }
}

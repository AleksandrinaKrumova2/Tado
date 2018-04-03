package com.tado.android.views;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import com.tado.C0676R;
import com.tado.android.report.ChartUtils;
import com.tado.android.utils.DisplaySizeHelper;
import com.tado.android.utils.Snitcher;

public class MorphingButton extends Button {
    public static final int ANIMATION_DURATION = 300;
    public static final int COLLAPSE_ANIMATION_DELAY = 2000;
    private static final String TAG = "endManualControl";
    private float animateTo;
    private View attachedToView;
    private Collapse collapseAnimationRunnable;
    private DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
    private OnLayoutChangeListener onLayoutChangeListener = new C12661();
    private OvershootInterpolator overshootInterpolator = new OvershootInterpolator(0.7f);
    private int rightMargin;
    private int screenRight;
    private int uncollapsedWidth = -1;
    private ValueAnimator valueAnimator;

    class C12661 implements OnLayoutChangeListener {
        C12661() {
        }

        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            MorphingButton.this.translateYTo((float) ((MorphingButton.this.attachedToView.getTop() - MorphingButton.this.getMeasuredHeight()) - ((LayoutParams) MorphingButton.this.getLayoutParams()).bottomMargin));
        }
    }

    class C12672 implements Runnable {
        C12672() {
        }

        public void run() {
            MorphingButton.this.setVisibility(4);
        }
    }

    class C12683 implements Runnable {
        C12683() {
        }

        public void run() {
            MorphingButton.this.setVisibility(0);
        }
    }

    class C12705 implements Runnable {
        C12705() {
        }

        public void run() {
            MorphingButton.this.setVisibility(0);
        }
    }

    class C12716 implements AnimatorUpdateListener {
        C12716() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            MorphingButton.this.setTextColor(((Integer) animation.getAnimatedValue()).intValue());
            MorphingButton.this.getCompoundDrawables()[0].mutate().setColorFilter(((Integer) animation.getAnimatedValue()).intValue(), Mode.SRC_ATOP);
        }
    }

    private class Collapse implements Runnable {
        int delay;
        int duration;
        boolean instant;

        public Collapse(int delay, boolean instant, int duration) {
            this.delay = delay;
            this.instant = instant;
            this.duration = duration;
        }

        public void run() {
            MorphingButton.this.collapseTarget(this.delay, this.instant, this.duration);
        }
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C12721();
        int width;

        static class C12721 implements Creator<SavedState> {
            C12721() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.width = in.readInt();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.width);
        }
    }

    public MorphingButton(Context context) {
        super(context);
        initScreenRight();
    }

    public MorphingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScreenRight();
    }

    public MorphingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScreenRight();
    }

    public MorphingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initScreenRight();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.uncollapsedWidth == -1) {
            this.uncollapsedWidth = getMeasuredWidth();
        }
        this.rightMargin = ((MarginLayoutParams) getLayoutParams()).rightMargin;
    }

    private void initScreenRight() {
        Snitcher.start().log(TAG, "%d initScreenRight", Integer.valueOf(System.identityHashCode(this)));
        this.screenRight = DisplaySizeHelper.getScreenSize((WindowManager) getContext().getSystemService("window")).x;
    }

    public Parcelable onSaveInstanceState() {
        Snitcher.start().log(TAG, "%d onSaveInstanceState", Integer.valueOf(System.identityHashCode(this)));
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.width = this.uncollapsedWidth;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        Snitcher.start().log(TAG, "%d onRestoreInstanceState", Integer.valueOf(System.identityHashCode(this)));
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.uncollapsedWidth = savedState.width;
    }

    public void slideUp(Interpolator slideInterpolator, boolean hasActiveOverlay, float yBy) {
        Snitcher.start().log(TAG, "%d slideUp:yBy %f", Integer.valueOf(System.identityHashCode(this)), Float.valueOf(yBy));
        removeAnimations();
        resetState(hasActiveOverlay);
        if (hasActiveOverlay) {
            ViewCompat.animate(this).yBy(yBy).alpha(1.0f).translationZ(5.0f).setDuration(300).setStartDelay(0).setInterpolator(this.overshootInterpolator).start();
            startBackgroundTransition(50);
            return;
        }
        resetState(hasActiveOverlay);
    }

    public void slideDown(Interpolator slideInterpolator, float yBy) {
        Snitcher.start().log(TAG, "%d slideDown:yBy %f", Integer.valueOf(System.identityHashCode(this)), Float.valueOf(yBy));
        removeAnimations();
        this.collapseAnimationRunnable = new Collapse(COLLAPSE_ANIMATION_DELAY, false, 300);
        ViewCompat.animate(this).yBy(yBy).alpha(1.0f).translationZ(0.0f).setDuration(300).setInterpolator(slideInterpolator).setStartDelay(0).withEndAction(this.collapseAnimationRunnable).start();
        reverseBackgroundTransition(300);
    }

    public void fadeOut() {
        fadeOut(300);
    }

    private void fadeOut(int duration) {
        Snitcher.start().log(TAG, "%d fadeOut:duration %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(duration));
        if (getVisibility() == 0) {
            ViewCompat.animate(this).scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setStartDelay(0).translationZ(0.0f).setDuration((long) duration).withEndAction(new C12672()).start();
        }
    }

    public void fadeIn(int duration) {
        Snitcher.start().log(TAG, "%d fadeIn:duration %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(duration));
        if (getVisibility() != 0) {
            setVisibility(0);
            setAlpha(0.0f);
            setScaleX(0.0f);
            setScaleY(0.0f);
            if (VERSION.SDK_INT >= 21) {
                setTranslationZ(0.0f);
            }
            ViewCompat.animate(this).scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setStartDelay(0).setDuration((long) duration).withEndAction(new C12683()).start();
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        Snitcher.start().log(TAG, "%d setVisibility %b", Integer.valueOf(System.identityHashCode(this)), Boolean.valueOf(isVisible()));
    }

    public void collapseWithDelay() {
        Snitcher.start().log(TAG, "%d collapseWithDelay", Integer.valueOf(System.identityHashCode(this)));
        removeAnimations();
        this.collapseAnimationRunnable = new Collapse(COLLAPSE_ANIMATION_DELAY, false, 300);
        post(this.collapseAnimationRunnable);
    }

    public void collapse() {
        Snitcher.start().log(TAG, "%d collapse", Integer.valueOf(System.identityHashCode(this)));
        this.collapseAnimationRunnable = new Collapse(0, true, 100);
        post(this.collapseAnimationRunnable);
    }

    public synchronized boolean isCollapsed() {
        return getMeasuredHeight() == getMeasuredWidth();
    }

    private void collapseTarget(int delay, boolean instant, int duration) {
        Snitcher.start().log(TAG, "%d collapseTarget:delay %d :instante %b :duration %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(delay), Boolean.valueOf(instant), Integer.valueOf(duration));
        resetState(true);
        if (instant) {
            setVisibility(4);
        }
        int startWidth = getMeasuredWidth();
        int endWidth = getMeasuredHeight();
        final float endRadius = ChartUtils.dpToPx(25.0f, getContext());
        this.valueAnimator = ValueAnimator.ofInt(new int[]{startWidth, endWidth});
        this.valueAnimator.setInterpolator(this.decelerateInterpolator);
        this.valueAnimator.setDuration((long) duration);
        this.valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                GradientDrawable shape;
                int value = ((Integer) animation.getAnimatedValue()).intValue();
                float alpha = animation.getAnimatedFraction();
                ViewGroup.LayoutParams params = MorphingButton.this.getLayoutParams();
                params.width = value;
                MorphingButton.this.setLayoutParams(params);
                int angle = (int) (10000.0f * animation.getAnimatedFraction());
                float alphaValue = 1.0f - alpha;
                int currentColor = MorphingButton.this.getCurrentTextColor();
                MorphingButton.this.setTextColor(Color.argb((int) (255.0f * alphaValue), Color.red(currentColor), Color.green(currentColor), Color.blue(currentColor)));
                MorphingButton.this.getCompoundDrawables()[0].mutate().setLevel(angle);
                TransitionDrawable bg = (TransitionDrawable) MorphingButton.this.getBackground().mutate();
                if (VERSION.SDK_INT >= 21) {
                    shape = (GradientDrawable) ((RippleDrawable) bg.getDrawable(0).mutate()).getDrawable(0).mutate();
                } else {
                    shape = (GradientDrawable) ((StateListDrawable) bg.getDrawable(0).mutate()).getCurrent().mutate();
                }
                shape.setCornerRadius(endRadius * alpha);
            }
        });
        this.valueAnimator.setStartDelay((long) delay);
        ViewPropertyAnimatorCompat slideRight = ViewCompat.animate(this).m0x((float) ((this.screenRight - endWidth) - this.rightMargin)).setInterpolator(this.decelerateInterpolator).setDuration((long) duration).setStartDelay((long) delay).withEndAction(new C12705());
        if (instant) {
            this.valueAnimator.end();
            slideRight.setDuration(100);
            slideRight.start();
            return;
        }
        this.valueAnimator.start();
        slideRight.start();
    }

    public void startBackgroundTransition(int duration) {
        Snitcher.start().log(TAG, "%d startBackgroundTransition:duration %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(duration));
        setTextColor(ContextCompat.getColor(getContext(), C0676R.color.ac_home));
        getCompoundDrawables()[0].mutate().setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.ac_home), Mode.SRC_ATOP);
        ((TransitionDrawable) getBackground().mutate()).startTransition(duration);
    }

    public void reverseBackgroundTransition(int duration) {
        Snitcher.start().log(TAG, "%d reverseBackgroundTransition:duration %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(duration));
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(ContextCompat.getColor(getContext(), C0676R.color.ac_home)), Integer.valueOf(ContextCompat.getColor(getContext(), C0676R.color.white))});
        colorAnimator.addUpdateListener(new C12716());
        colorAnimator.start();
        ((TransitionDrawable) getBackground().mutate()).reverseTransition(duration);
    }

    public void translateYTo(float yTo) {
        Snitcher.start().log(TAG, "%d translateYTo:yTo %f", Integer.valueOf(System.identityHashCode(this)), Float.valueOf(yTo));
        if (yTo != this.animateTo) {
            this.animateTo = yTo;
            ViewCompat.animate(this).m1y(this.animateTo).setStartDelay(0).setDuration(300).setInterpolator(this.overshootInterpolator).start();
        }
    }

    public void attachToView(View view) {
        Snitcher.start().log(TAG, "%d attachToView:view %d", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(view.getId()));
        if (view == null) {
            detachView();
            return;
        }
        this.attachedToView = view;
        this.attachedToView.addOnLayoutChangeListener(this.onLayoutChangeListener);
    }

    public void detachView() {
        Snitcher.start().log(TAG, "%d detachView", Integer.valueOf(System.identityHashCode(this)));
        if (this.attachedToView != null) {
            this.attachedToView.removeOnLayoutChangeListener(this.onLayoutChangeListener);
        }
        this.attachedToView = null;
    }

    public void resetState(boolean hasActiveOverlay) {
        GradientDrawable shape;
        Snitcher.start().log(TAG, "%d resetState:hasActiveOverlay %b", Integer.valueOf(System.identityHashCode(this)), Boolean.valueOf(hasActiveOverlay));
        setAlpha(1.0f);
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        if (VERSION.SDK_INT >= 21) {
            setTranslationZ(0.0f);
        }
        setScaleX(1.0f);
        setScaleY(1.0f);
        setTextColor(ContextCompat.getColor(getContext(), C0676R.color.white));
        getCompoundDrawables()[0].mutate().setLevel(0);
        if (this.uncollapsedWidth != -1) {
            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = this.uncollapsedWidth;
            setLayoutParams(params);
        }
        setText(C0676R.string.manualControl_endManualControlButton);
        setTextColor(-1);
        getCompoundDrawables()[0].mutate().setColorFilter(-1, Mode.SRC_ATOP);
        ((TransitionDrawable) getBackground().mutate()).resetTransition();
        LayerDrawable bg = (LayerDrawable) getBackground().mutate();
        if (VERSION.SDK_INT >= 21) {
            shape = (GradientDrawable) ((RippleDrawable) bg.getDrawable(0).mutate()).getDrawable(0).mutate();
        } else {
            shape = (GradientDrawable) ((StateListDrawable) bg.getDrawable(0).mutate()).getCurrent();
        }
        shape.setCornerRadius(ChartUtils.dpToPx(4.0f, getContext()));
        if (hasActiveOverlay) {
            fadeIn(225);
        } else {
            fadeOut(300);
        }
    }

    public void removeAnimations() {
        Snitcher.start().log(TAG, "%d removeAnimations", Integer.valueOf(System.identityHashCode(this)));
        clearAnimation();
        if (!(this.collapseAnimationRunnable == null || getHandler() == null)) {
            getHandler().removeCallbacks(this.collapseAnimationRunnable);
        }
        ViewCompat.animate(this).cancel();
        if (this.valueAnimator != null) {
            this.valueAnimator.cancel();
        }
    }
}

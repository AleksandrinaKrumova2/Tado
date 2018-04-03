package com.tado.android.installation.srt.view;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;

public class DevicePropertyPanel extends LinearLayout {
    private boolean inProgress = true;
    @BindView(2131296559)
    ImageView mImageView;
    @BindView(2131296560)
    ProgressBar mProgressBar;
    @BindView(2131296561)
    TextView mTextView;
    private boolean progressChange = true;
    private CharSequence text;

    public DevicePropertyPanel(Context context) {
        super(context);
        initView(context);
    }

    public DevicePropertyPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DevicePropertyPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public DevicePropertyPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        Object view = LayoutInflater.from(context).inflate(C0676R.layout.device_property_panel, this);
        view.setBackgroundResource(C0676R.color.device_property_panel_in_progress_color);
        ButterKnife.bind(view, (View) this);
        this.mProgressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.SRC_ATOP);
        this.mTextView.setMovementMethod(new LinkMovementMethod());
    }

    private void updateLayout() {
        this.mTextView.setText(this.text);
        crossfadeProgressBarWithImageView(this.progressChange, this.inProgress);
        updateBackgroundColor(this.progressChange, this.inProgress);
    }

    public void setDevicePropertyState(CharSequence text, boolean inProgress) {
        this.text = text;
        this.progressChange = inProgress != this.inProgress;
        this.inProgress = inProgress;
        updateLayout();
    }

    private void updateBackgroundColor(boolean progressChange, boolean inProgress) {
        if (!progressChange) {
            return;
        }
        if (inProgress) {
            animateBackgroundColorChange(ContextCompat.getColor(getContext(), C0676R.color.device_property_panel_successful_color), ContextCompat.getColor(getContext(), C0676R.color.device_property_panel_in_progress_color));
        } else {
            animateBackgroundColorChange(ContextCompat.getColor(getContext(), C0676R.color.device_property_panel_in_progress_color), ContextCompat.getColor(getContext(), C0676R.color.device_property_panel_successful_color));
        }
    }

    private void animateBackgroundColorChange(int fromColor, int toColor) {
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(fromColor), new ColorDrawable(toColor)});
        if (VERSION.SDK_INT >= 16) {
            setBackground(transitionDrawable);
        } else {
            setBackgroundDrawable(transitionDrawable);
        }
        transitionDrawable.startTransition(300);
    }

    private void crossfadeProgressBarWithImageView(boolean progressChange, boolean inProgress) {
        if (!progressChange) {
            return;
        }
        if (inProgress) {
            crossfadeAnimation(this.mImageView, this.mProgressBar);
        } else {
            crossfadeAnimation(this.mProgressBar, this.mImageView);
        }
    }

    private void crossfadeAnimation(final View from, View to) {
        to.setAlpha(0.0f);
        to.setVisibility(0);
        ViewCompat.animate(to).alpha(1.0f).setDuration(300).setListener(null).start();
        ViewCompat.animate(from).alpha(0.0f).setDuration(300).setListener(new ViewPropertyAnimatorListener() {
            public void onAnimationStart(View view) {
            }

            public void onAnimationEnd(View view) {
                from.setVisibility(4);
            }

            public void onAnimationCancel(View view) {
            }
        }).start();
    }
}

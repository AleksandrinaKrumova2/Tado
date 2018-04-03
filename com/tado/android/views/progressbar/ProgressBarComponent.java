package com.tado.android.views.progressbar;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

public class ProgressBarComponent extends ProgressBar implements ProgressBarComponentInterface {

    class C13001 implements AnimatorListener {
        C13001() {
        }

        public void onAnimationStart(Animator animation) {
            ProgressBarComponent.this.setIndeterminate(false);
        }

        public void onAnimationEnd(Animator animation) {
            ProgressBarComponent.this.setVisibility(8);
            ProgressBarComponent.this.setIndeterminate(true);
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    public ProgressBarComponent(Context context) {
        super(context);
    }

    public ProgressBarComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBarComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressBarComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void showView() {
        setVisibility(0);
    }

    public void hideView() {
        setVisibility(8);
    }

    public void hideViewWithAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, NotificationCompat.CATEGORY_PROGRESS, new int[]{getProgress(), 100});
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(getProgressBarAnimationListener());
        animator.start();
    }

    @NonNull
    private AnimatorListener getProgressBarAnimationListener() {
        return new C13001();
    }
}

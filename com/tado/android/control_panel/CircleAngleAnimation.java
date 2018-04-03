package com.tado.android.control_panel;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircleAngleAnimation extends Animation {
    private CircleView mCircleView;
    private float mNewAngle;
    private float mOldAngle;

    public CircleAngleAnimation(CircleView circleView, int newAngle) {
        this.mOldAngle = circleView.getAngle();
        this.mNewAngle = (float) newAngle;
        this.mCircleView = circleView;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        this.mCircleView.setAngle(this.mOldAngle + ((this.mNewAngle - this.mOldAngle) * interpolatedTime));
        this.mCircleView.requestLayout();
    }
}

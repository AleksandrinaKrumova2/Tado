package me.relex.circleindicator;

import android.view.animation.Interpolator;

class CircleIndicator$ReverseInterpolator implements Interpolator {
    final /* synthetic */ CircleIndicator this$0;

    private CircleIndicator$ReverseInterpolator(CircleIndicator circleIndicator) {
        this.this$0 = circleIndicator;
    }

    public float getInterpolation(float value) {
        return Math.abs(1.0f - value);
    }
}

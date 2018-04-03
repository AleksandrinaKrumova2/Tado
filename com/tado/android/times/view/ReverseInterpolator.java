package com.tado.android.times.view;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

class ReverseInterpolator implements Interpolator {
    private final Interpolator delegate;

    public ReverseInterpolator(Interpolator delegate) {
        this.delegate = delegate;
    }

    public ReverseInterpolator() {
        this(new LinearInterpolator());
    }

    public float getInterpolation(float v) {
        return 1.0f - this.delegate.getInterpolation(v);
    }
}

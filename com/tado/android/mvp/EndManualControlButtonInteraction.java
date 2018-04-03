package com.tado.android.mvp;

import android.view.View;
import android.view.animation.Interpolator;

public interface EndManualControlButtonInteraction {
    void collapseEndManualControlButton();

    boolean isCollapsed();

    void onAnimationCancel(boolean z);

    void onAnimationEnd(boolean z, boolean z2, View view, Interpolator interpolator);

    void onAnimationStart(boolean z, boolean z2, View view, Interpolator interpolator);

    void onEndManualControlButtonSlideAnimation(boolean z);
}

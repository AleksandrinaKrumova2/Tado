package com.tado.android.onboarding;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import com.nhaarman.supertooltips.ToolTipView;

public class PulsingAnimator extends AppCompatImageView {
    private static final int ANIMATION_TIME_IN_MS = 600;
    private static final float SCALE_FACTOR = 1.235f;
    private ObjectAnimator objAnim;

    public PulsingAnimator(Context context) {
        super(context);
    }

    public PulsingAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PulsingAnimator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        switch (visibility) {
            case 0:
                applyPulsingAnimation();
                return;
            default:
                stopPulsingAnimation();
                return;
        }
    }

    private void applyPulsingAnimation() {
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[2];
        propertyValuesHolderArr[0] = PropertyValuesHolder.ofFloat(ToolTipView.SCALE_X_COMPAT, new float[]{SCALE_FACTOR});
        propertyValuesHolderArr[1] = PropertyValuesHolder.ofFloat(ToolTipView.SCALE_Y_COMPAT, new float[]{SCALE_FACTOR});
        this.objAnim = ObjectAnimator.ofPropertyValuesHolder(this, propertyValuesHolderArr);
        this.objAnim.setDuration(600);
        this.objAnim.setRepeatCount(-1);
        this.objAnim.setRepeatMode(2);
        this.objAnim.start();
    }

    private void stopPulsingAnimation() {
        if (this.objAnim != null) {
            this.objAnim.cancel();
            this.objAnim = null;
        }
    }
}

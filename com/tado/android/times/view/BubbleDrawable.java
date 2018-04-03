package com.tado.android.times.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.nhaarman.supertooltips.ToolTipView;
import com.tado.C0676R;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

public class BubbleDrawable extends ScaleDrawable {
    public static final int BALLOON_ANIMATION_DURATION = 250;
    AnimatorListener animatorListener = new C12263();
    AnimatorListener animatorReverseListener = new C12252();
    private ObjectAnimator bubbleAlphaAnimator;
    private AnimatorSet bubbleAnimatorSet;
    private Interpolator bubbleForwardInterpolator = new AccelerateInterpolator();
    private Interpolator bubbleReverseInterpolator = new ReverseInterpolator(this.bubbleForwardInterpolator);
    private ObjectAnimator bubbleScaleAnimator;
    private Drawable deleteDrawable;
    private BubbleListener listener;
    private boolean showDeleteIcon = false;
    private float textHeight = 0.0f;
    private TextPaint timePaint;
    AnimatorUpdateListener updateListener = new C12241();

    class C12241 implements AnimatorUpdateListener {
        C12241() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (BubbleDrawable.this.listener != null) {
                BubbleDrawable.this.listener.onUpdate();
            }
        }
    }

    class C12252 implements AnimatorListener {
        C12252() {
        }

        public void onAnimationStart(Animator animator) {
            if (BubbleDrawable.this.listener != null) {
                BubbleDrawable.this.listener.onReverseStart();
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (BubbleDrawable.this.listener != null) {
                BubbleDrawable.this.listener.onReverseEnd();
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    class C12263 implements AnimatorListener {
        C12263() {
        }

        public void onAnimationStart(Animator animator) {
            if (BubbleDrawable.this.listener != null) {
                BubbleDrawable.this.listener.onStart();
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (BubbleDrawable.this.listener != null) {
                BubbleDrawable.this.listener.onEnd();
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    public interface BubbleListener {
        void onEnd();

        void onReverseEnd();

        void onReverseStart();

        void onStart();

        void onUpdate();
    }

    public BubbleDrawable(Context context, BubbleListener listener) {
        super(ContextCompat.getDrawable(context, C0676R.drawable.time_bubble), 81, 1.0f, 1.0f);
        this.listener = listener;
        this.bubbleScaleAnimator = ObjectAnimator.ofObject(this, Param.LEVEL, new IntEvaluator(), new Object[]{Integer.valueOf(0), Integer.valueOf(AbstractSpiCall.DEFAULT_TIMEOUT)});
        this.bubbleScaleAnimator.addUpdateListener(this.updateListener);
        this.bubbleAlphaAnimator = ObjectAnimator.ofInt(this, ToolTipView.ALPHA_COMPAT, new int[]{0, 255});
        this.bubbleAnimatorSet = new AnimatorSet();
        this.bubbleAnimatorSet.playTogether(new Animator[]{this.bubbleScaleAnimator, this.bubbleAlphaAnimator});
        this.bubbleAnimatorSet.setDuration(250);
        this.bubbleAnimatorSet.setInterpolator(this.bubbleForwardInterpolator);
        this.timePaint = getPaintForBubble(context.getResources().getDimension(C0676R.dimen.times_block_temp_text_size));
        this.textHeight = getTextHeight("00", this.timePaint);
        this.deleteDrawable = ContextCompat.getDrawable(context, C0676R.drawable.ic_delete_icon);
        setLevel(0);
    }

    public void setListener(BubbleListener listener) {
        this.listener = listener;
    }

    protected TextPaint getPaintForBubble(float textSize) {
        TextPaint paint = new TextPaint();
        paint.setColor(-1);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        paint.setTextSize(textSize);
        paint.setTextAlign(Align.CENTER);
        return paint;
    }

    private float getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return (float) rect.height();
    }

    public void setShowDeleteIcon(boolean showDeleteIcon) {
        this.showDeleteIcon = showDeleteIcon;
    }

    public void startAnimation() {
        this.bubbleAnimatorSet.setInterpolator(this.bubbleForwardInterpolator);
        this.bubbleAnimatorSet.removeListener(this.animatorReverseListener);
        this.bubbleAnimatorSet.addListener(this.animatorListener);
        this.bubbleAnimatorSet.start();
    }

    public void reverseAnimation() {
        this.bubbleAnimatorSet.setInterpolator(this.bubbleReverseInterpolator);
        this.bubbleAnimatorSet.removeListener(this.animatorListener);
        this.bubbleAnimatorSet.addListener(this.animatorReverseListener);
        this.bubbleAnimatorSet.start();
    }

    public void draw(Canvas canvas, String[] localizedTime, float x, int y, boolean is24HoursFormat) {
        super.draw(canvas);
        if (this.showDeleteIcon) {
            this.deleteDrawable.setBounds(((int) x) - (this.deleteDrawable.getIntrinsicWidth() / 2), y - this.deleteDrawable.getIntrinsicHeight(), ((int) x) + (this.deleteDrawable.getIntrinsicWidth() / 2), y);
            this.deleteDrawable.setColorFilter(-1, Mode.SRC_ATOP);
            this.deleteDrawable.draw(canvas);
        } else if (is24HoursFormat) {
            canvas.drawText(localizedTime[0], x, (float) y, this.timePaint);
        } else {
            canvas.drawText(localizedTime[0], x, ((float) y) - (this.textHeight / 2.0f), this.timePaint);
            canvas.drawText(localizedTime[1], x, ((float) y) + this.textHeight, this.timePaint);
        }
    }
}

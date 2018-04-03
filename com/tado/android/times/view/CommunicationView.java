package com.tado.android.times.view;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tado.C0676R;

public class CommunicationView extends RelativeLayout {
    @BindView(2131296350)
    ImageView bgImage;
    private boolean isPremium;
    @BindView(2131296351)
    ImageView leftImage;
    @BindView(2131296352)
    ImageView rightImage;
    @BindView(2131296353)
    TextView textView;
    private AnimatorUpdateListener updateListener = new C12271();

    class C12271 implements AnimatorUpdateListener {
        C12271() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CommunicationView.this.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    public CommunicationView(Context context) {
        super(context);
        init(context);
    }

    public CommunicationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommunicationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CommunicationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(C0676R.layout.communication_view, this);
        ButterKnife.bind((View) this);
        setBackgroundResource(C0676R.color.communication_area_heating_bg);
        if (isInEditMode()) {
            configureAsHeatingHomeView();
        }
    }

    public CommunicationView setImageLeft(@DrawableRes int drawableId) {
        this.leftImage.setImageResource(drawableId);
        return this;
    }

    public CommunicationView setImageRight(@DrawableRes int drawableId) {
        this.rightImage.setImageResource(drawableId);
        return this;
    }

    public CommunicationView setImageCenter(@DrawableRes int drawableId) {
        this.bgImage.setImageResource(drawableId);
        return this;
    }

    public CommunicationView setText(@StringRes int stringId) {
        if (this.isPremium) {
            this.textView.setText(stringId);
        } else {
            this.textView.setText("");
        }
        return this;
    }

    public void setDraggingText() {
        fadeOutViews(this.leftImage, this.rightImage, this.bgImage);
        setText(C0676R.string.smartSchedule_home_draggingInformationMessage);
    }

    public void setReleaseText() {
        fadeInViews(this.leftImage, this.rightImage, this.bgImage);
        setText(C0676R.string.smartSchedule_home_tabInformationMessage);
    }

    public CommunicationView setPremium(boolean premium) {
        this.isPremium = premium;
        return this;
    }

    private void fadeOutViews(View... views) {
        for (View view : views) {
            ViewCompat.animate(view).alpha(0.0f).start();
        }
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(ContextCompat.getColor(getContext(), C0676R.color.communication_area_heating_bg)), Integer.valueOf(-1)});
        colorAnimator.addUpdateListener(this.updateListener);
        colorAnimator.start();
    }

    private void fadeInViews(View... views) {
        for (View view : views) {
            ViewCompat.animate(view).alpha(1.0f).start();
        }
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(-1), Integer.valueOf(ContextCompat.getColor(getContext(), C0676R.color.communication_area_heating_bg))});
        colorAnimator.addUpdateListener(this.updateListener);
        colorAnimator.start();
    }

    public void configureAsHeatingHomeView() {
        setImageLeft(C0676R.drawable.heating_home_left).setImageRight(C0676R.drawable.heating_home_right).setImageCenter(C0676R.drawable.heating_bottom).setText(C0676R.string.smartSchedule_home_tabInformationMessage);
    }

    public void configureAsHeatingAwayView(boolean premium) {
        setPremium(premium).setImageLeft(C0676R.drawable.heating_away_left).setImageRight(C0676R.drawable.heating_away_right).setImageCenter(C0676R.drawable.heating_bottom).setText(C0676R.string.smartSchedule_away_tabInformationMessage);
    }

    public void configureAsCoolingHomeView() {
        setImageLeft(C0676R.drawable.cooling_home_left).setImageRight(C0676R.drawable.cooling_home_right).setImageCenter(C0676R.drawable.cooling_bottom).setText(C0676R.string.smartSchedule_home_tabInformationMessage);
    }

    public void configureAsCoolingAwayView() {
        setImageLeft(C0676R.drawable.cooling_away_left).setImageRight(C0676R.drawable.cooling_away_right).setImageCenter(C0676R.drawable.cooling_bottom).setText(C0676R.string.smartSchedule_away_tabInformationMessage);
    }
}

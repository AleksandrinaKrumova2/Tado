package com.tado.android.alert_dialogs;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.tado.C0676R;
import com.tado.android.controllers.RateAppUtil;
import org.joda.time.DateTime;

public class RateDialogFragment extends DialogFragment {
    private boolean buttonUpPressed;
    @BindView(2131296894)
    Button mCancelButton;
    @BindView(2131296895)
    ImageButton mDownButton;
    @BindView(2131296898)
    Button mFirstButton;
    @BindView(2131296899)
    View mOpinionLayout;
    @BindView(2131296896)
    View mRateDownContainer;
    @BindView(2131296897)
    View mRateDownImage;
    @BindView(2131296902)
    View mRateUpContainer;
    @BindView(2131296903)
    View mRateUpImage;
    @BindView(2131296900)
    TextView mTitleView;
    @BindView(2131296901)
    ImageButton mUpButton;
    private Unbinder unbinder;

    class C07131 implements OnClickListener {
        C07131() {
        }

        public void onClick(View v) {
            RateDialogFragment.this.dismiss();
        }
    }

    class C07142 implements OnClickListener {
        C07142() {
        }

        public void onClick(View v) {
            RateDialogFragment.this.dismiss();
            if (RateDialogFragment.this.buttonUpPressed) {
                String appPackageName = RateDialogFragment.this.getContext().getPackageName();
                try {
                    RateDialogFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
                    return;
                } catch (ActivityNotFoundException e) {
                    RateDialogFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    return;
                }
            }
            RateDialogFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(RateDialogFragment.this.getActivity().getString(C0676R.string.menu_feedbackLink))));
        }
    }

    class C07153 implements OnClickListener {
        C07153() {
        }

        public void onClick(View v) {
            RateDialogFragment.this.mUpButton.setClickable(false);
            RateDialogFragment.this.buttonUpPressed = true;
            RateDialogFragment.this.startShowFirstButtonAnimation(true);
            RateDialogFragment.this.startThumbsUpAnimation();
            new RateAppUtil().voted();
        }
    }

    class C07164 implements OnClickListener {
        C07164() {
        }

        public void onClick(View v) {
            RateDialogFragment.this.mDownButton.setClickable(false);
            RateDialogFragment.this.buttonUpPressed = false;
            RateDialogFragment.this.startShowFirstButtonAnimation(false);
            RateDialogFragment.this.startThumbsDownAnimation();
            new RateAppUtil().voted();
        }
    }

    class C07175 implements AnimatorListener {
        C07175() {
        }

        public void onAnimationStart(Animator animation) {
            RateDialogFragment.this.mRateDownImage.setAlpha(0.0f);
            RateDialogFragment.this.mRateDownImage.setVisibility(0);
        }

        public void onAnimationEnd(Animator animation) {
            RateDialogFragment.this.mDownButton.setVisibility(4);
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    class C07186 implements AnimatorListener {
        C07186() {
        }

        public void onAnimationStart(Animator animation) {
            RateDialogFragment.this.mRateUpImage.setAlpha(0.0f);
            RateDialogFragment.this.mRateUpImage.setVisibility(0);
        }

        public void onAnimationEnd(Animator animation) {
            RateDialogFragment.this.mUpButton.setVisibility(4);
            RateDialogFragment.this.mRateUpContainer.animate().scaleXBy(1.0f);
            RateDialogFragment.this.mRateUpContainer.animate().scaleYBy(1.0f);
            RateDialogFragment.this.mRateUpContainer.animate().setInterpolator(new BounceInterpolator());
            RateDialogFragment.this.mRateUpContainer.animate().start();
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    class C07197 implements AnimatorListener {
        C07197() {
        }

        public void onAnimationStart(Animator animation) {
            RateDialogFragment.this.mFirstButton.setVisibility(0);
        }

        public void onAnimationEnd(Animator animation) {
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.dialog_rate, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        if (getDialog() != null) {
            getDialog().requestWindowFeature(1);
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        init();
        new RateAppUtil().shown(DateTime.now());
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void init() {
        this.mCancelButton.setOnClickListener(new C07131());
        this.mFirstButton.setOnClickListener(new C07142());
        this.mUpButton.setOnClickListener(new C07153());
        this.mDownButton.setOnClickListener(new C07164());
    }

    private void startThumbsDownAnimation() {
        animateTumbsViewAway(this.mRateUpContainer);
        int parentCenterX = this.mOpinionLayout.getWidth() / 2;
        this.mDownButton.animate().scaleXBy(0.2f);
        this.mDownButton.animate().scaleYBy(0.2f);
        this.mRateDownContainer.animate().translationXBy(-20.0f);
        this.mRateDownContainer.animate().x((float) (parentCenterX - (this.mRateDownContainer.getWidth() / 2)));
        this.mDownButton.animate().alpha(0.0f);
        this.mDownButton.animate().setDuration(300);
        this.mRateDownImage.animate().setListener(new C07175());
        this.mRateDownImage.animate().setDuration(300);
        this.mRateDownImage.animate().alpha(255.0f);
        this.mDownButton.animate().start();
        this.mRateDownImage.animate().start();
    }

    private void animateTumbsViewAway(View view) {
        view.animate().scaleX(0.0f);
        view.animate().scaleY(0.0f);
        view.animate().setDuration(300);
        view.animate().translationXBy(BitmapDescriptorFactory.HUE_ORANGE);
        view.animate().start();
    }

    private void startThumbsUpAnimation() {
        animateTumbsViewAway(this.mRateDownContainer);
        int parentCenterX = this.mOpinionLayout.getWidth() / 2;
        this.mRateUpContainer.animate().scaleXBy(0.3f);
        this.mRateUpContainer.animate().scaleYBy(0.3f);
        this.mRateUpContainer.animate().translationXBy(-20.0f);
        this.mRateUpContainer.animate().x((float) (parentCenterX - (this.mRateUpContainer.getWidth() / 2)));
        this.mRateUpContainer.animate().setInterpolator(new FastOutSlowInInterpolator());
        this.mUpButton.animate().alpha(0.0f);
        this.mUpButton.animate().setDuration(300);
        this.mRateUpImage.animate().setListener(new C07186());
        this.mRateUpImage.animate().setDuration(300);
        this.mRateUpImage.animate().alpha(255.0f);
        this.mUpButton.animate().start();
        this.mRateUpImage.animate().start();
    }

    private void startShowFirstButtonAnimation(boolean up) {
        String titleText;
        if (up) {
            this.mFirstButton.setText(C0676R.string.rateTheApp_positiveFeedback_confirmButton);
            titleText = getString(C0676R.string.rateTheApp_positiveFeedback_message);
        } else {
            this.mFirstButton.setText(C0676R.string.rateTheApp_negativeFeedback_confirmButton);
            titleText = getString(C0676R.string.rateTheApp_negativeFeedback_message);
        }
        this.mCancelButton.setText(C0676R.string.rateTheApp_negativeFeedback_cancelButton);
        this.mTitleView.setText(titleText);
        this.mFirstButton.setAlpha(0.0f);
        this.mFirstButton.animate().alpha(1.0f);
        this.mFirstButton.animate().setInterpolator(new AccelerateDecelerateInterpolator());
        this.mFirstButton.animate().setDuration(300);
        this.mFirstButton.animate().setListener(new C07197());
        this.mFirstButton.animate().start();
    }
}

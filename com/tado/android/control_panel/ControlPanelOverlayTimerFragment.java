package com.tado.android.control_panel;

import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crashlytics.android.Crashlytics;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.model.TimerValue;
import com.tado.android.controllers.ZoneController;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.utils.TimeUtils;
import com.tado.android.views.OnTimerValueChanged;
import com.tado.android.views.TimerView;

public class ControlPanelOverlayTimerFragment extends Fragment implements OnTimerValueChanged {
    @BindView(2131297135)
    ImageButton backButton;
    private int deleteCount;
    @BindView(2131296481)
    TimerView editModeLayout;
    @BindView(2131296455)
    View mButtonsView;
    @BindView(2131296493)
    CircleView mCircleView;
    private OnControlOverlayTimerFragmentInteractionListener mListener;
    @BindView(2131297137)
    Button mStartButton;
    @BindView(2131297136)
    Button resetButton;
    boolean resetedLayout = false;
    @BindView(2131296492)
    View runningModeLayout;
    @BindView(2131296494)
    TextView runningModeTimeLeft;
    private Unbinder unbinder;

    class C07411 implements OnClickListener {
        C07411() {
        }

        public void onClick(View v) {
            if (ControlPanelOverlayTimerFragment.this.mListener != null) {
                ControlPanelOverlayTimerFragment.this.setTimerSetting();
                ControlPanelOverlayTimerFragment.this.mListener.onBackButton();
            }
        }
    }

    class C07422 implements OnClickListener {
        C07422() {
        }

        public void onClick(View v) {
            ControlPanelController.INSTANCE.setTimerEdited(true);
            ControlPanelOverlayTimerFragment.this.resetedLayout = true;
            ControlPanelOverlayTimerFragment.this.editModeLayout.resetTimer();
            ControlPanelOverlayTimerFragment.this.editModeLayout.setVisibility(0);
            ControlPanelOverlayTimerFragment.this.runningModeLayout.setVisibility(4);
            ZoneController.INSTANCE.resetTimerOverlay();
            ControlPanelOverlayTimerFragment.this.mStartButton.setVisibility(0);
            ControlPanelOverlayTimerFragment.this.resetButton.setVisibility(8);
            AnalyticsHelper.trackEvent(ControlPanelOverlayTimerFragment.this.getActivity(), Screen.MANUAL_CONTROL, "Reset");
        }
    }

    public interface OnControlOverlayTimerFragmentInteractionListener {
        void onBackButton();

        void onTimerSet(int i, boolean z);
    }

    public static ControlPanelOverlayTimerFragment newInstance(OnControlOverlayTimerFragmentInteractionListener listener) {
        ControlPanelOverlayTimerFragment fragment = new ControlPanelOverlayTimerFragment();
        registerCallback(listener, fragment);
        return fragment;
    }

    private static void registerCallback(OnControlOverlayTimerFragmentInteractionListener listener, ControlPanelOverlayTimerFragment fragment) {
        if (listener != null) {
            fragment.mListener = listener;
        } else {
            Crashlytics.log("Presenter must implement OnControlOverlayTimerFragmentInteractionListener.");
            throw new ClassCastException("Presenter must implement OnControlOverlayTimerFragmentInteractionListener.");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_control_panel_overlay_timer, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.deleteCount = 0;
    }

    public void onStart() {
        super.onStart();
        this.mButtonsView.setBackgroundColor(-1);
        this.backButton.getDrawable().mutate().setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.ac_home), Mode.SRC_ATOP);
        initListeners();
        initLayout();
        updateStartButton();
    }

    private void initListeners() {
        this.backButton.setOnClickListener(new C07411());
        this.resetButton.setOnClickListener(new C07422());
        this.editModeLayout.setListener(this);
    }

    private void setTimerSetting() {
        if (this.mListener != null) {
            this.mListener.onTimerSet(this.editModeLayout.getCurrentSeconds() == 0 ? 60 : this.editModeLayout.getCurrentSeconds(), shouldSetTimerSetting());
        }
    }

    private boolean shouldSetTimerSetting() {
        return !ZoneController.INSTANCE.isOverlayTimerRunning() || (ZoneController.INSTANCE.isOverlayTimerRunning() && this.resetedLayout);
    }

    public void onApply() {
        ZoneController.INSTANCE.setOverlayTimerDurationInSeconds(this.editModeLayout.getCurrentSeconds());
        AnalyticsHelper.trackEvent(getActivity(), Screen.MANUAL_CONTROL, "Duration", "Timer", Long.valueOf((long) this.editModeLayout.getCurrentSeconds()));
        AnalyticsHelper.trackEvent(getActivity(), Screen.MANUAL_CONTROL, "Duration", "Delete", Long.valueOf((long) this.deleteCount));
    }

    private void updateStartButton() {
        this.mStartButton.setEnabled(this.editModeLayout.getCurrentSeconds() != 0);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onResume() {
        super.onResume();
        TadoApplication.getBus().register(this);
    }

    public void onPause() {
        setTimerSetting();
        TadoApplication.getBus().unregister(this);
        super.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    public void initLayout() {
        if (ZoneController.INSTANCE.isOverlayTimerRunning()) {
            startRunningTimerLayout();
        } else {
            startSetTimerLayout();
        }
    }

    private void startSetTimerLayout() {
        ZoneOverlay overlay = ControlPanelController.INSTANCE.getOverlay();
        if (!(overlay == null || overlay.getTermination() == null || overlay.getTermination().getDurationInSeconds() == null)) {
            this.editModeLayout.setStartingSeconds(overlay.getTermination().getDurationInSeconds().intValue());
        }
        this.editModeLayout.setVisibility(0);
        this.mStartButton.setVisibility(0);
        this.resetButton.setVisibility(8);
        this.runningModeLayout.setVisibility(4);
    }

    private void startRunningTimerLayout() {
        int duration = ZoneController.INSTANCE.getOverlayTimerSecondsLeft() * 1000;
        this.mStartButton.setVisibility(8);
        this.resetButton.setVisibility(0);
        this.editModeLayout.setVisibility(4);
        this.runningModeLayout.setVisibility(0);
        CircleAngleAnimation angleAnimation = new CircleAngleAnimation(this.mCircleView, 360);
        angleAnimation.setDuration((long) duration);
        this.mCircleView.startAnimation(angleAnimation);
    }

    public void onTimerValueChanged(int value) {
        updateStartButton();
    }

    public void onDeleteDigit() {
        this.deleteCount++;
    }

    @Subscribe
    public void getOverlayTimerSecondsLeft(TimerValue timerValue) {
        if (!this.resetedLayout) {
            if (timerValue.getTimerSeconds() == 0) {
                timerValue.setTimerSeconds(ControlPanelController.INSTANCE.getDefaultZoneOverlay().getTermination().getDurationInSeconds().intValue());
                ControlPanelController.INSTANCE.getCurrentZoneState().getOverlay().getTermination().setDurationInSeconds(Integer.valueOf(timerValue.getTimerSeconds()));
                initLayout();
            }
            this.runningModeTimeLeft.setText(TimeUtils.getTimeFromSeconds(timerValue.getTimerSeconds()));
        }
    }
}

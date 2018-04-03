package com.tado.android.views;

import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crashlytics.android.Crashlytics;
import com.squareup.otto.Subscribe;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.ControlPanelController;
import com.tado.android.control_panel.ControlPanelOverlayTerminationListAdapter;
import com.tado.android.control_panel.OverlayTerminationListItem;
import com.tado.android.control_panel.model.TimerValue;
import com.tado.android.controllers.ZoneController;
import com.tado.android.premium.AutomaticChangeUpsellingActivity;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.OverlayTerminationCondition;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.List;

public class ControlPanelOverlayTerminationListFragment extends Fragment {
    ControlPanelOverlayTerminationListAdapter mAdapter;
    @BindView(2131296720)
    ImageButton mBackButton;
    @BindView(2131296721)
    View mButtonsView;
    private LicenseEnum mCurrentLicense;
    private ZoneState mCurrentZoneState;
    @BindView(2131296834)
    ListView mListView;
    private OnControlPanelOverlayTerminationListFragmentInteractionListener mListener;
    private OverlayTerminationListItem mSelectedItem;
    private String mSelectedTerminationCondition = OverlayTerminationCondition.TADO_MODE;
    private Unbinder unbinder;

    public interface OnControlPanelOverlayTerminationListFragmentInteractionListener {
        void onOverlayListTerminationBackClick();

        void onOverlayTerminationTimerClick();
    }

    class C12641 implements OnClickListener {
        C12641() {
        }

        public void onClick(View v) {
            if (ControlPanelOverlayTerminationListFragment.this.mListener != null) {
                ControlPanelOverlayTerminationListFragment.this.mListener.onOverlayListTerminationBackClick();
            }
        }
    }

    public static ControlPanelOverlayTerminationListFragment newInstance(OnControlPanelOverlayTerminationListFragmentInteractionListener listener) {
        ControlPanelOverlayTerminationListFragment fragment = new ControlPanelOverlayTerminationListFragment();
        registerCallback(listener, fragment);
        return fragment;
    }

    private static void registerCallback(OnControlPanelOverlayTerminationListFragmentInteractionListener listener, ControlPanelOverlayTerminationListFragment fragment) {
        if (listener != null) {
            fragment.mListener = listener;
        } else {
            Crashlytics.log("Presenter must implement OnControlPanelOverlayTerminationListFragmentInteractionListener.");
            throw new ClassCastException("Presenter must implement OnControlPanelOverlayTerminationListFragmentInteractionListener.");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_control_panel_overlay_termination_list, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initManualControl();
        this.mBackButton.getDrawable().mutate().setColorFilter(ContextCompat.getColor(getActivity(), C0676R.color.ac_home), Mode.SRC_ATOP);
        this.mBackButton.setOnClickListener(new C12641());
    }

    private void initManualControl() {
        this.mCurrentLicense = UserConfig.getLicense();
        int selectedIndex = 0;
        this.mCurrentZoneState = ControlPanelController.INSTANCE.getCurrentZoneState();
        if (!(this.mCurrentZoneState == null || this.mCurrentZoneState.getOverlay() == null || this.mCurrentZoneState.getOverlay().getTermination() == null || this.mCurrentZoneState.getOverlay().getTermination().getType() == null)) {
            this.mSelectedTerminationCondition = this.mCurrentZoneState.getOverlay().getTermination().getType();
            if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM && this.mSelectedTerminationCondition.equalsIgnoreCase(OverlayTerminationCondition.TADO_MODE)) {
                this.mSelectedTerminationCondition = "MANUAL";
            }
        }
        final List<OverlayTerminationListItem> data = new ArrayList(3);
        data.add(new OverlayTerminationListItem(getString(C0676R.string.components_terminationConditionSelector_automaticLabel), C0676R.drawable.termination_mode, this.mSelectedTerminationCondition.equalsIgnoreCase(OverlayTerminationCondition.TADO_MODE), OverlayTerminationCondition.TADO_MODE, UserConfig.getLicense() != LicenseEnum.NON_PREMIUM));
        data.add(new OverlayTerminationListItem(getString(C0676R.string.components_terminationConditionSelector_timerWithDurationLabel, getDefaultTimerValue()), C0676R.drawable.termination_timer, this.mSelectedTerminationCondition.equalsIgnoreCase(OverlayTerminationCondition.TIMER), OverlayTerminationCondition.TIMER));
        data.add(new OverlayTerminationListItem(getString(C0676R.string.components_terminationConditionSelector_manualLabel), C0676R.drawable.termination_manually, this.mSelectedTerminationCondition.equalsIgnoreCase("MANUAL"), "MANUAL"));
        for (OverlayTerminationListItem item : data) {
            if (item.isSelected()) {
                this.mSelectedItem = item;
                break;
            }
            selectedIndex++;
        }
        this.mAdapter = new ControlPanelOverlayTerminationListAdapter(getActivity(), C0676R.layout.control_panel_overlay_termination_list_layout, data);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                OverlayTerminationListItem clickedItem = (OverlayTerminationListItem) data.get(position);
                if (UserConfig.getLicense() == LicenseEnum.NON_PREMIUM && clickedItem.getType().equalsIgnoreCase(OverlayTerminationCondition.TADO_MODE)) {
                    ControlPanelOverlayTerminationListFragment.this.mListView.setItemChecked(position, false);
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i) == ControlPanelOverlayTerminationListFragment.this.mSelectedItem) {
                            ControlPanelOverlayTerminationListFragment.this.mListView.setItemChecked(i, true);
                        }
                    }
                    if (ControlPanelOverlayTerminationListFragment.this.getContext() != null) {
                        Intent intent = new Intent(ControlPanelOverlayTerminationListFragment.this.getActivity(), AutomaticChangeUpsellingActivity.class);
                        intent.addFlags(536870912);
                        ControlPanelOverlayTerminationListFragment.this.getContext().startActivity(intent);
                        return;
                    }
                    return;
                }
                ControlPanelOverlayTerminationListFragment.this.mSelectedItem = clickedItem;
                if (ControlPanelOverlayTerminationListFragment.this.mSelectedItem.getType().equalsIgnoreCase(OverlayTerminationCondition.TIMER) && ControlPanelOverlayTerminationListFragment.this.mListener != null) {
                    ControlPanelOverlayTerminationListFragment.this.mListener.onOverlayTerminationTimerClick();
                }
                ControlPanelOverlayTerminationListFragment.this.mCurrentZoneState.getOverlay().getTermination().setType(ControlPanelOverlayTerminationListFragment.this.mSelectedItem.getType());
            }
        });
        this.mListView.setItemChecked(selectedIndex, true);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onResume() {
        super.onResume();
        this.mButtonsView.setBackgroundColor(-1);
        if (this.mCurrentLicense == null || this.mCurrentLicense != UserConfig.getLicense()) {
            initManualControl();
        } else if (this.mAdapter != null) {
            this.mAdapter.setTimerValue(getContext().getString(C0676R.string.components_terminationConditionSelector_timerWithDurationLabel, new Object[]{TimeUtils.getTimeFromSeconds(ZoneController.INSTANCE.getOverlayTimerDurationInSeconds())}));
        }
        TadoApplication.getBus().register(this);
    }

    public void onPause() {
        TadoApplication.getBus().unregister(this);
        super.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @Subscribe
    public void getOverlayTimerSecondsLeft(TimerValue timerValue) {
        if (!ZoneController.INSTANCE.isTimerSet() && this.mAdapter != null) {
            if (timerValue.getTimerSeconds() == 0) {
                timerValue.setTimerSeconds(ControlPanelController.INSTANCE.getDefaultZoneOverlay().getTermination().getDurationInSeconds().intValue());
                ControlPanelController.INSTANCE.getCurrentZoneState().getOverlay().getTermination().setDurationInSeconds(Integer.valueOf(timerValue.getTimerSeconds()));
            }
            this.mAdapter.setTimerValue(getContext().getString(C0676R.string.components_terminationConditionSelector_timerWithDurationLabel, new Object[]{TimeUtils.getTimeFromSeconds(timerValue.getTimerSeconds())}));
        }
    }

    public void onBackClick() {
        if (this.mListener != null) {
            this.mListener.onOverlayListTerminationBackClick();
        }
    }

    private String getDefaultTimerValue() {
        int durationInSeconds = 0;
        ZoneOverlay overlay = ControlPanelController.INSTANCE.getOverlay();
        if (!(overlay == null || overlay.getTermination() == null || overlay.getTermination().getDurationInSeconds() == null)) {
            durationInSeconds = overlay.getTermination().getDurationInSeconds().intValue();
        }
        return TimeUtils.getTimeFromSeconds(durationInSeconds);
    }
}

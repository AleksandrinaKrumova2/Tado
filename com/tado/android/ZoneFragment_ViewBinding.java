package com.tado.android;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.C0676R;
import com.tado.android.user_radar.UserRadarLayout;
import com.tado.android.views.MorphingButton;
import com.tado.android.views.TadoStateTemperatureView;
import com.tado.android.views.loadingindicator.LoadingView;

public class ZoneFragment_ViewBinding<T extends ZoneFragment> implements Unbinder {
    protected T target;

    @UiThread
    public ZoneFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mMainLayout = Utils.findRequiredView(source, C0676R.id.container, "field 'mMainLayout'");
        target.mMainModeLayout = Utils.findRequiredView(source, C0676R.id.cooling_main_mode_layout, "field 'mMainModeLayout'");
        target.mWeatherInfo = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_weather_layout_temperature, "field 'mWeatherInfo'", TextView.class);
        target.mModeIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_mode_image, "field 'mModeIcon'", ImageView.class);
        target.mModeName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_mode_text, "field 'mModeName'", TextView.class);
        target.mFirstModeAttr = Utils.findRequiredView(source, C0676R.id.cooling_main_mode_layout_attr1, "field 'mFirstModeAttr'");
        target.mFirstModeAttrIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_attr1_image, "field 'mFirstModeAttrIcon'", ImageView.class);
        target.mFirstModeAttrName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_attr1_text, "field 'mFirstModeAttrName'", TextView.class);
        target.mSecondModeAttr = Utils.findRequiredView(source, C0676R.id.cooling_main_mode_layout_attr2, "field 'mSecondModeAttr'");
        target.mSecondModeAttrIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_attr2_image, "field 'mSecondModeAttrIcon'", ImageView.class);
        target.mSecondModeAttrName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_attr2_text, "field 'mSecondModeAttrName'", TextView.class);
        target.mStateIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_state_icon, "field 'mStateIcon'", ImageView.class);
        target.mStateHumidity = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_state_humidity_text, "field 'mStateHumidity'", TextView.class);
        target.mZoneName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_title_layout_room, "field 'mZoneName'", TextView.class);
        target.mFirstModeAttrImageValue = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_attr1_image_value, "field 'mFirstModeAttrImageValue'", ImageView.class);
        target.mSecondModeAttrImageValue = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_mode_layout_attr2_image_value, "field 'mSecondModeAttrImageValue'", ImageView.class);
        target.mUserRadarLayout = (UserRadarLayout) Utils.findRequiredViewAsType(source, C0676R.id.cooling_main_user_radar_layout, "field 'mUserRadarLayout'", UserRadarLayout.class);
        target.mToolTipRelativeLayout = (ToolTipRelativeLayout) Utils.findRequiredViewAsType(source, C0676R.id.activity_main_tooltipRelativeLayout, "field 'mToolTipRelativeLayout'", ToolTipRelativeLayout.class);
        target.endManualControlButton = (MorphingButton) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_remove_overlay, "field 'endManualControlButton'", MorphingButton.class);
        target.mOverlayTerminationInfoTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.overlay_termination_info_text, "field 'mOverlayTerminationInfoTextView'", TextView.class);
        target.mOverlayTimerTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.overlay_termination_timer_text, "field 'mOverlayTimerTextView'", TextView.class);
        target.mZoneStateHotWaterLayout = Utils.findRequiredView(source, C0676R.id.zone_state_hot_water_layout, "field 'mZoneStateHotWaterLayout'");
        target.mZoneStateHotWaterIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.zone_state_hot_water_icon, "field 'mZoneStateHotWaterIcon'", ImageView.class);
        target.mZoneStateNoConnectionLayout = Utils.findRequiredView(source, C0676R.id.zone_state_no_connection_layout, "field 'mZoneStateNoConnectionLayout'");
        target.mZoneModeNoConnectionLayout = Utils.findRequiredView(source, C0676R.id.zone_mode_no_connection_text_layout, "field 'mZoneModeNoConnectionLayout'");
        target.mOverlayTerminationInfoLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.overlay_termination_info_layout, "field 'mOverlayTerminationInfoLayout'", LinearLayout.class);
        target.mHumidityLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0676R.id.humidity_layout, "field 'mHumidityLayout'", ViewGroup.class);
        target.mReportButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.reportButton, "field 'mReportButton'", ImageButton.class);
        target.mReportButtonAnimation = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.reportButtonAnimation, "field 'mReportButtonAnimation'", ImageView.class);
        target.mCallForHeatIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.call_for_heat_icon, "field 'mCallForHeatIcon'", ImageView.class);
        target.mTadoStateTemperatureView = (TadoStateTemperatureView) Utils.findRequiredViewAsType(source, C0676R.id.tado_state_temperature, "field 'mTadoStateTemperatureView'", TadoStateTemperatureView.class);
        target.mAdditionalInfoLayout = Utils.findRequiredView(source, C0676R.id.additional_info_layout, "field 'mAdditionalInfoLayout'");
        target.mAdditionalInfoText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.additional_info_text, "field 'mAdditionalInfoText'", TextView.class);
        target.scheduleButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.button_schedule, "field 'scheduleButton'", ImageButton.class);
        target.mScheduleButtonAnimation = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.scheduleButtonAnimation, "field 'mScheduleButtonAnimation'", ImageView.class);
        target.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C0676R.id.loading_indicator, "field 'loadingView'", LoadingView.class);
        target.mZoneModeNoConnectionText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.zone_mode_no_connection_text, "field 'mZoneModeNoConnectionText'", TextView.class);
        target.ignoreOpenWindowDetectionButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.ignore_open_window_detection_button, "field 'ignoreOpenWindowDetectionButton'", Button.class);
        target.switchHomePresenceButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.switch_home_presence_button, "field 'switchHomePresenceButton'", Button.class);
        target.unmaskOpenWindowButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.unmask_open_window, "field 'unmaskOpenWindowButton'", Button.class);
        target.dimView = (ViewGroup) Utils.findRequiredViewAsType(source, C0676R.id.dim, "field 'dimView'", ViewGroup.class);
        target.drawerButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.drawer_button, "field 'drawerButton'", ImageButton.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mMainLayout = null;
        target.mMainModeLayout = null;
        target.mWeatherInfo = null;
        target.mModeIcon = null;
        target.mModeName = null;
        target.mFirstModeAttr = null;
        target.mFirstModeAttrIcon = null;
        target.mFirstModeAttrName = null;
        target.mSecondModeAttr = null;
        target.mSecondModeAttrIcon = null;
        target.mSecondModeAttrName = null;
        target.mStateIcon = null;
        target.mStateHumidity = null;
        target.mZoneName = null;
        target.mFirstModeAttrImageValue = null;
        target.mSecondModeAttrImageValue = null;
        target.mUserRadarLayout = null;
        target.mToolTipRelativeLayout = null;
        target.endManualControlButton = null;
        target.mOverlayTerminationInfoTextView = null;
        target.mOverlayTimerTextView = null;
        target.mZoneStateHotWaterLayout = null;
        target.mZoneStateHotWaterIcon = null;
        target.mZoneStateNoConnectionLayout = null;
        target.mZoneModeNoConnectionLayout = null;
        target.mOverlayTerminationInfoLayout = null;
        target.mHumidityLayout = null;
        target.mReportButton = null;
        target.mReportButtonAnimation = null;
        target.mCallForHeatIcon = null;
        target.mTadoStateTemperatureView = null;
        target.mAdditionalInfoLayout = null;
        target.mAdditionalInfoText = null;
        target.scheduleButton = null;
        target.mScheduleButtonAnimation = null;
        target.loadingView = null;
        target.mZoneModeNoConnectionText = null;
        target.ignoreOpenWindowDetectionButton = null;
        target.switchHomePresenceButton = null;
        target.unmaskOpenWindowButton = null;
        target.dimView = null;
        target.drawerButton = null;
        this.target = null;
    }
}

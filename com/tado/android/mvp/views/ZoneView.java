package com.tado.android.mvp.views;

import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import com.tado.android.mvp.common.BaseView;
import com.tado.android.mvp.model.TadoModeEnum;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.views.loadingindicator.LoadingIndicator;
import java.util.List;

public interface ZoneView extends BaseView, LoadingIndicator, WeatherView {
    void dispatchTouchEventToUserRadar(MotionEvent motionEvent);

    void endProgress();

    View getDimView();

    String getString(@StringRes int i, Object... objArr);

    void resetOverlayLayout();

    void setAdditionalInfoLayoutVisibility(boolean z);

    void setAdditionalInfoText(String str);

    void setCurrentSettingsMainModeIconId(int i);

    void setCurrentSettingsMainModeText(String str);

    void setDrawerBadgeVisibility(boolean z);

    void setHumidityValue(String str);

    void setIgnoreOpenWindowDetectionButtonBackgroundColor(int i);

    void setIgnoreOpenWindowDetectionButtonVisibility(boolean z);

    void setNoConnectionText(CharSequence charSequence);

    void setOverlayTerminationInfoIcon(Integer num);

    void setOverlayTerminationInfoTextValue(String str, boolean z);

    void setOverlayTimerTextValue(String str);

    void setStateIcon(Drawable drawable);

    void setSwitchHomePresenceButton(boolean z, TadoModeEnum tadoModeEnum);

    void setTadoStateTemperatureValueAndPrecision(float f, float f2);

    void setUnmaskOpenWindowButton(boolean z);

    void setZoneBackgroundColor(int i);

    void setZoneName(String str);

    void setZoneSettingsBackgroundColorAndState(int i, boolean z);

    void setZoneStateHotWaterIcon(int i);

    void showUpsellingScreen();

    void startProgress();

    void updateCallForHeatIconVisibility(boolean z, Pair<Integer, Integer> pair);

    void updateCoolingModeLayout(ZoneState zoneState);

    void updateCurrentSettingsMainModeVisibility(boolean z);

    void updateCurrentSettingsNoConnectionLayoutVisibility(boolean z);

    void updateEndOverlayButtonStateForOfflineZone();

    void updateEndOverlayButtonStateWithActiveOverlay(boolean z);

    void updateEndOverlayButtonStateWithoutActiveOverlay();

    void updateHumidityLayoutVisibility(boolean z);

    void updateMobileDevices(List<MobileDevice> list);

    void updateOverlayTerminationInfoLayoutVisibility(boolean z);

    void updateOverlayTimerLayoutVisibility(boolean z);

    void updateStateIconLayoutVisibility(boolean z);

    void updateStateNoConnectionLayoutVisibility(boolean z);

    void updateUserLayoutVisibility(boolean z);

    void updateZoneStateHotWaterLayoutVisibility(boolean z);

    void updateZoneStateTemperatureLayoutVisibility(boolean z);
}

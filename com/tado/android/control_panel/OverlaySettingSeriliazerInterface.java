package com.tado.android.control_panel;

import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneSetting;

public interface OverlaySettingSeriliazerInterface {
    void clear();

    ZoneOverlay restore(int i);

    String restoreLastOverlaySettingMode(int i);

    ZoneSetting restoreZoneSetting(int i, String str);

    void save(int i, ZoneOverlay zoneOverlay);
}

package com.tado.android.rest.model.installation;

import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;

public class HotWaterZoneSetting extends GenericZoneSetting {
    public HotWaterZoneSetting() {
        setType(TypeEnum.HOT_WATER);
    }
}

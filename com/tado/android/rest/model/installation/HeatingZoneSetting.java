package com.tado.android.rest.model.installation;

import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import java.io.Serializable;

public class HeatingZoneSetting extends GenericZoneSetting implements Serializable {
    public HeatingZoneSetting() {
        setType(TypeEnum.HEATING);
    }
}

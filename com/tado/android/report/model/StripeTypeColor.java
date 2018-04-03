package com.tado.android.report.model;

import com.tado.android.rest.model.StripeTypeEnum;
import com.tado.android.utils.ColorFactory.ColorPair;

public class StripeTypeColor {
    private ColorPair colorPair;
    private StripeTypeEnum type;

    public StripeTypeColor(ColorPair colorPair, StripeTypeEnum type) {
        this.colorPair = colorPair;
        this.type = type;
    }

    public ColorPair getColorPair() {
        return this.colorPair;
    }

    public void setColorPair(ColorPair colorPair) {
        this.colorPair = colorPair;
    }

    public StripeTypeEnum getType() {
        return this.type;
    }

    public void setType(StripeTypeEnum type) {
        this.type = type;
    }
}

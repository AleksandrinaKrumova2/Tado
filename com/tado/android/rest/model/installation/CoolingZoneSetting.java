package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.installation.GenericZoneSetting.TypeEnum;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class CoolingZoneSetting extends GenericZoneSetting {
    @SerializedName("fanSpeed")
    private FanSpeedEnum fanSpeed = null;
    @SerializedName("mode")
    private ModeEnum mode = null;
    @SerializedName("swing")
    private String swing = null;

    public CoolingZoneSetting(ModeEnum mode, boolean power, Temperature temperature) {
        super(TypeEnum.AIR_CONDITIONING, power, temperature);
        this.mode = mode;
    }

    @ApiModelProperty("Cooling system mode.\n")
    public ModeEnum getMode() {
        return this.mode;
    }

    public void setMode(ModeEnum mode) {
        setChanged();
        this.mode = mode;
        notifyObservers();
    }

    @ApiModelProperty("Cooling system fan speed.\n")
    public FanSpeedEnum getFanSpeed() {
        return this.fanSpeed;
    }

    public void setFanSpeed(FanSpeedEnum fanSpeed) {
        setChanged();
        this.fanSpeed = fanSpeed;
        notifyObservers();
    }

    @ApiModelProperty("Cooling system swing mode.\n")
    public String getSwing() {
        return this.swing;
    }

    public void setSwing(String swing) {
        this.swing = swing;
        setChanged();
        notifyObservers();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoolingZoneSetting coolingZoneSetting = (CoolingZoneSetting) o;
        if (super.equals(o) && Util.equals(this.mode, coolingZoneSetting.mode) && Util.equals(this.fanSpeed, coolingZoneSetting.fanSpeed) && Util.equals(this.swing, coolingZoneSetting.swing)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mode, this.fanSpeed, this.swing});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CoolingZoneSetting {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    mode: ").append(toIndentedString(this.mode)).append("\n");
        sb.append("    fanSpeed: ").append(toIndentedString(this.fanSpeed)).append("\n");
        sb.append("    swing: ").append(toIndentedString(this.swing)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.Temperatures;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoolingModeCapabilities {
    @SerializedName("fanSpeeds")
    private List<FanSpeedEnum> fanSpeeds = new ArrayList();
    @SerializedName("swings")
    private List<String> swings = null;
    @SerializedName("temperatures")
    private Temperatures temperatures = null;

    @ApiModelProperty("")
    public Temperatures getTemperatures() {
        return this.temperatures;
    }

    public void setTemperatures(Temperatures temperatures) {
        this.temperatures = temperatures;
    }

    @ApiModelProperty("Cooling system fan speed.\n")
    public List<FanSpeedEnum> getFanSpeeds() {
        return this.fanSpeeds;
    }

    public void setFanSpeeds(List<FanSpeedEnum> fanSpeeds) {
        this.fanSpeeds = fanSpeeds;
    }

    @ApiModelProperty("Cooling system swing mode.\n")
    public List<String> getSwings() {
        return this.swings;
    }

    public void setSwings(List<String> swings) {
        this.swings = swings;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoolingModeCapabilities coolingModeCapabilities = (CoolingModeCapabilities) o;
        if (Util.equals(this.temperatures, coolingModeCapabilities.temperatures) && Util.equals(this.fanSpeeds, coolingModeCapabilities.fanSpeeds) && Util.equals(this.swings, coolingModeCapabilities.swings)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.temperatures, this.fanSpeeds, this.swings});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CoolingModeCapabilities {\n");
        sb.append("    temperatures: ").append(toIndentedString(this.temperatures)).append("\n");
        sb.append("    fanSpeeds: ").append(toIndentedString(this.fanSpeeds)).append("\n");
        sb.append("    swings: ").append(toIndentedString(this.swings)).append("\n");
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

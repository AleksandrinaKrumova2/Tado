package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class CoolingCapabilities extends GenericZoneCapabilities {
    @SerializedName("AUTO")
    private CoolingModeCapabilities AUTO = null;
    @SerializedName("COOL")
    private CoolingModeCapabilities COOL = null;
    @SerializedName("DRY")
    private CoolingModeCapabilities DRY = null;
    @SerializedName("FAN")
    private CoolingModeCapabilities FAN = null;
    @SerializedName("HEAT")
    private CoolingModeCapabilities HEAT = null;

    @ApiModelProperty("")
    public CoolingModeCapabilities getCOOL() {
        return this.COOL;
    }

    public void setCOOL(CoolingModeCapabilities COOL) {
        this.COOL = COOL;
    }

    @ApiModelProperty("")
    public CoolingModeCapabilities getHEAT() {
        return this.HEAT;
    }

    public void setHEAT(CoolingModeCapabilities HEAT) {
        this.HEAT = HEAT;
    }

    @ApiModelProperty("")
    public CoolingModeCapabilities getDRY() {
        return this.DRY;
    }

    public void setDRY(CoolingModeCapabilities DRY) {
        this.DRY = DRY;
    }

    @ApiModelProperty("")
    public CoolingModeCapabilities getFAN() {
        return this.FAN;
    }

    public void setFAN(CoolingModeCapabilities FAN) {
        this.FAN = FAN;
    }

    @ApiModelProperty("")
    public CoolingModeCapabilities getAUTO() {
        return this.AUTO;
    }

    public void setAUTO(CoolingModeCapabilities AUTO) {
        this.AUTO = AUTO;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoolingCapabilities coolingCapabilities = (CoolingCapabilities) o;
        if (Util.equals(this.COOL, coolingCapabilities.COOL) && Util.equals(this.HEAT, coolingCapabilities.HEAT) && Util.equals(this.DRY, coolingCapabilities.DRY) && Util.equals(this.FAN, coolingCapabilities.FAN) && Util.equals(this.AUTO, coolingCapabilities.AUTO)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.COOL, this.HEAT, this.DRY, this.FAN, this.AUTO});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CoolingCapabilities {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    COOL: ").append(toIndentedString(this.COOL)).append("\n");
        sb.append("    HEAT: ").append(toIndentedString(this.HEAT)).append("\n");
        sb.append("    DRY: ").append(toIndentedString(this.DRY)).append("\n");
        sb.append("    FAN: ").append(toIndentedString(this.FAN)).append("\n");
        sb.append("    AUTO: ").append(toIndentedString(this.AUTO)).append("\n");
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

package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

public class AcSpecs implements Serializable {
    @SerializedName("acUnitDisplaysSetPointTemperature")
    private Boolean acUnitDisplaysSetPointTemperature = null;
    @SerializedName("manufacturer")
    private Manufacturer manufacturer = null;
    @SerializedName("remoteControl")
    private RemoteControl remoteControl = null;

    @ApiModelProperty(required = true, value = "")
    public RemoteControl getRemoteControl() {
        if (this.remoteControl == null) {
            this.remoteControl = new RemoteControl();
        }
        return this.remoteControl;
    }

    public void setRemoteControl(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    @ApiModelProperty(required = true, value = "")
    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @ApiModelProperty(required = true, value = "Specifies whether the AC unit is displaying the set point temperature on some display.\n")
    public Boolean getAcUnitDisplaysSetPointTemperature() {
        return this.acUnitDisplaysSetPointTemperature;
    }

    public void setAcUnitDisplaysSetPointTemperature(Boolean acUnitDisplaysSetPointTemperature) {
        this.acUnitDisplaysSetPointTemperature = acUnitDisplaysSetPointTemperature;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcSpecs acSpecs = (AcSpecs) o;
        if (Util.equals(this.remoteControl, acSpecs.remoteControl) && Util.equals(this.manufacturer, acSpecs.manufacturer) && Util.equals(this.acUnitDisplaysSetPointTemperature, acSpecs.acUnitDisplaysSetPointTemperature)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.remoteControl, this.manufacturer, this.acUnitDisplaysSetPointTemperature});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcSpecs {\n");
        sb.append("    remoteControl: ").append(toIndentedString(this.remoteControl)).append("\n");
        sb.append("    manufacturer: ").append(toIndentedString(this.manufacturer)).append("\n");
        sb.append("    acUnitDisplaysSetPointTemperature: ").append(toIndentedString(this.acUnitDisplaysSetPointTemperature)).append("\n");
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

package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

public class RemoteControl implements Serializable {
    @SerializedName("commandType")
    private CommandTypeEnum commandType = null;
    @SerializedName("temperatureUnit")
    private TemperatureUnitEnum temperatureUnit = null;

    @ApiModelProperty(required = true, value = "")
    public CommandTypeEnum getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommandTypeEnum commandType) {
        this.commandType = commandType;
    }

    @ApiModelProperty(required = true, value = "")
    public TemperatureUnitEnum getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(TemperatureUnitEnum temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RemoteControl remoteControl = (RemoteControl) o;
        if (Util.equals(this.commandType, remoteControl.commandType) && Util.equals(this.temperatureUnit, remoteControl.temperatureUnit)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.commandType, this.temperatureUnit});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RemoteControl {\n");
        sb.append("    commandType: ").append(toIndentedString(this.commandType)).append("\n");
        sb.append("    temperatureUnit: ").append(toIndentedString(this.temperatureUnit)).append("\n");
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

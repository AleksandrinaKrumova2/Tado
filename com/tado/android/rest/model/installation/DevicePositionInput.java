package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.DevicePosition;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class DevicePositionInput {
    @SerializedName("devicePositioning")
    private DevicePositioningEnum devicePositioning = null;

    public enum DevicePositioningEnum {
        VERTICAL(DevicePosition.VERTICAL),
        HORIZONTAL(DevicePosition.HORIZONTAL);
        
        private String value;

        private DevicePositioningEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public DevicePositionInput(DevicePositioningEnum position) {
        this.devicePositioning = position;
    }

    @ApiModelProperty(required = true, value = "")
    public DevicePositioningEnum getDevicePositioning() {
        return this.devicePositioning;
    }

    public void setDevicePositioning(DevicePositioningEnum devicePositioning) {
        this.devicePositioning = devicePositioning;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.devicePositioning, ((DevicePositionInput) o).devicePositioning);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.devicePositioning});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DevicePositionInput {\n");
        sb.append("    devicePositioning: ").append(toIndentedString(this.devicePositioning)).append("\n");
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

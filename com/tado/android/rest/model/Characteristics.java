package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Characteristics implements Serializable {
    @SerializedName("capabilities")
    private List<HardwareDeviceCapabilities> capabilities;

    public List<HardwareDeviceCapabilities> getCapabilities() {
        return this.capabilities;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Characteristics {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    capabilities: ").append(toIndentedString(this.capabilities)).append("\n");
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

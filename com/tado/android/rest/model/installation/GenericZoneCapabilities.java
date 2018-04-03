package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class GenericZoneCapabilities {
    @SerializedName("type")
    private TypeEnum type = null;

    public enum TypeEnum {
        HEATING("HEATING"),
        AIR_CONDITIONING("AIR_CONDITIONING"),
        HOT_WATER("HOT_WATER");
        
        private String value;

        private TypeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    @ApiModelProperty(required = true, value = "The system type of the zone. Depending on the value of `type`, either `CoolingCapabilities`, `HeatingCapabilities` or `HotWaterCapabilities` is returned.\n")
    public TypeEnum getType() {
        return this.type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.type, ((GenericZoneCapabilities) o).type);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GenericZoneCapabilities {\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
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

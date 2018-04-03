package com.tado.android.rest.model.installation;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Installation implements Comparable<Installation>, Serializable {
    @SerializedName("devices")
    private List<GenericHardwareDevice> devices;
    @SerializedName("id")
    protected Integer id = null;
    @SerializedName("revision")
    protected Integer revision = null;
    @SerializedName("type")
    private TypeEnum type = TypeEnum.UNKNOWN;

    public enum TypeEnum {
        INSTALL_ST_G1("INSTALL_ST_G1"),
        UPGRADE_TO_ST_G1("UPGRADE_TO_ST_G1"),
        SALE_FITTING_ST_G1("SALE_FITTING_ST_G1"),
        INSTALL_AC_G1("INSTALL_AC_G1"),
        REPLACE_BRIDGE("REPLACE_BRIDGE"),
        UNKNOWN("");
        
        private String value;

        private TypeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public abstract boolean isInstallationNotCompleted();

    @ApiModelProperty(required = true, value = "The type of installation, determining the states/steps required to complete the installation.\n")
    public TypeEnum getType() {
        if (this.type == null) {
            return TypeEnum.UNKNOWN;
        }
        return this.type;
    }

    public void setType(TypeEnum type) {
        if (type == null) {
            this.type = TypeEnum.UNKNOWN;
        } else {
            this.type = type;
        }
    }

    @ApiModelProperty(required = true, value = "ID of the installation within a home.\n")
    public Integer getId() {
        return this.id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.id, ((Installation) o).id);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Installation {\n");
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

    public int compareTo(@NonNull Installation installation) {
        return installation.id.intValue() - this.id.intValue();
    }

    @NonNull
    public List<GenericHardwareDevice> getDevices() {
        return this.devices != null ? this.devices : new ArrayList();
    }
}

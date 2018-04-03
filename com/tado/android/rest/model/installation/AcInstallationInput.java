package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class AcInstallationInput {
    @SerializedName("revision")
    private Integer revision = null;
    @SerializedName("type")
    private TypeEnum type = null;
    @SerializedName("wirelessRemote")
    private AcInstallationInputWirelessRemote wirelessRemote = null;

    public enum TypeEnum {
        G1("INSTALL_AC_G1");
        
        private String value;

        private TypeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    @ApiModelProperty(required = true, value = "The type of installation, determining the states/steps required to complete the installation.\n")
    public TypeEnum getType() {
        return this.type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    @ApiModelProperty(required = true, value = "")
    public Integer getRevision() {
        return this.revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    @ApiModelProperty("")
    public AcInstallationInputWirelessRemote getWirelessRemote() {
        return this.wirelessRemote;
    }

    public void setWirelessRemote(AcInstallationInputWirelessRemote wirelessRemote) {
        this.wirelessRemote = wirelessRemote;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcInstallationInput acInstallationInput = (AcInstallationInput) o;
        if (Util.equals(this.type, acInstallationInput.type) && Util.equals(this.revision, acInstallationInput.revision) && Util.equals(this.wirelessRemote, acInstallationInput.wirelessRemote)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type, this.revision, this.wirelessRemote});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcInstallationInput {\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    revision: ").append(toIndentedString(this.revision)).append("\n");
        sb.append("    wirelessRemote: ").append(toIndentedString(this.wirelessRemote)).append("\n");
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

package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

@ApiModel(description = "A heating installation or upgrade.\n")
public class HeatingInstallation extends Installation {
    @SerializedName("state")
    private StateEnum state = null;

    public enum StateEnum {
        WEB_USER_SUBSTITUTE("WEB_USER_SUBSTITUTE"),
        SETUP_SELECTION("SETUP_SELECTION"),
        SETUP_COMPATIBILITY_UNKNOWN("SETUP_COMPATIBILITY_UNKNOWN"),
        SETUP_INCOMPATIBLE("SETUP_INCOMPATIBLE"),
        REGISTER_DEVICES("REGISTER_DEVICES"),
        SWISS_SUBSIDY_PROGRAM("SWISS_SUBSIDY_PROGRAM"),
        CONNECT_GATEWAY("CONNECT_GATEWAY"),
        INSTALL_HW("INSTALL_HW"),
        SCHEDULE_INSTALLER_APPOINTMENT("SCHEDULE_INSTALLER_APPOINTMENT"),
        INSTALLER_INSTALLATION_PENDING("INSTALLER_INSTALLATION_PENDING"),
        COMPLETED(Constants.COMPLETED);
        
        private String value;

        private StateEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    @ApiModelProperty(required = true, value = "")
    public Integer getRevision() {
        return this.revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    @ApiModelProperty(required = true, value = "")
    public StateEnum getState() {
        return this.state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeatingInstallation heatingInstallation = (HeatingInstallation) o;
        if (Util.equals(this.id, heatingInstallation.id) && Util.equals(this.revision, heatingInstallation.revision) && Util.equals(this.state, heatingInstallation.state)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.revision, this.state});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HeatingInstallation {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    revision: ").append(toIndentedString(this.revision)).append("\n");
        sb.append("    state: ").append(toIndentedString(this.state)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public boolean isInstallationNotCompleted() {
        return this.state != StateEnum.COMPLETED;
    }
}

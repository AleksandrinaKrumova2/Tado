package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class AcInstallation extends Installation {
    @SerializedName("acInstallationInformation")
    private AcInstallationInformation acInstallationInformation = null;
    @SerializedName("state")
    private StateEnum state = null;

    public enum StateEnum {
        REGISTER_WR(Constants.REGISTER_WR),
        CONNECT_WIFI(Constants.CONNECT_WIFI),
        UPDATE_FW(Constants.UPDATE_FW),
        AC_SPECS("AC_SPECS"),
        NOT_COMPATIBLE(Constants.NOT_COMPATIBLE),
        SETUP_AC_SELECT_MANUFACTURER(Constants.AC_SETUP_MANUFACTURER_SELECTION),
        SETUP_AC_CLC_RECORDING(Constants.SETUP_AC_CLC_RECORDING),
        SETUP_AC_ON_OFF_REDUCTION(Constants.AC_SETUP_ON_OFF_REDUCTION),
        SETUP_AC_CREATE_AC_SETTING_RECORDING(Constants.SETUP_AC_CREATE_SETTING_RECORDING),
        SETUP_AC_RECORD_AC_SETTING_COMMANDS(Constants.SETUP_AC_RECORD_AC_SETTING_COMMANDS),
        SETUP_AC_SELECT_COMMAND_SET(Constants.SETUP_AC_SELECT_COMMAND_SET),
        NO_COMMAND_SET("NO_COMMAND_SET"),
        POSITION_DEVICE(Constants.POSITION_DEVICE),
        UPLOAD_COMMAND_TABLE(Constants.AC_SETUP_UPLOAD_COMMAND_TABLE),
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

    @ApiModelProperty(required = true, value = "AC installation details.\n")
    public AcInstallationInformation getAcInstallationInformation() {
        return this.acInstallationInformation;
    }

    public void setAcInstallationInformation(AcInstallationInformation acInstallationInformation) {
        this.acInstallationInformation = acInstallationInformation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcInstallation acInstallation = (AcInstallation) o;
        if (Util.equals(this.id, acInstallation.id) && Util.equals(this.revision, acInstallation.revision) && Util.equals(this.state, acInstallation.state) && Util.equals(this.acInstallationInformation, acInstallation.acInstallationInformation)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.revision, this.state, this.acInstallationInformation});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcInstallation {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    revision: ").append(toIndentedString(this.revision)).append("\n");
        sb.append("    state: ").append(toIndentedString(this.state)).append("\n");
        sb.append("    acInstallationInformation: ").append(toIndentedString(this.acInstallationInformation)).append("\n");
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

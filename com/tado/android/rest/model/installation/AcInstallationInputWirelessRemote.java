package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class AcInstallationInputWirelessRemote {
    @SerializedName("authKey")
    private String authKey = null;
    @SerializedName("serialNo")
    private String serialNo = null;

    @ApiModelProperty(required = true, value = "")
    public String getAuthKey() {
        return this.authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    @ApiModelProperty(required = true, value = "")
    public String getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcInstallationInputWirelessRemote acInstallationInputWirelessRemote = (AcInstallationInputWirelessRemote) o;
        if (Util.equals(this.authKey, acInstallationInputWirelessRemote.authKey) && Util.equals(this.serialNo, acInstallationInputWirelessRemote.serialNo)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.authKey, this.serialNo});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcInstallationInputWirelessRemote {\n");
        sb.append("    authKey: ").append(toIndentedString(this.authKey)).append("\n");
        sb.append("    serialNo: ").append(toIndentedString(this.serialNo)).append("\n");
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

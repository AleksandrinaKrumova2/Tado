package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class InstallationStateTransitionResult {
    @SerializedName("installation")
    private AcInstallation installation = null;

    @ApiModelProperty(required = true, value = "")
    public AcInstallation getInstallation() {
        return this.installation;
    }

    public void setInstallation(AcInstallation installation) {
        this.installation = installation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.installation, ((InstallationStateTransitionResult) o).installation);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.installation});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InstallationStateTransitionResult {\n");
        sb.append("    installation: ").append(toIndentedString(this.installation)).append("\n");
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

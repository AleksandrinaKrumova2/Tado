package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

public class AcInstallationInformationKeyCommandSetRecording implements Serializable {
    @SerializedName("id")
    private Long id = null;

    @ApiModelProperty("")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.id, ((AcInstallationInformationKeyCommandSetRecording) o).id);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcInstallationInformationKeyCommandSetRecording {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
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

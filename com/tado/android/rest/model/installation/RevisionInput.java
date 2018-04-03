package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class RevisionInput {
    @SerializedName("revision")
    private Integer revision = null;

    @ApiModelProperty(required = true, value = "")
    public Integer getRevision() {
        return this.revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.revision, ((RevisionInput) o).revision);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.revision});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RevisionInput {\n");
        sb.append("    revision: ").append(toIndentedString(this.revision)).append("\n");
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

package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class AcCommandSetId {
    @SerializedName("code")
    private Integer code = null;
    @SerializedName("commandType")
    private CommandTypeEnum commandType = null;

    @ApiModelProperty(required = true, value = "")
    public CommandTypeEnum getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommandTypeEnum commandType) {
        this.commandType = commandType;
    }

    @ApiModelProperty(required = true, value = "")
    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcCommandSetId acCommandSetId = (AcCommandSetId) o;
        if (Util.equals(this.commandType, acCommandSetId.commandType) && Util.equals(this.code, acCommandSetId.code)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.commandType, this.code});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcCommandSetId {\n");
        sb.append("    commandType: ").append(toIndentedString(this.commandType)).append("\n");
        sb.append("    code: ").append(toIndentedString(this.code)).append("\n");
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

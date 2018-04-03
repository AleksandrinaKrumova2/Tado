package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class CommandTableUpload {
    @SerializedName("state")
    private StateEnum state = null;

    public enum StateEnum {
        NOT_STARTED("NOT_STARTED"),
        IN_PROGRESS("IN_PROGRESS"),
        FINISHED("FINISHED"),
        FAILED("FAILED");
        
        private String value;

        private StateEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    @ApiModelProperty("")
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
        return Util.equals(this.state, ((CommandTableUpload) o).state);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.state});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CommandTableUpload {\n");
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
}

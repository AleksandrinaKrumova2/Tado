package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AcSettingCommandSetRecordingInput {
    @SerializedName("acModes")
    private List<ModeEnum> acModes = new ArrayList();

    @ApiModelProperty(required = true, value = "")
    public List<ModeEnum> getAcModes() {
        return this.acModes;
    }

    public void setAcModes(List<ModeEnum> acModes) {
        this.acModes = acModes;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.acModes, ((AcSettingCommandSetRecordingInput) o).acModes);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.acModes});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcSettingCommandSetRecordingInput {\n");
        sb.append("    acModes: ").append(toIndentedString(this.acModes)).append("\n");
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

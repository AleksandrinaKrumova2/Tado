package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class AcCommandSetTest {
    @SerializedName("acSetting")
    private CoolingZoneSetting acSetting = null;
    @SerializedName("commandSetId")
    private AcCommandSetId commandSetId = null;

    @ApiModelProperty(required = true, value = "")
    public AcCommandSetId getCommandSetId() {
        return this.commandSetId;
    }

    public void setCommandSetId(AcCommandSetId commandSetId) {
        this.commandSetId = commandSetId;
    }

    @ApiModelProperty(required = true, value = "")
    public CoolingZoneSetting getAcSetting() {
        return this.acSetting;
    }

    public void setAcSetting(CoolingZoneSetting acSetting) {
        this.acSetting = acSetting;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcCommandSetTest acCommandSetTest = (AcCommandSetTest) o;
        if (Util.equals(this.commandSetId, acCommandSetTest.commandSetId) && Util.equals(this.acSetting, acCommandSetTest.acSetting)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.commandSetId, this.acSetting});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcCommandSetTest {\n");
        sb.append("    commandSetId: ").append(toIndentedString(this.commandSetId)).append("\n");
        sb.append("    acSetting: ").append(toIndentedString(this.acSetting)).append("\n");
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

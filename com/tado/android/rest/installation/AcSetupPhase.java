package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.ACSetupPhase;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class AcSetupPhase {
    @SerializedName("phase")
    private PhaseEnum phase = null;

    public enum PhaseEnum {
        ON_OFF_REDUCTION(ACSetupPhase.ON_OFF_REDUCTION),
        AC_SPECS("AC_SPECS"),
        AC_SETTING_RECORDING(ACSetupPhase.AC_SETTING_RECORDING);
        
        private String value;

        private PhaseEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public AcSetupPhase(PhaseEnum phase) {
        this.phase = phase;
    }

    @ApiModelProperty(required = true, value = "")
    public PhaseEnum getPhase() {
        return this.phase;
    }

    public void setPhase(PhaseEnum phase) {
        this.phase = phase;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.phase, ((AcSetupPhase) o).phase);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.phase});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcSetupPhase {\n");
        sb.append("    phase: ").append(toIndentedString(this.phase)).append("\n");
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

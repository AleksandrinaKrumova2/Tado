package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class IntRange {
    @SerializedName("max")
    private Integer max = null;
    @SerializedName("min")
    private Integer min = null;
    @SerializedName("step")
    private Float step = null;

    @ApiModelProperty(required = true, value = "Minimum range value.")
    public Integer getMin() {
        return this.min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @ApiModelProperty(required = true, value = "Maximum range value.")
    public Integer getMax() {
        return this.max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @ApiModelProperty(required = true, value = "Step size between min and max.")
    public Float getStep() {
        return this.step;
    }

    public void setStep(Float step) {
        this.step = step;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntRange intRange = (IntRange) o;
        if (Util.equals(this.min, intRange.min) && Util.equals(this.max, intRange.max) && Util.equals(this.step, intRange.step)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.min, this.max, this.step});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IntRange {\n");
        sb.append("    min: ").append(toIndentedString(this.min)).append("\n");
        sb.append("    max: ").append(toIndentedString(this.max)).append("\n");
        sb.append("    step: ").append(toIndentedString(this.step)).append("\n");
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

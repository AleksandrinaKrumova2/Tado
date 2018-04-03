package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class TemperatureRange {
    @SerializedName("celsius")
    private IntRange celsius = null;
    @SerializedName("fahrenheit")
    private IntRange fahrenheit = null;

    @ApiModelProperty("")
    public IntRange getCelsius() {
        return this.celsius;
    }

    public void setCelsius(IntRange celsius) {
        this.celsius = celsius;
    }

    @ApiModelProperty("")
    public IntRange getFahrenheit() {
        return this.fahrenheit;
    }

    public void setFahrenheit(IntRange fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public Integer getMin() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return this.celsius.getMin();
        }
        return this.fahrenheit.getMin();
    }

    public int getMax() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return this.celsius.getMax().intValue();
        }
        return this.fahrenheit.getMax().intValue();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemperatureRange temperatureRange = (TemperatureRange) o;
        if (Util.equals(this.celsius, temperatureRange.celsius) && Util.equals(this.fahrenheit, temperatureRange.fahrenheit)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.celsius, this.fahrenheit});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemperatureRange {\n");
        sb.append("    celsius: ").append(toIndentedString(this.celsius)).append("\n");
        sb.append("    fahrenheit: ").append(toIndentedString(this.fahrenheit)).append("\n");
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

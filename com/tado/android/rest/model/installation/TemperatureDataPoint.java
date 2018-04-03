package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.Objects;

public class TemperatureDataPoint extends SensorDataPoint {
    @SerializedName("celsius")
    private Float celsius = null;
    @SerializedName("fahrenheit")
    private Float fahrenheit = null;
    @SerializedName("timestamp")
    private Date timestamp = null;

    @ApiModelProperty(required = true, value = "Measurement timestamp\n")
    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @ApiModelProperty("The temperature in celsius.\n")
    public Float getCelsius() {
        return this.celsius;
    }

    public void setCelsius(Float celsius) {
        this.celsius = celsius;
    }

    @ApiModelProperty("The temperature in fahrenheit.\n")
    public Float getFahrenheit() {
        return this.fahrenheit;
    }

    public void setFahrenheit(Float fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemperatureDataPoint temperatureDataPoint = (TemperatureDataPoint) o;
        if (Util.equals(this.timestamp, temperatureDataPoint.timestamp) && Util.equals(this.celsius, temperatureDataPoint.celsius) && Util.equals(this.fahrenheit, temperatureDataPoint.fahrenheit)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.timestamp, this.celsius, this.fahrenheit});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemperatureDataPoint {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(this.timestamp)).append("\n");
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

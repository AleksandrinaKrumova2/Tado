package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

@ApiModel(description = "A temperature specified in degrees celsius and fahrenheit.\n")
public class TemperatureObject {
    @SerializedName("celsius")
    private Float celsius = null;
    @SerializedName("fahrenheit")
    private Float fahrenheit = null;

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

    public float getTemperature() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return this.celsius.floatValue();
        }
        return this.fahrenheit.floatValue();
    }

    public void setTemperature(Float temperature) {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            this.celsius = temperature;
        } else {
            this.fahrenheit = temperature;
        }
    }

    public TemperatureObject(Integer value) {
        setTemperature(Float.valueOf((float) value.intValue()));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemperatureObject temperatureObject = (TemperatureObject) o;
        if (Util.equals(this.celsius, temperatureObject.celsius) && Util.equals(this.fahrenheit, temperatureObject.fahrenheit)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.celsius, this.fahrenheit});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemperatureObject {\n");
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

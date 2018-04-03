package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.report.model.DisplayValue;
import java.io.Serializable;
import java.util.Locale;
import java.util.Observable;

public class Temperature extends Observable implements Serializable, DisplayValue {
    @SerializedName("celsius")
    private Float mCelsius;
    @SerializedName("fahrenheit")
    private Float mFahrenheit;

    private Temperature() {
    }

    public static Temperature fromValue(float value) {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return fromCelsius(value);
        }
        return fromFahrenheit(value);
    }

    public static Temperature fromCelsius(float celsius) {
        Temperature temperature = new Temperature();
        temperature.setCelsius(Float.valueOf(celsius));
        return temperature;
    }

    public static Temperature fromFahrenheit(float fahrenheit) {
        Temperature temperature = new Temperature();
        temperature.setFahrenheit(Float.valueOf(fahrenheit));
        return temperature;
    }

    public float getCelsius() {
        return this.mCelsius != null ? this.mCelsius.floatValue() : 0.0f;
    }

    public void setCelsius(Float celsius) {
        this.mCelsius = celsius;
        if (celsius != null) {
            this.mFahrenheit = Float.valueOf((celsius.floatValue() * 1.8f) + 32.0f);
        }
    }

    public float getFahrenheit() {
        return this.mFahrenheit != null ? this.mFahrenheit.floatValue() : 0.0f;
    }

    public void setFahrenheit(Float fahrenheit) {
        this.mFahrenheit = fahrenheit;
        if (fahrenheit != null) {
            this.mCelsius = Float.valueOf((fahrenheit.floatValue() - 32.0f) / 1.8f);
        }
    }

    public void setTemperatureValue(float value) {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            setCelsius(Float.valueOf(value));
        } else {
            setFahrenheit(Float.valueOf(value));
        }
        setChanged();
    }

    public float getTemperatureValue() {
        if (CapabilitiesController.INSTANCE.isCelsiusTemperatureUnit()) {
            return this.mCelsius.floatValue();
        }
        return this.mFahrenheit.floatValue();
    }

    public float getTemperatureValue(float precision) {
        return ((float) Math.round(getTemperatureValue() / precision)) * precision;
    }

    public String getFormattedTemperatureValue(float precision) {
        if (precision < 1.0f) {
            return String.format(Locale.US, "%.1f°", new Object[]{Float.valueOf(getTemperatureValue(precision))});
        }
        return String.format(Locale.US, "%.0f°", new Object[]{Float.valueOf(getTemperatureValue(precision))});
    }

    public Temperature add(Temperature other) {
        return fromValue(getTemperatureValue() + other.getTemperatureValue());
    }

    public Temperature add(float value) {
        return fromValue(getTemperatureValue() + value);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Temperature that = (Temperature) o;
        if (Float.compare(that.mCelsius.floatValue(), this.mCelsius.floatValue()) != 0) {
            return false;
        }
        if (this.mFahrenheit == null || Float.compare(that.mFahrenheit.floatValue(), this.mFahrenheit.floatValue()) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.mCelsius.floatValue() != 0.0f) {
            result = Float.floatToIntBits(this.mCelsius.floatValue());
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.mFahrenheit.floatValue() != 0.0f) {
            i = Float.floatToIntBits(this.mFahrenheit.floatValue());
        }
        return i2 + i;
    }

    public float getDisplayValue() {
        return getTemperatureValue();
    }
}

package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.TemperatureUnitEnum;
import com.tado.android.utils.UserConfig;

public class TemperatureRange {
    @SerializedName("celsius")
    private IntRange mCelsius;
    @SerializedName("fahrenheit")
    private IntRange mFahrenheit;

    public IntRange getCelsius() {
        return this.mCelsius;
    }

    public void setCelsius(IntRange celsius) {
        this.mCelsius = celsius;
    }

    public IntRange getFahrenheit() {
        return this.mFahrenheit;
    }

    public void setFahrenheit(IntRange fahrenheit) {
        this.mFahrenheit = fahrenheit;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.mCelsius == null ? 0 : this.mCelsius.hashCode()) + 527) * 31;
        if (this.mFahrenheit != null) {
            i = this.mFahrenheit.hashCode();
        }
        return hashCode + i;
    }

    public float getStep() {
        if (UserConfig.getTemperatureUnit() == TemperatureUnitEnum.CELSIUS) {
            return this.mCelsius.getStep();
        }
        return this.mFahrenheit.getStep();
    }

    public float getMinTemperature() {
        if (UserConfig.getTemperatureUnit() == TemperatureUnitEnum.CELSIUS) {
            return (float) this.mCelsius.getMin();
        }
        return (float) this.mFahrenheit.getMin();
    }

    public float getMaxTemperature() {
        if (UserConfig.getTemperatureUnit() == TemperatureUnitEnum.CELSIUS) {
            return (float) this.mCelsius.getMax();
        }
        return (float) this.mFahrenheit.getMax();
    }
}

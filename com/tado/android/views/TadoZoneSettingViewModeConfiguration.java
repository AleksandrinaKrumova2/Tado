package com.tado.android.views;

import com.tado.android.rest.model.installation.FanSpeedEnum;
import com.tado.android.rest.model.installation.SwingEnum;
import com.tado.android.times.view.model.ModeEnum;
import java.io.Serializable;

public class TadoZoneSettingViewModeConfiguration implements Serializable {
    private FanSpeedEnum defaultFanSpeed;
    private SwingEnum defaultSwing;
    private float defaultTemperature;
    private boolean fan;
    private String[] fanSpeedValues;
    private float maxTemperature;
    private float minTemperature;
    private ModeEnum mode;
    private boolean swing;
    private boolean temperature;
    private float temperatureStep;

    public static class Builder {
        private FanSpeedEnum defaultFanSpeed;
        private SwingEnum defaultSwing;
        private float defaultTemperature;
        private boolean fan;
        private String[] fanSpeedValues;
        private float maxTemperature;
        private float minTemperature;
        private ModeEnum mode;
        private boolean swing;
        private boolean temperature;
        private float temperatureStep;

        public Builder create(ModeEnum modeEnum) {
            this.mode = modeEnum;
            return this;
        }

        public Builder withSwing(SwingEnum defaultSwing) {
            this.defaultSwing = defaultSwing;
            this.swing = true;
            return this;
        }

        public Builder withTemperature(float min, float max, float step, float defaultTemperature) {
            this.temperature = true;
            this.minTemperature = min;
            this.maxTemperature = max;
            this.temperatureStep = step;
            this.defaultTemperature = defaultTemperature;
            return this;
        }

        public Builder withFanSpeed(String[] fanSpeed, FanSpeedEnum defaultFanSpeed) {
            this.fan = true;
            this.fanSpeedValues = fanSpeed;
            this.defaultFanSpeed = defaultFanSpeed;
            return this;
        }

        public TadoZoneSettingViewModeConfiguration build() {
            return new TadoZoneSettingViewModeConfiguration(this);
        }
    }

    public TadoZoneSettingViewModeConfiguration(Builder builder) {
        this.mode = builder.mode;
        this.swing = builder.swing;
        this.temperatureStep = builder.temperatureStep;
        this.temperature = builder.temperature;
        this.minTemperature = builder.minTemperature;
        this.maxTemperature = builder.maxTemperature;
        this.fan = builder.fan;
        this.fanSpeedValues = builder.fanSpeedValues;
        this.defaultFanSpeed = builder.defaultFanSpeed;
        this.defaultSwing = builder.defaultSwing;
        this.defaultTemperature = builder.defaultTemperature;
    }

    public ModeEnum getMode() {
        return this.mode;
    }

    public boolean isSwing() {
        return this.swing;
    }

    public boolean isTemperature() {
        return this.temperature;
    }

    public boolean isFan() {
        return this.fan;
    }

    public float getMinTemperature() {
        return this.minTemperature;
    }

    public float getMaxTemperature() {
        return this.maxTemperature;
    }

    public float getTemperatureStep() {
        return this.temperatureStep;
    }

    public String[] getFanSpeedValues() {
        return this.fanSpeedValues;
    }

    public float getDefaultTemperature() {
        return this.defaultTemperature;
    }

    public FanSpeedEnum getDefaultFanSpeed() {
        return this.defaultFanSpeed;
    }

    public SwingEnum getDefaultSwing() {
        return this.defaultSwing;
    }

    public void setDefaultTemperature(float defaultTemperature) {
        this.defaultTemperature = defaultTemperature;
    }

    public void setDefaultFanSpeed(FanSpeedEnum defaultFanSpeed) {
        this.defaultFanSpeed = defaultFanSpeed;
    }

    public void setDefaultSwing(SwingEnum defaultSwing) {
        this.defaultSwing = defaultSwing;
    }
}

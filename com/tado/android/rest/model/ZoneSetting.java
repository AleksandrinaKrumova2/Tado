package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.control_panel.OverlaySettingSeriliazer;
import java.io.Serializable;

public class ZoneSetting implements Cloneable, Serializable {
    public static final String AIR_CONDITIONING = "AIR_CONDITIONING";
    public static final String FAN_AUTO = "AUTO";
    public static final String FAN_HIGH = "HIGH";
    public static final String FAN_LOW = "LOW";
    public static final String FAN_MIDDLE = "MIDDLE";
    public static final String HEATING = "HEATING";
    public static final String HOT_WATER = "HOT_WATER";
    public static final String MODE_AUTO = "AUTO";
    public static final String MODE_COOL = "COOL";
    public static final String MODE_DRY = "DRY";
    public static final String MODE_FAN = "FAN";
    public static final String MODE_HEAT = "HEAT";
    public static final String MODE_HEATING = "HEATING";
    public static final String MODE_HOT_WATER = "HOT_WATER";
    public static final String POWER_OFF = "OFF";
    public static final String POWER_ON = "ON";
    public static final String SWING_OFF = "OFF";
    public static final String SWING_ON = "ON";
    @SerializedName("fanSpeed")
    private String mFanSpeed;
    @SerializedName("mode")
    private String mMode;
    @SerializedName("power")
    private String mPower = "OFF";
    @SerializedName("swing")
    private String mSwing;
    @SerializedName("temperature")
    private Temperature mTemperature;
    @SerializedName("type")
    @Expose
    private String mType = "HEATING";

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getPower() {
        return this.mPower;
    }

    public void setPower(String power) {
        this.mPower = power;
    }

    private String handleHeatingAndHotWaterMode() {
        if (this.mType != null && this.mType.equalsIgnoreCase("HEATING")) {
            return "HEATING";
        }
        if (this.mType == null || !this.mType.equalsIgnoreCase("HOT_WATER")) {
            return null;
        }
        return "HOT_WATER";
    }

    public Temperature getTemperature() {
        return this.mTemperature;
    }

    public void setTemperature(Temperature temperature) {
        this.mTemperature = temperature;
    }

    public String getMode() {
        return this.mMode;
    }

    public void setMode(String mode) {
        this.mMode = mode;
    }

    public String getFanSpeed() {
        return this.mFanSpeed;
    }

    public void setFanSpeed(String fanSpeed) {
        this.mFanSpeed = fanSpeed;
    }

    public String getSwing() {
        return this.mSwing;
    }

    public void setSwing(String swing) {
        this.mSwing = swing;
    }

    public boolean isModeHeating() {
        return getMode().equalsIgnoreCase("HEATING");
    }

    public boolean isModeHotWater() {
        return getMode().equalsIgnoreCase("HOT_WATER");
    }

    public boolean isModeHeat() {
        return getMode().equalsIgnoreCase(MODE_HEAT);
    }

    public boolean isModeCool() {
        return getMode().equalsIgnoreCase(MODE_COOL);
    }

    public boolean isModeDry() {
        return getMode().equalsIgnoreCase(MODE_DRY);
    }

    public boolean isModeFan() {
        return getMode().equalsIgnoreCase(MODE_FAN);
    }

    public boolean isModeAuto() {
        return getMode().equalsIgnoreCase("AUTO");
    }

    public boolean isFanSpeedLow() {
        return this.mFanSpeed.equalsIgnoreCase("LOW");
    }

    public boolean isFanSpeedMiddle() {
        return this.mFanSpeed.equalsIgnoreCase("MIDDLE");
    }

    public boolean isFanSpeedHigh() {
        return this.mFanSpeed.equalsIgnoreCase("HIGH");
    }

    public boolean isFanSpeedAuto() {
        return this.mFanSpeed.equalsIgnoreCase("AUTO");
    }

    public boolean getPowerBoolean() {
        return "ON".equalsIgnoreCase(this.mPower);
    }

    public void setPowerBoolean(boolean value) {
        if (value) {
            this.mPower = "ON";
        } else {
            this.mPower = "OFF";
        }
        setPower(this.mPower);
    }

    public void prepareForUpdate(int currentZoneId) {
        OverlaySettingSeriliazer.setLastSavedMode(this.mMode, currentZoneId);
        if (this.mMode != null && this.mMode.equalsIgnoreCase("HEATING")) {
            this.mMode = null;
            this.mType = "HEATING";
        } else if (this.mMode != null && this.mMode.equalsIgnoreCase("HOT_WATER")) {
            this.mMode = null;
            this.mType = "HOT_WATER";
        } else if (this.mType == null) {
            this.mType = "AIR_CONDITIONING";
        }
        if (!getPowerBoolean()) {
            this.mMode = null;
            this.mTemperature = null;
            this.mFanSpeed = null;
            this.mSwing = null;
        }
    }

    public ZoneSetting clone() {
        ZoneSetting setting = new ZoneSetting();
        setting.setFanSpeed(this.mFanSpeed);
        setting.setMode(this.mMode);
        setting.setPower(this.mPower);
        setting.setSwing(this.mSwing);
        setting.setTemperature(this.mTemperature);
        setting.setType(this.mType);
        return setting;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
        r5 = this;
        r1 = 1;
        r2 = 0;
        if (r5 != r6) goto L_0x0006;
    L_0x0004:
        r2 = r1;
    L_0x0005:
        return r2;
    L_0x0006:
        if (r6 == 0) goto L_0x0005;
    L_0x0008:
        r3 = r5.getClass();
        r4 = r6.getClass();
        if (r3 != r4) goto L_0x0005;
    L_0x0012:
        r0 = r6;
        r0 = (com.tado.android.rest.model.ZoneSetting) r0;
        r3 = r5.mType;
        if (r3 == 0) goto L_0x006c;
    L_0x0019:
        r3 = r5.mType;
        r4 = r0.mType;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0023:
        r3 = r5.mPower;
        if (r3 == 0) goto L_0x0071;
    L_0x0027:
        r3 = r5.mPower;
        r4 = r0.mPower;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0031:
        r3 = r5.mTemperature;
        if (r3 == 0) goto L_0x0076;
    L_0x0035:
        r3 = r5.mTemperature;
        r4 = r0.mTemperature;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x003f:
        r3 = r5.mMode;
        if (r3 == 0) goto L_0x007b;
    L_0x0043:
        r3 = r5.mMode;
        r4 = r0.mMode;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x004d:
        r3 = r5.mFanSpeed;
        if (r3 == 0) goto L_0x0080;
    L_0x0051:
        r3 = r5.mFanSpeed;
        r4 = r0.mFanSpeed;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x005b:
        r3 = r5.mSwing;
        if (r3 == 0) goto L_0x0085;
    L_0x005f:
        r3 = r5.mSwing;
        r4 = r0.mSwing;
        r3 = r3.equals(r4);
        if (r3 != 0) goto L_0x006a;
    L_0x0069:
        r1 = r2;
    L_0x006a:
        r2 = r1;
        goto L_0x0005;
    L_0x006c:
        r3 = r0.mType;
        if (r3 == 0) goto L_0x0023;
    L_0x0070:
        goto L_0x0005;
    L_0x0071:
        r3 = r0.mPower;
        if (r3 == 0) goto L_0x0031;
    L_0x0075:
        goto L_0x0005;
    L_0x0076:
        r3 = r0.mTemperature;
        if (r3 == 0) goto L_0x003f;
    L_0x007a:
        goto L_0x0005;
    L_0x007b:
        r3 = r0.mMode;
        if (r3 == 0) goto L_0x004d;
    L_0x007f:
        goto L_0x0005;
    L_0x0080:
        r3 = r0.mFanSpeed;
        if (r3 == 0) goto L_0x005b;
    L_0x0084:
        goto L_0x0005;
    L_0x0085:
        r3 = r0.mSwing;
        if (r3 != 0) goto L_0x0069;
    L_0x0089:
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.ZoneSetting.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.mType != null) {
            result = this.mType.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.mPower != null) {
            hashCode = this.mPower.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.mTemperature != null) {
            hashCode = this.mTemperature.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.mMode != null) {
            hashCode = this.mMode.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.mFanSpeed != null) {
            hashCode = this.mFanSpeed.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.mSwing != null) {
            i = this.mSwing.hashCode();
        }
        return hashCode + i;
    }
}

package com.tado.android.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.controllers.CapabilitiesController;

public class ScheduleMode {
    @SerializedName("autoAdjust")
    @Expose
    private Boolean mAutoAdjust;
    @SerializedName("comfortLevel")
    @Expose
    private Integer mComfortLevel;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("inUseByBlock")
    private Boolean mInUseByBlock;
    @SerializedName("label")
    @Expose
    private String mName;
    @SerializedName("tadoMode")
    @Expose
    private String mTadoMode;
    @SerializedName("type")
    @Expose
    private String mType;
    @SerializedName("setting")
    @Expose
    private ZoneSetting mZoneSetting;

    public Boolean getInUseByBlock() {
        return this.mInUseByBlock;
    }

    public void setInUseByBlock(Boolean inUseByBlock) {
        this.mInUseByBlock = inUseByBlock;
    }

    public Integer getId() {
        return this.mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getTadoMode() {
        return this.mTadoMode;
    }

    public void setTadoMode(String tadoMode) {
        this.mTadoMode = tadoMode;
    }

    public Boolean getAutoAdjust() {
        return this.mAutoAdjust;
    }

    public void setAutoAdjust(Boolean autoAdjust) {
        this.mAutoAdjust = autoAdjust;
    }

    public Integer getComfortLevel() {
        return this.mComfortLevel;
    }

    public void setComfortLevel(Integer comfortLevel) {
        this.mComfortLevel = comfortLevel;
    }

    public ZoneSetting getZoneSetting() {
        return this.mZoneSetting;
    }

    public void setZoneSetting(ZoneSetting zoneSetting) {
        this.mZoneSetting = zoneSetting;
    }

    public void prepareForUpdate() {
        this.mId = null;
        this.mTadoMode = null;
        this.mInUseByBlock = null;
        if (this.mZoneSetting != null) {
            if (this.mZoneSetting.getType() == null || !(this.mZoneSetting.getType() == null || this.mZoneSetting.getType().equalsIgnoreCase(this.mType))) {
                this.mZoneSetting.setType(this.mType);
            }
            if (this.mZoneSetting.getMode() != null && (this.mZoneSetting.getMode().equalsIgnoreCase("HEATING") || this.mZoneSetting.getMode().equalsIgnoreCase("HOT_WATER"))) {
                this.mZoneSetting.setMode(null);
            }
            if (!this.mZoneSetting.getPowerBoolean()) {
                this.mZoneSetting.setTemperature(null);
                this.mZoneSetting.setFanSpeed(null);
                this.mZoneSetting.setSwing(null);
                this.mZoneSetting.setMode(null);
            }
            if (this.mAutoAdjust != null && this.mAutoAdjust.booleanValue()) {
                this.mZoneSetting = null;
            }
            if (this.mType != null && this.mType.equalsIgnoreCase("HOT_WATER") && CapabilitiesController.INSTANCE.isHotWaterZone() && !CapabilitiesController.INSTANCE.canSetCurrentZoneHotWaterTemperature()) {
                this.mZoneSetting.setTemperature(null);
            }
        }
        if (!(this.mAutoAdjust == null || this.mAutoAdjust.booleanValue())) {
            this.mComfortLevel = null;
        }
        this.mType = null;
        if (this.mName != null && this.mName.isEmpty()) {
            this.mName = null;
        }
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
        r0 = (com.tado.android.rest.model.ScheduleMode) r0;
        r3 = r5.mName;
        if (r3 == 0) goto L_0x006c;
    L_0x0019:
        r3 = r5.mName;
        r4 = r0.mName;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0023:
        r3 = r5.mType;
        if (r3 == 0) goto L_0x0071;
    L_0x0027:
        r3 = r5.mType;
        r4 = r0.mType;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0031:
        r3 = r5.mTadoMode;
        if (r3 == 0) goto L_0x0076;
    L_0x0035:
        r3 = r5.mTadoMode;
        r4 = r0.mTadoMode;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x003f:
        r3 = r5.mAutoAdjust;
        if (r3 == 0) goto L_0x007b;
    L_0x0043:
        r3 = r5.mAutoAdjust;
        r4 = r0.mAutoAdjust;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x004d:
        r3 = r5.mComfortLevel;
        if (r3 == 0) goto L_0x0080;
    L_0x0051:
        r3 = r5.mComfortLevel;
        r4 = r0.mComfortLevel;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x005b:
        r3 = r5.mZoneSetting;
        if (r3 == 0) goto L_0x0085;
    L_0x005f:
        r3 = r5.mZoneSetting;
        r4 = r0.mZoneSetting;
        r3 = r3.equals(r4);
        if (r3 != 0) goto L_0x006a;
    L_0x0069:
        r1 = r2;
    L_0x006a:
        r2 = r1;
        goto L_0x0005;
    L_0x006c:
        r3 = r0.mName;
        if (r3 == 0) goto L_0x0023;
    L_0x0070:
        goto L_0x0005;
    L_0x0071:
        r3 = r0.mType;
        if (r3 == 0) goto L_0x0031;
    L_0x0075:
        goto L_0x0005;
    L_0x0076:
        r3 = r0.mTadoMode;
        if (r3 == 0) goto L_0x003f;
    L_0x007a:
        goto L_0x0005;
    L_0x007b:
        r3 = r0.mAutoAdjust;
        if (r3 == 0) goto L_0x004d;
    L_0x007f:
        goto L_0x0005;
    L_0x0080:
        r3 = r0.mComfortLevel;
        if (r3 == 0) goto L_0x005b;
    L_0x0084:
        goto L_0x0005;
    L_0x0085:
        r3 = r0.mZoneSetting;
        if (r3 != 0) goto L_0x0069;
    L_0x0089:
        goto L_0x006a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.ScheduleMode.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.mName != null) {
            result = this.mName.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.mType != null) {
            hashCode = this.mType.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.mTadoMode != null) {
            hashCode = this.mTadoMode.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.mAutoAdjust != null) {
            hashCode = this.mAutoAdjust.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.mComfortLevel != null) {
            hashCode = this.mComfortLevel.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.mZoneSetting != null) {
            i = this.mZoneSetting.hashCode();
        }
        return hashCode + i;
    }
}

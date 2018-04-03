package com.tado.android.rest.model;

import android.annotation.SuppressLint;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.BatteryStateEnum;
import com.tado.android.rest.model.installation.Duty;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zone implements Serializable {
    @SerializedName("dateCreated")
    private Date mDateCreated;
    @SerializedName("dazzleMode")
    private ZoneDazzleMode mDazzleMode;
    @SerializedName("deviceTypes")
    private List<DeviceType> mDeviceTypes = new ArrayList();
    @SerializedName("devices")
    private List<GenericHardwareDevice> mDevices = new ArrayList();
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("openWindowDetection")
    private OpenWindowDetectionConfiguration mOpenWindowDetectionConfiguration;
    @SerializedName("reportAvailable")
    private boolean mReportAvailable;
    @SerializedName("type")
    @Expose
    private TypeEnum mType;
    private ZoneState mZoneState = new ZoneState();

    public enum TypeEnum {
        HEATING,
        HOT_WATER,
        AIR_CONDITIONING
    }

    public Zone(Integer mId, String mName, TypeEnum mType) {
        this.mId = mId;
        this.mName = mName;
        this.mType = mType;
    }

    public Date getDateCreated() {
        return this.mDateCreated;
    }

    public boolean getReportAvailable() {
        return this.mReportAvailable;
    }

    public void setReportAvailable(Boolean reportAvailable) {
        this.mReportAvailable = reportAvailable.booleanValue();
    }

    public List<DeviceType> getDeviceTypes() {
        return this.mDeviceTypes;
    }

    public void setDeviceTypes(List<DeviceType> deviceTypes) {
        this.mDeviceTypes = deviceTypes;
    }

    public int getId() {
        return this.mId.intValue();
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

    public TypeEnum getType() {
        return this.mType;
    }

    public void setType(TypeEnum type) {
        this.mType = type;
    }

    public List<GenericHardwareDevice> getDevices() {
        return this.mDevices;
    }

    public ZoneState getZoneState() {
        return this.mZoneState;
    }

    public void setZoneState(ZoneState mZoneState) {
        this.mZoneState = mZoneState;
    }

    public boolean isHeatingZone() {
        return TypeEnum.HEATING == this.mType;
    }

    public boolean isHotWaterZone() {
        return TypeEnum.HOT_WATER == this.mType;
    }

    public boolean isCoolingZone() {
        return TypeEnum.AIR_CONDITIONING == this.mType;
    }

    public ZoneDazzleMode getDazzleMode() {
        return this.mDazzleMode;
    }

    public OpenWindowDetectionConfiguration getOpenWindowDetectionConfiguration() {
        return this.mOpenWindowDetectionConfiguration;
    }

    public void setmOpenWindowDetectionConfiguration(OpenWindowDetectionConfiguration mOpenWindowDetectionConfiguration) {
        this.mOpenWindowDetectionConfiguration = mOpenWindowDetectionConfiguration;
    }

    public List<DeviceType> getDeviceUiDeviceTypes() {
        List<DeviceType> deviceUiDeviceTypes = new ArrayList();
        if (this.mDevices != null) {
            for (GenericHardwareDevice device : this.mDevices) {
                if (device.getDuties().contains(Duty.ZONE_UI)) {
                    deviceUiDeviceTypes.add(device.getDeviceType());
                }
            }
        }
        return deviceUiDeviceTypes;
    }

    public boolean shouldShowReport() {
        return !isHotWaterZone();
    }

    public boolean hasLowBattery() {
        for (GenericHardwareDevice device : getDevices()) {
            if (device.getBatteryState() == BatteryStateEnum.LOW) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return String.format("id:%d name:%s type:%s report:%b dazzle support:%b dazzle enabled:%b", new Object[]{this.mId, this.mName, this.mType.toString(), Boolean.valueOf(this.mReportAvailable), Boolean.valueOf(this.mDazzleMode.isEnabled()), Boolean.valueOf(this.mDazzleMode.isSupported())});
    }
}

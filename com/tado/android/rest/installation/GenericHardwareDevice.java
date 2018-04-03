package com.tado.android.rest.model.installation;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.Characteristics;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.HardwareDeviceCapabilities;
import com.tado.android.rest.model.hvac.MountingState;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericHardwareDevice implements Serializable, Comparable<GenericHardwareDevice> {
    @SerializedName("batteryState")
    private BatteryStateEnum batteryState;
    @SerializedName("characteristics")
    protected Characteristics characteristics = null;
    @SerializedName("connectionState")
    protected ConnectionState connectionState = null;
    @SerializedName("currentFwVersion")
    protected String currentFwVersion = null;
    @SerializedName("deviceType")
    private DeviceType deviceType = null;
    @SerializedName("duties")
    protected List<Duty> duties = null;
    @SerializedName("mountingState")
    protected MountingState mountingState = null;
    @SerializedName("serialNo")
    protected String serialNo = null;
    @SerializedName("shortSerialNo")
    protected String shortSerialNo = null;

    @ApiModelProperty(required = true, value = "")
    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Characteristics getCharacteristics() {
        return this.characteristics;
    }

    public boolean canMeasureTemperature() {
        if (this.characteristics == null || this.characteristics.getCapabilities() == null) {
            return false;
        }
        return this.characteristics.getCapabilities().contains(HardwareDeviceCapabilities.INSIDE_TEMPERATURE_MEASUREMENT);
    }

    public boolean canBeIdentified() {
        if (this.characteristics == null || this.characteristics.getCapabilities() == null) {
            return false;
        }
        return this.characteristics.getCapabilities().contains(HardwareDeviceCapabilities.IDENTIFY);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenericHardwareDevice genericHardwareDevice = (GenericHardwareDevice) o;
        if (Util.equals(this.shortSerialNo, genericHardwareDevice.shortSerialNo) && Util.equals(this.deviceType, genericHardwareDevice.deviceType)) {
            return true;
        }
        return false;
    }

    public String getShortSerialNo() {
        return this.shortSerialNo;
    }

    public void setShortSerialNo(String shortSerialNo) {
        this.shortSerialNo = shortSerialNo;
    }

    public ConnectionState getConnectionState() {
        return this.connectionState;
    }

    public MountingState getMountingState() {
        return this.mountingState;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getCurrentFwVersion() {
        if (this.currentFwVersion == null) {
            return "";
        }
        return this.currentFwVersion;
    }

    public boolean isValve() {
        return DeviceType.VA01 == this.deviceType;
    }

    public boolean isGateway() {
        return DeviceType.GW01 == this.deviceType || DeviceType.GW02 == this.deviceType || DeviceType.GW03 == this.deviceType || DeviceType.IB01 == this.deviceType;
    }

    public boolean isCooling() {
        return DeviceType.WR01 == this.deviceType;
    }

    public boolean isSmartThermostat() {
        return isRoomUnit() || DeviceType.BX01 == this.deviceType || DeviceType.BX02 == this.deviceType || DeviceType.BY01 == this.deviceType;
    }

    public boolean isOldGenerationSmartThermostat() {
        return DeviceType.BX01 == this.deviceType || DeviceType.BX02 == this.deviceType || DeviceType.BY01 == this.deviceType;
    }

    public boolean isRoomUnit() {
        return DeviceType.RU01 == this.deviceType || DeviceType.RU02 == this.deviceType;
    }

    public boolean isBoilerUnit() {
        return DeviceType.BU01 == this.deviceType;
    }

    public boolean isTemperatureSensor() {
        return DeviceType.TS01 == this.deviceType || DeviceType.TS02 == this.deviceType;
    }

    public BatteryStateEnum getBatteryState() {
        return this.batteryState;
    }

    public List<Duty> getDuties() {
        if (this.duties == null) {
            return new ArrayList(0);
        }
        return this.duties;
    }

    public void setDuties(List<Duty> duties) {
        this.duties = duties;
    }

    public int getDutiesValue() {
        int bitmask = 0;
        for (Duty duty : this.duties) {
            bitmask |= duty.getValue();
        }
        if (this.deviceType.name().startsWith("TS")) {
            return bitmask | 4;
        }
        return bitmask;
    }

    protected int getSortValue() {
        return (100 - (getDutiesValue() * 10)) + this.deviceType.getPriorityList();
    }

    public boolean isCircuitDriverAndZoneUI() {
        return getDutiesValue() == (Duty.CIRCUIT_DRIVER.getValue() | Duty.ZONE_UI.getValue());
    }

    public boolean isOnlyCircuitDriver() {
        return getDutiesValue() == Duty.CIRCUIT_DRIVER.getValue();
    }

    public boolean isOnlyZoneUI() {
        return getDutiesValue() == Duty.ZONE_UI.getValue();
    }

    public boolean isOnlyZoneDriver() {
        return getDuties().contains(Duty.ZONE_DRIVER) && !getDuties().contains(Duty.ZONE_UI);
    }

    public boolean isZoneDriverAndZoneUI() {
        return getDuties().contains(Duty.ZONE_DRIVER) && getDuties().contains(Duty.ZONE_UI);
    }

    public boolean showBatteryText() {
        return (this.batteryState == null || isTemperatureSensor()) ? false : true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.deviceType, this.shortSerialNo, this.connectionState, this.mountingState, this.characteristics});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GenericHardwareDevice {\n");
        sb.append("    shortSerialNo: ").append(toIndentedString(this.shortSerialNo)).append("\n");
        sb.append("    connectionState: ").append(toIndentedString(this.connectionState)).append("\n");
        sb.append("    mountingState: ").append(toIndentedString(this.mountingState)).append("\n");
        sb.append("    deviceType: ").append(toIndentedString(this.deviceType)).append("\n");
        sb.append("    characteristics: ").append(toIndentedString(this.characteristics)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public static GenericHardwareDevice generateSaleFittingDevice() {
        GenericHardwareDevice device = new GenericHardwareDevice();
        device.deviceType = DeviceType.RU01;
        device.shortSerialNo = "";
        return device;
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public int compareTo(@NonNull GenericHardwareDevice o) {
        if (getSortValue() - o.getSortValue() != 0) {
            return getSortValue() - o.getSortValue();
        }
        if (getShortSerialNo() == null || o.getShortSerialNo() == null) {
            return 0;
        }
        return getShortSerialNo().compareTo(o.getShortSerialNo());
    }
}

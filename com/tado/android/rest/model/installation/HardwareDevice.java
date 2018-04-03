package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.Wifi;
import com.tado.android.rest.model.installation.CommandTableUpload.StateEnum;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class HardwareDevice extends GenericHardwareDevice {
    @SerializedName("accessPointWiFi")
    private Wifi accessPointWifi = null;
    @SerializedName("commandTableUploadState")
    private StateEnum commandTableUploadState = null;

    @ApiModelProperty("Short serial number of device.\n")
    public String getShortSerialNo() {
        return this.shortSerialNo;
    }

    public void setShortSerialNo(String shortSerialNo) {
        this.shortSerialNo = shortSerialNo;
    }

    @ApiModelProperty("Current state of connection.\n")
    public ConnectionState getConnectionState() {
        return this.connectionState;
    }

    public Wifi getAccessPointWifi() {
        return this.accessPointWifi;
    }

    public void setAccessPointWifi(Wifi accessPointWifi) {
        this.accessPointWifi = accessPointWifi;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HardwareDevice hardwareDevice = (HardwareDevice) o;
        if (Util.equals(this.shortSerialNo, hardwareDevice.shortSerialNo) && Util.equals(this.connectionState, hardwareDevice.connectionState) && Util.equals(this.accessPointWifi, hardwareDevice.accessPointWifi) && Util.equals(this.commandTableUploadState, hardwareDevice.commandTableUploadState)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.shortSerialNo, this.connectionState, this.accessPointWifi, this.commandTableUploadState});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HardwareDevice {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    shortSerialNo: ").append(toIndentedString(this.shortSerialNo)).append("\n");
        sb.append("    connectionState: ").append(toIndentedString(this.connectionState)).append("\n");
        sb.append("    accessPointWifi: ").append(toIndentedString(this.accessPointWifi)).append("\n");
        sb.append("    commandTableUploadState: ").append(toIndentedString(this.commandTableUploadState)).append("\n");
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

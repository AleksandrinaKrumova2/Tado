package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.DevicePosition;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

public class AcInstallationInformation implements Serializable {
    @SerializedName("acSettingCommandSetRecording")
    private AcInstallationInformationKeyCommandSetRecording acSettingCommandSetRecording = null;
    @SerializedName("acSpecs")
    private AcSpecs acSpecs = null;
    @SerializedName("createdZone")
    private AcInstallationInformationKeyCommandSetRecording createdZone = null;
    @SerializedName("devicePositioning")
    private DevicePositioningEnum devicePositioning = null;
    @SerializedName("wirelessRemoteHasRequiredFirmware")
    private Boolean wirelessRemoteHasRequiredFirmware = null;

    public enum DevicePositioningEnum {
        VERTICAL(DevicePosition.VERTICAL),
        HORIZONTAL(DevicePosition.HORIZONTAL);
        
        private String value;

        private DevicePositioningEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    @ApiModelProperty(required = true, value = "")
    public AcSpecs getAcSpecs() {
        return this.acSpecs;
    }

    public void setAcSpecs(AcSpecs acSpecs) {
        this.acSpecs = acSpecs;
    }

    @ApiModelProperty("Whether the wireless remote of this installation has the firmware version running which is required\nfor this installation.\n")
    public Boolean getWirelessRemoteHasRequiredFirmware() {
        return this.wirelessRemoteHasRequiredFirmware;
    }

    public void setWirelessRemoteHasRequiredFirmware(Boolean wirelessRemoteHasRequiredFirmware) {
        this.wirelessRemoteHasRequiredFirmware = wirelessRemoteHasRequiredFirmware;
    }

    @ApiModelProperty("")
    public AcInstallationInformationKeyCommandSetRecording getAcSettingCommandSetRecording() {
        return this.acSettingCommandSetRecording;
    }

    public void setAcSettingCommandSetRecording(AcInstallationInformationKeyCommandSetRecording acSettingCommandSetRecording) {
        this.acSettingCommandSetRecording = acSettingCommandSetRecording;
    }

    @ApiModelProperty(required = true, value = "")
    public DevicePositioningEnum getDevicePositioning() {
        return this.devicePositioning;
    }

    public void setDevicePositioning(DevicePositioningEnum devicePositioning) {
        this.devicePositioning = devicePositioning;
    }

    @ApiModelProperty("")
    public AcInstallationInformationKeyCommandSetRecording getCreatedZone() {
        return this.createdZone;
    }

    public void setCreatedZone(AcInstallationInformationKeyCommandSetRecording createdZone) {
        this.createdZone = createdZone;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcInstallationInformation acInstallationInformation = (AcInstallationInformation) o;
        if (Util.equals(this.acSpecs, acInstallationInformation.acSpecs) && Util.equals(this.wirelessRemoteHasRequiredFirmware, acInstallationInformation.wirelessRemoteHasRequiredFirmware) && Util.equals(this.acSettingCommandSetRecording, acInstallationInformation.acSettingCommandSetRecording) && Util.equals(this.devicePositioning, acInstallationInformation.devicePositioning) && Util.equals(this.createdZone, acInstallationInformation.createdZone)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.acSpecs, this.wirelessRemoteHasRequiredFirmware, this.acSettingCommandSetRecording, this.devicePositioning, this.createdZone});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcInstallationInformation {\n");
        sb.append("    acSpecs: ").append(toIndentedString(this.acSpecs)).append("\n");
        sb.append("    wirelessRemoteHasRequiredFirmware: ").append(toIndentedString(this.wirelessRemoteHasRequiredFirmware)).append("\n");
        sb.append("    acSettingCommandSetRecording: ").append(toIndentedString(this.acSettingCommandSetRecording)).append("\n");
        sb.append("    devicePositioning: ").append(toIndentedString(this.devicePositioning)).append("\n");
        sb.append("    createdZone: ").append(toIndentedString(this.createdZone)).append("\n");
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

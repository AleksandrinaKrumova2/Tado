package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AcCommandSet implements Serializable {
    @SerializedName("userAcCapabilities")
    private CoolingCapabilities capabilities = null;
    @SerializedName("code")
    private Integer code = null;
    @SerializedName("commandType")
    private CommandTypeEnum commandType = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("origin")
    private OriginEnum origin = null;
    @SerializedName("supportedModes")
    private List<ModeEnum> supportedModes = new ArrayList();
    @SerializedName("temperatureUnit")
    private TemperatureUnitEnum temperatureUnit = null;

    public enum OriginEnum {
        REMOTEC("REMOTEC"),
        TADO("TADO"),
        USER(Constants.USER);
        
        private String value;

        private OriginEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    @ApiModelProperty(required = true, value = "")
    public CommandTypeEnum getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommandTypeEnum commandType) {
        this.commandType = commandType;
    }

    @ApiModelProperty(required = true, value = "")
    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @ApiModelProperty("")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty("")
    public OriginEnum getOrigin() {
        return this.origin;
    }

    @ApiModelProperty("")
    public TemperatureUnitEnum getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setTemperatureUnit(TemperatureUnitEnum temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    @ApiModelProperty("")
    public List<ModeEnum> getSupportedModes() {
        return this.supportedModes;
    }

    public void setSupportedModes(List<ModeEnum> supportedModes) {
        this.supportedModes = supportedModes;
    }

    public CoolingCapabilities getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(CoolingCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AcCommandSet acCommandSet = (AcCommandSet) o;
        if (Util.equals(this.commandType, acCommandSet.commandType) && Util.equals(this.code, acCommandSet.code) && Util.equals(this.name, acCommandSet.name) && Util.equals(this.origin, acCommandSet.origin) && Util.equals(this.temperatureUnit, acCommandSet.temperatureUnit) && Util.equals(this.supportedModes, acCommandSet.supportedModes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.commandType, this.code, this.name, this.origin, this.temperatureUnit, this.supportedModes});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcCommandSet {\n");
        sb.append("    commandType: ").append(toIndentedString(this.commandType)).append("\n");
        sb.append("    code: ").append(toIndentedString(this.code)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    origin: ").append(toIndentedString(this.origin)).append("\n");
        sb.append("    temperatureUnit: ").append(toIndentedString(this.temperatureUnit)).append("\n");
        sb.append("    supportedModes: ").append(toIndentedString(this.supportedModes)).append("\n");
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

package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.Temperature;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class GenericZoneSetting extends Observable implements Serializable {
    public static final String POWER_OFF = "OFF";
    public static final String POWER_ON = "ON";
    protected Observer mObserver;
    @SerializedName("power")
    private String power = null;
    @SerializedName("temperature")
    private Temperature temperature = null;
    @SerializedName("type")
    private TypeEnum type = null;

    public enum TypeEnum {
        HEATING("HEATING"),
        AIR_CONDITIONING("AIR_CONDITIONING"),
        HOT_WATER("HOT_WATER");
        
        private String value;

        private TypeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public GenericZoneSetting(TypeEnum type, boolean power, Temperature temperature) {
        this.type = type;
        this.temperature = temperature;
        setPowerBoolean(power);
    }

    @ApiModelProperty("The current temperature.\n")
    public Temperature getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @ApiModelProperty("System power.\n")
    public String getPower() {
        return this.power;
    }

    public void setPower(String power) {
        this.power = power;
        setChanged();
        notifyObservers();
    }

    public boolean getPowerBoolean() {
        return this.power != null && this.power.equalsIgnoreCase("ON");
    }

    public void setPowerBoolean(boolean powerBoolean) {
        this.power = powerBoolean ? "ON" : "OFF";
        setChanged();
    }

    @ApiModelProperty(required = true, value = "Tado system type.\n")
    public TypeEnum getType() {
        return this.type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenericZoneSetting genericZoneSetting = (GenericZoneSetting) o;
        if (getPowerBoolean() == ((GenericZoneSetting) o).getPowerBoolean() && Util.equals(this.type, genericZoneSetting.type) && Util.equals(this.temperature, genericZoneSetting.temperature)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GenericZoneSetting {\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public void addObserver(Observer observer) {
        super.addObserver(observer);
        this.mObserver = observer;
        if (this.temperature != null) {
            this.temperature.addObserver(observer);
        }
    }
}

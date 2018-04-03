package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import java.io.Serializable;

public class OverlayTerminationCondition implements Serializable {
    public static final String MANUAL = "MANUAL";
    public static final String TADO_MODE = "TADO_MODE";
    public static final String TIMER = "TIMER";
    @SerializedName("expiry")
    private String expiry;
    @SerializedName("durationInSeconds")
    private Integer mDurationInSeconds;
    @SerializedName("type")
    private String mType;
    @SerializedName("projectedExpiry")
    private String projectedExpiry;
    @SerializedName("remainingTimeInSeconds")
    private Integer remainingTimeInSeconds;

    public Integer getRemainingTimeInSeconds() {
        return this.remainingTimeInSeconds;
    }

    public void setRemainingTimeInSeconds(Integer remainingTimeInSeconds) {
        this.remainingTimeInSeconds = remainingTimeInSeconds;
    }

    public String getType() {
        return this.mType;
    }

    public OverlayTerminationCondition(String mType) {
        this.mType = mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public Integer getDurationInSeconds() {
        return this.mDurationInSeconds;
    }

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.mDurationInSeconds = durationInSeconds;
    }

    public String getExpiry() {
        return this.expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public boolean isOverlayTerminationManual() {
        return this.mType.equalsIgnoreCase("MANUAL");
    }

    public boolean isOverlayTerminationTadoMode() {
        return this.mType.equalsIgnoreCase(TADO_MODE);
    }

    public boolean isOverlayTerminationTimer() {
        return this.mType.equalsIgnoreCase(TIMER);
    }

    public String getTypeValue() {
        if (this.mType == null) {
            return "";
        }
        if (this.mType.equalsIgnoreCase("MANUAL")) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.components_terminationConditionSelector_manualLabel);
        }
        if (this.mType.equalsIgnoreCase(TIMER)) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.components_terminationConditionSelector_timerLabel);
        }
        return TadoApplication.getTadoAppContext().getString(C0676R.string.components_terminationConditionSelector_automaticLabel);
    }

    public String getTimerTypeValue(String timerValue) {
        return TadoApplication.getTadoAppContext().getString(C0676R.string.components_terminationConditionSelector_timerWithDurationLabel, new Object[]{timerValue});
    }

    public String getProjectedExpiry() {
        return this.projectedExpiry;
    }

    public void setProjectedExpiry(String projectedExpiry) {
        this.projectedExpiry = projectedExpiry;
    }

    public boolean isManualOverlayTermination() {
        return this.mType == null ? false : this.mType.equalsIgnoreCase("MANUAL");
    }

    public boolean isTimerOverlayTermination() {
        return this.mType == null ? false : this.mType.equalsIgnoreCase(TIMER);
    }

    public boolean isTadoModeOverlayTermination() {
        return this.mType == null ? false : this.mType.equalsIgnoreCase(TADO_MODE);
    }
}

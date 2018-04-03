package com.tado.android.onboarding.data.model;

import java.util.ArrayList;

public class FeatureOrderItem {
    private String key;
    private ArrayList<String> targetZoneTypes;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getTargetZoneTypes() {
        return this.targetZoneTypes;
    }

    public void setTargetZoneTypes(ArrayList<String> targetZoneTypes) {
        this.targetZoneTypes = targetZoneTypes;
    }
}

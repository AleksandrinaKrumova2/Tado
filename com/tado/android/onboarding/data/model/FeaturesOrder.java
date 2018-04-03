package com.tado.android.onboarding.data.model;

import java.util.ArrayList;

public class FeaturesOrder {
    private ArrayList<FeatureOrderItem> featureOrder;
    private int version;

    public ArrayList<FeatureOrderItem> getFeatureOrder() {
        return this.featureOrder;
    }

    public int getVersion() {
        return this.version;
    }

    public FeaturesOrder(int version, ArrayList<FeatureOrderItem> featureOrder) {
        this.version = version;
        this.featureOrder = featureOrder;
    }
}

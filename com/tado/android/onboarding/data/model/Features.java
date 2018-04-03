package com.tado.android.onboarding.data.model;

import java.util.HashMap;
import java.util.Map;

public class Features {
    private Map<String, FeatureInfo> introScreens;

    public Map<String, FeatureInfo> getIntroScreens() {
        return this.introScreens;
    }

    public Features() {
        this.introScreens = new HashMap();
    }

    public Features(Map<String, FeatureInfo> features) {
        this.introScreens = features;
    }
}

package com.tado.android.onboarding.data.model;

import android.content.Context;
import android.support.annotation.NonNull;
import com.tado.android.onboarding.data.FeatureDataSource;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeatureCallback;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeaturesCallback;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeaturesOrderCallback;
import com.tado.android.onboarding.data.FeatureDataSource.LoadVersionCallback;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SmartScheduleTutorialDataSource implements FeatureDataSource {
    private static final String AWAY_KEY = "smart_schedule_intro_away";
    private static final String EARLY_START_KEY = "smart_schedule_intro_early_start";
    private static final String HOME_KEY = "smart_schedule_intro_home";
    private static final int VERSION = 1;
    private static SmartScheduleTutorialDataSource instance;
    private Context context;
    private Features features;

    private SmartScheduleTutorialDataSource(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public static SmartScheduleTutorialDataSource getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new SmartScheduleTutorialDataSource(context);
        }
        return instance;
    }

    private Map<String, FeatureInfo> getSmartScheduleTutorialFeaturesMap() {
        return new LinkedHashMap<String, FeatureInfo>(3) {
        };
    }

    public void getFeatures(@NonNull LoadFeaturesCallback callback) {
        this.features = new Features(getSmartScheduleTutorialFeaturesMap());
        callback.onFeaturesLoaded(this.features);
    }

    public void getFeature(String id, @NonNull LoadFeatureCallback callback) {
        if (this.features == null) {
            this.features = new Features(getSmartScheduleTutorialFeaturesMap());
        }
        callback.onFeatureLoaded((FeatureInfo) this.features.getIntroScreens().get(id));
    }

    public void getFeaturesOrder(@NonNull LoadFeaturesOrderCallback callback) {
        ArrayList<FeatureOrderItem> featuresOrders = new ArrayList(3);
        FeatureOrderItem awayItem = new FeatureOrderItem();
        awayItem.setKey(AWAY_KEY);
        FeatureOrderItem homeItem = new FeatureOrderItem();
        homeItem.setKey(HOME_KEY);
        FeatureOrderItem earlyStartItem = new FeatureOrderItem();
        earlyStartItem.setKey(EARLY_START_KEY);
        featuresOrders.add(homeItem);
        featuresOrders.add(awayItem);
        featuresOrders.add(earlyStartItem);
        callback.onFeaturesOrderLoaded(new FeaturesOrder(1, featuresOrders));
    }

    public void getVersion(@NonNull LoadVersionCallback callback) {
        callback.onVersionLoaded(1);
    }

    public void release() {
        instance = null;
    }

    public TutorialDataSourceRepositoryEnum getType() {
        return TutorialDataSourceRepositoryEnum.SMART_SCHEDULE;
    }
}

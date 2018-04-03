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

public class HeatingReportTutorialDataSource implements FeatureDataSource {
    private static final String BUTTONS_KEY = "report_intro_buttons";
    private static final String INSPECTION_KEY = "report_intro_inspection";
    private static final int VERSION = 1;
    private static HeatingReportTutorialDataSource instance;
    private Context context;
    private Features features;

    private HeatingReportTutorialDataSource(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public static HeatingReportTutorialDataSource getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new HeatingReportTutorialDataSource(context);
        }
        return instance;
    }

    private Map<String, FeatureInfo> getFeaturesMap() {
        return new LinkedHashMap<String, FeatureInfo>(2) {
        };
    }

    public void getFeatures(@NonNull LoadFeaturesCallback callback) {
        this.features = new Features(getFeaturesMap());
        callback.onFeaturesLoaded(this.features);
    }

    public void getFeature(String id, @NonNull LoadFeatureCallback callback) {
        if (this.features == null) {
            this.features = new Features(getFeaturesMap());
        }
        callback.onFeatureLoaded((FeatureInfo) this.features.getIntroScreens().get(id));
    }

    public void getFeaturesOrder(@NonNull LoadFeaturesOrderCallback callback) {
        ArrayList<FeatureOrderItem> featuresOrders = new ArrayList(2);
        FeatureOrderItem buttonsItem = new FeatureOrderItem();
        buttonsItem.setKey(BUTTONS_KEY);
        featuresOrders.add(buttonsItem);
        FeatureOrderItem inspectionModeItem = new FeatureOrderItem();
        inspectionModeItem.setKey(INSPECTION_KEY);
        featuresOrders.add(inspectionModeItem);
        callback.onFeaturesOrderLoaded(new FeaturesOrder(1, featuresOrders));
    }

    public void getVersion(@NonNull LoadVersionCallback callback) {
        callback.onVersionLoaded(1);
    }

    public void release() {
        instance = null;
    }

    public TutorialDataSourceRepositoryEnum getType() {
        return TutorialDataSourceRepositoryEnum.REPORT_HEATING;
    }
}

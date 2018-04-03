package com.tado.android.onboarding.data;

import android.support.annotation.NonNull;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.onboarding.data.model.FeatureInfo;
import com.tado.android.onboarding.data.model.Features;
import com.tado.android.onboarding.data.model.FeaturesOrder;

public interface FeatureDataSource {

    public interface LoadVersionCallback {
        void onLoadingError(String str);

        void onVersionLoaded(int i);

        void onVersionNoFeaturesToShow();
    }

    public interface LoadFeatureCallback {
        void onFeatureLoaded(FeatureInfo featureInfo);

        void onLoadingError(String str);
    }

    public interface LoadFeaturesOrderCallback {
        void onFeaturesOrderLoaded(FeaturesOrder featuresOrder);

        void onLoadingError(String str);
    }

    public interface LoadFeaturesCallback {
        void onFeaturesLoaded(Features features);

        void onLoadingError(String str);
    }

    void getFeature(String str, @NonNull LoadFeatureCallback loadFeatureCallback);

    void getFeatures(@NonNull LoadFeaturesCallback loadFeaturesCallback);

    void getFeaturesOrder(@NonNull LoadFeaturesOrderCallback loadFeaturesOrderCallback);

    TutorialDataSourceRepositoryEnum getType();

    void getVersion(@NonNull LoadVersionCallback loadVersionCallback);

    void release();
}

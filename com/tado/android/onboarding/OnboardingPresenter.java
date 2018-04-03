package com.tado.android.onboarding;

import android.support.annotation.NonNull;
import com.tado.android.onboarding.data.FeatureDataSource;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeaturesOrderCallback;
import com.tado.android.onboarding.data.model.FeaturesOrder;
import com.tado.android.utils.Snitcher;

public class OnboardingPresenter implements LoadFeaturesOrderCallback {
    private static final String TAG = "OnboardingPresenter";
    private final View onboardingView;
    private FeatureDataSource repository;
    private int version;

    public interface View {
        void setPages(FeaturesOrder featuresOrder);

        void showErrorMessage(String str);
    }

    public OnboardingPresenter(@NonNull View onboardingView, FeatureDataSource repository) {
        this.onboardingView = onboardingView;
        this.repository = repository;
    }

    public void loadFeatures() {
        this.repository.getFeaturesOrder(this);
    }

    public void onFeaturesOrderLoaded(FeaturesOrder order) {
        this.onboardingView.setPages(order);
        this.version = order.getVersion();
    }

    public void onLoadingError(String msg) {
        Snitcher.start().toCrashlytics().log(6, TAG, msg, new Object[0]);
        this.onboardingView.showErrorMessage("Error loading presentation of new features");
    }

    public void onSkip() {
        this.repository.release();
    }
}

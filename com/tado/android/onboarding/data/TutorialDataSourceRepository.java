package com.tado.android.onboarding.data;

import android.content.Context;
import android.support.annotation.NonNull;
import com.tado.android.onboarding.data.model.CoolingReportTutorialDataSource;
import com.tado.android.onboarding.data.model.HeatingReportTutorialDataSource;
import com.tado.android.onboarding.data.model.SmartScheduleTutorialDataSource;
import java.util.Locale;

public class TutorialDataSourceRepository {

    public enum TutorialDataSourceRepositoryEnum {
        ON_BOARDING,
        SMART_SCHEDULE,
        REPORT_COOLING,
        REPORT_HEATING
    }

    @NonNull
    public static FeatureDataSource getDataSource(@NonNull TutorialDataSourceRepositoryEnum tutorial, @NonNull Context context, @NonNull Locale locale) {
        switch (tutorial) {
            case SMART_SCHEDULE:
                return SmartScheduleTutorialDataSource.getInstance(context.getApplicationContext());
            case REPORT_HEATING:
                return HeatingReportTutorialDataSource.getInstance(context.getApplicationContext());
            case REPORT_COOLING:
                return CoolingReportTutorialDataSource.getInstance(context.getApplicationContext());
            default:
                return FeatureAssetsDataSource.getInstance(context.getResources(), context.getAssets(), locale);
        }
    }
}

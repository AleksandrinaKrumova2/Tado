package com.tado.android.onboarding;

import android.content.Context;
import android.support.annotation.NonNull;
import com.tado.android.onboarding.data.FeatureDataSource;
import com.tado.android.onboarding.data.model.CoolingReportTutorialDataSource;
import com.tado.android.onboarding.data.model.HeatingReportTutorialDataSource;
import com.tado.android.onboarding.data.model.SmartScheduleTutorialDataSource;
import com.tado.android.rest.model.Zone.TypeEnum;

public class TutorialUtil {
    public static FeatureDataSource getReportTutorial(TypeEnum zoneType, @NonNull Context context) {
        if (zoneType == TypeEnum.HEATING) {
            return HeatingReportTutorialDataSource.getInstance(context);
        }
        if (zoneType == TypeEnum.AIR_CONDITIONING) {
            return CoolingReportTutorialDataSource.getInstance(context);
        }
        return null;
    }

    public static FeatureDataSource getScheduleTutorial(@NonNull Context context) {
        return SmartScheduleTutorialDataSource.getInstance(context);
    }
}

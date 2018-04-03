package com.tado.android.settings.model;

import android.support.annotation.Nullable;
import com.tado.android.app.TadoApplication;
import com.tado.android.notifications.NotificationUtil;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.MobileDeviceSettings;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import retrofit2.Call;
import retrofit2.Response;

public class PresenceDetectionSettings {

    public interface OnPresenceDetectionRemoteChanged {
        void onPresenceDetectionRemoteChanged(boolean z);

        void onRemoteChangeFailed();

        void onRemoteChangeNotSuccessful();
    }

    private static void setPresenceDetectionLocalSetting(boolean isEnabled) {
        UserConfig.setLocationBasedControlEnabled(isEnabled);
        NotificationUtil.updateAllNotifications(TadoApplication.getTadoAppContext());
        if (isEnabled) {
            TadoApplication.locationManager.startTrackingIfEnabled();
        } else {
            TadoApplication.locationManager.stopTracking();
        }
    }

    public static void updatePresenceDetectionSetting(final boolean isEnabled, @Nullable final OnPresenceDetectionRemoteChanged callback) {
        setPresenceDetectionLocalSetting(isEnabled);
        RestServiceGenerator.getTadoRestService().updateMobileDeviceSettings(UserConfig.getHomeId(), UserConfig.getMobileDeviceId(), new MobileDeviceSettings(isEnabled), RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<MobileDeviceSettings>() {
            public void onResponse(Call<MobileDeviceSettings> call, Response<MobileDeviceSettings> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onPresenceDetectionRemoteChanged(isEnabled);
                    }
                } else if (callback != null) {
                    callback.onRemoteChangeNotSuccessful();
                }
            }

            public void onFailure(Call<MobileDeviceSettings> call, Throwable t) {
                super.onFailure(call, t);
                if (callback != null) {
                    callback.onRemoteChangeFailed();
                }
            }
        });
    }
}

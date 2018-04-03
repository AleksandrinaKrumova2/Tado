package com.tado.android.location.updates;

import android.content.Context;
import android.location.Location;
import android.os.BaseBundle;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.LocationUtil;
import com.tado.android.location.PostState;
import com.tado.android.location.jobservices.IJobServiceAdapter;
import com.tado.android.notifications.SettingsUtil;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.model.GeolocationUpdate;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.DebugUtil;
import com.tado.android.utils.GeolocationLogEntry;
import com.tado.android.utils.GeolocationLogger;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;

class LocationUpdateUtil {
    private static final Map<String, Location> cache = new HashMap();

    LocationUpdateUtil() {
    }

    static Map<String, Location> getLocationCache() {
        return cache;
    }

    private static GeolocationLogEntry prepareGeolocationLogEntry(GeolocationUpdate geolocationUpdate) {
        GeolocationLogEntry mGeolocationLogEntry = GeolocationLogger.getGeoLocationEntry(geolocationUpdate.getLocation());
        mGeolocationLogEntry.setMode(LocationUtil.getLocationAcquisitionMode(geolocationUpdate.getLocation()).name());
        return mGeolocationLogEntry;
    }

    static <T extends BaseBundle> T prepareJobExtras(T extras, GeolocationUpdate geolocationUpdate) {
        extras.putString("KEY_LOCATION", new Gson().toJson((Object) geolocationUpdate));
        extras.putString("KEY_LOG", new Gson().toJson(prepareGeolocationLogEntry(geolocationUpdate)));
        return extras;
    }

    static Bundle prepareJobExtras(Bundle extras, GeolocationUpdate geolocationUpdate) {
        extras.putString("KEY_LOCATION", new Gson().toJson((Object) geolocationUpdate));
        extras.putString("KEY_LOG", new Gson().toJson(prepareGeolocationLogEntry(geolocationUpdate)));
        return extras;
    }

    static void postLocation(final String TAG, final GeolocationUpdate locationUpdate, final GeolocationLogEntry mGeolocationLogEntry, final IJobServiceAdapter jobServiceAdapter) {
        Call<Void> request = RestServiceGenerator.getTadoLocationRestService().postLocationUpdate(UserConfig.getHomeId(), UserConfig.getMobileDeviceId(), locationUpdate);
        Log.d(TAG, "URI is " + request.request().url());
        Snitcher.start().toLogger().log(4, TAG, "Posting location from %s (hasInternet)", Util.getOperatingSystemVersion());
        mGeolocationLogEntry.setSent(GeolocationLogger.getCurrentFormatedTime());
        request.enqueue(new TadoCallback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    jobServiceAdapter.resetAttempts();
                    try {
                        String geoConfigVersion = response.headers().get("config-etag");
                        GeolocationLogger.logToFile(mGeolocationLogEntry);
                        if (LocationUpdateUtil.cache.containsKey(locationUpdate.getId())) {
                            LocationUpdateUtil.cache.remove(locationUpdate.getId());
                        }
                        TadoApplication.locationManager.onPostSuccessful(geoConfigVersion, locationUpdate);
                    } catch (Exception e) {
                        Snitcher.start().toLogger().toCrashlytics().logException(e);
                    } finally {
                        jobServiceAdapter.finishJob();
                    }
                    return;
                }
                mGeolocationLogEntry.setReason(String.valueOf(response.code()));
                Builder toLogger = Snitcher.start().toLogger();
                String str = TAG;
                String str2 = "%d Unsuccessful response. Url %s Body was %s";
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(response.code());
                objArr[1] = response.raw().request().url().url().toString();
                objArr[2] = response.raw().request().body() != null ? response.raw().request().body().toString() : "null body";
                toLogger.log(3, str, str2, objArr);
                jobServiceAdapter.finishJob();
            }

            public void onFailure(Call<Void> call, Throwable t) {
                super.onFailure(call, t);
                jobServiceAdapter.rescheduleJob();
            }
        });
    }

    static void logOnStop(String TAG, GeolocationUpdate locationUpdate, GeolocationLogEntry mGeolocationLogEntry, int attempt, Context context) {
        try {
            synchronized (cache) {
                Snitcher.start().toLogger().log(3, TAG, "Stopping job", new Object[0]);
                mGeolocationLogEntry.setAttempts(attempt);
                StringBuilder append = new StringBuilder().append("reached max attempts ").append(attempt).append(". There was ");
                String str = (SettingsUtil.internetCapableSettings(context) && SettingsUtil.hasInternetConnection(context)) ? "internet connection" : "no internet connection";
                mGeolocationLogEntry.setReason(append.append(str).toString());
                GeolocationLogger.logToFile(mGeolocationLogEntry);
                Snitcher.start().toLogger().log(6, TAG, "GIVING UP " + SettingsUtil.hasInternetConnection(context) + "/" + SettingsUtil.internetCapableSettings(context), new Object[0]);
                if (locationUpdate != null) {
                    Location failedLocation = (Location) cache.get(locationUpdate.getId());
                    if (failedLocation != null) {
                        LocationUtil.addDroppedReason(failedLocation, "reached max attempts " + attempt);
                        DebugUtil.logDroppedLocation(failedLocation);
                        LocationUtil.updateLocationsListAndShowNotification(failedLocation, PostState.FAILED, context, null);
                    }
                }
                cache.clear();
            }
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        }
    }
}

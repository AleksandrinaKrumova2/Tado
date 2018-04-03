package com.tado.android.location.updates;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.google.gson.Gson;
import com.tado.android.location.jobservices.IJobServiceAdapter;
import com.tado.android.location.jobservices.StandardLocationPostJobServiceAdapter;
import com.tado.android.rest.model.GeolocationUpdate;
import com.tado.android.rest.model.GeolocationUpdate.Update;
import com.tado.android.utils.DebugUtil;
import com.tado.android.utils.GeolocationLogEntry;
import com.tado.android.utils.Snitcher;

public class StandardLocationPostJobService extends JobService {
    private static final String TAG = "LocationPostJobService";
    private static int attempt = 0;
    private static GeolocationLogEntry mGeolocationLogEntry;

    public void resetAttempts() {
        attempt = 0;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        IJobServiceAdapter jobServiceAdapter = new StandardLocationPostJobServiceAdapter(this, jobParameters);
        try {
            GeolocationUpdate locationUpdate = (GeolocationUpdate) new Gson().fromJson(jobParameters.getExtras().getString("KEY_LOCATION"), GeolocationUpdate.class);
            mGeolocationLogEntry = (GeolocationLogEntry) new Gson().fromJson(jobParameters.getExtras().getString("KEY_LOG"), GeolocationLogEntry.class);
            attempt++;
            locationUpdate.setUpdate(new Update(attempt, DebugUtil.getDroppedLocations()));
            LocationUpdateUtil.postLocation(TAG, locationUpdate, mGeolocationLogEntry, jobServiceAdapter);
            return true;
        } catch (Exception e) {
            Snitcher.start().toLogger().toCrashlytics().logException(e);
            jobServiceAdapter.finishJob();
            return false;
        }
    }

    public boolean onStopJob(JobParameters jobParameters) {
        try {
            LocationUpdateUtil.logOnStop(TAG, (GeolocationUpdate) new Gson().fromJson(jobParameters.getExtras().getString("KEY_LOCATION"), GeolocationUpdate.class), mGeolocationLogEntry, attempt, getApplicationContext());
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        }
        return true;
    }
}

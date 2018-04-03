package com.tado.android.location.updates;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import com.tado.android.rest.model.GeolocationUpdate;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;

public class StandardLocationUpdateScheduler implements ILocationUpdateScheduler {
    private static final int LOCATION_POST_JOB_ID = 197;
    private static final int NORMAL_MAX_DELAY_IN_MS = 300000;
    private static final String TAG = "StandardLocationUpdateSchedule";
    private static final int URGENT_MAX_DELAY_IN_MS = 60000;
    private Context context;
    private JobScheduler jobScheduler;

    public StandardLocationUpdateScheduler(Context context, JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
        this.context = context;
    }

    @RequiresApi(api = 21)
    private JobInfo getJob(GeolocationUpdate update, boolean locationPending) {
        LocationUpdateUtil.getLocationCache().put(update.getId(), update.getLocation());
        return prepareBundle(update, locationPending).build();
    }

    private Builder createJobParameters(GeolocationUpdate geolocationUpdate, boolean locationPending) {
        Snitcher.start().toLogger().log(4, getClass().getSimpleName(), "Creating job parameters", new Object[0]);
        PersistableBundle extras = (PersistableBundle) LocationUpdateUtil.prepareJobExtras(new PersistableBundle(), geolocationUpdate);
        long overrideDeadline = locationPending ? 60000 : Constants.WAIT_FOR_FIRMWARE_UPDATE_TIMEOUT;
        Snitcher.start().toLogger().log(4, getClass().getSimpleName(), "Override deadline set to " + overrideDeadline, new Object[0]);
        return new Builder(LOCATION_POST_JOB_ID, new ComponentName(this.context, StandardLocationPostJobService.class)).setBackoffCriteria(30000, 0).setRequiredNetworkType(1).setOverrideDeadline(overrideDeadline).setPersisted(true).setExtras(extras);
    }

    public Builder prepareBundle(GeolocationUpdate update, boolean locationPending) {
        return createJobParameters(update, locationPending);
    }

    public boolean scheduleLocationUpdate(GeolocationUpdate geolocationUpdate, boolean locationPending) {
        JobInfo job = getJob(geolocationUpdate, locationPending);
        Snitcher.start().toLogger().log(4, getClass().getSimpleName(), "Scheduling job", new Object[0]);
        if (this.jobScheduler.schedule(job) == 1) {
            return true;
        }
        return false;
    }

    public void cancelAll() {
        this.jobScheduler.cancelAll();
    }
}

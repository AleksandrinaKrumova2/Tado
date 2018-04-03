package com.tado.android.location.lifeline;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.tado.android.utils.Snitcher;
import org.joda.time.DateTime;

public class StandardLocationCheckScheduler implements ILocationCheckScheduler {
    private static final int LOCATION_POST_JOB_ID = 198;
    private static final String TAG = "StandardLocationCheckScheduler";
    private Context context;
    private JobScheduler jobScheduler;

    public StandardLocationCheckScheduler(Context context, JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
        this.context = context;
    }

    public void scheduleAlarm(long wakeupIntervalMillis) {
        this.jobScheduler.schedule(getJob(wakeupIntervalMillis));
    }

    private JobInfo getJob(long wakeupIntervalMillis) {
        Snitcher.start().toLogger().log(4, TAG, "Setting up next alarm at %s (%d minutes difference)", new DateTime(System.currentTimeMillis() + wakeupIntervalMillis).toString(), Long.valueOf(wakeupIntervalMillis / 60000));
        return new Builder(LOCATION_POST_JOB_ID, new ComponentName(this.context, StandardCheckLocationJobService.class)).setBackoffCriteria(60000, 0).setRequiredNetworkType(1).setPersisted(true).setMinimumLatency(wakeupIntervalMillis).build();
    }

    public void cancelAll() {
        this.jobScheduler.cancelAll();
    }
}

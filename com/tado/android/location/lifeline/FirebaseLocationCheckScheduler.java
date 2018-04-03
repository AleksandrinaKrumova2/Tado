package com.tado.android.location.lifeline;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Trigger;
import com.tado.android.utils.Snitcher;
import org.joda.time.DateTime;

public class FirebaseLocationCheckScheduler implements ILocationCheckScheduler {
    private static final String JOB_ID = "CheckLocationServicesJobService";
    private static final String TAG = "FirebaseLocationCheckScheduler";
    private FirebaseJobDispatcher jobDispatcher;

    public FirebaseLocationCheckScheduler(FirebaseJobDispatcher jobDispatcher) {
        this.jobDispatcher = jobDispatcher;
    }

    public void scheduleAlarm(long wakeupIntervalMillis) {
        this.jobDispatcher.schedule(getJob(this.jobDispatcher, (int) (wakeupIntervalMillis / 1000)));
    }

    private Job getJob(FirebaseJobDispatcher dispatcher, int delayInSeconds) {
        Snitcher.start().toLogger().log(4, TAG, "Setting up next alarm at %s (%d minutes difference)", new DateTime(System.currentTimeMillis() + ((long) (delayInSeconds * 1000))).toString(), Integer.valueOf(delayInSeconds / 60));
        return dispatcher.newJobBuilder().setTag(JOB_ID).setService(FirebaseCheckLocationServicesJobService.class).setRecurring(true).setReplaceCurrent(true).setLifetime(2).setTrigger(Trigger.executionWindow(delayInSeconds, delayInSeconds + 60)).build();
    }

    public void cancelAll() {
        this.jobDispatcher.cancelAll();
    }
}

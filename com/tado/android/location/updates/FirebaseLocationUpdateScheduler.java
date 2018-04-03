package com.tado.android.location.updates;

import android.os.Bundle;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Job.Builder;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.tado.android.rest.model.GeolocationUpdate;
import com.tado.android.utils.Snitcher;

public class FirebaseLocationUpdateScheduler implements ILocationUpdateScheduler {
    private static final String LOCATION_POST_JOB_ID = "LocationPostFirebaseJobService";
    private static final String TAG = "FirebaseLocationUpdateScheduler";
    private FirebaseJobDispatcher jobDispatcher;

    public FirebaseLocationUpdateScheduler(FirebaseJobDispatcher jobDispatcher) {
        this.jobDispatcher = jobDispatcher;
    }

    public Job getJob(FirebaseJobDispatcher dispatcher, GeolocationUpdate update) {
        LocationUpdateUtil.getLocationCache().put(update.getId(), update.getLocation());
        return prepareBundle(dispatcher, update, 0).build();
    }

    private Builder createJobParameters(FirebaseJobDispatcher dispatcher, GeolocationUpdate geolocationUpdate) {
        Snitcher.start().toLogger().log(4, getClass().getSimpleName(), "Creating job parameters", new Object[0]);
        return dispatcher.newJobBuilder().setTag(LOCATION_POST_JOB_ID).setService(FirebaseLocationPostJobService.class).setRetryStrategy(RetryStrategy.DEFAULT_LINEAR).setRecurring(false).setReplaceCurrent(true).setLifetime(2).setTrigger(Trigger.NOW).addConstraint(2).setExtras(LocationUpdateUtil.prepareJobExtras(new Bundle(), geolocationUpdate));
    }

    public Builder prepareBundle(FirebaseJobDispatcher dispatcher, GeolocationUpdate update, int escalationLevel) {
        JobParameters builder = createJobParameters(dispatcher, update);
        if (escalationLevel > 2 || dispatcher.getValidator().isValid(builder)) {
            return builder;
        }
        switch (escalationLevel) {
            case 0:
                update.getDevice().setStatus(null);
                break;
            case 1:
                update.setUpdate(null);
                break;
            default:
                update = new GeolocationUpdate(update.getLocation());
                break;
        }
        return prepareBundle(dispatcher, update, escalationLevel + 1);
    }

    public boolean scheduleLocationUpdate(GeolocationUpdate geolocationUpdate, boolean locationPending) {
        Job job = getJob(this.jobDispatcher, geolocationUpdate);
        Snitcher.start().toLogger().log(4, getClass().getSimpleName(), "Scheduling job", new Object[0]);
        if (this.jobDispatcher.schedule(job) == 0) {
            return true;
        }
        return false;
    }

    public void cancelAll() {
        this.jobDispatcher.cancelAll();
    }
}

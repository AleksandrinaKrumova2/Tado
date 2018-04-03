package com.tado.android.location.jobservices;

import com.firebase.jobdispatcher.JobParameters;
import com.tado.android.location.lifeline.FirebaseCheckLocationServicesJobService;
import com.tado.android.utils.Snitcher;

public class FirebaseCheckLocationJobServiceAdapter implements IJobServiceAdapter {
    private FirebaseCheckLocationServicesJobService jobService;
    private JobParameters parameters;

    public FirebaseCheckLocationJobServiceAdapter(FirebaseCheckLocationServicesJobService jobService, JobParameters parameters) {
        this.jobService = jobService;
        this.parameters = parameters;
    }

    public void finishJob() {
        this.jobService.jobFinished(this.parameters, false);
    }

    public void rescheduleJob() {
        Snitcher.start().toLogger().log(4, getClass().getSimpleName(), "Rescheduling job", new Object[0]);
        this.jobService.jobFinished(this.parameters, true);
    }

    public void resetAttempts() {
    }
}

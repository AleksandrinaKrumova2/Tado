package com.tado.android.location.jobservices;

import com.firebase.jobdispatcher.JobParameters;
import com.tado.android.location.updates.FirebaseLocationPostJobService;
import com.tado.android.utils.Snitcher;

public class FirebaseLocationPostJobServiceAdapter implements IJobServiceAdapter {
    private FirebaseLocationPostJobService jobService;
    private JobParameters parameters;

    public FirebaseLocationPostJobServiceAdapter(FirebaseLocationPostJobService jobService, JobParameters parameters) {
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
        this.jobService.resetAttempts();
    }
}

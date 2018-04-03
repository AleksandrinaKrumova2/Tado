package com.tado.android.location.jobservices;

import android.app.job.JobParameters;
import com.tado.android.location.lifeline.StandardCheckLocationJobService;
import com.tado.android.utils.Snitcher;

public class StandardCheckLocationJobServiceAdapter implements IJobServiceAdapter {
    private StandardCheckLocationJobService jobService;
    private JobParameters parameters;

    public StandardCheckLocationJobServiceAdapter(StandardCheckLocationJobService jobService, JobParameters parameters) {
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

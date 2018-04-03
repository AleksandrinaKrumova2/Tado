package com.tado.android.location.lifeline;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.tado.android.location.jobservices.StandardCheckLocationJobServiceAdapter;

public class StandardCheckLocationJobService extends JobService {
    private static final String TAG = "StandardCheckLocationJobService";

    public boolean onStartJob(JobParameters job) {
        LocationCheckUtil.checkLocationServices(new StandardCheckLocationJobServiceAdapter(this, job));
        return false;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}

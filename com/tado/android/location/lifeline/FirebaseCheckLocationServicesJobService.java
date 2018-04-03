package com.tado.android.location.lifeline;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.tado.android.location.jobservices.FirebaseCheckLocationJobServiceAdapter;

public class FirebaseCheckLocationServicesJobService extends JobService {
    private static final String TAG = "CheckLocationServices";

    public boolean onStartJob(JobParameters job) {
        LocationCheckUtil.checkLocationServices(new FirebaseCheckLocationJobServiceAdapter(this, job));
        return false;
    }

    public boolean onStopJob(JobParameters job) {
        return false;
    }
}

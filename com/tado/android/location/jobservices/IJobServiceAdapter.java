package com.tado.android.location.jobservices;

public interface IJobServiceAdapter {
    void finishJob();

    void rescheduleJob();

    void resetAttempts();
}

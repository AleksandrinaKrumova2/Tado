package com.tado.android.location.lifeline;

import com.tado.android.location.ICancelableScheduler;

public interface ILocationCheckScheduler extends ICancelableScheduler {
    void scheduleAlarm(long j);
}

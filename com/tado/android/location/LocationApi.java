package com.tado.android.location;

public interface LocationApi {
    int checkStatus();

    void getLastKnownLocation(OnLastKnownLocation onLastKnownLocation);

    void reset();

    void setLocationConfig(LocationConfiguration locationConfiguration);

    void startTracking();

    void stopTracking();
}

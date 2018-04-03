package com.tado.android.location;

public interface LocationCallbacks {
    void sendLastKnownLocation();

    void startTracking();

    void stopTracking();
}

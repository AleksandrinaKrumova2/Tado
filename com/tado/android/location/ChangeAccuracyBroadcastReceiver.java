package com.tado.android.location;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;

public class ChangeAccuracyBroadcastReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = "ChangeAccuracyBroadcast";

    public void onReceive(Context context, Intent intent) {
        WakeLock wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, TAG);
        wakeLock.acquire(Constants.WIFI_CONNECTION_RETRY_TIMEOUT);
        try {
            Snitcher.start().toLogger().log(3, TAG, "Received broadcast to reduce accuracy to BALANCED", new Object[0]);
            TadoApplication.locationManager.startTrackingIfEnabled();
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        } finally {
            wakeLock.release();
        }
    }
}

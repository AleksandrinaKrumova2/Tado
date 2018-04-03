package com.tado.android.location;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;

public class BootReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = "BootReceiver";

    public void onReceive(Context context, Intent intent) {
        UserConfig.setLastTimeBooted(System.currentTimeMillis());
        try {
            Snitcher.start().toLogger().log(3, TAG, "boot  -> starting location tracking", new Object[0]);
            TadoApplication.locationManager.startTrackingIfEnabled();
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        }
    }
}

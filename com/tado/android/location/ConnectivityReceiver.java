package com.tado.android.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import com.tado.android.mvp.model.HomeWifiRepository;
import com.tado.android.notifications.NotificationUtil;
import com.tado.android.utils.DebugUtil;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import java.util.Map;

public class ConnectivityReceiver extends BroadcastReceiver {
    private static final Object DATA_DISABLED = "dataDisabled";
    private static final Object DATA_ENABLED = "dataEnabled";
    private static final String REASON = "reason";
    private static final String STATE = "state";
    private static final String TAG = "TADO-ConnectivityRcv";

    public void onReceive(Context ctx, Intent intent) {
        try {
            Snitcher.start().log(3, TAG, DebugUtil.getIntentLog(intent), new Object[0]);
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
                NetworkInfo mWifi = null;
                ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService("connectivity");
                if (connManager != null) {
                    if (ContextCompat.checkSelfPermission(ctx, "android.permission.ACCESS_NETWORK_STATE") == 0) {
                        NetworkInfo networkInfo;
                        if (VERSION.SDK_INT >= 21) {
                            for (Network network : connManager.getAllNetworks()) {
                                networkInfo = connManager.getNetworkInfo(network);
                                if (networkInfo != null && 1 == networkInfo.getType()) {
                                    mWifi = networkInfo;
                                }
                            }
                        } else {
                            networkInfo = connManager.getActiveNetworkInfo();
                            if (networkInfo != null && 1 == networkInfo.getType()) {
                                mWifi = networkInfo;
                            }
                        }
                    }
                }
                if (mWifi == null) {
                    checkIfAbandoningHomeWifi(ctx);
                    notInHomeWIFI();
                } else if (mWifi.isConnected()) {
                    checkForHomeWifi(ctx);
                } else {
                    checkIfAbandoningHomeWifi(ctx);
                    notInHomeWIFI();
                }
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NotificationUtil.updateAllNotifications(ctx, intent.getStringExtra(REASON));
            } else if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
                int state = intent.getIntExtra("wifi_state", -1);
                if (state == 1 || state == 3) {
                    final Context finalCtx = ctx;
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            NotificationUtil.updateAllNotifications(finalCtx);
                        }
                    }, 3000);
                }
            } else if ("android.intent.action.AIRPLANE_MODE".equals(intent.getAction())) {
                if (!intent.getBooleanExtra(STATE, true)) {
                    UserConfig.setLastTimeAirplaneModeTurnedOff(System.currentTimeMillis());
                }
                NotificationUtil.updateAllNotifications(ctx);
            }
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        }
    }

    private void checkIfAbandoningHomeWifi(Context ctx) {
        if (UserConfig.isInHomeWifi()) {
            Snitcher.start().toLogger().log(3, TAG, "Wifi %s state changed from connected to disconnected", HomeWifiRepository.INSTANCE.getHomeWifis());
            if (!Util.isDeviceIdle(ctx)) {
            }
        }
    }

    private void notInHomeWIFI() {
        UserConfig.setInHomeWifi(false);
    }

    private void checkForHomeWifi(Context ctx) {
        if (UserConfig.isHomeWifiDetectionEnabled()) {
            WifiManager wifi = (WifiManager) ctx.getApplicationContext().getSystemService("wifi");
            if (wifi != null) {
                WifiInfo wifiInfo = wifi.getConnectionInfo();
                if (wifiInfo != null) {
                    String bssid = wifiInfo.getBSSID();
                    Map<String, String> homeSSID = HomeWifiRepository.INSTANCE.getHomeWifis();
                    if (bssid == null || !homeSSID.containsKey(bssid)) {
                        notInHomeWIFI();
                        return;
                    } else {
                        UserConfig.setInHomeWifi(true);
                        return;
                    }
                }
                return;
            }
            return;
        }
        notInHomeWIFI();
    }
}

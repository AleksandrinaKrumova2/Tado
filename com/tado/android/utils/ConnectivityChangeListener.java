package com.tado.android.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tado.android.app.TadoApplication;

public class ConnectivityChangeListener {

    public interface ConnectionChangeCallback {
        void onInternetConnected();

        void onInternetDisconnected();
    }

    public static class ConnectivityBroadcastReceiver extends BroadcastReceiver {
        ConnectionChangeCallback callback;
        private boolean wasDisconnected = false;

        public ConnectivityBroadcastReceiver(ConnectionChangeCallback callback) {
            boolean z = false;
            this.callback = callback;
            if (!ConnectivityChangeListener.isConnected()) {
                z = true;
            }
            this.wasDisconnected = z;
        }

        public void onReceive(Context context, Intent intent) {
            if (this.callback == null) {
                return;
            }
            if (!ConnectivityChangeListener.isConnected()) {
                this.callback.onInternetDisconnected();
                this.wasDisconnected = true;
            } else if (this.wasDisconnected) {
                this.callback.onInternetConnected();
                this.wasDisconnected = false;
            }
        }
    }

    private ConnectivityChangeListener() {
    }

    public static ConnectivityBroadcastReceiver register(ConnectionChangeCallback callback) {
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        ConnectivityBroadcastReceiver connectivityBroadcastReceiver = new ConnectivityBroadcastReceiver(callback);
        TadoApplication.getTadoAppContext().registerReceiver(connectivityBroadcastReceiver, intentFilter);
        return connectivityBroadcastReceiver;
    }

    public static void unregister(ConnectivityBroadcastReceiver receiver) {
        receiver.callback = null;
        TadoApplication.getTadoAppContext().unregisterReceiver(receiver);
    }

    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) TadoApplication.getTadoAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

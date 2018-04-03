package com.tado.android.client;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkRequest.Builder;
import android.os.Build.VERSION;
import com.tado.android.requests.Request;

public class LocalAPICallUtilities {
    public static void executeRequestOnWiFi(final ConnectivityManager cm, final Request request, final Activity activity, final APICallListener listener) {
        if (VERSION.SDK_INT >= 21) {
            cm.registerNetworkCallback(new Builder().addTransportType(1).build(), new NetworkCallback() {
                public void onAvailable(Network network) {
                    if (cm.getNetworkInfo(network).getType() == 1) {
                        LocalAPICallUtilities.executeRequest(new LocalAPICall(request, network, activity), listener);
                        cm.unregisterNetworkCallback(this);
                    }
                }
            });
        } else {
            executeRequest(new LocalAPICall(request, activity), listener);
        }
    }

    private static void executeRequest(LocalAPICall request, APICallListener listener) {
        request.setmShowLoaderDialog(true);
        request.setAPICallListener(listener);
        request.execute(new Void[0]);
    }
}

package com.tado.android.notifications;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SettingsUtil {
    public static boolean isAirplaneModeOn(Context context) {
        if (VERSION.SDK_INT >= 17) {
            if (Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0) {
                return true;
            }
            return false;
        } else if (System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isLocationServicesOn(Context context) {
        boolean isGpsEnabled;
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager == null || !locationManager.isProviderEnabled("gps")) {
            isGpsEnabled = false;
        } else {
            isGpsEnabled = true;
        }
        boolean isNetworkEnabled;
        if (locationManager == null || !locationManager.isProviderEnabled("network")) {
            isNetworkEnabled = false;
        } else {
            isNetworkEnabled = true;
        }
        if (isGpsEnabled || isNetworkEnabled) {
            return true;
        }
        return false;
    }

    public static boolean isWifiOn(Context ctx) {
        String wifi_on = VERSION.SDK_INT < 17 ? "wifi_on" : "wifi_on";
        boolean wifi = VERSION.SDK_INT < 17 ? System.getInt(ctx.getContentResolver(), wifi_on, 0) != 0 : Global.getInt(ctx.getContentResolver(), wifi_on, 0) != 0;
        return wifi || ((WifiManager) ctx.getApplicationContext().getSystemService("wifi")).isWifiEnabled() || isWifiTetheringOn(ctx);
    }

    private static boolean isWifiScanEnabled(Context ctx) {
        return VERSION.SDK_INT >= 18 && ((WifiManager) ctx.getApplicationContext().getSystemService("wifi")).isScanAlwaysAvailable();
    }

    private static boolean isWifiTetheringOn(Context ctx) {
        try {
            WifiManager wifi = (WifiManager) ctx.getApplicationContext().getSystemService("wifi");
            int state = ((Integer) reflect(wifi, "getWifiApState")).intValue();
            if (((Boolean) reflect(wifi, "isWifiApEnabled")).booleanValue() || state == 2) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isMobileDataOn(Context ctx) {
        boolean z = true;
        try {
            return ((Boolean) reflect((ConnectivityManager) ctx.getSystemService("connectivity"), "getMobileDataEnabled")).booleanValue();
        } catch (Exception e) {
            try {
                return ((Boolean) reflect((TelephonyManager) ctx.getSystemService(CreateHomeContactDetailsActivity.INTENT_PHONE), "getDataEnabled")).booleanValue();
            } catch (Exception e2) {
                try {
                    if (VERSION.SDK_INT >= 17) {
                        return Global.getInt(ctx.getContentResolver(), "mobile_data", 0) == 1;
                    } else {
                        if (Secure.getInt(ctx.getContentResolver(), "mobile_data", 0) != 1) {
                            z = false;
                        }
                        return z;
                    }
                } catch (Exception e3) {
                    return false;
                }
            }
        }
    }

    public static Object reflect(Object obj, String methodName) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Method method = Class.forName(obj.getClass().getName()).getDeclaredMethod(methodName, new Class[0]);
        method.setAccessible(true);
        return method.invoke(obj, new Object[0]);
    }

    public static boolean internetCapableSettings(Context ctx) {
        return isWifiOn(ctx) || isMobileDataOn(ctx);
    }

    public static boolean hasInternetConnection(Context context) {
        return hasInternetConnection(context, null);
    }

    public static boolean hasInternetConnection(Context context, Network network) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetwork = network == null ? cm.getActiveNetworkInfo() : cm.getNetworkInfo(network);
        return activeNetwork != null && activeNetwork.isConnected();
    }
}

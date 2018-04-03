package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceFragment;
import android.view.View;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0006H\u0002J\b\u0010\f\u001a\u00020\u0006H\u0002R\u0014\u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u0000X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/WifiReceiverPreferenceFragment;", "Landroid/preference/PreferenceFragment;", "()V", "wifiReceiver", "Lcom/tado/android/settings/locationbasedcontrol/homewifi/WifiReceiverPreferenceFragment$WifiReceiver;", "onPause", "", "onResume", "onWifiConnected", "wifiInfo", "Landroid/net/wifi/WifiInfo;", "registerWifiReceiver", "unregisterWifiReceiver", "WifiReceiver", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: WifiReceiverPreferenceFragment.kt */
public abstract class WifiReceiverPreferenceFragment extends PreferenceFragment {
    private HashMap _$_findViewCache;
    private WifiReceiver wifiReceiver;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/WifiReceiverPreferenceFragment$WifiReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/tado/android/settings/locationbasedcontrol/homewifi/WifiReceiverPreferenceFragment;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: WifiReceiverPreferenceFragment.kt */
    private final class WifiReceiver extends BroadcastReceiver {
        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            NetworkInfo info = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (info != null && info.isConnected()) {
                WifiManager wifiManager = context.getApplicationContext().getSystemService("wifi");
                if (wifiManager == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
                }
                wifiManager = wifiManager;
                WifiReceiverPreferenceFragment wifiReceiverPreferenceFragment = WifiReceiverPreferenceFragment.this;
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                Intrinsics.checkExpressionValueIsNotNull(connectionInfo, "wifiManager.connectionInfo");
                wifiReceiverPreferenceFragment.onWifiConnected(connectionInfo);
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public abstract void onWifiConnected(@NotNull WifiInfo wifiInfo);

    public void onResume() {
        super.onResume();
        registerWifiReceiver();
    }

    public void onPause() {
        unregisterWifiReceiver();
        super.onPause();
    }

    private final void registerWifiReceiver() {
        if (this.wifiReceiver == null) {
            this.wifiReceiver = new WifiReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            Activity activity = getActivity();
            if (activity != null) {
                activity.registerReceiver(this.wifiReceiver, intentFilter);
            }
        }
    }

    private final void unregisterWifiReceiver() {
        if (this.wifiReceiver != null) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.unregisterReceiver(this.wifiReceiver);
            }
            this.wifiReceiver = (WifiReceiver) null;
        }
    }
}

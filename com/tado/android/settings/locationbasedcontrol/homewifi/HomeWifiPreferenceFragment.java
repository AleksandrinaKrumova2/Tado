package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.databinding.ObservableMap;
import android.databinding.ObservableMap.OnMapChangedCallback;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.mvp.model.HomeWifiRepository;
import com.tado.android.utils.UserConfig;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0016\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0002J\u0012\u0010\u001e\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0010H\u0016J\b\u0010\"\u001a\u00020\u0010H\u0016J\u0010\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\nH\u0016J\b\u0010%\u001a\u00020\u0010H\u0002R.\u0010\u0003\u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFragment;", "Lcom/tado/android/settings/locationbasedcontrol/homewifi/WifiReceiverPreferenceFragment;", "()V", "callback", "Landroid/databinding/ObservableMap$OnMapChangedCallback;", "Landroid/databinding/ObservableMap;", "", "currentNetworkCategory", "Landroid/preference/PreferenceCategory;", "currentWifiInfo", "Landroid/net/wifi/WifiInfo;", "homeNetworkCategory", "homeWifiMap", "updateLocked", "", "addHomePreference", "", "bssid", "ssid", "createHomeWiFiTogglePreference", "Landroid/preference/SwitchPreference;", "createResetConfirmationDialog", "Landroid/app/AlertDialog;", "executeWithManualUpdate", "operation", "Lkotlin/Function0;", "initNetworkPreferences", "screen", "Landroid/preference/PreferenceScreen;", "initPreferenceScreen", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onWifiConnected", "wifiInfo", "recreatePreferenceScreen", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeWifiPreferenceFragment.kt */
public final class HomeWifiPreferenceFragment extends WifiReceiverPreferenceFragment {
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    private OnMapChangedCallback<ObservableMap<String, String>, String, String> callback;
    private PreferenceCategory currentNetworkCategory;
    private WifiInfo currentWifiInfo;
    private PreferenceCategory homeNetworkCategory;
    private ObservableMap<String, String> homeWifiMap = HomeWifiRepository.INSTANCE.getHomeWifis();
    private boolean updateLocked;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFragment$Companion;", "", "()V", "instance", "Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFragment;", "getInstance", "()Lcom/tado/android/settings/locationbasedcontrol/homewifi/HomeWifiPreferenceFragment;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeWifiPreferenceFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final HomeWifiPreferenceFragment getInstance() {
            return new HomeWifiPreferenceFragment();
        }
    }

    private final void initNetworkPreferences(android.preference.PreferenceScreen r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.settings.locationbasedcontrol.homewifi.HomeWifiPreferenceFragment.initNetworkPreferences(android.preference.PreferenceScreen):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r1 = r11.homeWifiMap;
        r1 = r1.isEmpty();
        if (r1 != 0) goto L_0x0043;
    L_0x0008:
        r2 = com.tado.android.settings.locationbasedcontrol.homewifi.HomeWifiPreferenceFactory.Companion;
        r1 = r11.getActivity();
        r1 = (android.content.Context) r1;
        r1 = r2.homeNetworksCategory(r1, r12);
        r11.homeNetworkCategory = r1;
        r1 = r11.homeWifiMap;
        r6 = r1.keySet();
        r6 = (java.lang.Iterable) r6;
        r2 = r6.iterator();
        r1 = r2.hasNext();
        if (r1 == 0) goto L_0x0042;
    L_0x0028:
        r9 = r2.next();
        r10 = r9;
        r10 = (java.lang.String) r10;
        r1 = "it";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r1);
        r1 = r11.homeWifiMap;
        r1 = r1.get(r10);
        r1 = (java.lang.String) r1;
        r11.addHomePreference(r10, r1);
        goto L_0x0022;
    L_0x0043:
        r8 = r11.currentWifiInfo;
        if (r8 == 0) goto L_0x00ac;
        r1 = r8.getBSSID();
        if (r1 == 0) goto L_0x00ac;
        r1 = r8.getBSSID();
        r1 = com.tado.android.location.LocationUtil.isValidBSSID(r1);
        if (r1 == 0) goto L_0x00ac;
        r1 = r11.homeWifiMap;
        r2 = r8.getBSSID();
        r1 = r1.containsKey(r2);
        if (r1 != 0) goto L_0x00ac;
        r2 = com.tado.android.settings.locationbasedcontrol.homewifi.HomeWifiPreferenceFactory.Companion;
        r1 = r11.getActivity();
        r1 = (android.content.Context) r1;
        r1 = r2.currentNetworkCategory(r1, r12);
        r11.currentNetworkCategory = r1;
        r0 = new com.tado.android.settings.locationbasedcontrol.homewifi.WiFiDisplayPreference;
        r1 = r11.getActivity();
        r1 = (android.content.Context) r1;
        r2 = r8.getSSID();
        r2 = com.tado.android.utils.UserConfig.getSSIDWithoutQuotes(r2);
        r3 = "UserConfig.getSSIDWithou…tes(currentWifiCopy.ssid)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3);
        r3 = r8.getBSSID();
        r4 = "currentWifiCopy.bssid";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4);
        r4 = com.tado.android.settings.locationbasedcontrol.homewifi.WiFiAddRemoveEnum.Add;
        r5 = 1;
        r0.<init>(r1, r2, r3, r4, r5);
        r1 = new com.tado.android.settings.locationbasedcontrol.homewifi.HomeWifiPreferenceFragment$initNetworkPreferences$$inlined$apply$lambda$1;
        r1.<init>(r11, r8);
        r1 = (android.view.View.OnClickListener) r1;
        r0.setOnClickListener(r1);
        r7 = r0;
        r1 = r11.currentNetworkCategory;
        if (r1 == 0) goto L_0x00ac;
        r7 = (android.preference.Preference) r7;
        r1.addPreference(r7);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.settings.locationbasedcontrol.homewifi.HomeWifiPreferenceFragment.initNetworkPreferences(android.preference.PreferenceScreen):void");
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

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnalyticsHelper.trackPageView(getActivity(), Screen.HOME_WIFI_SETTINGS);
        this.callback = new HomeWifiPreferenceFragment$onCreate$1(this);
        HomeWifiRepository.INSTANCE.getHomeWifis().addOnMapChangedCallback(this.callback);
    }

    public void onDestroyView() {
        super.onDestroyView();
        HomeWifiRepository.INSTANCE.getHomeWifis().removeOnMapChangedCallback(this.callback);
        _$_clearFindViewByIdCache();
    }

    public void onResume() {
        super.onResume();
        initPreferenceScreen();
    }

    private final void executeWithManualUpdate(Function0<Unit> operation) {
        synchronized (this) {
            this.updateLocked = true;
            operation.invoke();
            recreatePreferenceScreen();
            this.updateLocked = false;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void recreatePreferenceScreen() {
        getPreferenceScreen().removeAll();
        initPreferenceScreen();
    }

    private final void initPreferenceScreen() {
        if (isAdded() && getActivity() != null) {
            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
            screen.addPreference(createHomeWiFiTogglePreference());
            if (UserConfig.isHomeWifiDetectionEnabled()) {
                Intrinsics.checkExpressionValueIsNotNull(screen, "screen");
                initNetworkPreferences(screen);
            }
            setPreferenceScreen(screen);
        }
    }

    private final SwitchPreference createHomeWiFiTogglePreference() {
        SwitchPreference $receiver = new SwitchPreference(getActivity());
        $receiver.setTitle(C0676R.string.settings_locationBasedControl_homeWiFi_homeWiFiToggleLabel);
        $receiver.setSummary(C0676R.string.settings_locationBasedControl_homeWiFi_homeWiFiToggleDescription);
        $receiver.setDefaultValue(Boolean.valueOf(true));
        $receiver.setPersistent(false);
        $receiver.setKey("homeWiFiToggle");
        $receiver.setEnabled(true);
        $receiver.setChecked(true);
        $receiver.setOnPreferenceChangeListener(new C1118x2d36f2c7(this));
        return $receiver;
    }

    private final void addHomePreference(String bssid, String ssid) {
        Context activity = getActivity();
        String str = ssid != null ? ssid : "";
        WiFiAddRemoveEnum wiFiAddRemoveEnum = WiFiAddRemoveEnum.Remove;
        WifiInfo wifiInfo = this.currentWifiInfo;
        WiFiDisplayPreference $receiver = new WiFiDisplayPreference(activity, str, bssid, wiFiAddRemoveEnum, StringsKt__StringsJVMKt.equals(wifiInfo != null ? wifiInfo.getBSSID() : null, bssid, true));
        $receiver.setOnClickListener(new C1117x2a90e739(this, ssid, bssid));
        WiFiDisplayPreference homeNetworkPreference = $receiver;
        PreferenceCategory preferenceCategory = this.homeNetworkCategory;
        if (preferenceCategory != null) {
            preferenceCategory.addPreference(homeNetworkPreference);
        }
    }

    private final AlertDialog createResetConfirmationDialog(String ssid, String bssid) {
        if (getActivity() == null) {
            return null;
        }
        Builder builder = new Builder(getActivity());
        builder.setMessage(C0676R.string.settings_locationBasedControl_homeWiFi_removeNetworkConfirmation_confirmationLabel).setPositiveButton(getString(C0676R.string.settings_locationBasedControl_homeWiFi_removeNetworkConfirmation_removeButton, new Object[]{ssid}), new HomeWifiPreferenceFragment$createResetConfirmationDialog$1(bssid, ssid)).setNegativeButton(C0676R.string.settings_locationBasedControl_homeWiFi_removeNetworkConfirmation_cancelButton, HomeWifiPreferenceFragment$createResetConfirmationDialog$2.INSTANCE);
        return builder.create();
    }

    public void onWifiConnected(@NotNull WifiInfo wifiInfo) {
        Intrinsics.checkParameterIsNotNull(wifiInfo, "wifiInfo");
        String ssid = wifiInfo.getSSID();
        Intrinsics.checkExpressionValueIsNotNull(ssid, "wifiInfo.ssid");
        if (!StringsKt__StringsJVMKt.isBlank(ssid)) {
            this.currentWifiInfo = wifiInfo;
            recreatePreferenceScreen();
        }
    }
}

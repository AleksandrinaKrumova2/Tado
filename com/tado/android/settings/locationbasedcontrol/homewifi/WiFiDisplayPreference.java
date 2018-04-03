package com.tado.android.settings.locationbasedcontrol.homewifi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.tado.C0676R;
import com.tado.android.utils.ResourceFactory;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/homewifi/WiFiDisplayPreference;", "Landroid/preference/Preference;", "context", "Landroid/content/Context;", "ssid", "", "bssid", "addRemoveEnum", "Lcom/tado/android/settings/locationbasedcontrol/homewifi/WiFiAddRemoveEnum;", "connected", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/tado/android/settings/locationbasedcontrol/homewifi/WiFiAddRemoveEnum;Z)V", "clickListener", "Landroid/view/View$OnClickListener;", "getWifiIcon", "Landroid/graphics/drawable/Drawable;", "onBindView", "", "view", "Landroid/view/View;", "setOnClickListener", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: WiFiDisplayPreference.kt */
public final class WiFiDisplayPreference extends Preference {
    private final WiFiAddRemoveEnum addRemoveEnum;
    private OnClickListener clickListener;

    public WiFiDisplayPreference(@Nullable Context context, @NotNull String ssid, @NotNull String bssid, @NotNull WiFiAddRemoveEnum addRemoveEnum, boolean connected) {
        Intrinsics.checkParameterIsNotNull(ssid, "ssid");
        Intrinsics.checkParameterIsNotNull(bssid, "bssid");
        Intrinsics.checkParameterIsNotNull(addRemoveEnum, "addRemoveEnum");
        super(context);
        this.addRemoveEnum = addRemoveEnum;
        setTitle(ssid);
        setSummary(bssid);
        setIcon(getWifiIcon(connected));
        setWidgetLayoutResource(C0676R.layout.wifi_display_preference);
    }

    private final Drawable getWifiIcon(boolean connected) {
        Drawable vectorSupportDrawable;
        switch (this.addRemoveEnum) {
            case Add:
                vectorSupportDrawable = ResourceFactory.getVectorSupportDrawable(getContext(), C0676R.drawable.ic_connected_wifi);
                Intrinsics.checkExpressionValueIsNotNull(vectorSupportDrawable, "ResourceFactory.getVecto…awable.ic_connected_wifi)");
                return vectorSupportDrawable;
            case Remove:
                vectorSupportDrawable = ResourceFactory.getHomeWifiDrawable(getContext(), connected);
                Intrinsics.checkExpressionValueIsNotNull(vectorSupportDrawable, "ResourceFactory.getHomeW…wable(context, connected)");
                return vectorSupportDrawable;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    protected void onBindView(@Nullable View view) {
        super.onBindView(view);
        if (view != null) {
            View it = view;
            Button button = (Button) view.findViewById(C0676R.id.button);
            if (Intrinsics.areEqual(this.addRemoveEnum, WiFiAddRemoveEnum.Add)) {
                button.setText(C0676R.string.settings_locationBasedControl_homeWiFi_addButton);
            } else {
                button.setText(C0676R.string.settings_locationBasedControl_homeWiFi_removeButton);
            }
            button.setOnClickListener(this.clickListener);
        }
    }

    public final void setOnClickListener(@NotNull OnClickListener clickListener) {
        Intrinsics.checkParameterIsNotNull(clickListener, "clickListener");
        this.clickListener = clickListener;
    }
}

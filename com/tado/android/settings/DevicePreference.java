package com.tado.android.settings;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.widget.ImageView;
import com.tado.C0676R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/tado/android/settings/DevicePreference;", "Landroid/support/v7/preference/Preference;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hasBadge", "", "getHasBadge", "()Z", "setHasBadge", "(Z)V", "onBindViewHolder", "", "holder", "Landroid/support/v7/preference/PreferenceViewHolder;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: DevicePreference.kt */
public final class DevicePreference extends Preference {
    private boolean hasBadge;

    public DevicePreference(@Nullable Context context) {
        super(context);
        setLayoutResource(C0676R.layout.preference_device);
    }

    public final boolean getHasBadge() {
        return this.hasBadge;
    }

    public final void setHasBadge(boolean <set-?>) {
        this.hasBadge = <set-?>;
    }

    public void onBindViewHolder(@NotNull PreferenceViewHolder holder) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        super.onBindViewHolder(holder);
        ImageView badge = (ImageView) holder.findViewById(C0676R.id.battery_indicator);
        if (this.hasBadge) {
            if (badge != null) {
                badge.setImageResource(C0676R.drawable.ic_low_battery_badge);
            }
        } else if (badge != null) {
            badge.setImageResource(0);
        }
    }
}

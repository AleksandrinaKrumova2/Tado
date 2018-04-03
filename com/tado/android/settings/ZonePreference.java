package com.tado.android.settings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.widget.TextView;
import com.tado.C0676R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/tado/android/settings/ZonePreference;", "Landroid/support/v7/preference/Preference;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hasBadge", "", "getHasBadge", "()Z", "setHasBadge", "(Z)V", "onBindViewHolder", "", "holder", "Landroid/support/v7/preference/PreferenceViewHolder;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: ZonePreference.kt */
public final class ZonePreference extends Preference {
    private boolean hasBadge;

    public ZonePreference(@Nullable Context context) {
        super(context);
    }

    public final boolean getHasBadge() {
        return this.hasBadge;
    }

    public final void setHasBadge(boolean <set-?>) {
        this.hasBadge = <set-?>;
    }

    public void onBindViewHolder(@Nullable PreferenceViewHolder holder) {
        TextView title;
        Drawable drawable;
        super.onBindViewHolder(holder);
        if (holder != null) {
            title = holder.findViewById(16908310);
        } else {
            title = null;
        }
        title = title;
        if (this.hasBadge) {
            drawable = ContextCompat.getDrawable(getContext(), C0676R.drawable.badge_dot);
        } else {
            drawable = null;
        }
        if (title != null) {
            title.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
        if (title != null) {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            Resources resources = context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            title.setCompoundDrawablePadding((int) (resources.getDisplayMetrics().density * ((float) 4)));
        }
    }
}

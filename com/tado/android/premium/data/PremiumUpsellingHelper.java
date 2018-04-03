package com.tado.android.premium.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.tado.android.mvp.model.LocalPersistenceHelper;
import com.tado.android.onboarding.OnboardingPageFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/tado/android/premium/data/PremiumUpsellingHelper;", "Lcom/tado/android/mvp/model/LocalPersistenceHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clear", "", "getString", "", "key", "defaultValue", "putString", "value", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PremiumUpsellingHelper.kt */
public final class PremiumUpsellingHelper implements LocalPersistenceHelper {
    @NotNull
    private final Context context;
    private final SharedPreferences preferences = this.context.getSharedPreferences("premium_upselling", 0);

    public PremiumUpsellingHelper(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    public void putString(@NotNull String key, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(key, OnboardingPageFragment.FEATURE_KEY);
        Intrinsics.checkParameterIsNotNull(value, Param.VALUE);
        this.preferences.edit().putString(key, value).apply();
    }

    @NotNull
    public String getString(@NotNull String key, @NotNull String defaultValue) {
        Intrinsics.checkParameterIsNotNull(key, OnboardingPageFragment.FEATURE_KEY);
        Intrinsics.checkParameterIsNotNull(defaultValue, "defaultValue");
        String string = this.preferences.getString(key, defaultValue);
        Intrinsics.checkExpressionValueIsNotNull(string, "preferences.getString(key, defaultValue)");
        return string;
    }

    public final void clear() {
        this.preferences.edit().clear().apply();
    }
}

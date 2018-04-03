package com.tado.android.settings.homedetails;

import android.support.v7.preference.Preference;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"}, d2 = {"com/tado/android/settings/homedetails/HomeDetailsPreferenceFragment$setHomeName$1", "Lcom/tado/android/settings/interfaces/GenericCallbackListener;", "Ljava/lang/Void;", "(Lcom/tado/android/settings/homedetails/HomeDetailsPreferenceFragment;Ljava/lang/String;)V", "onFailure", "", "onSuccess", "body", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeDetailsPreferenceFragment.kt */
public final class HomeDetailsPreferenceFragment$setHomeName$1 implements GenericCallbackListener<Void> {
    final /* synthetic */ String $newName;
    final /* synthetic */ HomeDetailsPreferenceFragment this$0;

    HomeDetailsPreferenceFragment$setHomeName$1(HomeDetailsPreferenceFragment $outer, String $captured_local_variable$1) {
        this.this$0 = $outer;
        this.$newName = $captured_local_variable$1;
    }

    public void onSuccess(@Nullable Void body) {
        Preference access$getHomeNamePreference$p = this.this$0.homeNamePreference;
        if (access$getHomeNamePreference$p == null) {
            Intrinsics.throwNpe();
        }
        access$getHomeNamePreference$p.setSummary((CharSequence) this.$newName);
        Preference access$getHomeNamePreference$p2 = this.this$0.homeNamePreference;
        if (access$getHomeNamePreference$p2 == null) {
            Intrinsics.throwNpe();
        }
        access$getHomeNamePreference$p2.setEnabled(true);
    }

    public void onFailure() {
        Preference access$getHomeNamePreference$p = this.this$0.homeNamePreference;
        if (access$getHomeNamePreference$p == null) {
            Intrinsics.throwNpe();
        }
        access$getHomeNamePreference$p.setEnabled(true);
    }
}

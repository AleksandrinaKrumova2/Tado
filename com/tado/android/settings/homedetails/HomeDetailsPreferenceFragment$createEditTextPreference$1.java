package com.tado.android.settings.homedetails;

import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "preference", "Landroid/support/v7/preference/Preference;", "kotlin.jvm.PlatformType", "newValue", "", "onPreferenceChange"}, k = 3, mv = {1, 1, 9})
/* compiled from: HomeDetailsPreferenceFragment.kt */
final class HomeDetailsPreferenceFragment$createEditTextPreference$1 implements OnPreferenceChangeListener {
    final /* synthetic */ HomeDetailsPreferenceFragment this$0;

    HomeDetailsPreferenceFragment$createEditTextPreference$1(HomeDetailsPreferenceFragment homeDetailsPreferenceFragment) {
        this.this$0 = homeDetailsPreferenceFragment;
    }

    public final boolean onPreferenceChange(Preference preference, Object newValue) {
        CharSequence $receiver$iv$iv = newValue.toString();
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $receiver$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv;
            boolean match$iv$iv;
            if (startFound$iv$iv) {
                index$iv$iv = endIndex$iv$iv;
            } else {
                index$iv$iv = startIndex$iv$iv;
            }
            if ($receiver$iv$iv.charAt(index$iv$iv) <= ' ') {
                match$iv$iv = true;
            } else {
                match$iv$iv = false;
            }
            if (startFound$iv$iv) {
                if (!match$iv$iv) {
                    break;
                }
                endIndex$iv$iv--;
            } else if (match$iv$iv) {
                startIndex$iv$iv++;
            } else {
                startFound$iv$iv = true;
            }
        }
        String trimmedInput = $receiver$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
        int min = Math.min(trimmedInput.length(), 40);
        if (trimmedInput == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String value = trimmedInput.substring(0, min);
        Intrinsics.checkExpressionValueIsNotNull(value, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if ((((CharSequence) value).length() == 0 ? 1 : null) != null) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(preference, "preference");
        preference.setEnabled(false);
        this.this$0.setHomeName(value);
        return true;
    }
}

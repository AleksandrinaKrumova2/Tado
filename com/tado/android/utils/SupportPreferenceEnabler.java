package com.tado.android.utils;

import android.support.v7.preference.Preference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class SupportPreferenceEnabler {
    private List<WeakReference<Preference>> preferenceList = new LinkedList();

    public SupportPreferenceEnabler(Preference preference) {
        addPreference(preference);
    }

    public void addPreference(Preference preference) {
        this.preferenceList.add(new WeakReference(preference));
    }

    private void enablePreferences(boolean enable) {
        for (WeakReference<Preference> preferenceWeakReference : this.preferenceList) {
            Preference preference = (Preference) preferenceWeakReference.get();
            if (preference != null) {
                preference.setEnabled(enable);
            }
        }
    }

    public void enablePreference() {
        enablePreferences(true);
    }

    public void disablePreferences() {
        enablePreferences(false);
    }
}

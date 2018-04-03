package com.tado.android.settings.manualcontrolsettings;

import com.tado.android.rest.model.OverlayTerminationCondition;

/* compiled from: ManualControlPreferenceFragment */
class OverlayTerminationConditionAdapter {
    OverlayTerminationCondition[] terminationConditions;

    public OverlayTerminationConditionAdapter(OverlayTerminationCondition[] terminationConditions) {
        this.terminationConditions = terminationConditions;
    }

    public CharSequence[] toDescriptionsCharSequence() {
        CharSequence[] descriptions = new CharSequence[this.terminationConditions.length];
        int j = this.terminationConditions.length;
        for (int i = 0; i < j; i++) {
            descriptions[i] = this.terminationConditions[i].getTypeValue();
        }
        return descriptions;
    }

    public CharSequence[] toValuesCharSequence() {
        CharSequence[] types = new CharSequence[this.terminationConditions.length];
        int j = this.terminationConditions.length;
        for (int i = 0; i < j; i++) {
            types[i] = this.terminationConditions[i].getType();
        }
        return types;
    }

    public int indexOf(OverlayTerminationCondition overlayTerminationCondition) {
        for (int i = 0; i < this.terminationConditions.length; i++) {
            if (this.terminationConditions[i].getType().equals(overlayTerminationCondition.getType())) {
                return i;
            }
        }
        return 0;
    }
}

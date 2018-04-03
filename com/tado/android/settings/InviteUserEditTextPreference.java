package com.tado.android.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

public class InviteUserEditTextPreference extends EditTextPreference {
    public InviteUserEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public InviteUserEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InviteUserEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InviteUserEditTextPreference(Context context) {
        super(context);
    }

    protected void showDialog(Bundle state) {
        super.showDialog(state);
        ((AlertDialog) getDialog()).getButton(-1).setEnabled(false);
    }
}

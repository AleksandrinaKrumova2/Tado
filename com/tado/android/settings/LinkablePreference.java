package com.tado.android.settings;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

public class LinkablePreference extends Preference {
    public LinkablePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public LinkablePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinkablePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinkablePreference(Context context) {
        super(context);
    }

    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        try {
            ((TextView) holder.findViewById(16908304)).setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
        }
    }
}

package com.tado.android.settings;

import android.content.Context;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;

public class EarlyStartSwitchPreference extends SwitchPreferenceCompat {
    @BindView(16908304)
    TextView summaryTextView;

    public EarlyStartSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public EarlyStartSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EarlyStartSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EarlyStartSwitchPreference(Context context) {
        super(context);
    }

    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        TextView tv = (TextView) holder.findViewById(16908304);
        if (tv != null) {
            tv.setMinLines(4);
        }
    }
}

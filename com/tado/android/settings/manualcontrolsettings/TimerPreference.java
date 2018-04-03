package com.tado.android.settings.manualcontrolsettings;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.views.TimerView;

public class TimerPreference extends DialogPreference {
    int seconds;
    private TimerView timerView;

    public TimerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public TimerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TimerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimerPreference(Context context) {
        super(context, null);
        init();
    }

    private void init() {
        setDialogLayoutResource(C0676R.layout.dialog_timepicker_view);
    }

    public void setDefaultValue(Object defaultValue) {
        super.setDefaultValue(defaultValue);
        if (defaultValue instanceof Integer) {
            this.seconds = ((Integer) defaultValue).intValue();
            if (this.timerView != null) {
                this.timerView.setStartingSeconds(this.seconds);
            }
        }
    }

    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.timerView = (TimerView) view.findViewById(C0676R.id.timer);
        this.timerView.setStartingSeconds(this.seconds);
    }

    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        int newSeconds = this.timerView.getCurrentSeconds();
        if (positiveResult && callChangeListener(Integer.valueOf(newSeconds))) {
            boolean wasBlocking = shouldDisableDependents();
            persistInt(newSeconds);
            this.seconds = newSeconds;
            boolean isBlocking = shouldDisableDependents();
            if (isBlocking != wasBlocking) {
                notifyDependencyChange(isBlocking);
            }
        }
    }
}

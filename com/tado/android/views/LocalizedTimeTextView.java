package com.tado.android.views;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.TextView;
import com.tado.android.utils.TimeUtils;

public class LocalizedTimeTextView extends TextView {
    public LocalizedTimeTextView(Context context) {
        super(context);
    }

    public LocalizedTimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LocalizedTimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LocalizedTimeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setLocalisedTime(int minutesFromMidnight) {
        setText(TimeUtils.formatTimeToAmPm(TimeUtils.getHours(minutesFromMidnight * 60), TimeUtils.getMinutesRemainder(minutesFromMidnight * 60), DateFormat.is24HourFormat(getContext())));
    }
}

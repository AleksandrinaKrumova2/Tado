package com.tado.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.tado.C0676R;

public class TimeTextView extends TextView {
    private int digitStyle = C0676R.style.controlPanelDigits;
    private String hourSeparator = "h";
    private String minuteSeparator = "m";
    private int numSpaces = 5;
    private int separatorStyle = C0676R.style.controlPanelSeparators;

    public TimeTextView(Context context) {
        super(context);
        init(null);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, C0676R.styleable.TimeTextView, 0, 0);
            try {
                this.hourSeparator = a.getString(1);
                this.minuteSeparator = a.getString(2);
                this.numSpaces = a.getInt(3, 5);
                this.separatorStyle = a.getResourceId(4, C0676R.style.controlPanelSeparators);
                this.digitStyle = a.getResourceId(0, C0676R.style.controlPanelDigits);
            } finally {
                a.recycle();
            }
        } else {
            this.hourSeparator = this.hourSeparator == null ? "h" : this.hourSeparator;
            this.minuteSeparator = this.minuteSeparator == null ? "m" : this.minuteSeparator;
            this.digitStyle = this.digitStyle == 0 ? C0676R.style.controlPanelDigits : this.digitStyle;
            this.separatorStyle = this.separatorStyle == 0 ? C0676R.style.controlPanelSeparators : this.separatorStyle;
            this.numSpaces = 5;
        }
    }

    public void setText(CharSequence text, BufferType type) {
        if (this.hourSeparator == null) {
            init(null);
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        CharSequence hours = text.subSequence(0, 2);
        CharSequence minutes = text.subSequence(2, 4);
        builder.append(formatString(hours.toString(), this.digitStyle));
        builder.append(formatString(this.hourSeparator, this.separatorStyle));
        for (int i = 0; i < this.numSpaces; i++) {
            builder.append(" ");
        }
        builder.append(formatString(minutes.toString(), this.digitStyle));
        builder.append(formatString(this.minuteSeparator, this.separatorStyle));
        super.setText(builder.subSequence(0, builder.length()), type);
    }

    private CharSequence formatString(String text, int styleid) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new TextAppearanceSpan(getContext(), styleid), 0, text.length(), 0);
        return spannableString;
    }
}

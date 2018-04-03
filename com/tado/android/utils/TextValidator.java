package com.tado.android.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    public abstract void validate(TextView textView, String str);

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public final void afterTextChanged(Editable s) {
        validate(this.textView, this.textView.getText().toString());
    }

    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public final void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}

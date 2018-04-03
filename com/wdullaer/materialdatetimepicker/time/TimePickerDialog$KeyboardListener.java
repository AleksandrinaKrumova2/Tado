package com.wdullaer.materialdatetimepicker.time;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class TimePickerDialog$KeyboardListener implements OnKeyListener {
    final /* synthetic */ TimePickerDialog this$0;

    private TimePickerDialog$KeyboardListener(TimePickerDialog timePickerDialog) {
        this.this$0 = timePickerDialog;
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 1) {
            return TimePickerDialog.access$600(this.this$0, keyCode);
        }
        return false;
    }
}

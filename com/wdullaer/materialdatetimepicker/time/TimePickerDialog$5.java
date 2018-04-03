package com.wdullaer.materialdatetimepicker.time;

import android.view.View;
import android.view.View.OnClickListener;

class TimePickerDialog$5 implements OnClickListener {
    final /* synthetic */ TimePickerDialog this$0;

    TimePickerDialog$5(TimePickerDialog this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        this.this$0.tryVibrate();
        if (this.this$0.getDialog() != null) {
            this.this$0.getDialog().cancel();
        }
    }
}

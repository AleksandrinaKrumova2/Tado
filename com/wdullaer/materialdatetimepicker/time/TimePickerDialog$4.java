package com.wdullaer.materialdatetimepicker.time;

import android.view.View;
import android.view.View.OnClickListener;

class TimePickerDialog$4 implements OnClickListener {
    final /* synthetic */ TimePickerDialog this$0;

    TimePickerDialog$4(TimePickerDialog this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        if (TimePickerDialog.access$200(this.this$0) && TimePickerDialog.access$300(this.this$0)) {
            TimePickerDialog.access$400(this.this$0, false);
        } else {
            this.this$0.tryVibrate();
        }
        this.this$0.notifyOnDateListener();
        this.this$0.dismiss();
    }
}

package com.wdullaer.materialdatetimepicker.time;

import android.view.View;
import android.view.View.OnClickListener;

class TimePickerDialog$1 implements OnClickListener {
    final /* synthetic */ TimePickerDialog this$0;

    TimePickerDialog$1(TimePickerDialog this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        TimePickerDialog.access$100(this.this$0, 0, true, false, true);
        this.this$0.tryVibrate();
    }
}

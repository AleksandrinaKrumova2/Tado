package com.wdullaer.materialdatetimepicker.time;

import android.view.View;
import android.view.View.OnClickListener;

class TimePickerDialog$6 implements OnClickListener {
    final /* synthetic */ TimePickerDialog this$0;

    TimePickerDialog$6(TimePickerDialog this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        if (!this.this$0.isAmDisabled() && !this.this$0.isPmDisabled()) {
            this.this$0.tryVibrate();
            int amOrPm = TimePickerDialog.access$500(this.this$0).getIsCurrentlyAmOrPm();
            if (amOrPm == 0) {
                amOrPm = 1;
            } else if (amOrPm == 1) {
                amOrPm = 0;
            }
            TimePickerDialog.access$500(this.this$0).setAmOrPm(amOrPm);
        }
    }
}

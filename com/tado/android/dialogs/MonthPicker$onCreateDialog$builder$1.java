package com.tado.android.dialogs;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.tado.android.dialogs.MonthPicker.OnDateSelected;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 9})
/* compiled from: MonthPicker.kt */
final class MonthPicker$onCreateDialog$builder$1 implements OnClickListener {
    final /* synthetic */ MonthPicker this$0;

    MonthPicker$onCreateDialog$builder$1(MonthPicker monthPicker) {
        this.this$0 = monthPicker;
    }

    public final void onClick(DialogInterface $noName_0, int $noName_1) {
        OnDateSelected listener = this.this$0.getListener();
        if (listener != null) {
            listener.onDateSelected(this.this$0.displayedYear, this.this$0.displayedMonth);
        }
    }
}

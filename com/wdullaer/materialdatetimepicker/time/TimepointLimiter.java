package com.wdullaer.materialdatetimepicker.time;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.wdullaer.materialdatetimepicker.time.Timepoint.TYPE;

public interface TimepointLimiter extends Parcelable {
    boolean isAmDisabled();

    boolean isOutOfRange(@Nullable Timepoint timepoint, int i, @NonNull TYPE type);

    boolean isPmDisabled();

    @NonNull
    Timepoint roundToNearest(@NonNull Timepoint timepoint, @Nullable TYPE type, @NonNull TYPE type2);
}

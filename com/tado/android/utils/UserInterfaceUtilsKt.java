package com.tado.android.utils;

import android.text.TextUtils;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, d2 = {"setTextOrHide", "", "Landroid/widget/TextView;", "content", "", "4.9.3-1500409030_tadoRelease"}, k = 2, mv = {1, 1, 9})
/* compiled from: UserInterfaceUtils.kt */
public final class UserInterfaceUtilsKt {
    public static final void setTextOrHide(@NotNull TextView $receiver, @Nullable String content) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (TextUtils.isEmpty(content)) {
            $receiver.setVisibility(8);
        } else {
            $receiver.setText(content);
        }
    }
}

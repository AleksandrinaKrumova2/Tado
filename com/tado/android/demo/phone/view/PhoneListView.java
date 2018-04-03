package com.tado.android.demo.phone.view;

import android.content.res.AssetManager;
import com.tado.android.demo.phone.model.AbstractPhoneNumberListItem;
import com.tado.android.mvp.common.BaseView;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&¨\u0006\f"}, d2 = {"Lcom/tado/android/demo/phone/view/PhoneListView;", "Lcom/tado/android/mvp/common/BaseView;", "assets", "Landroid/content/res/AssetManager;", "dialNumber", "", "phoneNumber", "", "preparePhoneList", "items", "", "Lcom/tado/android/demo/phone/model/AbstractPhoneNumberListItem;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneListView.kt */
public interface PhoneListView extends BaseView {
    @Nullable
    AssetManager assets();

    void dialNumber(@NotNull String str);

    void preparePhoneList(@NotNull List<? extends AbstractPhoneNumberListItem> list);
}

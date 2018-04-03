package com.tado.android.mvp.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/tado/android/mvp/model/LocalPersistenceHelper;", "", "getString", "", "key", "defaultValue", "putString", "", "value", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: LocalPersistenceHelper.kt */
public interface LocalPersistenceHelper {
    @NotNull
    String getString(@NotNull String str, @NotNull String str2);

    void putString(@NotNull String str, @NotNull String str2);
}

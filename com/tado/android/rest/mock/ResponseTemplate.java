package com.tado.android.rest.mock;

import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/tado/android/rest/mock/ResponseTemplate;", "", "status", "", "body", "(ILjava/lang/Object;)V", "getBody", "()Ljava/lang/Object;", "bodyAsJsonString", "", "getBodyAsJsonString", "()Ljava/lang/String;", "getStatus", "()I", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MockDataModels.kt */
public final class ResponseTemplate {
    @NotNull
    private final Object body;
    private final int status;

    public ResponseTemplate(int status, @NotNull Object body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        this.status = status;
        this.body = body;
    }

    @NotNull
    public final Object getBody() {
        return this.body;
    }

    public final int getStatus() {
        return this.status;
    }

    @Nullable
    public final String getBodyAsJsonString() {
        return new Gson().toJson(this.body);
    }
}

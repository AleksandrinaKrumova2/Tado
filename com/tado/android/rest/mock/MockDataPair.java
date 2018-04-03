package com.tado.android.rest.mock;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/tado/android/rest/mock/MockDataPair;", "", "request", "Lcom/tado/android/rest/mock/RequestTemplate;", "response", "Lcom/tado/android/rest/mock/ResponseTemplate;", "(Lcom/tado/android/rest/mock/RequestTemplate;Lcom/tado/android/rest/mock/ResponseTemplate;)V", "getRequest", "()Lcom/tado/android/rest/mock/RequestTemplate;", "getResponse", "()Lcom/tado/android/rest/mock/ResponseTemplate;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MockDataModels.kt */
public final class MockDataPair {
    @NotNull
    private final RequestTemplate request;
    @NotNull
    private final ResponseTemplate response;

    @NotNull
    public static /* bridge */ /* synthetic */ MockDataPair copy$default(MockDataPair mockDataPair, RequestTemplate requestTemplate, ResponseTemplate responseTemplate, int i, Object obj) {
        if ((i & 1) != 0) {
            requestTemplate = mockDataPair.request;
        }
        if ((i & 2) != 0) {
            responseTemplate = mockDataPair.response;
        }
        return mockDataPair.copy(requestTemplate, responseTemplate);
    }

    @NotNull
    public final RequestTemplate component1() {
        return this.request;
    }

    @NotNull
    public final ResponseTemplate component2() {
        return this.response;
    }

    @NotNull
    public final MockDataPair copy(@NotNull RequestTemplate request, @NotNull ResponseTemplate response) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(response, "response");
        return new MockDataPair(request, response);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x001c;
    L_0x0002:
        r0 = r3 instanceof com.tado.android.rest.mock.MockDataPair;
        if (r0 == 0) goto L_0x001e;
    L_0x0006:
        r3 = (com.tado.android.rest.mock.MockDataPair) r3;
        r0 = r2.request;
        r1 = r3.request;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x0012:
        r0 = r2.response;
        r1 = r3.response;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x001e;
    L_0x001c:
        r0 = 1;
    L_0x001d:
        return r0;
    L_0x001e:
        r0 = 0;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.mock.MockDataPair.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        RequestTemplate requestTemplate = this.request;
        int hashCode = (requestTemplate != null ? requestTemplate.hashCode() : 0) * 31;
        ResponseTemplate responseTemplate = this.response;
        if (responseTemplate != null) {
            i = responseTemplate.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "MockDataPair(request=" + this.request + ", response=" + this.response + ")";
    }

    public MockDataPair(@NotNull RequestTemplate request, @NotNull ResponseTemplate response) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.request = request;
        this.response = response;
    }

    @NotNull
    public final RequestTemplate getRequest() {
        return this.request;
    }

    @NotNull
    public final ResponseTemplate getResponse() {
        return this.response;
    }
}

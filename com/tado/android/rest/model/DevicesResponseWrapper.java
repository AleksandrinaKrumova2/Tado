package com.tado.android.rest.model;

import android.support.v4.app.NotificationCompat;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB-\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u0016J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0007H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/tado/android/rest/model/DevicesResponseWrapper;", "Lcom/tado/android/rest/model/ResponseWrapper;", "call", "Lretrofit2/Call;", "", "Lcom/tado/android/rest/model/installation/GenericHardwareDevice;", "response", "Lretrofit2/Response;", "(Lretrofit2/Call;Lretrofit2/Response;)V", "getCall", "getResponse", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: DevicesResponseWrapper.kt */
public final class DevicesResponseWrapper implements ResponseWrapper {
    public static final Companion Companion = new Companion();
    private final Call<List<GenericHardwareDevice>> call;
    private final Response<List<GenericHardwareDevice>> response;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¨\u0006\u0005"}, d2 = {"Lcom/tado/android/rest/model/DevicesResponseWrapper$Companion;", "", "()V", "callable", "Ljava/util/concurrent/Callable;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: DevicesResponseWrapper.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Callable<Object> callable() {
            return DevicesResponseWrapper$Companion$callable$1.INSTANCE;
        }
    }

    public DevicesResponseWrapper(@NotNull Call<List<GenericHardwareDevice>> call, @NotNull Response<List<GenericHardwareDevice>> response) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.call = call;
        this.response = response;
    }

    @NotNull
    public Call<List<GenericHardwareDevice>> getCall() {
        return this.call;
    }

    @NotNull
    public Response<List<GenericHardwareDevice>> getResponse() {
        return this.response;
    }
}

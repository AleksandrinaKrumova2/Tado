package com.tado.android.rest.model;

import android.support.v4.app.NotificationCompat;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/tado/android/rest/model/DevicesResponseWrapper;", "call"}, k = 3, mv = {1, 1, 9})
/* compiled from: DevicesResponseWrapper.kt */
final class DevicesResponseWrapper$Companion$callable$1<V> implements Callable<Object> {
    public static final DevicesResponseWrapper$Companion$callable$1 INSTANCE = new DevicesResponseWrapper$Companion$callable$1();

    DevicesResponseWrapper$Companion$callable$1() {
    }

    @NotNull
    public final DevicesResponseWrapper call() {
        Call call = RestServiceGenerator.getTadoRestService().getDevices(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap());
        Intrinsics.checkExpressionValueIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Response execute = call.execute();
        Intrinsics.checkExpressionValueIsNotNull(execute, "call.execute()");
        return new DevicesResponseWrapper(call, execute);
    }
}

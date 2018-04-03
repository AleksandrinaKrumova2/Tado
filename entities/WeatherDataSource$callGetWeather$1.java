package com.tado.android.entities;

import android.support.v4.app.NotificationCompat;
import com.tado.android.rest.callback.TadoCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016¨\u0006\n"}, d2 = {"com/tado/android/entities/WeatherDataSource$callGetWeather$1", "Lcom/tado/android/rest/callback/TadoCallback;", "Lcom/tado/android/entities/Weather;", "()V", "onResponse", "", "call", "Lretrofit2/Call;", "response", "Lretrofit2/Response;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: WeatherDataSource.kt */
public final class WeatherDataSource$callGetWeather$1 extends TadoCallback<Weather> {
    WeatherDataSource$callGetWeather$1() {
    }

    public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(response, "response");
        super.onResponse(call, response);
        if (response.isSuccessful()) {
            WeatherDataSource.weather = (Weather) response.body();
            WeatherDataSource.INSTANCE.setChanged();
            WeatherDataSource.INSTANCE.notifyObservers();
        }
    }
}

package com.tado.android.entities;

import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import java.util.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bR(\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/tado/android/entities/WeatherDataSource;", "Ljava/util/Observable;", "()V", "<set-?>", "Lcom/tado/android/entities/Weather;", "weather", "getWeather", "()Lcom/tado/android/entities/Weather;", "setWeather", "(Lcom/tado/android/entities/Weather;)V", "callGetWeather", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: WeatherDataSource.kt */
public final class WeatherDataSource extends Observable {
    public static final WeatherDataSource INSTANCE = new WeatherDataSource();
    @Nullable
    private static Weather weather;

    private WeatherDataSource() {
    }

    private final void setWeather(Weather <set-?>) {
        weather = <set-?>;
    }

    @Nullable
    public final Weather getWeather() {
        return weather;
    }

    public final void callGetWeather() {
        RestServiceGenerator.getTadoRestService().getWeather(UserConfig.getHomeId(), RestServiceGenerator.getCredentialsMap()).enqueue(new WeatherDataSource$callGetWeather$1());
    }
}

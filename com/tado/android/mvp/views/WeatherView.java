package com.tado.android.mvp.views;

import com.tado.android.mvp.common.BaseView;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0017\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/tado/android/mvp/views/WeatherView;", "Lcom/tado/android/mvp/common/BaseView;", "setWeatherIcon", "", "weatherResource", "", "setWeatherTemperature", "format", "(Ljava/lang/Integer;)V", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: WeatherView.kt */
public interface WeatherView extends BaseView {
    void setWeatherIcon(int i);

    void setWeatherTemperature(@Nullable Integer num);
}

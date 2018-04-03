package com.tado.android.mvp.presenters;

import com.tado.android.entities.WeatherDataSource;
import java.util.Observable;
import java.util.Observer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/util/Observable;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "update"}, k = 3, mv = {1, 1, 9})
/* compiled from: WeatherPresenter.kt */
final class WeatherPresenter$init$1 implements Observer {
    final /* synthetic */ WeatherPresenter this$0;

    WeatherPresenter$init$1(WeatherPresenter weatherPresenter) {
        this.this$0 = weatherPresenter;
    }

    public final void update(Observable $noName_0, Object $noName_1) {
        this.this$0.updateWeather(WeatherDataSource.INSTANCE.getWeather());
    }
}

package com.tado.android.mvp.presenters;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tado.android.entities.WeatherDataSource;
import com.tado.android.mvp.common.BasePresenter;
import com.tado.android.mvp.views.WeatherView;
import com.tado.android.utils.Snitcher;
import java.util.ArrayList;
import java.util.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u001aB\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u0014\u001a\u00020\u000eJ\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0011H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000bj\b\u0012\u0004\u0012\u00020\u0002`\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/tado/android/mvp/presenters/WeatherPresenter;", "Lcom/tado/android/mvp/common/BasePresenter;", "Lcom/tado/android/mvp/views/WeatherView;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "observer", "Ljava/util/Observer;", "views", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "bindView", "", "view", "describeContents", "", "init", "unbindView", "unregister", "updateWeather", "weather", "Lcom/tado/android/entities/Weather;", "writeToParcel", "flags", "CREATOR", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: WeatherPresenter.kt */
public final class WeatherPresenter implements BasePresenter<WeatherView>, Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    private Observer observer;
    private ArrayList<WeatherView> views;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/tado/android/mvp/presenters/WeatherPresenter$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/tado/android/mvp/presenters/WeatherPresenter;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/tado/android/mvp/presenters/WeatherPresenter;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: WeatherPresenter.kt */
    public static final class CREATOR implements Creator<WeatherPresenter> {
        private CREATOR() {
        }

        @NotNull
        public WeatherPresenter createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new WeatherPresenter(parcel);
        }

        @NotNull
        public WeatherPresenter[] newArray(int size) {
            return new WeatherPresenter[size];
        }
    }

    private final void updateWeather(com.tado.android.entities.Weather r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.mvp.presenters.WeatherPresenter.updateWeather(com.tado.android.entities.Weather):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 7 more
*/
        /*
        r0 = this;
        r4 = 0;
        r3 = com.tado.android.utils.Snitcher.start();
        r5 = 3;
        r6 = "weather";
        r7 = "updating weather data";
        r8 = 0;
        r8 = new java.lang.Object[r8];
        r3.log(r5, r6, r7, r8);
        if (r10 == 0) goto L_0x0055;
    L_0x0014:
        r2 = r10;
        r0 = r9.views;
        r0 = (java.lang.Iterable) r0;
        r5 = r0.iterator();
        r3 = r5.hasNext();
        if (r3 == 0) goto L_0x0052;
    L_0x0023:
        r1 = r5.next();
        r2 = r1;
        r2 = (com.tado.android.mvp.views.WeatherView) r2;
        r3 = r10.getStateTemperature();
        if (r3 == 0) goto L_0x004e;
        r3 = r3.getTemperature();
        r3 = java.lang.Integer.valueOf(r3);
        r2.setWeatherTemperature(r3);
        r3 = r10.getWeatherState();
        if (r3 == 0) goto L_0x0050;
        r3 = r3.getValue();
        r3 = com.tado.android.utils.ResourceFactory.getWeatherResource(r3);
        r2.setWeatherIcon(r3);
        goto L_0x001d;
        r3 = r4;
        goto L_0x0038;
        r3 = r4;
        goto L_0x0045;
    L_0x0055:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.mvp.presenters.WeatherPresenter.updateWeather(com.tado.android.entities.Weather):void");
    }

    public WeatherPresenter() {
        this.views = new ArrayList();
    }

    public WeatherPresenter(@NotNull Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        this();
    }

    public void bindView(@Nullable WeatherView view) {
        if (view != null) {
            WeatherView it = view;
            this.views.add(view);
        }
        init();
    }

    public void unbindView() {
    }

    public final void unbindView(@Nullable WeatherView view) {
        if (view != null) {
            WeatherView it = view;
            this.views.remove(view);
        }
    }

    public final void unregister() {
        this.views.clear();
        WeatherDataSource.INSTANCE.deleteObserver(this.observer);
    }

    private final void init() {
        if (this.observer == null) {
            this.observer = new WeatherPresenter$init$1(this);
        }
        Snitcher.start().log(3, "weather", "adding weather observer", new Object[0]);
        WeatherDataSource.INSTANCE.addObserver(this.observer);
        updateWeather(WeatherDataSource.INSTANCE.getWeather());
    }

    public void writeToParcel(@NotNull Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
    }

    public int describeContents() {
        return 0;
    }
}

package com.tado.android.controllers;

import com.tado.android.utils.NetworkTimer;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/tado/android/controllers/TimersManager;", "", "()V", "timers", "", "Lcom/tado/android/utils/NetworkTimer;", "getTimers", "()Ljava/util/List;", "startTimers", "", "stopTimers", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TimersManager.kt */
public final class TimersManager {
    public static final TimersManager INSTANCE = new TimersManager();
    @NotNull
    private static final List<NetworkTimer> timers = CollectionsKt__CollectionsKt.mutableListOf(new HvacTimer(), new MobileDevicesTimer(), new WeatherTimer(), new HomeStateTimer());

    public final void startTimers() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.controllers.TimersManager.startTimers():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 5 more
*/
        /*
        r0 = this;
        r0 = timers;
        r0 = (java.lang.Iterable) r0;
        r3 = r0.iterator();
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x001a;
    L_0x000e:
        r1 = r3.next();
        r2 = r1;
        r2 = (com.tado.android.utils.NetworkTimer) r2;
        r2.start();
        goto L_0x0008;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.controllers.TimersManager.startTimers():void");
    }

    public final void stopTimers() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.controllers.TimersManager.stopTimers():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.addJump(MethodNode.java:370)
	at jadx.core.dex.nodes.MethodNode.initJumps(MethodNode.java:356)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:106)
	... 5 more
*/
        /*
        r0 = this;
        r0 = timers;
        r0 = (java.lang.Iterable) r0;
        r3 = r0.iterator();
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x001a;
    L_0x000e:
        r1 = r3.next();
        r2 = r1;
        r2 = (com.tado.android.utils.NetworkTimer) r2;
        r2.stop();
        goto L_0x0008;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.controllers.TimersManager.stopTimers():void");
    }

    private TimersManager() {
    }

    @NotNull
    public final List<NetworkTimer> getTimers() {
        return timers;
    }
}

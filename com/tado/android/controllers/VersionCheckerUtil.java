package com.tado.android.controllers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006¨\u0006\u000e"}, d2 = {"Lcom/tado/android/controllers/VersionCheckerUtil;", "", "()V", "checkVersion", "", "version", "", "lastVersion", "listener", "Lcom/tado/android/controllers/OnVersionResult;", "isMayorUpdate", "", "oldVersion", "newVersion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: VersionCheckerUtil.kt */
public final class VersionCheckerUtil {
    public final boolean isMayorUpdate(@org.jetbrains.annotations.NotNull java.lang.String r1, @org.jetbrains.annotations.NotNull java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.controllers.VersionCheckerUtil.isMayorUpdate(java.lang.String, java.lang.String):boolean
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
        r10 = "oldVersion";
        r0 = r16;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r10);
        r10 = "newVersion";
        r0 = r17;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r10);
        r16 = (java.lang.CharSequence) r16;	 Catch:{ Exception -> 0x009b }
        r10 = 0;	 Catch:{ Exception -> 0x009b }
        r11 = 3;	 Catch:{ Exception -> 0x009b }
        r12 = 1;	 Catch:{ Exception -> 0x009b }
        r12 = new java.lang.String[r12];	 Catch:{ Exception -> 0x009b }
        r13 = 0;	 Catch:{ Exception -> 0x009b }
        r14 = ".";	 Catch:{ Exception -> 0x009b }
        r12[r13] = r14;	 Catch:{ Exception -> 0x009b }
        r0 = r16;	 Catch:{ Exception -> 0x009b }
        r9 = kotlin.text.StringsKt__StringsKt.split(r0, r12, r10, r11);	 Catch:{ Exception -> 0x009b }
        r10 = 0;	 Catch:{ Exception -> 0x009b }
        r11 = new kotlin.ranges.IntRange;	 Catch:{ Exception -> 0x009b }
        r12 = 1;	 Catch:{ Exception -> 0x009b }
        r13 = r9.size();	 Catch:{ Exception -> 0x009b }
        r13 = r13 + -1;	 Catch:{ Exception -> 0x009b }
        r12 = java.lang.Math.min(r12, r13);	 Catch:{ Exception -> 0x009b }
        r11.<init>(r10, r12);	 Catch:{ Exception -> 0x009b }
        r8 = kotlin.collections.CollectionsKt___CollectionsKt.slice(r9, r11);	 Catch:{ Exception -> 0x009b }
        r17 = (java.lang.CharSequence) r17;	 Catch:{ Exception -> 0x009b }
        r10 = 0;	 Catch:{ Exception -> 0x009b }
        r11 = 3;	 Catch:{ Exception -> 0x009b }
        r12 = 1;	 Catch:{ Exception -> 0x009b }
        r12 = new java.lang.String[r12];	 Catch:{ Exception -> 0x009b }
        r13 = 0;	 Catch:{ Exception -> 0x009b }
        r14 = ".";	 Catch:{ Exception -> 0x009b }
        r12[r13] = r14;	 Catch:{ Exception -> 0x009b }
        r0 = r17;	 Catch:{ Exception -> 0x009b }
        r6 = kotlin.text.StringsKt__StringsKt.split(r0, r12, r10, r11);	 Catch:{ Exception -> 0x009b }
        r10 = 0;	 Catch:{ Exception -> 0x009b }
        r11 = new kotlin.ranges.IntRange;	 Catch:{ Exception -> 0x009b }
        r12 = 1;	 Catch:{ Exception -> 0x009b }
        r13 = r6.size();	 Catch:{ Exception -> 0x009b }
        r13 = r13 + -1;	 Catch:{ Exception -> 0x009b }
        r12 = java.lang.Math.min(r12, r13);	 Catch:{ Exception -> 0x009b }
        r11.<init>(r10, r12);	 Catch:{ Exception -> 0x009b }
        r5 = kotlin.collections.CollectionsKt___CollectionsKt.slice(r6, r11);	 Catch:{ Exception -> 0x009b }
        r5 = (java.lang.Iterable) r5;	 Catch:{ Exception -> 0x009b }
        r8 = (java.lang.Iterable) r8;	 Catch:{ Exception -> 0x009b }
        r2 = kotlin.collections.CollectionsKt___CollectionsKt.zip(r5, r8);	 Catch:{ Exception -> 0x009b }
        r2 = (java.lang.Iterable) r2;	 Catch:{ Exception -> 0x009b }
        r10 = r2.iterator();	 Catch:{ Exception -> 0x009b }
        r11 = r10.hasNext();	 Catch:{ Exception -> 0x009b }
        if (r11 == 0) goto L_0x009c;	 Catch:{ Exception -> 0x009b }
    L_0x0075:
        r3 = r10.next();	 Catch:{ Exception -> 0x009b }
        r0 = r3;	 Catch:{ Exception -> 0x009b }
        r0 = (kotlin.Pair) r0;	 Catch:{ Exception -> 0x009b }
        r1 = r0;	 Catch:{ Exception -> 0x009b }
        r4 = r1.component1();	 Catch:{ Exception -> 0x009b }
        r4 = (java.lang.String) r4;	 Catch:{ Exception -> 0x009b }
        r7 = r1.component2();	 Catch:{ Exception -> 0x009b }
        r7 = (java.lang.String) r7;	 Catch:{ Exception -> 0x009b }
        r11 = r7.compareTo(r4);	 Catch:{ Exception -> 0x009b }
        if (r11 >= 0) goto L_0x0091;	 Catch:{ Exception -> 0x009b }
    L_0x008f:
        r10 = 1;	 Catch:{ Exception -> 0x009b }
        return r10;	 Catch:{ Exception -> 0x009b }
    L_0x0091:
        r11 = r7.compareTo(r4);	 Catch:{ Exception -> 0x009b }
        if (r11 <= 0) goto L_0x0099;
    L_0x0097:
        r10 = 0;
        goto L_0x0090;
        goto L_0x006f;
    L_0x009b:
        r10 = move-exception;
    L_0x009c:
        r10 = 0;
        goto L_0x0090;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.controllers.VersionCheckerUtil.isMayorUpdate(java.lang.String, java.lang.String):boolean");
    }

    public final void checkVersion(@NotNull String version, @NotNull String lastVersion, @NotNull OnVersionResult listener) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(lastVersion, "lastVersion");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        try {
            if ((((CharSequence) lastVersion).length() == 0 ? 1 : null) != null) {
                listener.onCleanInstall(version);
            } else if (Intrinsics.areEqual(lastVersion, version)) {
                listener.onVersionNotUpdated(version);
            } else if (isMayorUpdate(lastVersion, version)) {
                listener.onMajorVersionUpdated(lastVersion, version);
            } else {
                listener.onMinorVersionUpdated(lastVersion, version);
            }
        } catch (Exception e) {
        }
    }
}

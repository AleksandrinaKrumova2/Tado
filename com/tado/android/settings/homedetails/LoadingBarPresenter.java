package com.tado.android.settings.homedetails;

import android.view.View;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000eH\u0016R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/tado/android/settings/homedetails/LoadingBarPresenter;", "Lcom/tado/android/settings/homedetails/AbstractLoadingPresenter;", "progressBar", "Lcom/tado/android/views/progressbar/ProgressBarComponent;", "rootView", "Landroid/view/View;", "(Lcom/tado/android/views/progressbar/ProgressBarComponent;Landroid/view/View;)V", "progressBarReference", "Ljava/lang/ref/WeakReference;", "rootViewReference", "setViewAndChildrenEnabled", "", "view", "enabled", "", "startLoading", "stopLoading", "withAnimation", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: LoadingPresenters.kt */
public final class LoadingBarPresenter implements AbstractLoadingPresenter {
    private final WeakReference<ProgressBarComponent> progressBarReference;
    private final WeakReference<View> rootViewReference;

    private final void setViewAndChildrenEnabled(android.view.View r1, boolean r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: com.tado.android.settings.homedetails.LoadingBarPresenter.setViewAndChildrenEnabled(android.view.View, boolean):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        if (r7 == 0) goto L_0x0005;
    L_0x0002:
        r7.setEnabled(r8);
    L_0x0005:
        r4 = r7 instanceof android.view.ViewGroup;
        if (r4 == 0) goto L_0x005d;
    L_0x0009:
        r5 = 0;
        r4 = r7;
        r4 = (android.view.ViewGroup) r4;
        r4 = r4.getChildCount();
        r4 = kotlin.ranges.RangesKt___RangesKt.until(r5, r4);
        r4 = (java.lang.Iterable) r4;
        r0 = new java.util.ArrayList;
        r5 = 10;
        r5 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r4, r5);
        r0.<init>(r5);
        r0 = (java.util.Collection) r0;
        r5 = r4.iterator();
    L_0x0029:
        r4 = r5.hasNext();
        if (r4 == 0) goto L_0x0041;
    L_0x002f:
        r4 = r5;
        r4 = (kotlin.collections.IntIterator) r4;
        r3 = r4.nextInt();
        r4 = r7;
        r4 = (android.view.ViewGroup) r4;
        r4 = r4.getChildAt(r3);
        r0.add(r4);
        goto L_0x0029;
    L_0x0041:
        r0 = (java.util.List) r0;
        r0 = (java.lang.Iterable) r0;
        r4 = r0.iterator();
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x005c;
    L_0x0050:
        r1 = r4.next();
        r2 = r1;
        r2 = (android.view.View) r2;
        r6.setViewAndChildrenEnabled(r2, r8);
        goto L_0x004a;
    L_0x005d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.settings.homedetails.LoadingBarPresenter.setViewAndChildrenEnabled(android.view.View, boolean):void");
    }

    public LoadingBarPresenter(@Nullable ProgressBarComponent progressBar, @Nullable View rootView) {
        this.progressBarReference = new WeakReference(progressBar);
        this.rootViewReference = new WeakReference(rootView);
    }

    public void startLoading() {
        ProgressBarComponent progressBarComponent = (ProgressBarComponent) this.progressBarReference.get();
        if (progressBarComponent != null) {
            progressBarComponent.showView();
        }
        setViewAndChildrenEnabled((View) this.rootViewReference.get(), false);
    }

    public void stopLoading(boolean withAnimation) {
        setViewAndChildrenEnabled((View) this.rootViewReference.get(), true);
        ProgressBarComponent progressBarComponent;
        if (withAnimation) {
            progressBarComponent = (ProgressBarComponent) this.progressBarReference.get();
            if (progressBarComponent != null) {
                progressBarComponent.hideViewWithAnimation();
                return;
            }
            return;
        }
        progressBarComponent = (ProgressBarComponent) this.progressBarReference.get();
        if (progressBarComponent != null) {
            progressBarComponent.hideView();
        }
    }
}

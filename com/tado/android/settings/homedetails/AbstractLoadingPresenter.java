package com.tado.android.settings.homedetails;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/tado/android/settings/homedetails/AbstractLoadingPresenter;", "", "startLoading", "", "stopLoading", "withAnimation", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: LoadingPresenters.kt */
public interface AbstractLoadingPresenter {

    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 9})
    /* compiled from: LoadingPresenters.kt */
    public static final class DefaultImpls {
        public static /* bridge */ /* synthetic */ void stopLoading$default(AbstractLoadingPresenter abstractLoadingPresenter, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopLoading");
            }
            if ((i & 1) != 0) {
                z = true;
            }
            abstractLoadingPresenter.stopLoading(z);
        }
    }

    void startLoading();

    void stopLoading(boolean z);
}

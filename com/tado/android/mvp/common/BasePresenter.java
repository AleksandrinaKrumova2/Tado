package com.tado.android.mvp.common;

import android.support.annotation.UiThread;

public interface BasePresenter<V extends BaseView> {
    @UiThread
    void bindView(V v);

    @UiThread
    void unbindView();
}

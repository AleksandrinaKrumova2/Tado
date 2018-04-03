package com.tado.android.settings.interfaces;

public interface GenericCallbackListener<T> {
    void onFailure();

    void onSuccess(T t);
}

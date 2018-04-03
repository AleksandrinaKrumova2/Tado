package com.tado.android.rest.callback;

import com.tado.android.rest.callback.presenters.PresenterDelegate;
import com.tado.android.settings.interfaces.GenericCallbackListener;
import retrofit2.Call;
import retrofit2.Response;

public abstract class RetryCallback<T> extends TadoCallback<T> {
    private PresenterDelegate mPresenterDelegate;

    public RetryCallback(PresenterDelegate presenterDelegate, GenericCallbackListener<T> listener) {
        super(listener, presenterDelegate);
        this.mPresenterDelegate = presenterDelegate;
        this.mPresenterDelegate.setTadoCallBack(this);
    }

    public RetryCallback(PresenterDelegate presenterDelegate) {
        this(presenterDelegate, null);
    }

    public void onResponse(Call<T> call, Response<T> response) {
        super.onResponse(call, response);
        if (!response.isSuccessful() && !this.handled) {
            this.handled = true;
            this.mPresenterDelegate.showErrorOnSuccessfulRequest(call);
        }
    }

    public void onFailure(Call<T> call, Throwable t) {
        super.onFailure(call, t);
        if (!this.handled) {
            this.handled = true;
            this.mPresenterDelegate.showErrorOnFailedRequest(call);
        }
    }
}

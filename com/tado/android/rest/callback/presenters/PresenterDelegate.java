package com.tado.android.rest.callback.presenters;

import com.tado.android.rest.callback.TadoCallback;
import retrofit2.Call;

public abstract class PresenterDelegate {
    TadoCallback mTadoCallBack;

    public abstract void onNotSupportedInDemoMode();

    public abstract void showErrorOnFailedRequest(Call call);

    public abstract void showErrorOnSuccessfulRequest(Call call);

    public void setTadoCallBack(TadoCallback tadoCallBack) {
        this.mTadoCallBack = tadoCallBack;
    }
}

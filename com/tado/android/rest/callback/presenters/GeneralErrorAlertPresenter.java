package com.tado.android.rest.callback.presenters;

import android.content.Context;
import com.tado.android.dialogs.AlertDialogs;
import retrofit2.Call;

public class GeneralErrorAlertPresenter extends AbstractAlertPresenter {
    public GeneralErrorAlertPresenter(Context context) {
        super(context);
    }

    public void showErrorOnSuccessfulRequest(Call call) {
        AlertDialogs.showNetworkErrorWithRetry((Context) this.mContext.get(), call, this.mTadoCallBack);
    }

    public void showErrorOnFailedRequest(Call call) {
        AlertDialogs.showNetworkErrorWithRetry((Context) this.mContext.get(), call, this.mTadoCallBack);
    }
}

package com.tado.android.rest.callback.presenters;

import android.app.Activity;
import android.content.Context;
import com.tado.android.dialogs.AlertDialogs;
import retrofit2.Call;

public class ErrorAlertWithBackOnCancelPresenter extends AbstractAlertPresenter implements OnCancelAlertListener {
    public ErrorAlertWithBackOnCancelPresenter(Activity activity) {
        super(activity);
    }

    public void showErrorOnSuccessfulRequest(Call call) {
        AlertDialogs.showNetworkErrorWithRetry((Context) this.mContext.get(), call, this.mTadoCallBack, this);
    }

    public void showErrorOnFailedRequest(Call call) {
        AlertDialogs.showNetworkErrorWithRetry((Context) this.mContext.get(), call, this.mTadoCallBack, this);
    }

    public void onCancel() {
        if (this.mContext.get() != null) {
            ((Activity) this.mContext.get()).onBackPressed();
        }
    }
}

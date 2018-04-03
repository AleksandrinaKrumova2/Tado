package com.tado.android.rest.callback.presenters;

import android.view.View;
import com.tado.C0676R;
import java.lang.ref.WeakReference;
import retrofit2.Call;

public class SendingErrorSnackbarPresenter extends AbstractSnackbarPresenter {
    private WeakReference<View> mView;

    public SendingErrorSnackbarPresenter(View view) {
        super(view);
    }

    public void showErrorOnSuccessfulRequest(Call call) {
        showSnackbar((int) C0676R.string.errors_sendingFailed_message, (int) C0676R.string.errors_sendingFailed_retryButton, call);
    }

    public void showErrorOnFailedRequest(Call call) {
        showSnackbar((int) C0676R.string.errors_sendingFailed_message, (int) C0676R.string.errors_sendingFailed_retryButton, call);
    }
}

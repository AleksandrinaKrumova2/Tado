package com.tado.android.rest.callback.presenters;

import android.view.View;
import com.tado.C0676R;
import retrofit2.Call;

public class GeneralErrorSnackbarPresenter extends AbstractSnackbarPresenter {
    public GeneralErrorSnackbarPresenter(View view) {
        super(view);
    }

    public void showErrorOnSuccessfulRequest(Call call) {
        showSnackbar((int) C0676R.string.errors_somethingWentWrong_message, (int) C0676R.string.errors_noInternetConnection_confirmButton, call);
    }

    public void showErrorOnFailedRequest(Call call) {
        showSnackbar((int) C0676R.string.errors_noInternetConnection_message, (int) C0676R.string.errors_noInternetConnection_confirmButton, call);
    }
}

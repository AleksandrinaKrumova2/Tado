package com.tado.android.rest.callback.presenters;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.tado.C0676R;
import java.lang.ref.WeakReference;
import retrofit2.Call;

public abstract class AbstractSnackbarPresenter extends PresenterDelegate {
    private WeakReference<View> mView;

    AbstractSnackbarPresenter(View view) {
        this.mView = new WeakReference(view);
    }

    void showSnackbar(int messageResId, int buttonResId, Call call) {
        if (this.mView.get() != null) {
            showSnackbar(((View) this.mView.get()).getResources().getText(messageResId), buttonResId, call);
        }
    }

    void showSnackbar(CharSequence message, int buttonResId, final Call call) {
        if (this.mView.get() != null) {
            try {
                Snackbar.make((View) this.mView.get(), message, -2).setAction(buttonResId, new OnClickListener() {
                    public void onClick(View v) {
                        if (call != null) {
                            AbstractSnackbarPresenter.this.mTadoCallBack.retry(call);
                        }
                    }
                }).show();
            } catch (IllegalArgumentException e) {
                if (((View) this.mView.get()).getContext() != null) {
                    Toast.makeText(((View) this.mView.get()).getContext(), message, 1).show();
                }
            }
        }
    }

    public void onNotSupportedInDemoMode() {
        showSnackbar((CharSequence) "Not supported in demo mode", (int) C0676R.string.ok, null);
    }
}

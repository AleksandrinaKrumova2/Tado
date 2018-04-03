package com.tado.android.rest.callback.presenters;

import android.content.Context;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertDialogs;
import java.lang.ref.WeakReference;

public abstract class AbstractAlertPresenter extends PresenterDelegate {
    WeakReference<Context> mContext;

    public AbstractAlertPresenter(Context context) {
        this.mContext = new WeakReference(context);
    }

    public void onNotSupportedInDemoMode() {
        AlertDialogs.showSimpleWarning("", "Not supported in demo mode", ((Context) this.mContext.get()).getString(C0676R.string.ok), (Context) this.mContext.get(), null);
    }
}

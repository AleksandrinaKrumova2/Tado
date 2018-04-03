package com.tado.android.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.rest.service.TadoApiService;
import com.tado.android.utils.GeolocationLogger;
import com.tado.android.utils.UserConfig;
import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public enum LoggerController {
    INSTANCE;

    class C07482 implements OnClickListener {
        C07482() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C07526 implements OnClickListener {
        C07526() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    private void clearLogFile(View view) {
        GeolocationLogger.clearLogFile();
        Snackbar.make(view, (int) C0676R.string.logUpload_messages_logsDeletedMessage, 0).show();
    }

    private void sendLogFile(final Context context, final boolean loginScreen) {
        Call<Void> uploadLog;
        File file = GeolocationLogger.getLogFile();
        TadoApiService service = RestServiceGenerator.getTadoRestService();
        RequestBody body = RequestBody.create(MediaType.parse(NanoHTTPD.MIME_PLAINTEXT), file);
        if (loginScreen) {
            uploadLog = service.uploadLoginLog(file.getName(), "multipart/form-data", "uri=\"http://acs.amazonaws.com/groups/global/AuthenticatedUsers\"", body);
        } else {
            uploadLog = service.uploadLocationLog(UserConfig.getHomeId(), file.getName(), "multipart/form-data", "uri=\"http://acs.amazonaws.com/groups/global/AuthenticatedUsers\"", body);
        }
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setIndeterminate(true);
        pd.setTitle(C0676R.string.logUpload_title);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        uploadLog.enqueue(new TadoCallback<Void>() {

            class C07461 implements OnClickListener {
                C07461() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }

            public void onResponse(Call<Void> call, Response<Void> response) {
                super.onResponse(call, response);
                pd.dismiss();
                if (response.isSuccessful()) {
                    new Builder(context).setMessage((int) C0676R.string.logUpload_messages_sendingSuccessfulMessage).setPositiveButton((int) C0676R.string.ok, new C07461()).show();
                } else {
                    LoggerController.this.onSendingLogsError(context, loginScreen);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                super.onFailure(call, t);
                pd.dismiss();
                LoggerController.this.onSendingLogsError(context, loginScreen);
            }
        });
    }

    private void onSendingLogsError(final Context context, final boolean loginScreen) {
        new Builder(context).setMessage((int) C0676R.string.logUpload_messages_sendingFailedMessage).setPositiveButton((int) C0676R.string.retry, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                LoggerController.this.sendLogFile(context, loginScreen);
            }
        }).setNegativeButton((int) C0676R.string.logUpload_cancelButton, new C07482()).show();
    }

    public void showSendLogsDialog(Context context, final View view, final boolean loginScreen) {
        final int[] selected = new int[]{0};
        CharSequence[] items = new CharSequence[]{context.getString(C0676R.string.logUpload_sendLogsButton), context.getString(C0676R.string.logUpload_clearLogsButton)};
        Builder builder = new Builder(context);
        builder.setTitle((int) C0676R.string.logUpload_title).setSingleChoiceItems(items, selected[0], new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                selected[0] = which;
            }
        });
        builder.setPositiveButton((int) C0676R.string.logUpload_sendLogsButton, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                LoggerController.this.handleLogsSelection(selected[0], view, loginScreen);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton((int) C0676R.string.logUpload_cancelButton, new C07526());
        builder.setCancelable(false);
        builder.create().show();
    }

    private void handleLogsSelection(int which, View view, boolean loginScreen) {
        if (which == 0) {
            sendLogFile(view.getContext(), loginScreen);
        } else {
            clearLogFile(view);
        }
    }
}

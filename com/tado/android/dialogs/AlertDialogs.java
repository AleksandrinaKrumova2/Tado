package com.tado.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.MyGlobalProperties;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.OnCancelAlertListener;
import com.tado.android.utils.Snitcher;
import java.util.HashMap;
import java.util.Map.Entry;
import retrofit2.Call;

public class AlertDialogs {
    private static boolean isSafeContext(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        if (!(context instanceof Activity)) {
            return false;
        }
        if (((Activity) context).isFinishing()) {
            return false;
        }
        if (VERSION.SDK_INT >= 17 && (context instanceof AppCompatActivity) && ((AppCompatActivity) context).isDestroyed()) {
            return false;
        }
        if (VERSION.SDK_INT < 18 || !((Activity) context).isDestroyed()) {
            return true;
        }
        return false;
    }

    public static void showTermsAndConditionsDialog(String header, String buttonText, @Nullable final Activity context, final AlertWarningDialogListener listener) {
        if (context != null && !context.isFinishing()) {
            Builder alertDialogBuilder = new Builder(context);
            alertDialogBuilder.setTitle(header);
            View view = LayoutInflater.from(context).inflate(C0676R.layout.dialog_terms_conditions, null);
            alertDialogBuilder.setView(view);
            view.findViewById(C0676R.id.create_account_terms_and_conditions_link).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AnalyticsHelper.trackEvent(context.getApplication(), "Legal", "TermsAndConditions");
                    Intent i = new Intent("android.intent.action.VIEW");
                    i.setData(Uri.parse(context.getString(C0676R.string.createUserAccount_goToLegalInfoPrompt_generalTermsAndConditionsButtonURL)));
                    context.startActivity(i);
                }
            });
            view.findViewById(C0676R.id.create_account_privacy_policy_link).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AnalyticsHelper.trackEvent(context.getApplication(), "Legal", "PrivacyPolicy");
                    Intent i = new Intent("android.intent.action.VIEW");
                    i.setData(Uri.parse(context.getString(C0676R.string.createUserAccount_goToLegalInfoPrompt_privacyPolicyButtonURL)));
                    context.startActivity(i);
                }
            });
            alertDialogBuilder.setCancelable(false).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                    if (listener != null && AlertWarningDialogListener.class.isInstance(listener)) {
                        listener.OnOKClicked();
                    }
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            setButtonTextColors(alertDialog);
            alertDialog.show();
        }
    }

    public static void showGenericError(String header, HashMap<Integer, String> content, String buttonText, @Nullable Activity context, final AlertWarningDialogListener listener) {
        if (context != null && !context.isFinishing()) {
            Builder alertDialogBuilder = new Builder(context);
            alertDialogBuilder.setTitle(header);
            View view = LayoutInflater.from(context).inflate(C0676R.layout.dialog_general_error, null);
            alertDialogBuilder.setView(view);
            alertDialogBuilder.setCancelable(false).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                    if (listener != null && AlertWarningDialogListener.class.isInstance(listener)) {
                        listener.OnOKClicked();
                    }
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            for (Entry<Integer, String> integerStringEntry : content.entrySet()) {
                TextView textView = (TextView) view.findViewById(((Integer) integerStringEntry.getKey()).intValue());
                textView.setVisibility(0);
                textView.setText((CharSequence) integerStringEntry.getValue());
            }
            setButtonTextColors(alertDialog);
            alertDialog.show();
        }
    }

    public static void showCancelRetryAlert(String header, String body, String buttonRetryText, String buttonCancelText, @Nullable Context context, AlertChoiceDialogListener listener) {
        if (isSafeContext(context)) {
            final Context context2 = context;
            final String str = header;
            final String str2 = body;
            final String str3 = buttonCancelText;
            final AlertChoiceDialogListener alertChoiceDialogListener = listener;
            final String str4 = buttonRetryText;
            ((Activity) context).runOnUiThread(new Runnable() {

                class C07761 implements DialogInterface.OnClickListener {
                    C07761() {
                    }

                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                        if (alertChoiceDialogListener != null && AlertChoiceDialogListener.class.isInstance(alertChoiceDialogListener)) {
                            alertChoiceDialogListener.OnOKClicked();
                        }
                    }
                }

                class C07772 implements DialogInterface.OnClickListener {
                    C07772() {
                    }

                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                        if (alertChoiceDialogListener != null && AlertChoiceDialogListener.class.isInstance(alertChoiceDialogListener)) {
                            alertChoiceDialogListener.OnCancelClicked();
                        }
                    }
                }

                public void run() {
                    Builder alertDialogBuilder = new Builder(context2);
                    alertDialogBuilder.setTitle(str);
                    alertDialogBuilder.setMessage(str2).setCancelable(true).setNegativeButton(str3, new C07772()).setPositiveButton(str4, new C07761());
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    AlertDialogs.setButtonTextColors(alertDialog);
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setCancelable(false);
                    if (AlertDialogs.isSafeContext(context2)) {
                        alertDialog.show();
                    }
                }
            });
        }
    }

    public static AlertDialog getCancelRetryAlert(String header, String body, String buttonRetryText, String buttonCancelText, @Nullable Activity context, final AlertChoiceDialogListener listener) {
        if (context == null || context.isFinishing()) {
            return null;
        }
        Builder alertDialogBuilder = new Builder(context);
        alertDialogBuilder.setTitle(header);
        alertDialogBuilder.setMessage(body).setCancelable(true).setNegativeButton(buttonCancelText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                if (listener != null && AlertChoiceDialogListener.class.isInstance(listener)) {
                    listener.OnCancelClicked();
                }
                dialog.cancel();
            }
        }).setPositiveButton(buttonRetryText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                if (listener != null && AlertChoiceDialogListener.class.isInstance(listener)) {
                    listener.OnOKClicked();
                }
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        setButtonTextColors(alertDialog);
        return alertDialog;
    }

    public static void showWarning(String header, CharSequence body, String buttonText, @Nullable Activity context, final AlertWarningDialogListener listener) {
        if (context != null && !context.isFinishing()) {
            Builder alertDialogBuilder = new Builder(context);
            alertDialogBuilder.setTitle(header);
            alertDialogBuilder.setMessage(body).setCancelable(false).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                    if (listener != null && AlertWarningDialogListener.class.isInstance(listener)) {
                        listener.OnOKClicked();
                    }
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            setButtonTextColors(alertDialog);
            alertDialog.show();
        }
    }

    public static void showSendingErrorWithRetry(@Nullable Context context, Call call, TadoCallback tadoCallback) {
        if (context == null) {
            return;
        }
        if (!(context instanceof Activity) || !((Activity) context).isFinishing()) {
            showAutoRetryAlert(context.getString(C0676R.string.errors_sendingFailed_title), context.getString(C0676R.string.errors_sendingFailed_message), context.getString(C0676R.string.errors_sendingFailed_retryButton), context.getString(C0676R.string.errors_sendingFailed_cancelButton), context, call, tadoCallback);
        }
    }

    public static void showNetworkErrorWithRetry(@Nullable Context context, Call call, TadoCallback tadoCallback) {
        showNetworkErrorWithRetry(context, call, tadoCallback, null);
    }

    public static void showNetworkErrorWithRetry(@Nullable Context context, Call call, TadoCallback tadoCallback, OnCancelAlertListener onCancelAlertListener) {
        if (context == null) {
            return;
        }
        if (!(context instanceof Activity) || !((Activity) context).isFinishing()) {
            showAutoRetryAlert(context.getString(C0676R.string.errors_noInternetConnection_title), context.getString(C0676R.string.errors_noInternetConnection_message), context.getString(C0676R.string.errors_noInternetConnection_confirmButton), context.getString(C0676R.string.errors_noInternetConnection_cancelButton), context, call, tadoCallback, onCancelAlertListener);
        }
    }

    private static void showAutoRetryAlert(String header, String body, String buttonRetryText, String buttonCancelText, @Nullable Context context, Call call, TadoCallback tadoCallback) {
        showAutoRetryAlert(header, body, buttonRetryText, buttonCancelText, context, call, tadoCallback, null);
    }

    private static void showAutoRetryAlert(String header, String body, String buttonRetryText, String buttonCancelText, @Nullable Context context, final Call call, final TadoCallback tadoCallback, final OnCancelAlertListener onCancelAlertListener) {
        if (context == null) {
            return;
        }
        if (!(context instanceof Activity) || !((Activity) context).isFinishing()) {
            showCancelRetryAlert(header, body, buttonRetryText, buttonCancelText, context, new AlertChoiceDialogListener() {
                public void OnOKClicked() {
                    tadoCallback.retry(call);
                }

                public void OnCancelClicked() {
                    if (onCancelAlertListener != null) {
                        onCancelAlertListener.onCancel();
                    }
                }
            });
        }
    }

    public static void showSimpleWarning(String header, String body, String buttonText, @Nullable Context context, final AlertWarningDialogListener listener) {
        if (context == null) {
            return;
        }
        if (!(context instanceof Activity) || !((Activity) context).isFinishing()) {
            Builder alertDialogBuilder = new Builder(context);
            alertDialogBuilder.setTitle(header);
            alertDialogBuilder.setMessage(body).setCancelable(false).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(false);
                    if (listener != null && AlertWarningDialogListener.class.isInstance(listener)) {
                        listener.OnOKClicked();
                    }
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            setButtonTextColors(alertDialog);
            if (MyGlobalProperties.INSTANCE.isNetworkDialogDisplayed) {
                Snitcher.start().log(2, "Alert dialog", "Ignoring alert dialog", new Object[0]);
                return;
            }
            alertDialog.show();
            MyGlobalProperties.INSTANCE.setNetworkDialogDisplayed(true);
        }
    }

    public static void showChoiceAlert(String header, CharSequence body, String buttonOKText, String buttonCancelText, @Nullable Activity context, final AlertChoiceDialogListener listener) {
        if (context != null && !context.isFinishing()) {
            Builder alertDialogBuilder = new Builder(context);
            alertDialogBuilder.setTitle(header);
            alertDialogBuilder.setMessage(body).setCancelable(false).setPositiveButton(buttonOKText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if (listener != null && AlertChoiceDialogListener.class.isInstance(listener)) {
                        listener.OnOKClicked();
                    }
                }
            }).setNegativeButton(buttonCancelText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if (listener != null && AlertChoiceDialogListener.class.isInstance(listener)) {
                        listener.OnCancelClicked();
                    }
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            setButtonTextColors(alertDialog);
            alertDialog.show();
        }
    }

    private static void setButtonTextColors(AlertDialog alertDialog) {
        if (alertDialog != null) {
            alertDialog.setOnShowListener(new OnShowListener() {
                public void onShow(DialogInterface dialog) {
                    Button positiveButton = ((AlertDialog) dialog).getButton(-1);
                    Button negativeButton = ((AlertDialog) dialog).getButton(-2);
                    if (positiveButton != null) {
                        positiveButton.setTextColor(ContextCompat.getColor(positiveButton.getContext(), C0676R.color.action_dialog_positive_button));
                    }
                    if (negativeButton != null) {
                        negativeButton.setTextColor(ContextCompat.getColor(negativeButton.getContext(), C0676R.color.action_dialog_negative_button));
                    }
                }
            });
        }
    }

    public static void showTwoButtonAlertDialog(Context context, @StringRes int message, @StringRes int positiveButton, DialogInterface.OnClickListener positiveButtonListener, @StringRes int negativeButton, DialogInterface.OnClickListener negativeButtonListener) {
        Builder builder = new Builder(context);
        builder.setCancelable(false).setMessage(message).setPositiveButton(positiveButton, positiveButtonListener).setNegativeButton(negativeButton, negativeButtonListener);
        AlertDialog dialog = builder.create();
        setButtonTextColors(dialog);
        dialog.show();
    }
}

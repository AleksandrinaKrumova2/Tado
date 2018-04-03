package com.tado.android.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.login.LoginPresenter;
import com.tado.android.rest.service.RestServiceGenerator;

public abstract class ServerBase {
    public Dialog getServerSelectionDialog(Activity context, LoginPresenter presenter) {
        Builder builder = new Builder(context);
        View dialogView = context.getLayoutInflater().inflate(C0676R.layout.login_select_server_dialog, null);
        final Activity activity = context;
        final LoginPresenter loginPresenter = presenter;
        activity = context;
        builder.setView(dialogView).setTitle((CharSequence) "Select server").setPositiveButton((CharSequence) "Ok", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(activity, loginPresenter.getConnectAddress(), 0).show();
                UserConfig.setServerAddress(loginPresenter.getConnectAddress());
                RestServiceGenerator.destroyClient();
            }
        }).setNegativeButton((CharSequence) "Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AnalyticsHelper.trackEvent(activity, "HiddenMenu", "HiddenServerMenu", "cancel");
            }
        });
        final RadioButton production = (RadioButton) dialogView.findViewById(C0676R.id.production_radio);
        final RadioButton preproduction = (RadioButton) dialogView.findViewById(C0676R.id.preproduction_radio);
        final RadioButton customServer = (RadioButton) dialogView.findViewById(C0676R.id.custom_server_radio);
        final EditText customServerText = (EditText) dialogView.findViewById(C0676R.id.production_server);
        final LoginPresenter loginPresenter2 = presenter;
        production.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preproduction.setChecked(false);
                    customServer.setChecked(false);
                    production.setChecked(true);
                    loginPresenter2.setConnectAddress(Server.getProduction());
                }
            }
        });
        final RadioButton radioButton = customServer;
        final RadioButton radioButton2 = production;
        final RadioButton radioButton3 = preproduction;
        final LoginPresenter loginPresenter3 = presenter;
        preproduction.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioButton.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(true);
                    loginPresenter3.setConnectAddress(Server.getPreproduction());
                }
            }
        });
        radioButton = preproduction;
        radioButton2 = production;
        radioButton3 = customServer;
        loginPresenter3 = presenter;
        customServer.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioButton.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(true);
                    loginPresenter3.setConnectAddress(customServerText.getText().toString());
                }
            }
        });
        return builder.create();
    }
}

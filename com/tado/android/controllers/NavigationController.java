package com.tado.android.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.tado.C0676R;
import com.tado.android.MainZoneActivity;
import com.tado.android.app.ForceUpdateActivity;
import com.tado.android.installation.ChooseProductActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.installation.connectwifi.ConnectToDeviceActivity;
import com.tado.android.installation.connectwifi.DeviceConnectionSuccessfulActivity;
import com.tado.android.login.LoginActivity;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;

public class NavigationController {
    public static void showForceUpdateScreen(Context context) {
        context.startActivity(new Intent(context, ForceUpdateActivity.class));
    }

    public static void navigateToForgotPassword(@Nullable Context context, Uri url) {
        if (context != null) {
            context.startActivity(new Intent("android.intent.action.VIEW", url));
        }
    }

    public static void navigateToLogin(Context context) {
        startActivity(context, LoginActivity.class);
        finishActivity(context);
    }

    private static void finishActivity(Context context) {
        if (context != null && (context instanceof Activity)) {
            ((Activity) context).finish();
        }
    }

    public static void showProductSelectionScreen(Context context) {
        startActivity(context, ChooseProductActivity.class);
        finishActivity(context);
    }

    public static void navigateToConnectToDevice(Context context, String key, int zoneId) {
        Intent intent = new Intent(context, ConnectToDeviceActivity.class);
        intent.putExtra(key, zoneId);
        context.startActivity(intent);
    }

    private static void startActivity(@Nullable Context context, Class clazz) {
        if (context != null) {
            Intent intent = new Intent(context, clazz);
            intent.setFlags(268468224);
            context.startActivity(intent);
        }
    }

    public static void navigateToDeviceConnected(Context context) {
        startActivity(context, DeviceConnectionSuccessfulActivity.class);
    }

    public static void showMainScreen(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, MainZoneActivity.class);
            intent.setFlags(268468224);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(17432576, 17432577);
        }
    }

    public static void enterDemoMode(final Activity activity) {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.setIndeterminate(true);
        pd.setTitle(C0676R.string.settings_loadingLabel);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        UserConfig.setServerAddress(RestServiceGenerator.DEMO_SERVER_PATH);
        UserConfig.setUsername("md1F60442F67284D3AAB9DE4DAE72590BB");
        UserConfig.setPassword("dhvgrvafrpher");
        UserConfig.setNickname("Your phone");
        UserConfig.setMobileDeviceId(2);
        UserConfig.setHomeId(1);
        UserConfig.setHomeName("tado° Demo Home");
        new Thread(new Runnable() {
            public void run() {
                InstallationProcessController.getInstallationProcessController().detectStatus(activity, false);
            }
        }).start();
    }
}

package com.tado.android.demo;

import android.content.Context;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;

public class DemoUtils {
    public static boolean isInDemoMode() {
        return UserConfig.getServerAddress().equalsIgnoreCase(RestServiceGenerator.DEMO_SERVER_PATH);
    }

    public static void showHideDemoButton(View view, Context context) {
        boolean showButton = context.getResources().getBoolean(C0676R.bool.demo);
        if (view != null) {
            view.setVisibility(showButton ? 0 : 8);
        }
    }

    public static void exitDemo() {
        Util.clearUserData();
    }
}

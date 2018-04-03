package com.tado.android.utils;

import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

public class DisplaySizeHelper {
    public static Point getScreenSize(WindowManager windowManager) {
        Point screenSize = new Point();
        if (VERSION.SDK_INT >= 13) {
            windowManager.getDefaultDisplay().getSize(screenSize);
        } else {
            Display display = windowManager.getDefaultDisplay();
            screenSize.x = display.getWidth();
            screenSize.y = display.getHeight();
        }
        return screenSize;
    }
}

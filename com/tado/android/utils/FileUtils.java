package com.tado.android.utils;

import android.content.res.AssetManager;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileUtils {
    public static String loadJSONFromAsset(AssetManager assetManager, String path, String filename) {
        if (path == null || path.isEmpty()) {
            return loadJSONFromAsset(assetManager, filename);
        }
        return loadJSONFromAsset(assetManager, path.concat("/").concat(filename));
    }

    public static String loadJSONFromAsset(AssetManager assetManager, String filename) {
        try {
            InputStream is = assetManager.open(filename);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, HttpRequest.CHARSET_UTF8);
            return json;
        } catch (IOException ex) {
            Crashlytics.logException(ex);
            return null;
        }
    }

    public static boolean fileExistInAssets(AssetManager assetManager, String path, String filename) {
        try {
            return Arrays.asList(assetManager.list(path)).contains(filename);
        } catch (IOException e) {
            return false;
        }
    }
}

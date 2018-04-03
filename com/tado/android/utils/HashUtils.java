package com.tado.android.utils;

import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    private static final String TAG = HashUtils.class.getCanonicalName();
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String sha1Hash(String toHash) {
        String hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            byte[] bytes = toHash.getBytes(HttpRequest.CHARSET_UTF8);
            digest.update(bytes, 0, bytes.length);
            hash = bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            Snitcher.start().toCrashlytics().logException(TAG, e);
        } catch (UnsupportedEncodingException e2) {
            Snitcher.start().toCrashlytics().logException(TAG, e2);
        }
        return hash;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }
}

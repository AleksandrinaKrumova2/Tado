package com.tado.android.utils;

public class Server extends ServerBase {
    private static final String SERVER_CIDEVELOP = "https://cidevelop.tado.com/";
    private static final String SERVER_PRODUCTION = "https://my.tado.com/";
    private static final CharSequence[] servers = new CharSequence[]{SERVER_PRODUCTION, SERVER_CIDEVELOP};
    private static int tapCounter = 0;

    public static String getProduction() {
        return SERVER_PRODUCTION;
    }

    public static String getPreproduction() {
        return SERVER_CIDEVELOP;
    }
}

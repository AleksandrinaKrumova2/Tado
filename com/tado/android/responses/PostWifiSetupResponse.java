package com.tado.android.responses;

public class PostWifiSetupResponse extends Response {
    public void parse(String stream) {
        setSuccess(true);
    }
}

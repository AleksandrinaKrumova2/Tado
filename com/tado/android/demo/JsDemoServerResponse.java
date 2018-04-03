package com.tado.android.demo;

public class JsDemoServerResponse {
    private String body;
    private int status;

    JsDemoServerResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return this.status;
    }

    public String getBody() {
        return this.body;
    }
}

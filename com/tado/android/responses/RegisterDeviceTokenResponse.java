package com.tado.android.responses;

import com.google.gson.JsonSyntaxException;
import com.tado.android.utils.Snitcher;

public class RegisterDeviceTokenResponse extends Response {
    public void parse(String stream) throws NullPointerException, JsonSyntaxException {
        Snitcher.start().log(4, "", stream, new Object[0]);
    }
}

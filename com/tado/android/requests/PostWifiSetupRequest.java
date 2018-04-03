package com.tado.android.requests;

import com.tado.android.responses.PostWifiSetupResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Constants;
import com.tado.android.utils.FormParams;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;

public class PostWifiSetupRequest extends Request {
    private FormParams mFormData = new FormParams();

    public PostWifiSetupRequest(String ssid, String password, int securityType) {
        this.mFormData.put("__SL_P_S.R", "main.html");
        this.mFormData.put("__SL_P_P.A", ssid);
        this.mFormData.put("__SL_P_P.B", String.valueOf(securityType));
        this.mFormData.put("__SL_P_P.C", password);
        this.mFormData.put("__SL_P_P.D", "0");
    }

    public String getUrl() {
        return "http://192.168.1.1/profiles_add.html";
    }

    public String getAddress() {
        return Constants.URL_DEVICE_POST_CREDENTIALS;
    }

    public Response createResponse() {
        return new PostWifiSetupResponse();
    }

    public byte[] toBytes() {
        if (this.mFormData.isEmpty()) {
            return null;
        }
        return this.mFormData.toString().getBytes(Charset.forName(HttpRequest.CHARSET_UTF8));
    }
}

package com.tado.android.requests;

import com.tado.android.responses.RegisterDeviceTokenResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Constants;
import com.tado.android.utils.FormParams;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;

public class RegisterDeviceTokenRequest extends Request {
    private FormParams mFormData = new FormParams();

    public RegisterDeviceTokenRequest(String token) {
        setCredentialsToFormData(this.mFormData);
        this.mFormData.put("deviceToken", token);
    }

    public String getAddress() {
        return Constants.URL_REGISTERDEVICETOKEN;
    }

    public Response createResponse() {
        return new RegisterDeviceTokenResponse();
    }

    public byte[] toBytes() {
        if (this.mFormData.isEmpty()) {
            return null;
        }
        return this.mFormData.toString().getBytes(Charset.forName(HttpRequest.CHARSET_UTF8));
    }
}

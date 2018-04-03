package com.tado.android.requests;

import com.tado.android.requests.Request.RequestMethodEnum;
import com.tado.android.responses.GetEmptyResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;

public class StopBeepingRequest extends Request {
    private String mRequestContent = "";
    private String serialID;

    public StopBeepingRequest(String serialID) {
        this.serialID = serialID;
        setScheduleApi(true);
        setHttpMethod(RequestMethodEnum.DELETE);
    }

    protected String getAddress() {
        return "devices/" + this.serialID + getNewApiUrlWithoutHome(Constants.URL_DEVICE_BEEP);
    }

    public Response createResponse() {
        return new GetEmptyResponse();
    }

    public byte[] toBytes() {
        if (this.mRequestContent.isEmpty()) {
            return null;
        }
        return this.mRequestContent.toString().getBytes(Charset.forName(HttpRequest.CHARSET_UTF8));
    }
}

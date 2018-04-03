package com.tado.android.requests;

import com.tado.android.requests.Request.RequestMethodEnum;
import com.tado.android.responses.GetWifiListFromDeviceResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Constants;
import com.tado.android.utils.FormParams;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;

public class GetWifiListFromDeviceRequest extends Request {
    private FormParams mFormParams = new FormParams();

    public GetWifiListFromDeviceRequest() {
        setScheduleApi(true);
        setHttpMethod(RequestMethodEnum.GET);
    }

    protected String getApi() {
        return Constants.URL_DEVICE;
    }

    public String getUrl() {
        return "http://192.168.1.1/list.json";
    }

    public String getAddress() {
        return Constants.URL_DEVICE_WIFI_LIST;
    }

    public Response createResponse() {
        return new GetWifiListFromDeviceResponse();
    }

    public byte[] toBytes() {
        if (this.mFormParams.isEmpty()) {
            return null;
        }
        return this.mFormParams.toString().getBytes(Charset.forName(HttpRequest.CHARSET_UTF8));
    }
}

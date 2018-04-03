package com.tado.android.requests;

import com.tado.android.requests.Request.RequestMethodEnum;
import com.tado.android.responses.GetGoogleGeocodingResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Constants;
import com.tado.android.utils.FormParams;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class GetGoogleGeocodingRequest extends Request {
    private String address;
    private String apiKey;
    private FormParams mFormParams = new FormParams();

    public GetGoogleGeocodingRequest(String address, String apiKey) {
        setScheduleApi(true);
        setHttpMethod(RequestMethodEnum.GET);
        try {
            this.address = URLEncoder.encode(address, HttpRequest.CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            this.address = "";
        }
        this.apiKey = apiKey;
        setReferer(Constants.GOOGLE_MAPS_REFERER);
    }

    public String getUrl() {
        return "https://maps.googleapis.com/maps/api/geocode/json?address=" + this.address + "&key=" + this.apiKey;
    }

    public String getAddress() {
        return "https://maps.googleapis.com/maps/api/geocode/json?address=" + this.address + "&key=" + this.apiKey;
    }

    public Response createResponse() {
        return new GetGoogleGeocodingResponse();
    }

    public byte[] toBytes() {
        if (this.mFormParams.isEmpty()) {
            return null;
        }
        return this.mFormParams.toString().getBytes(Charset.forName(HttpRequest.CHARSET_UTF8));
    }
}

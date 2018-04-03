package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.GeocodingResponse;
import com.tado.android.utils.Snitcher;
import org.json.JSONException;
import org.json.JSONObject;

public class GetGoogleGeocodingResponse extends Response {
    private GeocodingResponse geocodingResponse;

    public void parse(String stream) {
        String json = stream;
        Snitcher.start().log(3, GetGoogleGeocodingResponse.class.getSimpleName(), json, new Object[0]);
        try {
            GeocodingResponse geocodingResponse = (GeocodingResponse) new GsonBuilder().create().fromJson(json, GeocodingResponse.class);
            JSONObject obj = null;
            try {
                obj = new JSONObject(stream);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if (obj.getString("status").compareTo("OK") == 0) {
                    setSuccess(true);
                }
            } catch (JSONException e2) {
                setSuccess(false);
            }
            setGeocodingResponse(geocodingResponse);
        } catch (JsonSyntaxException ex) {
            Snitcher.start().toCrashlytics().logException(GetGoogleGeocodingResponse.class.getCanonicalName(), ex);
        }
    }

    public GeocodingResponse getGeocodingResponse() {
        return this.geocodingResponse;
    }

    public void setGeocodingResponse(GeocodingResponse geocodingResponse) {
        this.geocodingResponse = geocodingResponse;
    }
}

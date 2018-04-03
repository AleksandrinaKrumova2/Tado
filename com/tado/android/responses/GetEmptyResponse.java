package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.EmptyResponse;
import com.tado.android.utils.Snitcher;
import org.json.JSONException;
import org.json.JSONObject;

public class GetEmptyResponse extends Response {
    EmptyResponse emptyResponse;

    public void parse(String stream) {
        JSONException e;
        String json = stream;
        Snitcher.start().log(3, GetEmptyResponse.class.getSimpleName(), json, new Object[0]);
        try {
            this.emptyResponse = (EmptyResponse) new GsonBuilder().create().fromJson(json, EmptyResponse.class);
        } catch (JsonSyntaxException ex) {
            Snitcher.start().toCrashlytics().logException(GetEmptyResponse.class.getCanonicalName(), ex);
        }
        setSuccess(true);
        try {
            JSONObject obj = new JSONObject(stream);
            JSONObject jSONObject;
            try {
                if (obj.has("errors")) {
                    setSuccess(false);
                }
                jSONObject = obj;
            } catch (JSONException e2) {
                e = e2;
                jSONObject = obj;
                e.printStackTrace();
                setEmptyResponse(this.emptyResponse);
            }
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            setEmptyResponse(this.emptyResponse);
        }
        setEmptyResponse(this.emptyResponse);
    }

    public EmptyResponse getEmptyResponse() {
        return this.emptyResponse;
    }

    public void setEmptyResponse(EmptyResponse emptyResponse) {
        this.emptyResponse = emptyResponse;
    }
}

package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.TeachingMode;
import com.tado.android.utils.Snitcher;
import org.json.JSONException;
import org.json.JSONObject;

public class GetCommandSetRecordingModeResponse extends Response {
    private TeachingMode mode;

    public void parse(String stream) {
        String json = stream;
        Snitcher.start().log(3, GetCommandSetRecordingModeResponse.class.getSimpleName(), json, new Object[0]);
        try {
            TeachingMode mode = (TeachingMode) new GsonBuilder().create().fromJson(json, TeachingMode.class);
            JSONObject obj = null;
            try {
                obj = new JSONObject(stream);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (obj == null || !obj.has("runs")) {
                setSuccess(false);
            } else {
                setSuccess(true);
            }
            setMode(mode);
        } catch (JsonSyntaxException ex) {
            Snitcher.start().toCrashlytics().logException(GetCommandSetRecordingModeResponse.class.getCanonicalName(), ex);
        }
    }

    public TeachingMode getMode() {
        return this.mode;
    }

    public void setMode(TeachingMode mode) {
        this.mode = mode;
    }
}

package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.Installation;
import org.json.JSONException;
import org.json.JSONObject;

public class GetInstallationResponse extends Response {
    private Installation installation;

    public void parse(String stream) {
        String json = stream;
        try {
            Installation installation = (Installation) new GsonBuilder().create().fromJson(json, Installation.class);
            JSONObject obj = null;
            try {
                obj = new JSONObject(stream);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (obj == null || !obj.has("installationProcess")) {
                setSuccess(false);
            } else {
                setSuccess(true);
            }
            setInstallation(installation);
        } catch (JsonSyntaxException e2) {
        }
    }

    public Installation getInstallation() {
        return this.installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }
}

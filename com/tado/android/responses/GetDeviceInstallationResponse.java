package com.tado.android.responses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.Device;
import com.tado.android.utils.Snitcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetDeviceInstallationResponse extends Response {
    private Device device;

    public void parse(String stream) {
        JSONException e;
        JSONArray errorArray;
        String[] errors;
        int i;
        String json = stream;
        Snitcher.start().log(3, GetDeviceInstallationResponse.class.getSimpleName(), json, new Object[0]);
        Gson gson = new GsonBuilder().create();
        Device device = new Device();
        try {
            Device[] devices = (Device[]) gson.fromJson(json, Device[].class);
            try {
                JSONArray jSONArray = new JSONArray(stream);
                JSONArray jSONArray2;
                try {
                    if (jSONArray.length() > 0) {
                        device = devices[0];
                        setSuccess(true);
                    } else {
                        setSuccess(false);
                    }
                    jSONArray2 = jSONArray;
                } catch (JSONException e2) {
                    e = e2;
                    jSONArray2 = jSONArray;
                    setSuccess(false);
                    try {
                        errorArray = (JSONArray) new JSONObject(stream).get("errors");
                        errors = new String[errorArray.length()];
                        for (i = 0; i < errors.length; i++) {
                            errors[i] = ((JSONObject) errorArray.get(i)).getString("code");
                        }
                        device.setErrorArray(errors);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    setSuccess(false);
                    e.printStackTrace();
                    setDevice(device);
                }
            } catch (JSONException e3) {
                e = e3;
                setSuccess(false);
                errorArray = (JSONArray) new JSONObject(stream).get("errors");
                errors = new String[errorArray.length()];
                for (i = 0; i < errors.length; i++) {
                    errors[i] = ((JSONObject) errorArray.get(i)).getString("code");
                }
                device.setErrorArray(errors);
                setSuccess(false);
                e.printStackTrace();
                setDevice(device);
            }
            setDevice(device);
        } catch (JsonSyntaxException ex) {
            Snitcher.start().logException(GetDeviceInstallationResponse.class.getCanonicalName(), ex);
        }
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}

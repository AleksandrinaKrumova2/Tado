package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.DeviceWifiList;
import com.tado.android.utils.Snitcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetWifiListFromDeviceResponse extends Response {
    private DeviceWifiList mDeviceWifiList;

    public void parse(String stream) {
        String json = stream;
        Snitcher.start().log(3, GetWifiListFromDeviceResponse.class.getSimpleName(), json, new Object[0]);
        try {
            DeviceWifiList deviceWifiList = (DeviceWifiList) new GsonBuilder().create().fromJson(json, DeviceWifiList.class);
            JSONObject obj = null;
            try {
                obj = new JSONObject(stream);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if (obj.getString("serialnumber") != null) {
                    setSuccess(true);
                }
            } catch (JSONException e2) {
                setSuccess(false);
                try {
                    JSONArray errorArray = (JSONArray) obj.get("errors");
                    String[] errors = new String[errorArray.length()];
                    for (int i = 0; i < errors.length; i++) {
                        errors[i] = ((JSONObject) errorArray.get(i)).getString("code");
                    }
                    this.mDeviceWifiList.setErrorArray(errors);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                e2.printStackTrace();
            }
            setDeviceWifiList(deviceWifiList);
        } catch (JsonSyntaxException ex) {
            Snitcher.start().toCrashlytics().logException(GetWifiListFromDeviceResponse.class.getCanonicalName(), ex);
        }
    }

    public DeviceWifiList getDeviceWifiList() {
        return this.mDeviceWifiList;
    }

    public void setDeviceWifiList(DeviceWifiList mDeviceWifiList) {
        this.mDeviceWifiList = mDeviceWifiList;
    }
}

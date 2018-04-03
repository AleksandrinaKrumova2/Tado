package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.UserDetails;
import com.tado.android.utils.ApiResponseLogEntry;
import com.tado.android.utils.Constants;
import com.tado.android.utils.GeolocationLogger;
import com.tado.android.utils.Snitcher;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUserResponse extends Response {
    private UserDetails userDetails;

    public void parse(String stream) {
        ApiResponseLogEntry responseLogEntry = new ApiResponseLogEntry();
        responseLogEntry.setStream(stream);
        GeolocationLogger.logToFile(responseLogEntry);
        String json = stream;
        try {
            UserDetails userDetails = (UserDetails) new GsonBuilder().create().fromJson(json, UserDetails.class);
            JSONObject obj = null;
            try {
                obj = new JSONObject(stream);
            } catch (JSONException e) {
                responseLogEntry.setExceptionMessage(e.getMessage());
                GeolocationLogger.logToFile(responseLogEntry);
                e.printStackTrace();
            }
            try {
                if (obj.getString(Constants.KEY_EXTRA_USERNAME) != null) {
                    setSuccess(true);
                }
            } catch (JSONException e2) {
                responseLogEntry.setExceptionMessage(e2.getMessage());
                GeolocationLogger.logToFile(responseLogEntry);
                setSuccess(false);
            }
            setUserDetails(userDetails);
        } catch (JsonSyntaxException ex) {
            responseLogEntry.setExceptionMessage(ex.getMessage());
            GeolocationLogger.logToFile(responseLogEntry);
            Snitcher.start().logException(GetUserResponse.class.getCanonicalName(), ex);
        }
    }

    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}

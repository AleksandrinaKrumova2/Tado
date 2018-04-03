package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.Email;
import com.tado.android.utils.Snitcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostInvitationRoommatesResponse extends Response {
    Email[] emails;

    public void parse(String stream) {
        String json = stream;
        Snitcher.start().log(3, PostInvitationRoommatesResponse.class.getSimpleName(), json, new Object[0]);
        try {
            Email[] emails = (Email[]) new GsonBuilder().create().fromJson(json, Email[].class);
            JSONArray obj = null;
            try {
                obj = new JSONArray(stream);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (obj.length() > 0) {
                boolean z;
                boolean hasErrors = false;
                int i = 0;
                while (i < obj.length()) {
                    try {
                        if (((JSONObject) obj.get(i)).has("errors")) {
                            hasErrors = true;
                            break;
                        }
                        i++;
                    } catch (JSONException e2) {
                        setSuccess(false);
                    }
                }
                if (hasErrors) {
                    z = false;
                } else {
                    z = true;
                }
                setSuccess(z);
            } else {
                setSuccess(false);
            }
            setEmails(emails);
        } catch (JsonSyntaxException ex) {
            Snitcher.start().toCrashlytics().logException(PostInvitationRoommatesResponse.class.getCanonicalName(), ex);
        }
    }

    public Email[] getEmails() {
        return this.emails;
    }

    public void setEmails(Email[] emails) {
        this.emails = emails;
    }
}

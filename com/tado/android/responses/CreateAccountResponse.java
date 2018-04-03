package com.tado.android.responses;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.entities.AccountResponse;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateAccountResponse extends Response {
    private AccountResponse accountResponse;

    public void parse(String stream) {
        String json = stream;
        Snitcher.start().log(3, "AccountResponse", json, new Object[0]);
        try {
            AccountResponse accountResponse = (AccountResponse) new GsonBuilder().create().fromJson(json, AccountResponse.class);
            JSONObject obj = null;
            try {
                obj = new JSONObject(stream);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if (obj.getString(Constants.KEY_EXTRA_USERNAME) != null) {
                    setSuccess(true);
                }
            } catch (JSONException e2) {
                setSuccess(false);
            }
            setAccountResponse(accountResponse);
        } catch (JsonSyntaxException ex) {
            Snitcher.start().toCrashlytics().logException(CreateAccountResponse.class.getCanonicalName(), ex);
        }
    }

    public AccountResponse getAccountResponse() {
        return this.accountResponse;
    }

    public void setAccountResponse(AccountResponse accountResponse) {
        this.accountResponse = accountResponse;
    }
}

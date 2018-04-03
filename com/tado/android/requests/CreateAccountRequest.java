package com.tado.android.requests;

import com.google.gson.GsonBuilder;
import com.tado.android.entities.Account;
import com.tado.android.responses.CreateAccountResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Util;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;

public class CreateAccountRequest extends Request {
    private String mAddress;
    private String mRequestContent;

    public CreateAccountRequest(String address, Account account) {
        setScheduleApi(true);
        account.setLocale(Util.getSupportedLanguage());
        this.mRequestContent = new GsonBuilder().create().toJson((Object) account);
        this.mAddress = address;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public Response createResponse() {
        return new CreateAccountResponse();
    }

    public byte[] toBytes() {
        if (this.mRequestContent.isEmpty()) {
            return null;
        }
        return this.mRequestContent.toString().getBytes(Charset.forName(HttpRequest.CHARSET_UTF8));
    }
}

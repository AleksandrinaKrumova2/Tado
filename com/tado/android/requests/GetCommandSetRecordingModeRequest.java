package com.tado.android.requests;

import com.tado.android.requests.Request.RequestMethodEnum;
import com.tado.android.responses.GetCommandSetRecordingModeResponse;
import com.tado.android.responses.Response;
import com.tado.android.utils.Constants;
import com.tado.android.utils.FormParams;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;

public class GetCommandSetRecordingModeRequest extends Request {
    private FormParams mFormParams = new FormParams();
    private int mId;
    private String mMode;

    public GetCommandSetRecordingModeRequest(int id, String mode) {
        setScheduleApi(true);
        setHttpMethod(RequestMethodEnum.GET);
        this.mId = id;
        this.mMode = mode;
    }

    public String getAddress() {
        return getNewApiUrlWithoutHome(Constants.URL_AC_LEARNING_COMMAND_SET_RECORDING, "/" + this.mId + "/" + this.mMode, "");
    }

    public Response createResponse() {
        return new GetCommandSetRecordingModeResponse();
    }

    public byte[] toBytes() {
        if (this.mFormParams.isEmpty()) {
            return null;
        }
        return this.mFormParams.toString().getBytes(Charset.forName(HttpRequest.CHARSET_UTF8));
    }
}

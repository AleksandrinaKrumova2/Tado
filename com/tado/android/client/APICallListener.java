package com.tado.android.client;

import com.tado.android.responses.Response;

public interface APICallListener {
    void onCallFailed(APICall aPICall, Response response);

    void onProcessResponse(APICall aPICall, Response response);
}

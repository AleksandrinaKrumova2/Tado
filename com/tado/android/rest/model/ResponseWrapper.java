package com.tado.android.rest.model;

import retrofit2.Call;
import retrofit2.Response;

public interface ResponseWrapper {
    Call getCall();

    Response getResponse();
}

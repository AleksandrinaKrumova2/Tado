package com.tado.android.entities;

public class CandidateTestingResponse {
    private ServerError[] errors;

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }
}

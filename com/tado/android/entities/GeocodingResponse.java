package com.tado.android.entities;

public class GeocodingResponse {
    private String error_message;
    private GAddress[] results;
    private String status;

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GAddress[] getResults() {
        return this.results;
    }

    public void setResults(GAddress[] results) {
        this.results = results;
    }
}

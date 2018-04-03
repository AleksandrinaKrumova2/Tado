package com.tado.android.controllers;

import com.tado.android.entities.ServerError;

public class HomeRestrictionEvent {
    private ServerError serverError;

    public HomeRestrictionEvent(ServerError serverError) {
        this.serverError = serverError;
    }

    public ServerError getServerError() {
        return this.serverError;
    }
}

package com.tado.android.entities;

public class EmailError {
    public static final String EMAIL_ERROR_ALREADY_JOINED = "com.tado.security.Invitation.email.already.joined";
    public static final String EMAIL_ERROR_INVALID_EMAIL = "email.invalid";
    private String[] codes;

    public String[] getCodes() {
        return this.codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }

    public EmailError(String[] codes) {
        this.codes = codes;
    }
}

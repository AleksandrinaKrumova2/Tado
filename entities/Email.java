package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class Email {
    @Expose
    private String email;
    private EmailError[] errors;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailError[] getErrors() {
        return this.errors;
    }

    public void setErrors(EmailError[] errors) {
        this.errors = errors;
    }
}

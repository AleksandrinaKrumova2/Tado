package com.tado.android.entities;

public class UserDetails {
    private String email;
    private boolean enabled;
    private ServerError[] errors;
    private Integer homeId;
    private String locale;
    private String name;
    private String type;
    private String username;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHomeId() {
        return this.homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }
}

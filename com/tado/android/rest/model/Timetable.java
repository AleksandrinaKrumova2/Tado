package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;

public class Timetable {
    @SerializedName("id")
    private int mId;
    @SerializedName("type")
    private String mType;

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }
}

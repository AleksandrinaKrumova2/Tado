package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class StateLookup {
    @SerializedName("defaultState")
    @Expose
    private boolean defaultState;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private StateLookupEnum type;
    @SerializedName("values")
    @Expose
    private List<String> values = new ArrayList();

    public enum StateLookupEnum {
        IN_PROGRESS,
        SUCCESS
    }

    public List<String> getValues() {
        return this.values;
    }

    public String getText() {
        return this.text;
    }

    public StateLookupEnum getType() {
        return this.type;
    }

    public Boolean isDefaultState() {
        return Boolean.valueOf(this.defaultState);
    }
}

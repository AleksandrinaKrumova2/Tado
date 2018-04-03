package com.tado.android.rest.model.hvac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Step {
    @SerializedName("deadEnd")
    @Expose
    private Boolean deadEnd;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subSteps")
    @Expose
    private List<List<SubStep>> subSteps = new ArrayList();

    public Integer getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getDeadEnd() {
        return this.deadEnd;
    }

    public List<List<SubStep>> getSubSteps() {
        return this.subSteps;
    }
}

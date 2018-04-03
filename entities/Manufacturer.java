package com.tado.android.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

public class Manufacturer implements Serializable {
    private int id;
    @Expose
    private String name;

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

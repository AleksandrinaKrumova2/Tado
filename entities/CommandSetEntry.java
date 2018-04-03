package com.tado.android.entities;

import java.io.Serializable;

public class CommandSetEntry implements Serializable, Comparable<CommandSetEntry> {
    private Double confidence;
    private int index;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Double getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public int compareTo(CommandSetEntry another) {
        return this.index - another.index;
    }
}

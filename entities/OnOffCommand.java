package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class OnOffCommand {
    private int candidateIndex;
    @Expose
    private String textToDisplay;

    public OnOffCommand(int candidateIndex, String textToDisplay) {
        this.candidateIndex = candidateIndex;
        this.textToDisplay = textToDisplay;
    }

    public int getCandidateIndex() {
        return this.candidateIndex;
    }

    public void setCandidateIndex(int candidateIndex) {
        this.candidateIndex = candidateIndex;
    }

    public String getTextToDisplay() {
        return this.textToDisplay;
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }
}

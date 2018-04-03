package com.tado.android.entities;

import com.google.gson.annotations.Expose;

public class CandidateRating {
    public static final String CONFIRM = "CONFIRM";
    public static final String REJECT = "REJECT";
    public static final String RESET = "RESET";
    private int candidateIndex;
    @Expose
    private String rating;

    public CandidateRating(int candidateIndex, String rating) {
        this.candidateIndex = candidateIndex;
        this.rating = rating;
    }

    public int getCandidateIndex() {
        return this.candidateIndex;
    }

    public void setCandidateIndex(int candidateIndex) {
        this.candidateIndex = candidateIndex;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

package com.tado.android.entities;

public class CandidateRatingResponse {
    private CommandSetEntry[] candidates;
    private ServerError[] errors;

    public ServerError[] getErrors() {
        return this.errors;
    }

    public void setErrors(ServerError[] errors) {
        this.errors = errors;
    }

    public CommandSetEntry[] getCandidates() {
        return this.candidates;
    }

    public void setCandidates(CommandSetEntry[] candidates) {
        this.candidates = candidates;
    }
}

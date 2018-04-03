package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnOffCandidateList {
    @SerializedName("candidates")
    private List<OnOffCandidate> candidates = new ArrayList();

    @ApiModelProperty(required = true, value = "")
    public List<OnOffCandidate> getCandidates() {
        return this.candidates;
    }

    public void setCandidates(List<OnOffCandidate> candidates) {
        this.candidates = candidates;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.candidates, ((OnOffCandidateList) o).candidates);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.candidates});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OnOffCandidateList {\n");
        sb.append("    candidates: ").append(toIndentedString(this.candidates)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

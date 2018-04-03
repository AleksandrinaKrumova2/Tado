package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class OnOffCandidate implements Comparable<OnOffCandidate> {
    @SerializedName("commandSet")
    private AcCommandSet commandSet = null;
    @SerializedName("confidence")
    private Double confidence = null;
    @SerializedName("index")
    private Integer index = null;

    @ApiModelProperty(required = true, value = "")
    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @ApiModelProperty(required = true, value = "")
    public Double getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    @ApiModelProperty(required = true, value = "")
    public AcCommandSet getCommandSet() {
        return this.commandSet;
    }

    public void setCommandSet(AcCommandSet commandSet) {
        this.commandSet = commandSet;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OnOffCandidate onOffCandidate = (OnOffCandidate) o;
        if (Util.equals(this.index, onOffCandidate.index) && Util.equals(this.confidence, onOffCandidate.confidence) && Util.equals(this.commandSet, onOffCandidate.commandSet)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.index, this.confidence, this.commandSet});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OnOffCandidate {\n");
        sb.append("    index: ").append(toIndentedString(this.index)).append("\n");
        sb.append("    confidence: ").append(toIndentedString(this.confidence)).append("\n");
        sb.append("    commandSet: ").append(toIndentedString(this.commandSet)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public int compareTo(OnOffCandidate another) {
        return this.index.intValue() - another.index.intValue();
    }
}

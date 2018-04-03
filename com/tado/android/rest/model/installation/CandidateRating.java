package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class CandidateRating {
    @SerializedName("rating")
    private RatingEnum rating = null;

    public enum RatingEnum {
        CONFIRM(com.tado.android.entities.CandidateRating.CONFIRM),
        REJECT(com.tado.android.entities.CandidateRating.REJECT),
        RESET(com.tado.android.entities.CandidateRating.RESET);
        
        private String value;

        private RatingEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public CandidateRating(RatingEnum rating) {
        this.rating = rating;
    }

    @ApiModelProperty(required = true, value = "")
    public RatingEnum getRating() {
        return this.rating;
    }

    public void setRating(RatingEnum rating) {
        this.rating = rating;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.rating, ((CandidateRating) o).rating);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.rating});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CandidateRating {\n");
        sb.append("    rating: ").append(toIndentedString(this.rating)).append("\n");
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

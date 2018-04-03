package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvailableCommandSets {
    @SerializedName("availableCommandSets")
    private List<AcCommandSet> availableCommandSets = new ArrayList();

    @ApiModelProperty(required = true, value = "")
    public List<AcCommandSet> getAvailableCommandSets() {
        return this.availableCommandSets;
    }

    public void setAvailableCommandSets(List<AcCommandSet> availableCommandSets) {
        this.availableCommandSets = availableCommandSets;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AvailableCommandSets availableCommandSets = (AvailableCommandSets) o;
        return Util.equals(availableCommandSets, availableCommandSets.availableCommandSets);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.availableCommandSets});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AvailableCommandSets {\n");
        sb.append("    availableCommandSets: ").append(toIndentedString(this.availableCommandSets)).append("\n");
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

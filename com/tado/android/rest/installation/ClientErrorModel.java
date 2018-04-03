package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientErrorModel {
    @SerializedName("errors")
    private List<ClientError> errors = new ArrayList();

    @ApiModelProperty("")
    public List<ClientError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ClientError> errors) {
        this.errors = errors;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.errors, ((ClientErrorModel) o).errors);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.errors});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientErrorModel {\n");
        sb.append("    errors: ").append(toIndentedString(this.errors)).append("\n");
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

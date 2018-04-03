package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class ClientError {
    @SerializedName("code")
    private String code = null;
    @SerializedName("title")
    private String title = null;

    @ApiModelProperty(required = true, value = "Error identifier\n")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ApiModelProperty(required = true, value = "Detailed error message. Just for informational purposes. Might change at any time!\n")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientError clientError = (ClientError) o;
        if (Util.equals(this.code, clientError.code) && Util.equals(this.title, clientError.title)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.code, this.title});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientError {\n");
        sb.append("    code: ").append(toIndentedString(this.code)).append("\n");
        sb.append("    title: ").append(toIndentedString(this.title)).append("\n");
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

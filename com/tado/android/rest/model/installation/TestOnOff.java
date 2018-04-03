package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class TestOnOff {
    @SerializedName("textToDisplay")
    private String textToDisplay = null;

    public TestOnOff(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    @ApiModelProperty(required = true, value = "")
    public String getTextToDisplay() {
        return this.textToDisplay;
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.textToDisplay, ((TestOnOff) o).textToDisplay);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.textToDisplay});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TestOnOff {\n");
        sb.append("    textToDisplay: ").append(toIndentedString(this.textToDisplay)).append("\n");
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

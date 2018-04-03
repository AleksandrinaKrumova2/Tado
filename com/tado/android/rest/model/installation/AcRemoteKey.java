package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.entities.Key;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class AcRemoteKey {
    @SerializedName("key")
    private KeyEnum key = null;

    public enum KeyEnum {
        POWER(Key.POWER),
        TEMP_UP(Key.TEMP_UP),
        TEMP_DOWN(Key.TEMP_DOWN);
        
        private String value;

        private KeyEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public AcRemoteKey(KeyEnum key) {
        this.key = key;
    }

    @ApiModelProperty(required = true, value = "")
    public KeyEnum getKey() {
        return this.key;
    }

    public void setKey(KeyEnum key) {
        this.key = key;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Util.equals(this.key, ((AcRemoteKey) o).key);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.key});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AcRemoteKey {\n");
        sb.append("    key: ").append(toIndentedString(this.key)).append("\n");
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

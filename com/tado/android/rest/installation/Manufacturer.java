package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

public class Manufacturer implements Serializable {
    @SerializedName("id")
    private Integer id = null;
    @SerializedName("name")
    private String name = null;

    public Manufacturer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    @ApiModelProperty("")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty("")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manufacturer manufacturer = (Manufacturer) o;
        if (Util.equals(this.id, manufacturer.id) && Util.equals(this.name, manufacturer.name)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.name});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Manufacturer {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
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

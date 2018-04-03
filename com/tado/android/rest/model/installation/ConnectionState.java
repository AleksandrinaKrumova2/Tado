package com.tado.android.rest.model.installation;

import com.google.gson.annotations.SerializedName;
import com.tado.android.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ConnectionState implements Serializable {
    @SerializedName("value")
    private Boolean isConnected = null;
    @SerializedName("timestamp")
    private Date timestamp = null;

    @ApiModelProperty(required = true, value = "")
    public Boolean isConnected() {
        return this.isConnected;
    }

    public void setValue(Boolean value) {
        this.isConnected = value;
    }

    @ApiModelProperty(required = true, value = "Measurement timestamp\n")
    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectionState genericDataPoint = (ConnectionState) o;
        if (Util.equals(this.isConnected, genericDataPoint.isConnected) && Util.equals(this.timestamp, genericDataPoint.timestamp)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.isConnected, this.timestamp});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConnectionState {\n");
        sb.append("    isConnected: ").append(toIndentedString(this.isConnected)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(this.timestamp)).append("\n");
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

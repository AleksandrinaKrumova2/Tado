package com.tado.android.location;

public enum PostState {
    UNKNOWN("?"),
    POSTED("+"),
    FAILED("-"),
    FILTERED("!");
    
    private String value;

    private PostState(String value) {
        this.value = value;
    }

    public static PostState fromValue(String s) {
        Object obj = -1;
        switch (s.hashCode()) {
            case 33:
                if (s.equals("!")) {
                    obj = 3;
                    break;
                }
                break;
            case 43:
                if (s.equals("+")) {
                    obj = 1;
                    break;
                }
                break;
            case 45:
                if (s.equals("-")) {
                    obj = 2;
                    break;
                }
                break;
            case 63:
                if (s.equals("?")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return UNKNOWN;
            case 1:
                return POSTED;
            case 2:
                return FAILED;
            case 3:
                return FILTERED;
            default:
                return UNKNOWN;
        }
    }

    public String toString() {
        return this.value;
    }
}

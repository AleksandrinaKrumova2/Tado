package com.tado.android.utils;

import java.util.HashMap;
import java.util.Iterator;

public class FormParams extends HashMap<String, String> {
    private static final long serialVersionUID = 1;

    public String toString() {
        String result = "";
        Iterator<String> it = keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            result = result + key + "=" + ((String) get(key));
            if (it.hasNext()) {
                result = result + "&";
            }
            it.remove();
        }
        return result;
    }
}

package com.tado.android.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date> {
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        int i = 0;
        String date = element.getAsString();
        String[] acceptedFormats = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'"};
        int length = acceptedFormats.length;
        while (i < length) {
            SimpleDateFormat formatter = new SimpleDateFormat(acceptedFormats[i]);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                Snitcher.start().toCrashlytics().logException(e);
                i++;
            }
        }
        return null;
    }
}

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import com.google.android.gms.internal.zzdjc;
import com.google.android.gms.internal.zzdje;
import com.google.android.gms.internal.zzdjf;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzdb {
    private static Object zzak(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            Map hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, zzak(jSONObject.get(str)));
            }
            return hashMap;
        }
    }

    public static zzdje zzls(String str) throws JSONException {
        zzbs zzam = zzgk.zzam(zzak(new JSONObject(str)));
        zzdjf zzbjd = zzdje.zzbjd();
        for (int i = 0; i < zzam.zzym.length; i++) {
            zzbjd.zzc(zzdjc.zzbjb().zzb(zzbh.INSTANCE_NAME.toString(), zzam.zzym[i]).zzb(zzbh.FUNCTION.toString(), zzgk.zzmc(zzt.zzbdr())).zzb(zzt.zzbds(), zzam.zzyn[i]).zzbjc());
        }
        return zzbjd.zzbjf();
    }
}

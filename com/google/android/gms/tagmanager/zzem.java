package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzem extends zzga {
    private static final String ID = zzbg.REGEX.toString();
    private static final String zzkhy = zzbh.IGNORE_CASE.toString();

    public zzem() {
        super(ID);
    }

    protected final boolean zza(String str, String str2, Map<String, zzbs> map) {
        try {
            return Pattern.compile(str2, zzgk.zzf((zzbs) map.get(zzkhy)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class zzde extends zzbr {
    private static final String ID = zzbg.LANGUAGE.toString();

    public zzde() {
        super(ID, new String[0]);
    }

    public final boolean zzbdp() {
        return false;
    }

    public final /* bridge */ /* synthetic */ String zzbew() {
        return super.zzbew();
    }

    public final /* bridge */ /* synthetic */ Set zzbex() {
        return super.zzbex();
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzgk.zzbgs();
        }
        String language = locale.getLanguage();
        return language == null ? zzgk.zzbgs() : zzgk.zzam(language.toLowerCase());
    }
}

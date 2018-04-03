package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbq;
import com.google.android.gms.internal.zzbs;
import com.google.android.gms.internal.zzdja;
import com.google.android.gms.internal.zzdjc;
import com.google.android.gms.internal.zzdje;
import com.google.android.gms.internal.zzdjg;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzfc {
    private static final zzea<zzbs> zzkil = new zzea(zzgk.zzbgs(), true);
    private final DataLayer zzkde;
    private final zzdje zzkim;
    private final zzbo zzkin;
    private final Map<String, zzbr> zzkio;
    private final Map<String, zzbr> zzkip;
    private final Map<String, zzbr> zzkiq;
    private final zzp<zzdjc, zzea<zzbs>> zzkir;
    private final zzp<String, zzfi> zzkis;
    private final Set<zzdjg> zzkit;
    private final Map<String, zzfj> zzkiu;
    private volatile String zzkiv;
    private int zzkiw;

    public zzfc(Context context, zzdje com_google_android_gms_internal_zzdje, DataLayer dataLayer, zzan com_google_android_gms_tagmanager_zzan, zzan com_google_android_gms_tagmanager_zzan2, zzbo com_google_android_gms_tagmanager_zzbo) {
        if (com_google_android_gms_internal_zzdje == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzkim = com_google_android_gms_internal_zzdje;
        this.zzkit = new HashSet(com_google_android_gms_internal_zzdje.zzbii());
        this.zzkde = dataLayer;
        this.zzkin = com_google_android_gms_tagmanager_zzbo;
        zzs com_google_android_gms_tagmanager_zzfd = new zzfd(this);
        zzq com_google_android_gms_tagmanager_zzq = new zzq();
        this.zzkir = zzq.zza(1048576, com_google_android_gms_tagmanager_zzfd);
        com_google_android_gms_tagmanager_zzfd = new zzfe(this);
        com_google_android_gms_tagmanager_zzq = new zzq();
        this.zzkis = zzq.zza(1048576, com_google_android_gms_tagmanager_zzfd);
        this.zzkio = new HashMap();
        zzb(new zzm(context));
        zzb(new zzam(com_google_android_gms_tagmanager_zzan2));
        zzb(new zzaz(dataLayer));
        zzb(new zzgl(context, dataLayer));
        this.zzkip = new HashMap();
        zzc(new zzak());
        zzc(new zzbl());
        zzc(new zzbm());
        zzc(new zzbt());
        zzc(new zzbu());
        zzc(new zzdf());
        zzc(new zzdg());
        zzc(new zzem());
        zzc(new zzfz());
        this.zzkiq = new HashMap();
        zza(new zze(context));
        zza(new zzf(context));
        zza(new zzh(context));
        zza(new zzi(context));
        zza(new zzj(context));
        zza(new zzk(context));
        zza(new zzl(context));
        zza(new zzt());
        zza(new zzaj(this.zzkim.getVersion()));
        zza(new zzam(com_google_android_gms_tagmanager_zzan));
        zza(new zzas(dataLayer));
        zza(new zzbc(context));
        zza(new zzbd());
        zza(new zzbk());
        zza(new zzbp(this));
        zza(new zzbv());
        zza(new zzbw());
        zza(new zzcw(context));
        zza(new zzcy());
        zza(new zzde());
        zza(new zzdl());
        zza(new zzdn(context));
        zza(new zzeb());
        zza(new zzef());
        zza(new zzej());
        zza(new zzel());
        zza(new zzen(context));
        zza(new zzfk());
        zza(new zzfl());
        zza(new zzgf());
        zza(new zzgm());
        this.zzkiu = new HashMap();
        for (zzdjg com_google_android_gms_internal_zzdjg : this.zzkit) {
            int i;
            for (i = 0; i < com_google_android_gms_internal_zzdjg.zzbjg().size(); i++) {
                zzdjc com_google_android_gms_internal_zzdjc = (zzdjc) com_google_android_gms_internal_zzdjg.zzbjg().get(i);
                zzfj zzg = zzg(this.zzkiu, zza(com_google_android_gms_internal_zzdjc));
                zzg.zza(com_google_android_gms_internal_zzdjg);
                zzg.zza(com_google_android_gms_internal_zzdjg, com_google_android_gms_internal_zzdjc);
                zzg.zza(com_google_android_gms_internal_zzdjg, "Unknown");
            }
            for (i = 0; i < com_google_android_gms_internal_zzdjg.zzbjh().size(); i++) {
                com_google_android_gms_internal_zzdjc = (zzdjc) com_google_android_gms_internal_zzdjg.zzbjh().get(i);
                zzg = zzg(this.zzkiu, zza(com_google_android_gms_internal_zzdjc));
                zzg.zza(com_google_android_gms_internal_zzdjg);
                zzg.zzb(com_google_android_gms_internal_zzdjg, com_google_android_gms_internal_zzdjc);
                zzg.zzb(com_google_android_gms_internal_zzdjg, "Unknown");
            }
        }
        for (Entry entry : this.zzkim.zzbje().entrySet()) {
            for (zzdjc com_google_android_gms_internal_zzdjc2 : (List) entry.getValue()) {
                if (!zzgk.zzf((zzbs) com_google_android_gms_internal_zzdjc2.zzbik().get(zzbh.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzg(this.zzkiu, (String) entry.getKey()).zzb(com_google_android_gms_internal_zzdjc2);
                }
            }
        }
    }

    private final zzea<zzbs> zza(zzbs com_google_android_gms_internal_zzbs, Set<String> set, zzgn com_google_android_gms_tagmanager_zzgn) {
        if (!com_google_android_gms_internal_zzbs.zzyu) {
            return new zzea(com_google_android_gms_internal_zzbs, true);
        }
        zzbs zzj;
        int i;
        zzea zza;
        String str;
        String valueOf;
        switch (com_google_android_gms_internal_zzbs.type) {
            case 2:
                zzj = zzdja.zzj(com_google_android_gms_internal_zzbs);
                zzj.zzyl = new zzbs[com_google_android_gms_internal_zzbs.zzyl.length];
                for (i = 0; i < com_google_android_gms_internal_zzbs.zzyl.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzbs.zzyl[i], (Set) set, com_google_android_gms_tagmanager_zzgn.zzel(i));
                    if (zza == zzkil) {
                        return zzkil;
                    }
                    zzj.zzyl[i] = (zzbs) zza.getObject();
                }
                return new zzea(zzj, false);
            case 3:
                zzj = zzdja.zzj(com_google_android_gms_internal_zzbs);
                if (com_google_android_gms_internal_zzbs.zzym.length != com_google_android_gms_internal_zzbs.zzyn.length) {
                    str = "Invalid serving value: ";
                    valueOf = String.valueOf(com_google_android_gms_internal_zzbs.toString());
                    zzdj.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return zzkil;
                }
                zzj.zzym = new zzbs[com_google_android_gms_internal_zzbs.zzym.length];
                zzj.zzyn = new zzbs[com_google_android_gms_internal_zzbs.zzym.length];
                for (i = 0; i < com_google_android_gms_internal_zzbs.zzym.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzbs.zzym[i], (Set) set, com_google_android_gms_tagmanager_zzgn.zzem(i));
                    zzea zza2 = zza(com_google_android_gms_internal_zzbs.zzyn[i], (Set) set, com_google_android_gms_tagmanager_zzgn.zzen(i));
                    if (zza == zzkil || zza2 == zzkil) {
                        return zzkil;
                    }
                    zzj.zzym[i] = (zzbs) zza.getObject();
                    zzj.zzyn[i] = (zzbs) zza2.getObject();
                }
                return new zzea(zzj, false);
            case 4:
                if (set.contains(com_google_android_gms_internal_zzbs.zzyo)) {
                    valueOf = com_google_android_gms_internal_zzbs.zzyo;
                    str = set.toString();
                    zzdj.m10e(new StringBuilder((String.valueOf(valueOf).length() + 79) + String.valueOf(str).length()).append("Macro cycle detected.  Current macro reference: ").append(valueOf).append(".  Previous macro references: ").append(str).append(".").toString());
                    return zzkil;
                }
                set.add(com_google_android_gms_internal_zzbs.zzyo);
                zzea<zzbs> zza3 = zzgo.zza(zza(com_google_android_gms_internal_zzbs.zzyo, (Set) set, com_google_android_gms_tagmanager_zzgn.zzbfj()), com_google_android_gms_internal_zzbs.zzyt);
                set.remove(com_google_android_gms_internal_zzbs.zzyo);
                return zza3;
            case 7:
                zzj = zzdja.zzj(com_google_android_gms_internal_zzbs);
                zzj.zzys = new zzbs[com_google_android_gms_internal_zzbs.zzys.length];
                for (i = 0; i < com_google_android_gms_internal_zzbs.zzys.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzbs.zzys[i], (Set) set, com_google_android_gms_tagmanager_zzgn.zzeo(i));
                    if (zza == zzkil) {
                        return zzkil;
                    }
                    zzj.zzys[i] = (zzbs) zza.getObject();
                }
                return new zzea(zzj, false);
            default:
                zzdj.m10e("Unknown type: " + com_google_android_gms_internal_zzbs.type);
                return zzkil;
        }
    }

    private final zzea<Boolean> zza(zzdjc com_google_android_gms_internal_zzdjc, Set<String> set, zzeo com_google_android_gms_tagmanager_zzeo) {
        zzea zza = zza(this.zzkip, com_google_android_gms_internal_zzdjc, (Set) set, com_google_android_gms_tagmanager_zzeo);
        Boolean zzf = zzgk.zzf((zzbs) zza.getObject());
        zzgk.zzam(zzf);
        return new zzea(zzf, zza.zzbfk());
    }

    private final zzea<Boolean> zza(zzdjg com_google_android_gms_internal_zzdjg, Set<String> set, zzer com_google_android_gms_tagmanager_zzer) {
        boolean z = true;
        for (zzdjc zza : com_google_android_gms_internal_zzdjg.zzbin()) {
            zzea zza2 = zza(zza, (Set) set, com_google_android_gms_tagmanager_zzer.zzbfc());
            if (((Boolean) zza2.getObject()).booleanValue()) {
                zzgk.zzam(Boolean.valueOf(false));
                return new zzea(Boolean.valueOf(false), zza2.zzbfk());
            }
            boolean z2 = z && zza2.zzbfk();
            z = z2;
        }
        for (zzdjc zza3 : com_google_android_gms_internal_zzdjg.zzbim()) {
            zza2 = zza(zza3, (Set) set, com_google_android_gms_tagmanager_zzer.zzbfd());
            if (((Boolean) zza2.getObject()).booleanValue()) {
                z = z && zza2.zzbfk();
            } else {
                zzgk.zzam(Boolean.valueOf(false));
                return new zzea(Boolean.valueOf(false), zza2.zzbfk());
            }
        }
        zzgk.zzam(Boolean.valueOf(true));
        return new zzea(Boolean.valueOf(true), z);
    }

    private final zzea<zzbs> zza(String str, Set<String> set, zzdm com_google_android_gms_tagmanager_zzdm) {
        this.zzkiw++;
        zzfi com_google_android_gms_tagmanager_zzfi = (zzfi) this.zzkis.get(str);
        if (com_google_android_gms_tagmanager_zzfi != null) {
            zza(com_google_android_gms_tagmanager_zzfi.zzbfy(), (Set) set);
            this.zzkiw--;
            return com_google_android_gms_tagmanager_zzfi.zzbfx();
        }
        zzfj com_google_android_gms_tagmanager_zzfj = (zzfj) this.zzkiu.get(str);
        if (com_google_android_gms_tagmanager_zzfj == null) {
            String zzbfw = zzbfw();
            zzdj.m10e(new StringBuilder((String.valueOf(zzbfw).length() + 15) + String.valueOf(str).length()).append(zzbfw).append("Invalid macro: ").append(str).toString());
            this.zzkiw--;
            return zzkil;
        }
        zzdjc zzbge;
        zzea zza = zza(str, com_google_android_gms_tagmanager_zzfj.zzbfz(), com_google_android_gms_tagmanager_zzfj.zzbga(), com_google_android_gms_tagmanager_zzfj.zzbgb(), com_google_android_gms_tagmanager_zzfj.zzbgd(), com_google_android_gms_tagmanager_zzfj.zzbgc(), set, com_google_android_gms_tagmanager_zzdm.zzbek());
        if (((Set) zza.getObject()).isEmpty()) {
            zzbge = com_google_android_gms_tagmanager_zzfj.zzbge();
        } else {
            if (((Set) zza.getObject()).size() > 1) {
                zzbfw = zzbfw();
                zzdj.zzcu(new StringBuilder((String.valueOf(zzbfw).length() + 37) + String.valueOf(str).length()).append(zzbfw).append("Multiple macros active for macroName ").append(str).toString());
            }
            zzbge = (zzdjc) ((Set) zza.getObject()).iterator().next();
        }
        if (zzbge == null) {
            this.zzkiw--;
            return zzkil;
        }
        zzea zza2 = zza(this.zzkiq, zzbge, (Set) set, com_google_android_gms_tagmanager_zzdm.zzbfb());
        boolean z = zza.zzbfk() && zza2.zzbfk();
        zzea<zzbs> com_google_android_gms_tagmanager_zzea = zza2 == zzkil ? zzkil : new zzea((zzbs) zza2.getObject(), z);
        zzbs zzbfy = zzbge.zzbfy();
        if (com_google_android_gms_tagmanager_zzea.zzbfk()) {
            this.zzkis.zzf(str, new zzfi(com_google_android_gms_tagmanager_zzea, zzbfy));
        }
        zza(zzbfy, (Set) set);
        this.zzkiw--;
        return com_google_android_gms_tagmanager_zzea;
    }

    private final zzea<Set<zzdjc>> zza(String str, Set<zzdjg> set, Map<zzdjg, List<zzdjc>> map, Map<zzdjg, List<String>> map2, Map<zzdjg, List<zzdjc>> map3, Map<zzdjg, List<String>> map4, Set<String> set2, zzfb com_google_android_gms_tagmanager_zzfb) {
        return zza((Set) set, (Set) set2, new zzff(this, map, map2, map3, map4), com_google_android_gms_tagmanager_zzfb);
    }

    private final zzea<zzbs> zza(Map<String, zzbr> map, zzdjc com_google_android_gms_internal_zzdjc, Set<String> set, zzeo com_google_android_gms_tagmanager_zzeo) {
        boolean z = true;
        zzbs com_google_android_gms_internal_zzbs = (zzbs) com_google_android_gms_internal_zzdjc.zzbik().get(zzbh.FUNCTION.toString());
        if (com_google_android_gms_internal_zzbs == null) {
            zzdj.m10e("No function id in properties");
            return zzkil;
        }
        String str = com_google_android_gms_internal_zzbs.zzyp;
        zzbr com_google_android_gms_tagmanager_zzbr = (zzbr) map.get(str);
        if (com_google_android_gms_tagmanager_zzbr == null) {
            zzdj.m10e(String.valueOf(str).concat(" has no backing implementation."));
            return zzkil;
        }
        zzea<zzbs> com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs = (zzea) this.zzkir.get(com_google_android_gms_internal_zzdjc);
        if (com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs != null) {
            return com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs;
        }
        Map hashMap = new HashMap();
        boolean z2 = true;
        for (Entry entry : com_google_android_gms_internal_zzdjc.zzbik().entrySet()) {
            zzea zza = zza((zzbs) entry.getValue(), (Set) set, com_google_android_gms_tagmanager_zzeo.zzlt((String) entry.getKey()).zza((zzbs) entry.getValue()));
            if (zza == zzkil) {
                return zzkil;
            }
            boolean z3;
            if (zza.zzbfk()) {
                com_google_android_gms_internal_zzdjc.zza((String) entry.getKey(), (zzbs) zza.getObject());
                z3 = z2;
            } else {
                z3 = false;
            }
            hashMap.put((String) entry.getKey(), (zzbs) zza.getObject());
            z2 = z3;
        }
        if (com_google_android_gms_tagmanager_zzbr.zzd(hashMap.keySet())) {
            if (!(z2 && com_google_android_gms_tagmanager_zzbr.zzbdp())) {
                z = false;
            }
            com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs = new zzea(com_google_android_gms_tagmanager_zzbr.zzv(hashMap), z);
            if (!z) {
                return com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs;
            }
            this.zzkir.zzf(com_google_android_gms_internal_zzdjc, com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs);
            return com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs;
        }
        String valueOf = String.valueOf(com_google_android_gms_tagmanager_zzbr.zzbex());
        String valueOf2 = String.valueOf(hashMap.keySet());
        zzdj.m10e(new StringBuilder(((String.valueOf(str).length() + 43) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length()).append("Incorrect keys for function ").append(str).append(" required ").append(valueOf).append(" had ").append(valueOf2).toString());
        return zzkil;
    }

    private final zzea<Set<zzdjc>> zza(Set<zzdjg> set, Set<String> set2, zzfh com_google_android_gms_tagmanager_zzfh, zzfb com_google_android_gms_tagmanager_zzfb) {
        Set hashSet = new HashSet();
        Collection hashSet2 = new HashSet();
        boolean z = true;
        for (zzdjg com_google_android_gms_internal_zzdjg : set) {
            zzer zzbfi = com_google_android_gms_tagmanager_zzfb.zzbfi();
            zzea zza = zza(com_google_android_gms_internal_zzdjg, (Set) set2, zzbfi);
            if (((Boolean) zza.getObject()).booleanValue()) {
                com_google_android_gms_tagmanager_zzfh.zza(com_google_android_gms_internal_zzdjg, hashSet, hashSet2, zzbfi);
            }
            boolean z2 = z && zza.zzbfk();
            z = z2;
        }
        hashSet.removeAll(hashSet2);
        return new zzea(hashSet, z);
    }

    private static String zza(zzdjc com_google_android_gms_internal_zzdjc) {
        return zzgk.zzb((zzbs) com_google_android_gms_internal_zzdjc.zzbik().get(zzbh.INSTANCE_NAME.toString()));
    }

    private final void zza(zzbs com_google_android_gms_internal_zzbs, Set<String> set) {
        if (com_google_android_gms_internal_zzbs != null) {
            zzea zza = zza(com_google_android_gms_internal_zzbs, (Set) set, new zzdy());
            if (zza != zzkil) {
                Object zzg = zzgk.zzg((zzbs) zza.getObject());
                if (zzg instanceof Map) {
                    this.zzkde.push((Map) zzg);
                } else if (zzg instanceof List) {
                    for (Object zzg2 : (List) zzg2) {
                        if (zzg2 instanceof Map) {
                            this.zzkde.push((Map) zzg2);
                        } else {
                            zzdj.zzcu("pushAfterEvaluate: value not a Map");
                        }
                    }
                } else {
                    zzdj.zzcu("pushAfterEvaluate: value not a Map or List");
                }
            }
        }
    }

    private final void zza(zzbr com_google_android_gms_tagmanager_zzbr) {
        zza(this.zzkiq, com_google_android_gms_tagmanager_zzbr);
    }

    private static void zza(Map<String, zzbr> map, zzbr com_google_android_gms_tagmanager_zzbr) {
        if (map.containsKey(com_google_android_gms_tagmanager_zzbr.zzbew())) {
            String str = "Duplicate function type name: ";
            String valueOf = String.valueOf(com_google_android_gms_tagmanager_zzbr.zzbew());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        map.put(com_google_android_gms_tagmanager_zzbr.zzbew(), com_google_android_gms_tagmanager_zzbr);
    }

    private final void zzb(zzbr com_google_android_gms_tagmanager_zzbr) {
        zza(this.zzkio, com_google_android_gms_tagmanager_zzbr);
    }

    private final String zzbfw() {
        if (this.zzkiw <= 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toString(this.zzkiw));
        for (int i = 2; i < this.zzkiw; i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(": ");
        return stringBuilder.toString();
    }

    private final void zzc(zzbr com_google_android_gms_tagmanager_zzbr) {
        zza(this.zzkip, com_google_android_gms_tagmanager_zzbr);
    }

    private static zzfj zzg(Map<String, zzfj> map, String str) {
        zzfj com_google_android_gms_tagmanager_zzfj = (zzfj) map.get(str);
        if (com_google_android_gms_tagmanager_zzfj != null) {
            return com_google_android_gms_tagmanager_zzfj;
        }
        com_google_android_gms_tagmanager_zzfj = new zzfj();
        map.put(str, com_google_android_gms_tagmanager_zzfj);
        return com_google_android_gms_tagmanager_zzfj;
    }

    private final synchronized void zzlx(String str) {
        this.zzkiv = str;
    }

    public final synchronized void zzam(List<zzbq> list) {
        for (zzbq com_google_android_gms_internal_zzbq : list) {
            if (com_google_android_gms_internal_zzbq.name == null || !com_google_android_gms_internal_zzbq.name.startsWith("gaExperiment:")) {
                String valueOf = String.valueOf(com_google_android_gms_internal_zzbq);
                zzdj.m11v(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Ignored supplemental: ").append(valueOf).toString());
            } else {
                zzbq.zza(this.zzkde, com_google_android_gms_internal_zzbq);
            }
        }
    }

    final synchronized String zzbfv() {
        return this.zzkiv;
    }

    public final synchronized void zzld(String str) {
        zzlx(str);
        zzar zzbev = this.zzkin.zzln(str).zzbev();
        for (zzdjc zza : (Set) zza(this.zzkit, new HashSet(), new zzfg(this), zzbev.zzbek()).getObject()) {
            zza(this.zzkio, zza, new HashSet(), zzbev.zzbej());
        }
        zzlx(null);
    }

    public final zzea<zzbs> zzlw(String str) {
        this.zzkiw = 0;
        return zza(str, new HashSet(), this.zzkin.zzlm(str).zzbeu());
    }
}

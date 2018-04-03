package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class zzdjg {
    private final List<zzdjc> zzksi;
    private final List<zzdjc> zzksj;
    private final List<zzdjc> zzksk;
    private final List<zzdjc> zzksl;
    private final List<zzdjc> zzktq;
    private final List<zzdjc> zzktr;
    private final List<String> zzkts;
    private final List<String> zzktt;
    private final List<String> zzktu;
    private final List<String> zzktv;

    private zzdjg(List<zzdjc> list, List<zzdjc> list2, List<zzdjc> list3, List<zzdjc> list4, List<zzdjc> list5, List<zzdjc> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
        this.zzksi = Collections.unmodifiableList(list);
        this.zzksj = Collections.unmodifiableList(list2);
        this.zzksk = Collections.unmodifiableList(list3);
        this.zzksl = Collections.unmodifiableList(list4);
        this.zzktq = Collections.unmodifiableList(list5);
        this.zzktr = Collections.unmodifiableList(list6);
        this.zzkts = Collections.unmodifiableList(list7);
        this.zzktt = Collections.unmodifiableList(list8);
        this.zzktu = Collections.unmodifiableList(list9);
        this.zzktv = Collections.unmodifiableList(list10);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzksi);
        String valueOf2 = String.valueOf(this.zzksj);
        String valueOf3 = String.valueOf(this.zzksk);
        String valueOf4 = String.valueOf(this.zzksl);
        String valueOf5 = String.valueOf(this.zzktq);
        String valueOf6 = String.valueOf(this.zzktr);
        return new StringBuilder((((((String.valueOf(valueOf).length() + 102) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()).append("Positive predicates: ").append(valueOf).append("  Negative predicates: ").append(valueOf2).append("  Add tags: ").append(valueOf3).append("  Remove tags: ").append(valueOf4).append("  Add macros: ").append(valueOf5).append("  Remove macros: ").append(valueOf6).toString();
    }

    public final List<zzdjc> zzbim() {
        return this.zzksi;
    }

    public final List<zzdjc> zzbin() {
        return this.zzksj;
    }

    public final List<zzdjc> zzbio() {
        return this.zzksk;
    }

    public final List<zzdjc> zzbip() {
        return this.zzksl;
    }

    public final List<zzdjc> zzbjg() {
        return this.zzktq;
    }

    public final List<zzdjc> zzbjh() {
        return this.zzktr;
    }
}

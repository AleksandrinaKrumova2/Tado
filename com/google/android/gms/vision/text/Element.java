package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.zzdkn;
import java.util.ArrayList;
import java.util.List;

public class Element implements Text {
    private zzdkn zzkxu;

    Element(zzdkn com_google_android_gms_internal_zzdkn) {
        this.zzkxu = com_google_android_gms_internal_zzdkn;
    }

    public Rect getBoundingBox() {
        return zzc.zza((Text) this);
    }

    public List<? extends Text> getComponents() {
        return new ArrayList();
    }

    public Point[] getCornerPoints() {
        return zzc.zza(this.zzkxu.zzkyf);
    }

    public String getLanguage() {
        return this.zzkxu.zzkxz;
    }

    public String getValue() {
        return this.zzkxu.zzkyi;
    }
}

package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.zzdke;
import com.google.android.gms.internal.zzdkn;
import java.util.ArrayList;
import java.util.List;

public class Line implements Text {
    private zzdke zzkxv;
    private List<Element> zzkxw;

    Line(zzdke com_google_android_gms_internal_zzdke) {
        this.zzkxv = com_google_android_gms_internal_zzdke;
    }

    public float getAngle() {
        return this.zzkxv.zzkyf.zzkyd;
    }

    public Rect getBoundingBox() {
        return zzc.zza((Text) this);
    }

    public List<? extends Text> getComponents() {
        if (this.zzkxv.zzkye.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzkxw == null) {
            this.zzkxw = new ArrayList(this.zzkxv.zzkye.length);
            for (zzdkn element : this.zzkxv.zzkye) {
                this.zzkxw.add(new Element(element));
            }
        }
        return this.zzkxw;
    }

    public Point[] getCornerPoints() {
        return zzc.zza(this.zzkxv.zzkyf);
    }

    public String getLanguage() {
        return this.zzkxv.zzkxz;
    }

    public String getValue() {
        return this.zzkxv.zzkyi;
    }

    public boolean isVertical() {
        return this.zzkxv.zzkyl;
    }
}

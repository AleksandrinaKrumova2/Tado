package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.tado.android.views.MorphingButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzec implements zzcc {
    private static final String zzdug = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    private final Context mContext;
    private zzd zzata;
    private final zzee zzkhb;
    private volatile zzbe zzkhc;
    private final zzcd zzkhd;
    private final String zzkhe;
    private long zzkhf;
    private final int zzkhg;

    zzec(zzcd com_google_android_gms_tagmanager_zzcd, Context context) {
        this(com_google_android_gms_tagmanager_zzcd, context, "gtm_urls.db", MorphingButton.COLLAPSE_ANIMATION_DELAY);
    }

    private zzec(zzcd com_google_android_gms_tagmanager_zzcd, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.zzkhe = str;
        this.zzkhd = com_google_android_gms_tagmanager_zzcd;
        this.zzata = zzh.zzamg();
        this.zzkhb = new zzee(this, this.mContext, this.zzkhe);
        this.zzkhc = new zzfv(this.mContext, new zzed(this));
        this.zzkhf = 0;
        this.zzkhg = MorphingButton.COLLAPSE_ANIMATION_DELAY;
    }

    private final int zzbfl() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzlk = zzlk("Error opening database for getNumStoredHits.");
        if (zzlk != null) {
            try {
                cursor = zzlk.rawQuery("SELECT COUNT(*) from gtm_hits", null);
                if (cursor.moveToFirst()) {
                    i = (int) cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                zzdj.zzcu("Error getting numStoredHits");
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    private final int zzbfm() {
        int count;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        SQLiteDatabase zzlk = zzlk("Error opening database for getNumStoredHits.");
        if (zzlk == null) {
            return 0;
        }
        try {
            Cursor query = zzlk.query("gtm_hits", new String[]{"hit_id", "hit_first_send_time"}, "hit_first_send_time=0", null, null, null, null);
            try {
                count = query.getCount();
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e) {
                cursor = query;
                try {
                    zzdj.zzcu("Error getting num untried hits");
                    if (cursor == null) {
                        count = 0;
                    } else {
                        cursor.close();
                        count = 0;
                    }
                    return count;
                } catch (Throwable th2) {
                    cursor2 = cursor;
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            cursor = null;
            zzdj.zzcu("Error getting num untried hits");
            if (cursor == null) {
                cursor.close();
                count = 0;
            } else {
                count = 0;
            }
            return count;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        return count;
    }

    private final void zze(String[] strArr) {
        boolean z = true;
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzlk = zzlk("Error opening database for deleteHits.");
            if (zzlk != null) {
                try {
                    zzlk.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                    zzcd com_google_android_gms_tagmanager_zzcd = this.zzkhd;
                    if (zzbfl() != 0) {
                        z = false;
                    }
                    com_google_android_gms_tagmanager_zzcd.zzbu(z);
                } catch (SQLiteException e) {
                    zzdj.zzcu("Error deleting hits");
                }
            }
        }
    }

    private final List<String> zzep(int i) {
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        List<String> arrayList = new ArrayList();
        if (i <= 0) {
            zzdj.zzcu("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase zzlk = zzlk("Error opening database for peekHitIds.");
        if (zzlk == null) {
            return arrayList;
        }
        Cursor query;
        try {
            query = zzlk.query("gtm_hits", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", new Object[]{"hit_id"}), Integer.toString(i));
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(query.getLong(0)));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    str = "Error in peekHits fetching hitIds: ";
                    valueOf = String.valueOf(e.getMessage());
                    zzdj.zzcu(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            str = "Error in peekHits fetching hitIds: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            zzdj.zzcu(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<com.google.android.gms.tagmanager.zzbx> zzeq(int r17) {
        /*
        r16 = this;
        r12 = new java.util.ArrayList;
        r12.<init>();
        r2 = "Error opening database for peekHits";
        r0 = r16;
        r2 = r0.zzlk(r2);
        if (r2 != 0) goto L_0x0012;
    L_0x0010:
        r11 = r12;
    L_0x0011:
        return r11;
    L_0x0012:
        r11 = 0;
        r3 = "gtm_hits";
        r4 = 3;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r5 = 1;
        r6 = "hit_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r5 = 2;
        r6 = "hit_first_send_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r13 = 0;
        r14 = "hit_id";
        r10[r13] = r14;	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r10 = 40;
        r10 = java.lang.Integer.toString(r10);	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x00d9, all -> 0x0187 }
        r11 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x018e, all -> 0x018a }
        r11.<init>();	 Catch:{ SQLiteException -> 0x018e, all -> 0x018a }
        r3 = r13.moveToFirst();	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        if (r3 == 0) goto L_0x0071;
    L_0x0054:
        r3 = new com.google.android.gms.tagmanager.zzbx;	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        r4 = 0;
        r4 = r13.getLong(r4);	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        r6 = 1;
        r6 = r13.getLong(r6);	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        r8 = 2;
        r8 = r13.getLong(r8);	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        r3.<init>(r4, r6, r8);	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        r11.add(r3);	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        r3 = r13.moveToNext();	 Catch:{ SQLiteException -> 0x0193, all -> 0x018a }
        if (r3 != 0) goto L_0x0054;
    L_0x0071:
        if (r13 == 0) goto L_0x0076;
    L_0x0073:
        r13.close();
    L_0x0076:
        r12 = 0;
        r3 = "gtm_hits";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0185 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0185 }
        r5 = 1;
        r6 = "hit_url";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0185 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x0185 }
        r14 = 0;
        r15 = "hit_id";
        r10[r14] = r15;	 Catch:{ SQLiteException -> 0x0185 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x0185 }
        r10 = 40;
        r10 = java.lang.Integer.toString(r10);	 Catch:{ SQLiteException -> 0x0185 }
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x0185 }
        r2 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        if (r2 == 0) goto L_0x00d2;
    L_0x00ad:
        r4 = r12;
    L_0x00ae:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteCursor) r0;	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r2 = r0;
        r2 = r2.getWindow();	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r2 = r2.getNumRows();	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        if (r2 <= 0) goto L_0x0109;
    L_0x00bc:
        r2 = r11.get(r4);	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r2 = (com.google.android.gms.tagmanager.zzbx) r2;	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r5 = 1;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r2.zzlp(r5);	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
    L_0x00ca:
        r2 = r4 + 1;
        r4 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        if (r4 != 0) goto L_0x0197;
    L_0x00d2:
        if (r3 == 0) goto L_0x0011;
    L_0x00d4:
        r3.close();
        goto L_0x0011;
    L_0x00d9:
        r2 = move-exception;
        r3 = r11;
        r11 = r12;
    L_0x00dc:
        r4 = "Error in peekHits fetching hitIds: ";
        r2 = r2.getMessage();	 Catch:{ all -> 0x0101 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0101 }
        r5 = r2.length();	 Catch:{ all -> 0x0101 }
        if (r5 == 0) goto L_0x00fb;
    L_0x00ed:
        r2 = r4.concat(r2);	 Catch:{ all -> 0x0101 }
    L_0x00f1:
        com.google.android.gms.tagmanager.zzdj.zzcu(r2);	 Catch:{ all -> 0x0101 }
        if (r3 == 0) goto L_0x0011;
    L_0x00f6:
        r3.close();
        goto L_0x0011;
    L_0x00fb:
        r2 = new java.lang.String;	 Catch:{ all -> 0x0101 }
        r2.<init>(r4);	 Catch:{ all -> 0x0101 }
        goto L_0x00f1;
    L_0x0101:
        r2 = move-exception;
        r11 = r3;
    L_0x0103:
        if (r11 == 0) goto L_0x0108;
    L_0x0105:
        r11.close();
    L_0x0108:
        throw r2;
    L_0x0109:
        r5 = "HitString for hitId %d too large.  Hit will be deleted.";
        r2 = 1;
        r6 = new java.lang.Object[r2];	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r7 = 0;
        r2 = r11.get(r4);	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r2 = (com.google.android.gms.tagmanager.zzbx) r2;	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r8 = r2.zzbey();	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r6[r7] = r2;	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        r2 = java.lang.String.format(r5, r6);	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        com.google.android.gms.tagmanager.zzdj.zzcu(r2);	 Catch:{ SQLiteException -> 0x0128, all -> 0x0182 }
        goto L_0x00ca;
    L_0x0128:
        r2 = move-exception;
        r13 = r3;
    L_0x012a:
        r3 = "Error in peekHits fetching hit url: ";
        r2 = r2.getMessage();	 Catch:{ all -> 0x016d }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x016d }
        r4 = r2.length();	 Catch:{ all -> 0x016d }
        if (r4 == 0) goto L_0x0174;
    L_0x013b:
        r2 = r3.concat(r2);	 Catch:{ all -> 0x016d }
    L_0x013f:
        com.google.android.gms.tagmanager.zzdj.zzcu(r2);	 Catch:{ all -> 0x016d }
        r4 = new java.util.ArrayList;	 Catch:{ all -> 0x016d }
        r4.<init>();	 Catch:{ all -> 0x016d }
        r5 = 0;
        r0 = r11;
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x016d }
        r2 = r0;
        r7 = r2.size();	 Catch:{ all -> 0x016d }
        r3 = 0;
        r6 = r3;
    L_0x0152:
        if (r6 >= r7) goto L_0x017a;
    L_0x0154:
        r3 = r2.get(r6);	 Catch:{ all -> 0x016d }
        r6 = r6 + 1;
        r3 = (com.google.android.gms.tagmanager.zzbx) r3;	 Catch:{ all -> 0x016d }
        r8 = r3.zzbfa();	 Catch:{ all -> 0x016d }
        r8 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x016d }
        if (r8 == 0) goto L_0x0169;
    L_0x0166:
        if (r5 != 0) goto L_0x017a;
    L_0x0168:
        r5 = 1;
    L_0x0169:
        r4.add(r3);	 Catch:{ all -> 0x016d }
        goto L_0x0152;
    L_0x016d:
        r2 = move-exception;
    L_0x016e:
        if (r13 == 0) goto L_0x0173;
    L_0x0170:
        r13.close();
    L_0x0173:
        throw r2;
    L_0x0174:
        r2 = new java.lang.String;	 Catch:{ all -> 0x016d }
        r2.<init>(r3);	 Catch:{ all -> 0x016d }
        goto L_0x013f;
    L_0x017a:
        if (r13 == 0) goto L_0x017f;
    L_0x017c:
        r13.close();
    L_0x017f:
        r11 = r4;
        goto L_0x0011;
    L_0x0182:
        r2 = move-exception;
        r13 = r3;
        goto L_0x016e;
    L_0x0185:
        r2 = move-exception;
        goto L_0x012a;
    L_0x0187:
        r2 = move-exception;
        goto L_0x0103;
    L_0x018a:
        r2 = move-exception;
        r11 = r13;
        goto L_0x0103;
    L_0x018e:
        r2 = move-exception;
        r3 = r13;
        r11 = r12;
        goto L_0x00dc;
    L_0x0193:
        r2 = move-exception;
        r3 = r13;
        goto L_0x00dc;
    L_0x0197:
        r4 = r2;
        goto L_0x00ae;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzec.zzeq(int):java.util.List<com.google.android.gms.tagmanager.zzbx>");
    }

    private final void zzh(long j, long j2) {
        SQLiteDatabase zzlk = zzlk("Error opening database for getNumStoredHits.");
        if (zzlk != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzlk.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException e) {
                zzdj.zzcu("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j);
                zzp(j);
            }
        }
    }

    private final SQLiteDatabase zzlk(String str) {
        try {
            return this.zzkhb.getWritableDatabase();
        } catch (SQLiteException e) {
            zzdj.zzcu(str);
            return null;
        }
    }

    private final void zzp(long j) {
        zze(new String[]{String.valueOf(j)});
    }

    public final void dispatch() {
        zzdj.m11v("GTM Dispatch running...");
        if (this.zzkhc.zzbeq()) {
            List zzeq = zzeq(40);
            if (zzeq.isEmpty()) {
                zzdj.m11v("...nothing to dispatch");
                this.zzkhd.zzbu(true);
                return;
            }
            this.zzkhc.zzal(zzeq);
            if (zzbfm() > 0) {
                zzfo.zzbgg().dispatch();
            }
        }
    }

    public final void zzb(long j, String str) {
        boolean z = true;
        long currentTimeMillis = this.zzata.currentTimeMillis();
        if (currentTimeMillis > this.zzkhf + 86400000) {
            this.zzkhf = currentTimeMillis;
            SQLiteDatabase zzlk = zzlk("Error opening database for deleteStaleHits.");
            if (zzlk != null) {
                zzlk.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzata.currentTimeMillis() - 2592000000L)});
                zzcd com_google_android_gms_tagmanager_zzcd = this.zzkhd;
                if (zzbfl() != 0) {
                    z = false;
                }
                com_google_android_gms_tagmanager_zzcd.zzbu(z);
            }
        }
        int zzbfl = (zzbfl() - this.zzkhg) + 1;
        if (zzbfl > 0) {
            List zzep = zzep(zzbfl);
            zzdj.m11v("Store full, deleting " + zzep.size() + " hits to make room.");
            zze((String[]) zzep.toArray(new String[0]));
        }
        SQLiteDatabase zzlk2 = zzlk("Error opening database for putHit");
        if (zzlk2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", Integer.valueOf(0));
            try {
                zzlk2.insert("gtm_hits", null, contentValues);
                this.zzkhd.zzbu(false);
            } catch (SQLiteException e) {
                zzdj.zzcu("Error storing hit");
            }
        }
    }
}

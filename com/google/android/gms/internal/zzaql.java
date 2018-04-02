package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzm;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzaql extends zzaqa implements Closeable {
    private static final String zzdug = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzduh = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zzaqm zzdui;
    private final zzash zzduj = new zzash(zzws());
    private final zzash zzduk = new zzash(zzws());

    zzaql(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
        this.zzdui = new zzaqm(this, com_google_android_gms_internal_zzaqc.getContext(), "google_analytics_v4.db");
    }

    private final long zza(String str, String[] strArr, long j) {
        Object e;
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            try {
                if (rawQuery.moveToFirst()) {
                    long j2 = rawQuery.getLong(0);
                    if (rawQuery == null) {
                        return j2;
                    }
                    rawQuery.close();
                    return j2;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return 0;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = rawQuery;
                try {
                    zzd("Database error", str, e);
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = rawQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            zzd("Database error", str, e);
            throw e;
        }
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, null);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private final Map<String, String> zzdz(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String str2 = "?";
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
            }
            return zzm.zza(new URI(str), HttpRequest.CHARSET_UTF8);
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    private final Map<String, String> zzea(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String str2 = "?";
            String valueOf = String.valueOf(str);
            return zzm.zza(new URI(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2)), HttpRequest.CHARSET_UTF8);
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    private final List<Long> zzn(long j) {
        Object e;
        Throwable th;
        Cursor cursor = null;
        zzj.zzve();
        zzxf();
        if (j <= 0) {
            return Collections.emptyList();
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        List<Long> arrayList = new ArrayList();
        Cursor query;
        try {
            query = writableDatabase.query("hits2", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", new Object[]{"hit_id"}), Long.toString(j));
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(Long.valueOf(query.getLong(0)));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzd("Error selecting hit ids", e);
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzd("Error selecting hit ids", e);
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    private final long zzxp() {
        zzj.zzve();
        zzxf();
        return zzb("SELECT COUNT(*) FROM hits2", null);
    }

    private static String zzxw() {
        return "google_analytics_v4.db";
    }

    public final void beginTransaction() {
        zzxf();
        getWritableDatabase().beginTransaction();
    }

    public final void close() {
        try {
            this.zzdui.close();
        } catch (SQLiteException e) {
            zze("Sql error closing database", e);
        } catch (IllegalStateException e2) {
            zze("Error closing database", e2);
        }
    }

    public final void endTransaction() {
        zzxf();
        getWritableDatabase().endTransaction();
    }

    final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzdui.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    final boolean isEmpty() {
        return zzxp() == 0;
    }

    public final void setTransactionSuccessful() {
        zzxf();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(long j, String str, String str2) {
        zzbq.zzgm(str);
        zzbq.zzgm(str2);
        zzxf();
        zzj.zzve();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public final void zzc(zzarq com_google_android_gms_internal_zzarq) {
        String str;
        zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
        zzj.zzve();
        zzxf();
        zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
        Builder builder = new Builder();
        for (Entry entry : com_google_android_gms_internal_zzarq.zzjh().entrySet()) {
            str = (String) entry.getKey();
            if (!("ht".equals(str) || "qt".equals(str) || "AppUID".equals(str))) {
                builder.appendQueryParameter(str, (String) entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        str = encodedQuery == null ? "" : encodedQuery;
        if (str.length() > 8192) {
            zzwt().zza(com_google_android_gms_internal_zzarq, "Hit length exceeds the maximum allowed size");
            return;
        }
        int intValue = ((Integer) zzarl.zzdwb.get()).intValue();
        long zzxp = zzxp();
        if (zzxp > ((long) (intValue - 1))) {
            List zzn = zzn((zzxp - ((long) intValue)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzn.size()));
            zzs(zzn);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", str);
        contentValues.put("hit_time", Long.valueOf(com_google_android_gms_internal_zzarq.zzzi()));
        contentValues.put("hit_app_id", Integer.valueOf(com_google_android_gms_internal_zzarq.zzzg()));
        contentValues.put("hit_url", com_google_android_gms_internal_zzarq.zzzk() ? zzard.zzyw() : zzard.zzyx());
        try {
            long insert = writableDatabase.insert("hits2", null, contentValues);
            if (insert == -1) {
                zzdy("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), com_google_android_gms_internal_zzarq);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.zzarq> zzo(long r14) {
        /*
        r13 = this;
        r0 = 1;
        r1 = 0;
        r9 = 0;
        r2 = 0;
        r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x0097;
    L_0x0009:
        com.google.android.gms.common.internal.zzbq.checkArgument(r0);
        com.google.android.gms.analytics.zzj.zzve();
        r13.zzxf();
        r0 = r13.getWritableDatabase();
        r1 = "hits2";
        r2 = 5;
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r3 = 0;
        r4 = "hit_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r3 = 1;
        r4 = "hit_time";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r3 = 2;
        r4 = "hit_string";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r3 = 3;
        r4 = "hit_url";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r3 = 4;
        r4 = "hit_app_id";
        r2[r3] = r4;	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "%s ASC";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r10 = 0;
        r11 = "hit_id";
        r8[r10] = r11;	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r7 = java.lang.String.format(r7, r8);	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r8 = java.lang.Long.toString(r14);	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x009a, all -> 0x00ab }
        r10 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r10.<init>();	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r0 = r9.moveToFirst();	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        if (r0 == 0) goto L_0x0091;
    L_0x0061:
        r0 = 0;
        r6 = r9.getLong(r0);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r0 = 1;
        r3 = r9.getLong(r0);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r0 = 2;
        r0 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r1 = 3;
        r1 = r9.getString(r1);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r2 = 4;
        r8 = r9.getInt(r2);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r2 = r13.zzdz(r0);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r5 = com.google.android.gms.internal.zzasl.zzel(r1);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r0 = new com.google.android.gms.internal.zzarq;	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r1 = r13;
        r0.<init>(r1, r2, r3, r5, r6, r8);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r10.add(r0);	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        r0 = r9.moveToNext();	 Catch:{ SQLiteException -> 0x00ad, all -> 0x00ab }
        if (r0 != 0) goto L_0x0061;
    L_0x0091:
        if (r9 == 0) goto L_0x0096;
    L_0x0093:
        r9.close();
    L_0x0096:
        return r10;
    L_0x0097:
        r0 = r1;
        goto L_0x0009;
    L_0x009a:
        r0 = move-exception;
        r1 = r9;
    L_0x009c:
        r2 = "Error loading hits from the database";
        r13.zze(r2, r0);	 Catch:{ all -> 0x00a3 }
        throw r0;	 Catch:{ all -> 0x00a3 }
    L_0x00a3:
        r0 = move-exception;
        r9 = r1;
    L_0x00a5:
        if (r9 == 0) goto L_0x00aa;
    L_0x00a7:
        r9.close();
    L_0x00aa:
        throw r0;
    L_0x00ab:
        r0 = move-exception;
        goto L_0x00a5;
    L_0x00ad:
        r0 = move-exception;
        r1 = r9;
        goto L_0x009c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaql.zzo(long):java.util.List<com.google.android.gms.internal.zzarq>");
    }

    public final void zzp(long j) {
        zzj.zzve();
        zzxf();
        List arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zzs(arrayList);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.zzaqf> zzq(long r13) {
        /*
        r12 = this;
        r12.zzxf();
        com.google.android.gms.analytics.zzj.zzve();
        r0 = r12.getWritableDatabase();
        r9 = 0;
        r1 = 5;
        r2 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = 0;
        r3 = "cid";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = 1;
        r3 = "tid";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = 2;
        r3 = "adid";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = 3;
        r3 = "hits_count";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = 4;
        r3 = "params";
        r2[r1] = r3;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = com.google.android.gms.internal.zzarl.zzdwd;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = r1.get();	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = (java.lang.Integer) r1;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r10 = r1.intValue();	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r8 = java.lang.String.valueOf(r10);	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r3 = "app_uid=?";
        r1 = 1;
        r4 = new java.lang.String[r1];	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = 0;
        r5 = "0";
        r4[r1] = r5;	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r1 = "properties";
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x00c8, all -> 0x00c6 }
        r11 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r11.<init>();	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r0 = r9.moveToFirst();	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        if (r0 == 0) goto L_0x0096;
    L_0x005d:
        r0 = 0;
        r3 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r0 = 1;
        r4 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r0 = 2;
        r0 = r9.getInt(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00a8;
    L_0x006e:
        r5 = 1;
    L_0x006f:
        r0 = 3;
        r0 = r9.getInt(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r6 = (long) r0;	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r0 = 4;
        r0 = r9.getString(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r8 = r12.zzea(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        if (r0 != 0) goto L_0x008a;
    L_0x0084:
        r0 = android.text.TextUtils.isEmpty(r4);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        if (r0 == 0) goto L_0x00aa;
    L_0x008a:
        r0 = "Read property with empty client id or tracker id";
        r12.zzc(r0, r3, r4);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
    L_0x0090:
        r0 = r9.moveToNext();	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        if (r0 != 0) goto L_0x005d;
    L_0x0096:
        r0 = r11.size();	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        if (r0 < r10) goto L_0x00a2;
    L_0x009c:
        r0 = "Sending hits to too many properties. Campaign report might be incorrect";
        r12.zzdx(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
    L_0x00a2:
        if (r9 == 0) goto L_0x00a7;
    L_0x00a4:
        r9.close();
    L_0x00a7:
        return r11;
    L_0x00a8:
        r5 = 0;
        goto L_0x006f;
    L_0x00aa:
        r0 = new com.google.android.gms.internal.zzaqf;	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r1 = 0;
        r0.<init>(r1, r3, r4, r5, r6, r8);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        r11.add(r0);	 Catch:{ SQLiteException -> 0x00b5, all -> 0x00c6 }
        goto L_0x0090;
    L_0x00b5:
        r0 = move-exception;
        r1 = r9;
    L_0x00b7:
        r2 = "Error loading hits from the database";
        r12.zze(r2, r0);	 Catch:{ all -> 0x00be }
        throw r0;	 Catch:{ all -> 0x00be }
    L_0x00be:
        r0 = move-exception;
        r9 = r1;
    L_0x00c0:
        if (r9 == 0) goto L_0x00c5;
    L_0x00c2:
        r9.close();
    L_0x00c5:
        throw r0;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x00c0;
    L_0x00c8:
        r0 = move-exception;
        r1 = r9;
        goto L_0x00b7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaql.zzq(long):java.util.List<com.google.android.gms.internal.zzaqf>");
    }

    public final void zzs(List<Long> list) {
        zzbq.checkNotNull(list);
        zzj.zzve();
        zzxf();
        if (!list.isEmpty()) {
            int i;
            StringBuilder stringBuilder = new StringBuilder("hit_id");
            stringBuilder.append(" in (");
            for (i = 0; i < list.size(); i++) {
                Long l = (Long) list.get(i);
                if (l == null || l.longValue() == 0) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(l);
            }
            stringBuilder.append(")");
            String stringBuilder2 = stringBuilder.toString();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                i = writableDatabase.delete("hits2", stringBuilder2, null);
                if (i != list.size()) {
                    zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(i), stringBuilder2);
                }
            } catch (SQLiteException e) {
                zze("Error deleting hits", e);
                throw e;
            }
        }
    }

    protected final void zzvf() {
    }

    public final int zzxu() {
        zzj.zzve();
        zzxf();
        if (!this.zzduj.zzu(86400000)) {
            return 0;
        }
        this.zzduj.start();
        zzdu("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzws().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public final long zzxv() {
        zzj.zzve();
        zzxf();
        return zza(zzduh, null, 0);
    }
}

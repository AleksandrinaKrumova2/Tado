package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import java.util.HashSet;
import java.util.Set;

final class zzee extends SQLiteOpenHelper {
    private /* synthetic */ zzec zzkhh;
    private boolean zzkhi;
    private long zzkhj = 0;

    zzee(zzec com_google_android_gms_tagmanager_zzec, Context context, String str) {
        this.zzkhh = com_google_android_gms_tagmanager_zzec;
        super(context, str, null, 1);
    }

    private static boolean zza(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        String str2;
        String valueOf;
        Throwable th;
        Cursor cursor2 = null;
        try {
            Cursor query = sQLiteDatabase.query("SQLITE_MASTER", new String[]{CreateHomeContactDetailsActivity.INTENT_NAME}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean moveToFirst = query.moveToFirst();
                if (query == null) {
                    return moveToFirst;
                }
                query.close();
                return moveToFirst;
            } catch (SQLiteException e) {
                cursor = query;
                try {
                    str2 = "Error querying for table ";
                    valueOf = String.valueOf(str);
                    zzdj.zzcu(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf));
                    if (cursor != null) {
                        cursor.close();
                    }
                    return false;
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
            str2 = "Error querying for table ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            zzdj.zzcu(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf));
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final SQLiteDatabase getWritableDatabase() {
        if (!this.zzkhi || this.zzkhj + 3600000 <= this.zzkhh.zzata.currentTimeMillis()) {
            SQLiteDatabase sQLiteDatabase = null;
            this.zzkhi = true;
            this.zzkhj = this.zzkhh.zzata.currentTimeMillis();
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                this.zzkhh.mContext.getDatabasePath(this.zzkhh.zzkhe).delete();
            }
            if (sQLiteDatabase == null) {
                sQLiteDatabase = super.getWritableDatabase();
            }
            this.zzkhi = false;
            return sQLiteDatabase;
        }
        throw new SQLiteException("Database creation failed");
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzbs.zzlo(sQLiteDatabase.getPath());
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        if (VERSION.SDK_INT < 15) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                rawQuery.moveToFirst();
            } finally {
                rawQuery.close();
            }
        }
        if (zza("gtm_hits", sQLiteDatabase)) {
            Cursor rawQuery2 = sQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
            Set hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery2.getColumnNames();
                for (Object add : columnNames) {
                    hashSet.add(add);
                }
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } finally {
                rawQuery2.close();
            }
        } else {
            sQLiteDatabase.execSQL(zzec.zzdug);
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}

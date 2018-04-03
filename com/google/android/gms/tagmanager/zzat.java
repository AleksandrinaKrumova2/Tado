package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.tado.android.onboarding.OnboardingPageFragment;
import com.tado.android.views.MorphingButton;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

final class zzat implements zzc {
    private static final String zzkeu = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{"datalayer", "ID", OnboardingPageFragment.FEATURE_KEY, Param.VALUE, "expires"});
    private final Context mContext;
    private zzd zzata;
    private final Executor zzkev;
    private zzax zzkew;
    private int zzkex;

    public zzat(Context context) {
        this(context, zzh.zzamg(), "google_tagmanager.db", MorphingButton.COLLAPSE_ANIMATION_DELAY, Executors.newSingleThreadExecutor());
    }

    private zzat(Context context, zzd com_google_android_gms_common_util_zzd, String str, int i, Executor executor) {
        this.mContext = context;
        this.zzata = com_google_android_gms_common_util_zzd;
        this.zzkex = MorphingButton.COLLAPSE_ANIMATION_DELAY;
        this.zzkev = executor;
        this.zzkew = new zzax(this, this.mContext, str);
    }

    private static byte[] zzaj(Object obj) {
        ObjectOutputStream objectOutputStream;
        Throwable th;
        byte[] bArr = null;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                }
            } catch (IOException e2) {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e3) {
                    }
                }
                byteArrayOutputStream.close();
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e4) {
                        throw th;
                    }
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException e5) {
            objectOutputStream = bArr;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            byteArrayOutputStream.close();
            return bArr;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            objectOutputStream = bArr;
            th = th4;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            byteArrayOutputStream.close();
            throw th;
        }
        return bArr;
    }

    private final synchronized void zzb(List<zzay> list, long j) {
        long currentTimeMillis;
        String[] strArr;
        try {
            currentTimeMillis = this.zzata.currentTimeMillis();
            zzbh(currentTimeMillis);
            int size = list.size() + (zzben() - this.zzkex);
            if (size > 0) {
                List zzek = zzek(size);
                zzdj.zzct("DataLayer store full, deleting " + zzek.size() + " entries to make room.");
                strArr = (String[]) zzek.toArray(new String[0]);
                if (!(strArr == null || strArr.length == 0)) {
                    SQLiteDatabase zzlk = zzlk("Error opening database for deleteEntries.");
                    if (zzlk != null) {
                        zzlk.delete("datalayer", String.format("%s in (%s)", new Object[]{"ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                    }
                }
            }
        } catch (SQLiteException e) {
            String str = "Error deleting entries ";
            String valueOf = String.valueOf(Arrays.toString(strArr));
            zzdj.zzcu(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        } catch (Throwable th) {
            zzbeo();
        }
        zzc(list, currentTimeMillis + j);
        zzbeo();
    }

    private final List<zza> zzbel() {
        try {
            zzbh(this.zzata.currentTimeMillis());
            List<zzay> zzbem = zzbem();
            List<zza> arrayList = new ArrayList();
            for (zzay com_google_android_gms_tagmanager_zzay : zzbem) {
                arrayList.add(new zza(com_google_android_gms_tagmanager_zzay.zzbhb, zzv(com_google_android_gms_tagmanager_zzay.zzkfd)));
            }
            return arrayList;
        } finally {
            zzbeo();
        }
    }

    private final List<zzay> zzbem() {
        SQLiteDatabase zzlk = zzlk("Error opening database for loadSerialized.");
        List<zzay> arrayList = new ArrayList();
        if (zzlk == null) {
            return arrayList;
        }
        Cursor query = zzlk.query("datalayer", new String[]{OnboardingPageFragment.FEATURE_KEY, Param.VALUE}, null, null, null, null, "ID", null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzay(query.getString(0), query.getBlob(1)));
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

    private final int zzben() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzlk = zzlk("Error opening database for getNumStoredEntries.");
        if (zzlk != null) {
            try {
                cursor = zzlk.rawQuery("SELECT COUNT(*) from datalayer", null);
                if (cursor.moveToFirst()) {
                    i = (int) cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                zzdj.zzcu("Error getting numStoredEntries");
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

    private final void zzbeo() {
        try {
            this.zzkew.close();
        } catch (SQLiteException e) {
        }
    }

    private final void zzbh(long j) {
        SQLiteDatabase zzlk = zzlk("Error opening database for deleteOlderThan.");
        if (zzlk != null) {
            try {
                zzdj.m11v("Deleted " + zzlk.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)}) + " expired items");
            } catch (SQLiteException e) {
                zzdj.zzcu("Error deleting old entries.");
            }
        }
    }

    private final void zzc(List<zzay> list, long j) {
        SQLiteDatabase zzlk = zzlk("Error opening database for writeEntryToDatabase.");
        if (zzlk != null) {
            for (zzay com_google_android_gms_tagmanager_zzay : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("expires", Long.valueOf(j));
                contentValues.put(OnboardingPageFragment.FEATURE_KEY, com_google_android_gms_tagmanager_zzay.zzbhb);
                contentValues.put(Param.VALUE, com_google_android_gms_tagmanager_zzay.zzkfd);
                zzlk.insert("datalayer", null, contentValues);
            }
        }
    }

    private final List<String> zzek(int i) {
        Cursor query;
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        List<String> arrayList = new ArrayList();
        if (i <= 0) {
            zzdj.zzcu("Invalid maxEntries specified. Skipping.");
            return arrayList;
        }
        SQLiteDatabase zzlk = zzlk("Error opening database for peekEntryIds.");
        if (zzlk == null) {
            return arrayList;
        }
        try {
            query = zzlk.query("datalayer", new String[]{"ID"}, null, null, null, null, String.format("%s ASC", new Object[]{"ID"}), Integer.toString(i));
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
                    str = "Error in peekEntries fetching entryIds: ";
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
            str = "Error in peekEntries fetching entryIds: ";
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

    private final void zzlj(String str) {
        SQLiteDatabase zzlk = zzlk("Error opening database for clearKeysWithPrefix.");
        if (zzlk != null) {
            try {
                zzdj.m11v("Cleared " + zzlk.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")}) + " items");
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                zzdj.zzcu(new StringBuilder((String.valueOf(str).length() + 44) + String.valueOf(valueOf).length()).append("Error deleting entries with key prefix: ").append(str).append(" (").append(valueOf).append(").").toString());
            } finally {
                zzbeo();
            }
        }
    }

    private final SQLiteDatabase zzlk(String str) {
        try {
            return this.zzkew.getWritableDatabase();
        } catch (SQLiteException e) {
            zzdj.zzcu(str);
            return null;
        }
    }

    private static Object zzv(byte[] bArr) {
        ObjectInputStream objectInputStream;
        Object readObject;
        Throwable th;
        ObjectInputStream objectInputStream2 = null;
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            try {
                readObject = objectInputStream.readObject();
                try {
                    objectInputStream.close();
                    byteArrayInputStream.close();
                } catch (IOException e) {
                }
            } catch (IOException e2) {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                byteArrayInputStream.close();
                return readObject;
            } catch (ClassNotFoundException e4) {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e5) {
                    }
                }
                byteArrayInputStream.close();
                return readObject;
            } catch (Throwable th2) {
                th = th2;
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e6) {
                        throw th;
                    }
                }
                byteArrayInputStream.close();
                throw th;
            }
        } catch (IOException e7) {
            objectInputStream = objectInputStream2;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            byteArrayInputStream.close();
            return readObject;
        } catch (ClassNotFoundException e8) {
            objectInputStream = objectInputStream2;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            byteArrayInputStream.close();
            return readObject;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            objectInputStream = objectInputStream2;
            th = th4;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            byteArrayInputStream.close();
            throw th;
        }
        return readObject;
    }

    public final void zza(zzaq com_google_android_gms_tagmanager_zzaq) {
        this.zzkev.execute(new zzav(this, com_google_android_gms_tagmanager_zzaq));
    }

    public final void zza(List<zza> list, long j) {
        List arrayList = new ArrayList();
        for (zza com_google_android_gms_tagmanager_DataLayer_zza : list) {
            arrayList.add(new zzay(com_google_android_gms_tagmanager_DataLayer_zza.zzbhb, zzaj(com_google_android_gms_tagmanager_DataLayer_zza.mValue)));
        }
        this.zzkev.execute(new zzau(this, arrayList, j));
    }

    public final void zzli(String str) {
        this.zzkev.execute(new zzaw(this, str));
    }
}

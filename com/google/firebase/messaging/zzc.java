package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzfjr;
import com.google.android.gms.internal.zzfkt;
import com.google.android.gms.internal.zzfku;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class zzc {
    @Nullable
    private static Object zza(@NonNull zzfku com_google_android_gms_internal_zzfku, @NonNull String str, @NonNull zzb com_google_firebase_messaging_zzb) {
        Object newInstance;
        Throwable e;
        Object obj = null;
        try {
            Class cls = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle zzaz = zzaz(com_google_android_gms_internal_zzfku.zzpri, com_google_android_gms_internal_zzfku.zzprj);
            newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            try {
                cls.getField("mOrigin").set(newInstance, str);
                cls.getField("mCreationTimestamp").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzfku.zzprk));
                cls.getField("mName").set(newInstance, com_google_android_gms_internal_zzfku.zzpri);
                cls.getField("mValue").set(newInstance, com_google_android_gms_internal_zzfku.zzprj);
                if (!TextUtils.isEmpty(com_google_android_gms_internal_zzfku.zzprl)) {
                    obj = com_google_android_gms_internal_zzfku.zzprl;
                }
                cls.getField("mTriggerEventName").set(newInstance, obj);
                cls.getField("mTimedOutEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzfku.zzprq) ? com_google_android_gms_internal_zzfku.zzprq : com_google_firebase_messaging_zzb.zzbqu());
                cls.getField("mTimedOutEventParams").set(newInstance, zzaz);
                cls.getField("mTriggerTimeout").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzfku.zzprm));
                cls.getField("mTriggeredEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzfku.zzpro) ? com_google_android_gms_internal_zzfku.zzpro : com_google_firebase_messaging_zzb.zzbqt());
                cls.getField("mTriggeredEventParams").set(newInstance, zzaz);
                cls.getField("mTimeToLive").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzfku.zzghq));
                cls.getField("mExpiredEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzfku.zzprr) ? com_google_android_gms_internal_zzfku.zzprr : com_google_firebase_messaging_zzb.zzbqv());
                cls.getField("mExpiredEventParams").set(newInstance, zzaz);
            } catch (Exception e2) {
                e = e2;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            newInstance = null;
            e = th;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        }
        return newInstance;
    }

    private static String zza(@Nullable zzfku com_google_android_gms_internal_zzfku, @NonNull zzb com_google_firebase_messaging_zzb) {
        return (com_google_android_gms_internal_zzfku == null || TextUtils.isEmpty(com_google_android_gms_internal_zzfku.zzprp)) ? com_google_firebase_messaging_zzb.zzbqw() : com_google_android_gms_internal_zzfku.zzprp;
    }

    private static List<Object> zza(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        List<Object> list;
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[]{String.class, String.class});
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(appMeasurement, new Object[]{str, ""});
        } catch (Throwable e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            Object obj = arrayList;
        }
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
        }
        return list;
    }

    private static void zza(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str5 = "FirebaseAbtUtil";
            String str6 = "_CE(experimentId) called by ";
            String valueOf = String.valueOf(str);
            Log.v(str5, valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
        }
        if (zzet(context)) {
            AppMeasurement zzdc = zzdc(context);
            try {
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[]{String.class, String.class, Bundle.class});
                declaredMethod.setAccessible(true);
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 17) + String.valueOf(str3).length()).append("Clearing _E: [").append(str2).append(", ").append(str3).append("]").toString());
                }
                declaredMethod.invoke(zzdc, new Object[]{str2, str4, zzaz(str2, str3)});
            } catch (Throwable e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    public static void zza(@NonNull Context context, @NonNull String str, @NonNull byte[] bArr, @NonNull zzb com_google_firebase_messaging_zzb, int i) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = "FirebaseAbtUtil";
            String str3 = "_SE called by ";
            String valueOf = String.valueOf(str);
            Log.v(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        if (zzet(context)) {
            AppMeasurement zzdc = zzdc(context);
            zzfku zzam = zzam(bArr);
            if (zzam != null) {
                try {
                    Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                    Object obj = null;
                    for (Object next : zza(zzdc, str)) {
                        Object next2;
                        String zzba = zzba(next2);
                        String zzbb = zzbb(next2);
                        long longValue = ((Long) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mCreationTimestamp").get(next2)).longValue();
                        if (zzam.zzpri.equals(zzba) && zzam.zzprj.equals(zzbb)) {
                            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 23) + String.valueOf(zzbb).length()).append("_E is already set. [").append(zzba).append(", ").append(zzbb).append("]").toString());
                            }
                            obj = 1;
                        } else {
                            next2 = null;
                            zzfkt[] com_google_android_gms_internal_zzfktArr = zzam.zzprt;
                            int length = com_google_android_gms_internal_zzfktArr.length;
                            int i2 = 0;
                            while (i2 < length) {
                                if (com_google_android_gms_internal_zzfktArr[i2].zzpri.equals(zzba)) {
                                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 33) + String.valueOf(zzbb).length()).append("_E is found in the _OE list. [").append(zzba).append(", ").append(zzbb).append("]").toString());
                                    }
                                    next2 = 1;
                                    if (next2 != null) {
                                        continue;
                                    } else if (zzam.zzprk > longValue) {
                                        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                            Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 115) + String.valueOf(zzbb).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(zzba).append(", ").append(zzbb).append("]").toString());
                                        }
                                        zza(context, str, zzba, zzbb, zza(zzam, com_google_firebase_messaging_zzb));
                                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 109) + String.valueOf(zzbb).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(zzba).append(", ").append(zzbb).append("]").toString());
                                    }
                                } else {
                                    i2++;
                                }
                            }
                            if (next2 != null) {
                                continue;
                            } else if (zzam.zzprk > longValue) {
                                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 115) + String.valueOf(zzbb).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(zzba).append(", ").append(zzbb).append("]").toString());
                                }
                                zza(context, str, zzba, zzbb, zza(zzam, com_google_firebase_messaging_zzb));
                            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 109) + String.valueOf(zzbb).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(zzba).append(", ").append(zzbb).append("]").toString());
                            }
                        }
                    }
                    if (obj == null) {
                        zza(zzdc, context, str, zzam, com_google_firebase_messaging_zzb, 1);
                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        str2 = zzam.zzpri;
                        str3 = zzam.zzprj;
                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 44) + String.valueOf(str3).length()).append("_E is already set. Not setting it again [").append(str2).append(", ").append(str3).append("]").toString());
                    }
                } catch (Throwable e) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P.");
            }
        }
    }

    private static void zza(@NonNull AppMeasurement appMeasurement, @NonNull Context context, @NonNull String str, @NonNull zzfku com_google_android_gms_internal_zzfku, @NonNull zzb com_google_firebase_messaging_zzb, int i) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = com_google_android_gms_internal_zzfku.zzpri;
            String str3 = com_google_android_gms_internal_zzfku.zzprj;
            Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 7) + String.valueOf(str3).length()).append("_SEI: ").append(str2).append(" ").append(str3).toString());
        }
        try {
            Object obj;
            String zzba;
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            List zza = zza(appMeasurement, str);
            if (zza(appMeasurement, str).size() >= zzb(appMeasurement, str)) {
                if ((com_google_android_gms_internal_zzfku.zzprs != 0 ? com_google_android_gms_internal_zzfku.zzprs : 1) == 1) {
                    obj = zza.get(0);
                    zzba = zzba(obj);
                    String zzbb = zzbb(obj);
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzba).length() + 38).append("Clearing _E due to overflow policy: [").append(zzba).append("]").toString());
                    }
                    zza(context, str, zzba, zzbb, zza(com_google_android_gms_internal_zzfku, com_google_firebase_messaging_zzb));
                } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    zzba = com_google_android_gms_internal_zzfku.zzpri;
                    str2 = com_google_android_gms_internal_zzfku.zzprj;
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 44) + String.valueOf(str2).length()).append("_E won't be set due to overflow policy. [").append(zzba).append(", ").append(str2).append("]").toString());
                    return;
                } else {
                    return;
                }
            }
            for (Object next : zza) {
                str2 = zzba(next);
                zzba = zzbb(next);
                if (str2.equals(com_google_android_gms_internal_zzfku.zzpri) && !zzba.equals(com_google_android_gms_internal_zzfku.zzprj) && Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 77) + String.valueOf(zzba).length()).append("Clearing _E, as only one _V of the same _E can be set atany given time: [").append(str2).append(", ").append(zzba).append("].").toString());
                    zza(context, str, str2, zzba, zza(com_google_android_gms_internal_zzfku, com_google_firebase_messaging_zzb));
                }
            }
            if (zza(com_google_android_gms_internal_zzfku, str, com_google_firebase_messaging_zzb) != null) {
                try {
                    Method declaredMethod = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", new Class[]{Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty")});
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(appMeasurement, new Object[]{obj});
                } catch (Throwable e) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                zzba = com_google_android_gms_internal_zzfku.zzpri;
                str2 = com_google_android_gms_internal_zzfku.zzprj;
                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzba).length() + 42) + String.valueOf(str2).length()).append("Could not create _CUP for: [").append(zzba).append(", ").append(str2).append("]. Skipping.").toString());
            }
        } catch (Throwable e2) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e2);
        }
    }

    @Nullable
    private static zzfku zzam(@NonNull byte[] bArr) {
        try {
            return zzfku.zzbi(bArr);
        } catch (zzfjr e) {
            return null;
        }
    }

    private static Bundle zzaz(@NonNull String str, @NonNull String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    private static int zzb(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(appMeasurement, new Object[]{str})).intValue();
        } catch (Throwable e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        }
    }

    private static String zzba(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(obj);
    }

    private static String zzbb(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(obj);
    }

    @Nullable
    private static AppMeasurement zzdc(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    private static boolean zzet(Context context) {
        if (zzdc(context) != null) {
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                return true;
            } catch (ClassNotFoundException e) {
                if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
                    return false;
                }
                Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
                return false;
            }
        } else if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
            return false;
        } else {
            Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            return false;
        }
    }
}

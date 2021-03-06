package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.C0565R;
import com.google.android.gms.common.util.zzq;
import com.google.firebase.iid.zzx;
import com.squareup.otto.Bus;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
    private static zza zznzz;
    private final Context mContext;
    private Bundle zzfwe;
    private Method zzoaa;
    private Method zzoab;
    private final AtomicInteger zzoac = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private zza(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @TargetApi(26)
    private final Notification zza(CharSequence charSequence, String str, int i, Integer num, Uri uri, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str2) {
        Builder smallIcon = new Builder(this.mContext).setAutoCancel(true).setSmallIcon(i);
        if (!TextUtils.isEmpty(charSequence)) {
            smallIcon.setContentTitle(charSequence);
        }
        if (!TextUtils.isEmpty(str)) {
            smallIcon.setContentText(str);
            smallIcon.setStyle(new BigTextStyle().bigText(str));
        }
        if (num != null) {
            smallIcon.setColor(num.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (pendingIntent != null) {
            smallIcon.setContentIntent(pendingIntent);
        }
        if (pendingIntent2 != null) {
            smallIcon.setDeleteIntent(pendingIntent2);
        }
        if (str2 != null) {
            if (this.zzoaa == null) {
                this.zzoaa = zzrr("setChannelId");
            }
            if (this.zzoaa == null) {
                this.zzoaa = zzrr("setChannel");
            }
            if (this.zzoaa == null) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel");
            } else {
                try {
                    this.zzoaa.invoke(smallIcon, new Object[]{str2});
                } catch (Throwable e) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
                } catch (Throwable e2) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e2);
                } catch (Throwable e22) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e22);
                } catch (Throwable e222) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e222);
                }
            }
        }
        return smallIcon.build();
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    static boolean zzag(Bundle bundle) {
        return "1".equals(zze(bundle, "gcm.n.e")) || zze(bundle, "gcm.n.icon") != null;
    }

    @Nullable
    static Uri zzah(@NonNull Bundle bundle) {
        Object zze = zze(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zze)) {
            zze = zze(bundle, "gcm.n.link");
        }
        return !TextUtils.isEmpty(zze) ? Uri.parse(zze) : null;
    }

    static String zzai(Bundle bundle) {
        Object zze = zze(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(zze) ? zze(bundle, "gcm.n.sound") : zze;
    }

    private final Bundle zzauu() {
        if (this.zzfwe != null) {
            return this.zzfwe;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
        } catch (NameNotFoundException e) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.zzfwe = applicationInfo.metaData;
        return this.zzfwe;
    }

    static String zze(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    static synchronized zza zzex(Context context) {
        zza com_google_firebase_messaging_zza;
        synchronized (zza.class) {
            if (zznzz == null) {
                zznzz = new zza(context);
            }
            com_google_firebase_messaging_zza = zznzz;
        }
        return com_google_firebase_messaging_zza;
    }

    static String zzh(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return zze(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    static Object[] zzi(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String zze = zze(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zze)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zze);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException e) {
            valueOf = "FirebaseMessaging";
            String valueOf3 = String.valueOf(str);
            valueOf2 = String.valueOf("_loc_args");
            valueOf2 = (valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3)).substring(6);
            Log.w(valueOf, new StringBuilder((String.valueOf(valueOf2).length() + 41) + String.valueOf(zze).length()).append("Malformed ").append(valueOf2).append(": ").append(zze).append("  Default value will be used.").toString());
            return null;
        }
    }

    @TargetApi(26)
    private final boolean zzid(int i) {
        if (VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            Drawable drawable = this.mContext.getResources().getDrawable(i, null);
            if (drawable.getBounds().height() != 0 && drawable.getBounds().width() != 0) {
                return true;
            }
            Log.e("FirebaseMessaging", "Icon with id: " + i + " uses an invalid gradient. Using fallback icon.");
            return false;
        } catch (NotFoundException e) {
            return false;
        }
    }

    private final String zzj(Bundle bundle, String str) {
        Object zze = zze(bundle, str);
        if (!TextUtils.isEmpty(zze)) {
            return zze;
        }
        String zzh = zzh(bundle, str);
        if (TextUtils.isEmpty(zzh)) {
            return null;
        }
        Resources resources = this.mContext.getResources();
        int identifier = resources.getIdentifier(zzh, "string", this.mContext.getPackageName());
        if (identifier == 0) {
            String str2 = "FirebaseMessaging";
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf("_loc_key");
            valueOf2 = (valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).substring(6);
            Log.w(str2, new StringBuilder((String.valueOf(valueOf2).length() + 49) + String.valueOf(zzh).length()).append(valueOf2).append(" resource not found: ").append(zzh).append(" Default value will be used.").toString());
            return null;
        }
        Object[] zzi = zzi(bundle, str);
        if (zzi == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzi);
        } catch (Throwable e) {
            valueOf = Arrays.toString(zzi);
            Log.w("FirebaseMessaging", new StringBuilder((String.valueOf(zzh).length() + 58) + String.valueOf(valueOf).length()).append("Missing format argument for ").append(zzh).append(": ").append(valueOf).append(" Default value will be used.").toString(), e);
            return null;
        }
    }

    @TargetApi(26)
    private static Method zzrr(String str) {
        try {
            return Builder.class.getMethod(str, new Class[]{String.class});
        } catch (NoSuchMethodException e) {
            return null;
        } catch (SecurityException e2) {
            return null;
        }
    }

    private final Integer zzrs(String str) {
        Integer num = null;
        if (VERSION.SDK_INT >= 21) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    num = Integer.valueOf(Color.parseColor(str));
                } catch (IllegalArgumentException e) {
                    Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 54).append("Color ").append(str).append(" not valid. Notification will use default color.").toString());
                }
            }
            int i = zzauu().getInt("com.google.firebase.messaging.default_notification_color", 0);
            if (i != 0) {
                try {
                    num = Integer.valueOf(ContextCompat.getColor(this.mContext, i));
                } catch (NotFoundException e2) {
                    Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
                }
            }
        }
        return num;
    }

    @TargetApi(26)
    private final String zzrt(String str) {
        if (!zzq.isAtLeastO()) {
            return null;
        }
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
        try {
            if (this.zzoab == null) {
                this.zzoab = notificationManager.getClass().getMethod("getNotificationChannel", new Class[]{String.class});
            }
            if (!TextUtils.isEmpty(str)) {
                if (this.zzoab.invoke(notificationManager, new Object[]{str}) != null) {
                    return str;
                }
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 122).append("Notification Channel requested (").append(str).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
            }
            Object string = zzauu().getString("com.google.firebase.messaging.default_notification_channel_id");
            if (TextUtils.isEmpty(string)) {
                Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
            } else {
                if (this.zzoab.invoke(notificationManager, new Object[]{string}) != null) {
                    return string;
                }
                Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
            }
            if (this.zzoab.invoke(notificationManager, new Object[]{"fcm_fallback_notification_channel"}) == null) {
                Object newInstance = Class.forName("android.app.NotificationChannel").getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE}).newInstance(new Object[]{"fcm_fallback_notification_channel", this.mContext.getString(C0565R.string.fcm_fallback_notification_channel_label), Integer.valueOf(3)});
                notificationManager.getClass().getMethod("createNotificationChannel", new Class[]{r2}).invoke(notificationManager, new Object[]{newInstance});
            }
            return "fcm_fallback_notification_channel";
        } catch (Throwable e) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
            return null;
        } catch (Throwable e2) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e2);
            return null;
        } catch (Throwable e22) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e22);
            return null;
        } catch (Throwable e222) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e222);
            return null;
        } catch (Throwable e2222) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e2222);
            return null;
        } catch (Throwable e22222) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e22222);
            return null;
        } catch (Throwable e222222) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e222222);
            return null;
        } catch (Throwable e2222222) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e2222222);
            return null;
        }
    }

    private final PendingIntent zzt(Bundle bundle) {
        Intent intent;
        Object zze = zze(bundle, "gcm.n.click_action");
        Intent intent2;
        if (TextUtils.isEmpty(zze)) {
            Uri zzah = zzah(bundle);
            if (zzah != null) {
                intent2 = new Intent("android.intent.action.VIEW");
                intent2.setPackage(this.mContext.getPackageName());
                intent2.setData(zzah);
                intent = intent2;
            } else {
                intent2 = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
                if (intent2 == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }
                intent = intent2;
            }
        } else {
            intent2 = new Intent(zze);
            intent2.setPackage(this.mContext.getPackageName());
            intent2.setFlags(268435456);
            intent = intent2;
        }
        if (intent == null) {
            return null;
        }
        intent.addFlags(67108864);
        Bundle bundle2 = new Bundle(bundle);
        FirebaseMessagingService.zzq(bundle2);
        intent.putExtras(bundle2);
        for (String str : bundle2.keySet()) {
            if (str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                intent.removeExtra(str);
            }
        }
        return PendingIntent.getActivity(this.mContext, this.zzoac.incrementAndGet(), intent, 1073741824);
    }

    final boolean zzs(Bundle bundle) {
        if ("1".equals(zze(bundle, "gcm.n.noui"))) {
            return true;
        }
        boolean z;
        CharSequence zzj;
        CharSequence zzj2;
        String zze;
        Resources resources;
        int identifier;
        Integer zzrs;
        Uri uri;
        Parcelable zzt;
        Intent intent;
        PendingIntent zza;
        Intent intent2;
        PendingIntent zza2;
        Parcelable parcelable;
        NotificationCompat.Builder smallIcon;
        Notification build;
        String zze2;
        NotificationManager notificationManager;
        int i;
        if (!((KeyguardManager) this.mContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            if (!zzq.zzamn()) {
                SystemClock.sleep(10);
            }
            int myPid = Process.myPid();
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == myPid) {
                        z = runningAppProcessInfo.importance == 100;
                        if (z) {
                            return false;
                        }
                        zzj = zzj(bundle, "gcm.n.title");
                        if (TextUtils.isEmpty(zzj)) {
                            zzj = this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager());
                        }
                        zzj2 = zzj(bundle, "gcm.n.body");
                        zze = zze(bundle, "gcm.n.icon");
                        if (!TextUtils.isEmpty(zze)) {
                            resources = this.mContext.getResources();
                            identifier = resources.getIdentifier(zze, "drawable", this.mContext.getPackageName());
                            if (identifier == 0 || !zzid(identifier)) {
                                identifier = resources.getIdentifier(zze, "mipmap", this.mContext.getPackageName());
                                if (identifier == 0 || !zzid(identifier)) {
                                    Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zze).length() + 61).append("Icon resource ").append(zze).append(" not found. Notification will use default icon.").toString());
                                }
                            }
                            zzrs = zzrs(zze(bundle, "gcm.n.color"));
                            zze = zzai(bundle);
                            if (TextUtils.isEmpty(zze)) {
                                uri = null;
                            } else if (!Bus.DEFAULT_IDENTIFIER.equals(zze) || this.mContext.getResources().getIdentifier(zze, "raw", this.mContext.getPackageName()) == 0) {
                                uri = RingtoneManager.getDefaultUri(2);
                            } else {
                                String str = "android.resource://";
                                String packageName = this.mContext.getPackageName();
                                uri = Uri.parse(new StringBuilder(((String.valueOf(str).length() + 5) + String.valueOf(packageName).length()) + String.valueOf(zze).length()).append(str).append(packageName).append("/raw/").append(zze).toString());
                            }
                            zzt = zzt(bundle);
                            if (FirebaseMessagingService.zzaj(bundle)) {
                                intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                                zza(intent, bundle);
                                intent.putExtra("pending_intent", zzt);
                                zza = zzx.zza(this.mContext, this.zzoac.incrementAndGet(), intent, 1073741824);
                                intent2 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                                zza(intent2, bundle);
                                zza2 = zzx.zza(this.mContext, this.zzoac.incrementAndGet(), intent2, 1073741824);
                            } else {
                                zza2 = null;
                                parcelable = zzt;
                            }
                            if (zzq.isAtLeastO() || this.mContext.getApplicationInfo().targetSdkVersion <= 25) {
                                smallIcon = new NotificationCompat.Builder(this.mContext).setAutoCancel(true).setSmallIcon(identifier);
                                if (!TextUtils.isEmpty(zzj)) {
                                    smallIcon.setContentTitle(zzj);
                                }
                                if (!TextUtils.isEmpty(zzj2)) {
                                    smallIcon.setContentText(zzj2);
                                    smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzj2));
                                }
                                if (zzrs != null) {
                                    smallIcon.setColor(zzrs.intValue());
                                }
                                if (uri != null) {
                                    smallIcon.setSound(uri);
                                }
                                if (zza != null) {
                                    smallIcon.setContentIntent(zza);
                                }
                                if (zza2 != null) {
                                    smallIcon.setDeleteIntent(zza2);
                                }
                                build = smallIcon.build();
                            } else {
                                build = zza(zzj, zzj2, identifier, zzrs, uri, zza, zza2, zzrt(zze(bundle, "gcm.n.android_channel_id")));
                            }
                            zze2 = zze(bundle, "gcm.n.tag");
                            if (Log.isLoggable("FirebaseMessaging", 3)) {
                                Log.d("FirebaseMessaging", "Showing notification");
                            }
                            notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
                            if (TextUtils.isEmpty(zze2)) {
                                zze2 = "FCM-Notification:" + SystemClock.uptimeMillis();
                            }
                            notificationManager.notify(zze2, 0, build);
                            return true;
                        }
                        i = zzauu().getInt("com.google.firebase.messaging.default_notification_icon", 0);
                        if (i == 0 || !zzid(i)) {
                            i = this.mContext.getApplicationInfo().icon;
                        }
                        if (i == 0 || !zzid(i)) {
                            i = 17301651;
                        }
                        identifier = i;
                        zzrs = zzrs(zze(bundle, "gcm.n.color"));
                        zze = zzai(bundle);
                        if (TextUtils.isEmpty(zze)) {
                            if (Bus.DEFAULT_IDENTIFIER.equals(zze)) {
                            }
                            uri = RingtoneManager.getDefaultUri(2);
                        } else {
                            uri = null;
                        }
                        zzt = zzt(bundle);
                        if (FirebaseMessagingService.zzaj(bundle)) {
                            zza2 = null;
                            parcelable = zzt;
                        } else {
                            intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                            zza(intent, bundle);
                            intent.putExtra("pending_intent", zzt);
                            zza = zzx.zza(this.mContext, this.zzoac.incrementAndGet(), intent, 1073741824);
                            intent2 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                            zza(intent2, bundle);
                            zza2 = zzx.zza(this.mContext, this.zzoac.incrementAndGet(), intent2, 1073741824);
                        }
                        if (zzq.isAtLeastO()) {
                        }
                        smallIcon = new NotificationCompat.Builder(this.mContext).setAutoCancel(true).setSmallIcon(identifier);
                        if (TextUtils.isEmpty(zzj)) {
                            smallIcon.setContentTitle(zzj);
                        }
                        if (TextUtils.isEmpty(zzj2)) {
                            smallIcon.setContentText(zzj2);
                            smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzj2));
                        }
                        if (zzrs != null) {
                            smallIcon.setColor(zzrs.intValue());
                        }
                        if (uri != null) {
                            smallIcon.setSound(uri);
                        }
                        if (zza != null) {
                            smallIcon.setContentIntent(zza);
                        }
                        if (zza2 != null) {
                            smallIcon.setDeleteIntent(zza2);
                        }
                        build = smallIcon.build();
                        zze2 = zze(bundle, "gcm.n.tag");
                        if (Log.isLoggable("FirebaseMessaging", 3)) {
                            Log.d("FirebaseMessaging", "Showing notification");
                        }
                        notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
                        if (TextUtils.isEmpty(zze2)) {
                            zze2 = "FCM-Notification:" + SystemClock.uptimeMillis();
                        }
                        notificationManager.notify(zze2, 0, build);
                        return true;
                    }
                }
            }
        }
        z = false;
        if (z) {
            return false;
        }
        zzj = zzj(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(zzj)) {
            zzj = this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager());
        }
        zzj2 = zzj(bundle, "gcm.n.body");
        zze = zze(bundle, "gcm.n.icon");
        if (TextUtils.isEmpty(zze)) {
            resources = this.mContext.getResources();
            identifier = resources.getIdentifier(zze, "drawable", this.mContext.getPackageName());
            identifier = resources.getIdentifier(zze, "mipmap", this.mContext.getPackageName());
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zze).length() + 61).append("Icon resource ").append(zze).append(" not found. Notification will use default icon.").toString());
        }
        i = zzauu().getInt("com.google.firebase.messaging.default_notification_icon", 0);
        i = this.mContext.getApplicationInfo().icon;
        i = 17301651;
        identifier = i;
        zzrs = zzrs(zze(bundle, "gcm.n.color"));
        zze = zzai(bundle);
        if (TextUtils.isEmpty(zze)) {
            uri = null;
        } else {
            if (Bus.DEFAULT_IDENTIFIER.equals(zze)) {
            }
            uri = RingtoneManager.getDefaultUri(2);
        }
        zzt = zzt(bundle);
        if (FirebaseMessagingService.zzaj(bundle)) {
            intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
            zza(intent, bundle);
            intent.putExtra("pending_intent", zzt);
            zza = zzx.zza(this.mContext, this.zzoac.incrementAndGet(), intent, 1073741824);
            intent2 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
            zza(intent2, bundle);
            zza2 = zzx.zza(this.mContext, this.zzoac.incrementAndGet(), intent2, 1073741824);
        } else {
            zza2 = null;
            parcelable = zzt;
        }
        if (zzq.isAtLeastO()) {
        }
        smallIcon = new NotificationCompat.Builder(this.mContext).setAutoCancel(true).setSmallIcon(identifier);
        if (TextUtils.isEmpty(zzj)) {
            smallIcon.setContentTitle(zzj);
        }
        if (TextUtils.isEmpty(zzj2)) {
            smallIcon.setContentText(zzj2);
            smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzj2));
        }
        if (zzrs != null) {
            smallIcon.setColor(zzrs.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (zza != null) {
            smallIcon.setContentIntent(zza);
        }
        if (zza2 != null) {
            smallIcon.setDeleteIntent(zza2);
        }
        build = smallIcon.build();
        zze2 = zze(bundle, "gcm.n.tag");
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (TextUtils.isEmpty(zze2)) {
            zze2 = "FCM-Notification:" + SystemClock.uptimeMillis();
        }
        notificationManager.notify(zze2, 0, build);
        return true;
    }
}

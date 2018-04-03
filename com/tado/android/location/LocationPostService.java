package com.tado.android.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tado.BuildConfig;
import com.tado.android.app.TadoApplication;
import com.tado.android.notifications.SettingsUtil;
import com.tado.android.rest.model.GeolocationUpdate;
import com.tado.android.utils.DebugUtil;
import com.tado.android.utils.GeolocationLogEntry;
import com.tado.android.utils.GeolocationLogger;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;
import okhttp3.OkHttpClient;
import retrofit2.Call;

@Deprecated
public class LocationPostService extends Service {
    public static final String EXTRA_DROPPED_REASON = "dropReason";
    public static final String EXTRA_GEOFENCE_LIST = "geofenceList";
    public static final String EXTRA_GEOFENCE_TRANSITION = "geofenceTransition";
    public static final String EXTRA_LOCATION_OVERRIDE = "overriden";
    public static final String EXTRA_REAL_LATITUDE = "realLat";
    public static final String EXTRA_REAL_LONGITUDE = "realLon";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_MODE = "mode";
    public static final String KEY_TRIGGER = "trigger";
    private static final int MAX_POST_ATTEMPTS = 5;
    private static final String TAG = "LocationPostService";
    private static final String UNKNOWN = "unknown";
    private static long lockAcquireTime = -1;
    private final IBinder binder = new LocalBinder();
    private LocationHandler handler;
    private GeolocationLogEntry mGeolocationLogEntry;
    private String mode = "unknown";
    private Call<Void> request;
    private WakeLock wakeLock;

    public class LocalBinder extends Binder {
        LocationPostService getService() {
            return LocationPostService.this;
        }
    }

    private class LocationHandler extends Handler {
        int attempt = 1;

        LocationHandler(Looper looper) {
            super(looper);
        }

        public synchronized void cancel() {
            Snitcher.start().toLogger().log(LocationPostService.TAG, "cancelling", new Object[0]);
            if (!(LocationPostService.this.request == null || LocationPostService.this.request.isCanceled())) {
                LocationPostService.this.request.cancel();
            }
            removeMessages(0);
            this.attempt = 1;
        }

        public void handleMessage(Message msg) {
            LocationPostService.this.acquireWakeLock(TadoApplication.getTadoAppContext());
            Snitcher.start().toLogger().log(3, LocationPostService.TAG, "message handled %f, %f", Double.valueOf(location.getLocation().getLatitude()), Double.valueOf(msg.obj.getLocation().getLongitude()));
            tryPostLocation(location);
            LocationPostService.this.releaseWakelockAndSetupNextAlarm();
        }

        private void tryPostLocation(GeolocationUpdate locationUpdate) {
            try {
                if (this.attempt <= 5) {
                    Snitcher.start().toLogger().log(3, LocationPostService.TAG, "attempt %d", Integer.valueOf(this.attempt));
                    if (LocationUtil.sameLocation(locationUpdate.getLocation(), TadoApplication.locationManager.getLastPostedLocation())) {
                        Snitcher.start().toLogger().log(3, LocationPostService.TAG, "Location is the same as last posted. Exiting", new Object[0]);
                        return;
                    } else if (LocationPostService.this.postLocation(locationUpdate, this.attempt)) {
                        Snitcher.start().toLogger().log(LocationPostService.TAG, "POST successful", new Object[0]);
                        DebugUtil.getDroppedLocations().clear();
                        this.attempt = 1;
                        return;
                    } else {
                        Snitcher.start().toLogger().log(6, LocationPostService.TAG, "HTTP post FAILED", new Object[0]);
                        this.attempt++;
                        Snitcher.start().toLogger().log(LocationPostService.TAG, "retry in %d seconds", Integer.valueOf((int) Math.pow(3.0d, (double) this.attempt)));
                        sendMessageDelayed(obtainMessage(0, locationUpdate), (long) (delay * 1000));
                        return;
                    }
                }
                LocationPostService.this.mGeolocationLogEntry.setAttempts(this.attempt);
                GeolocationLogEntry access$300 = LocationPostService.this.mGeolocationLogEntry;
                StringBuilder append = new StringBuilder().append("reached max attempts ").append(this.attempt).append(". There was ");
                String str = (SettingsUtil.internetCapableSettings(LocationPostService.this.getApplicationContext()) && SettingsUtil.hasInternetConnection(LocationPostService.this.getApplicationContext())) ? "internet connection" : "no internet connection";
                access$300.setReason(append.append(str).toString());
                GeolocationLogger.logToFile(LocationPostService.this.mGeolocationLogEntry);
                Snitcher.start().toLogger().log(6, LocationPostService.TAG, "GIVING UP " + SettingsUtil.hasInternetConnection(LocationPostService.this.getApplicationContext()) + "/" + SettingsUtil.internetCapableSettings(LocationPostService.this.getApplicationContext()), new Object[0]);
                Location failedLocation = locationUpdate.getLocation();
                if (failedLocation != null) {
                    LocationUtil.addDroppedReason(failedLocation, "reached max attempts " + this.attempt);
                    DebugUtil.logDroppedLocation(failedLocation);
                    LocationUtil.updateLocationsListAndShowNotification(failedLocation, PostState.FAILED, LocationPostService.this.getApplicationContext(), null);
                }
            } catch (Exception e) {
                Snitcher.start().toLogger().logException(e);
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        this.wakeLock = getWakeLock(this);
        HandlerThread handlerThread = new HandlerThread("LocationPost", 10);
        handlerThread.start();
        this.handler = new LocationHandler(handlerThread.getLooper());
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        onHandleIntent(intent);
        return 2;
    }

    protected synchronized void onHandleIntent(Intent intent) {
        try {
            Snitcher.start().toLogger().log(3, TAG, "Starting post service... App version: %s", BuildConfig.VERSION_NAME);
            if (intent == null) {
                Snitcher.start().toLogger().log(6, TAG, "intent is NULL", new Object[0]);
            } else {
                GeolocationUpdate body = (GeolocationUpdate) intent.getSerializableExtra("location");
                if (body == null) {
                    Snitcher.start().toLogger().log(3, TAG, "intent has not location extra", new Object[0]);
                } else {
                    String str;
                    String pw = (String) Util.either(UserConfig.getPassword(), "");
                    String usr = (String) Util.either(UserConfig.getUsername(), "");
                    Builder toLogger = Snitcher.start().toLogger();
                    String str2 = TAG;
                    String str3 = "Username is %s and password is %s from %s";
                    Object[] objArr = new Object[3];
                    if (usr.isEmpty()) {
                        str = "undefined";
                    } else {
                        str = usr;
                    }
                    objArr[0] = str;
                    objArr[1] = pw.isEmpty() ? "undefined" : "***";
                    objArr[2] = TAG;
                    toLogger.log(3, str2, str3, objArr);
                    if (usr.isEmpty() || pw.isEmpty()) {
                        Snitcher.start().toLogger().log(6, TAG, "##### NOT posting because username and password are empty #####", new Object[0]);
                        TadoApplication.locationManager.stopTracking();
                    } else {
                        this.mGeolocationLogEntry = GeolocationLogger.getGeoLocationEntry(body.getLocation());
                        this.handler.cancel();
                        Message msg = this.handler.obtainMessage(0);
                        msg.obj = body;
                        Snitcher.start().toLogger().log(3, TAG, this.handler.sendMessage(msg) ? "Successfully queued up HTTP Post in Looper (%f,%f)" : "Error queuing up HTTP Post, Looper has is exiting.. (%f,%f)", Double.valueOf(body.getLocation().getLatitude()), Double.valueOf(body.getLocation().getLongitude()));
                    }
                }
            }
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean postLocation(com.tado.android.rest.model.GeolocationUpdate r13, int r14) {
        /*
        r12 = this;
        monitor-enter(r12);
        r4 = com.tado.android.utils.UserConfig.getUsername();	 Catch:{ Exception -> 0x01f4 }
        if (r4 == 0) goto L_0x0021;
    L_0x0007:
        r4 = com.tado.android.utils.UserConfig.getUsername();	 Catch:{ Exception -> 0x01f4 }
        r4 = r4.isEmpty();	 Catch:{ Exception -> 0x01f4 }
        if (r4 != 0) goto L_0x0021;
    L_0x0011:
        r4 = com.tado.android.utils.UserConfig.getPassword();	 Catch:{ Exception -> 0x01f4 }
        if (r4 == 0) goto L_0x0021;
    L_0x0017:
        r4 = com.tado.android.utils.UserConfig.getPassword();	 Catch:{ Exception -> 0x01f4 }
        r4 = r4.isEmpty();	 Catch:{ Exception -> 0x01f4 }
        if (r4 == 0) goto L_0x0024;
    L_0x0021:
        r4 = 1;
    L_0x0022:
        monitor-exit(r12);
        return r4;
    L_0x0024:
        r4 = new com.tado.android.rest.model.GeolocationUpdate$Update;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = com.tado.android.utils.DebugUtil.getDroppedLocations();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.<init>(r14, r5);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r13.setUpdate(r4);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r13.getLocation();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r0 = com.tado.android.utils.DebugUtil.getDebugString(r14, r4);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r13.setExtraInfo(r0);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r12.mGeolocationLogEntry;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.setDebugString(r0);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r12.mGeolocationLogEntry;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = r12.mode;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.setMode(r5);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r12.getApplicationContext();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = com.tado.android.notifications.SettingsUtil.hasInternetConnection(r4);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        if (r4 == 0) goto L_0x01ad;
    L_0x0051:
        r4 = com.tado.android.rest.service.RestServiceGenerator.getTadoLocationRestService();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = com.tado.android.utils.UserConfig.getHomeId();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r6 = com.tado.android.utils.UserConfig.getMobileDeviceId();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.postLocationUpdate(r5, r6, r13);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r12.request = r4;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = "LocationPostService";
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5.<init>();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r6 = "URI is ";
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r6 = r12.request;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r6 = r6.request();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r6 = r6.url();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        android.util.Log.d(r4, r5);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.toLogger();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = 4;
        r6 = "LocationPostService";
        r7 = "Posting location from %s (hasInternet)";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r9 = 0;
        r10 = com.tado.android.utils.Util.getOperatingSystemVersion();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r8[r9] = r10;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.log(r5, r6, r7, r8);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r12.mGeolocationLogEntry;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = com.tado.android.utils.GeolocationLogger.getCurrentFormatedTime();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.setSent(r5);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r12.request;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r3 = r4.execute();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r3.isSuccessful();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        if (r4 == 0) goto L_0x0134;
    L_0x00b8:
        r4 = r3.headers();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r5 = "config-etag";
        r2 = r4.get(r5);	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4 = com.tado.android.app.TadoApplication.locationManager;	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4.onPostSuccessful(r2, r13);	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4 = r12.mGeolocationLogEntry;	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        com.tado.android.utils.GeolocationLogger.logToFile(r4);	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4 = r4.toLogger();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r5 = 4;
        r6 = "LocationPostService";
        r7 = "   HTTP post SUCCESS && json[\"success\"]= true :) (%f,%f)";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r9 = 0;
        r10 = r13.getLocation();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r10 = r10.getLatitude();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r10 = java.lang.Double.valueOf(r10);	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r8[r9] = r10;	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r9 = 1;
        r10 = r13.getLocation();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r10 = r10.getLongitude();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r10 = java.lang.Double.valueOf(r10);	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r8[r9] = r10;	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4.log(r5, r6, r7, r8);	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4 = com.tado.android.app.TadoApplication.getBus();	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
        r4.post(r13);	 Catch:{ Exception -> 0x010a, IOException -> 0x011b }
    L_0x0107:
        r4 = 1;
        goto L_0x0022;
    L_0x010a:
        r1 = move-exception;
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.toLogger();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.toCrashlytics();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.logException(r1);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        goto L_0x0107;
    L_0x011b:
        r1 = move-exception;
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ Exception -> 0x01f4 }
        r4 = r4.toLogger();	 Catch:{ Exception -> 0x01f4 }
        r4 = r4.toCrashlytics();	 Catch:{ Exception -> 0x01f4 }
        r5 = "LocationPostService";
        r6 = "Connection Exception";
        r4.logException(r5, r6, r1);	 Catch:{ Exception -> 0x01f4 }
    L_0x0131:
        r4 = 0;
        goto L_0x0022;
    L_0x0134:
        r4 = r12.mGeolocationLogEntry;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = r3.code();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.setReason(r5);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = com.tado.android.app.TadoApplication.locationManager;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = r3.code();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r6 = r12.getApplicationContext();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.onPostFailed(r5, r6);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = r4.toLogger();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r6 = 3;
        r7 = "LocationPostService";
        r8 = "%d Unsuccessful response. Url %s Body was %s";
        r4 = 3;
        r9 = new java.lang.Object[r4];	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = 0;
        r10 = r3.code();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r9[r4] = r10;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = 1;
        r10 = r3.raw();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = r10.request();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = r10.url();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = r10.url();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = r10.toString();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r9[r4] = r10;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = 2;
        r4 = r3.raw();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.request();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.body();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        if (r4 == 0) goto L_0x01a9;
    L_0x0191:
        r4 = r3.raw();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.request();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.body();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
    L_0x01a1:
        r9[r10] = r4;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5.log(r6, r7, r8, r9);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = 1;
        goto L_0x0022;
    L_0x01a9:
        r4 = "null body";
        goto L_0x01a1;
    L_0x01ad:
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4 = r4.toLogger();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r5 = 4;
        r6 = "LocationPostService";
        r7 = "Not posting location because the app has no internet: hasInternetConnection: %b /internetCapableSettings: %b";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r9 = 0;
        r10 = r12.getApplicationContext();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = com.tado.android.notifications.SettingsUtil.hasInternetConnection(r10);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = java.lang.Boolean.valueOf(r10);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r8[r9] = r10;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r9 = 1;
        r10 = r12.getApplicationContext();	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = com.tado.android.notifications.SettingsUtil.internetCapableSettings(r10);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r10 = java.lang.Boolean.valueOf(r10);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r8[r9] = r10;	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        r4.log(r5, r6, r7, r8);	 Catch:{ IOException -> 0x011b, Exception -> 0x01e2 }
        goto L_0x0131;
    L_0x01e2:
        r1 = move-exception;
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ Exception -> 0x01f4 }
        r4 = r4.toLogger();	 Catch:{ Exception -> 0x01f4 }
        r4 = r4.toCrashlytics();	 Catch:{ Exception -> 0x01f4 }
        r4.logException(r1);	 Catch:{ Exception -> 0x01f4 }
        goto L_0x0131;
    L_0x01f4:
        r1 = move-exception;
        r4 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x0202 }
        r4 = r4.toLogger();	 Catch:{ all -> 0x0202 }
        r4.logException(r1);	 Catch:{ all -> 0x0202 }
        goto L_0x0131;
    L_0x0202:
        r4 = move-exception;
        monitor-exit(r12);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.location.LocationPostService.postLocation(com.tado.android.rest.model.GeolocationUpdate, int):boolean");
    }

    synchronized WakeLock getWakeLock(Context ctx) {
        if (this.wakeLock == null) {
            this.wakeLock = ((PowerManager) ctx.getSystemService("power")).newWakeLock(1, TAG);
        }
        return this.wakeLock;
    }

    private void cancelCalls(OkHttpClient mOkHttpClient) {
        if (mOkHttpClient != null) {
            for (okhttp3.Call call : mOkHttpClient.dispatcher().queuedCalls()) {
                call.cancel();
                Snitcher.start().toLogger().log(4, TAG, "queued call cancelled", new Object[0]);
            }
            for (okhttp3.Call call2 : mOkHttpClient.dispatcher().runningCalls()) {
                call2.cancel();
                Snitcher.start().toLogger().log(4, TAG, "running call cancelled", new Object[0]);
            }
        }
    }

    public synchronized void acquireWakeLock(Context ctx) {
        getWakeLock(ctx).acquire(60000);
        lockAcquireTime = SystemClock.elapsedRealtime();
        Log.d(TAG, "WakeLock acquired at: " + SystemClock.elapsedRealtime());
    }

    private synchronized void releaseWakelockAndSetupNextAlarm() {
        try {
            CheckLocationServicesReceiver.setupNextAlarm(getApplicationContext());
            releaseWakeLock();
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
            releaseWakeLock();
        } catch (Throwable th) {
            releaseWakeLock();
        }
    }

    synchronized void releaseWakeLock() {
        if (this.wakeLock != null && this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        lockAcquireTime = -1;
        Log.d(TAG, "WakeLock released.");
    }

    public void onDestroy() {
        Snitcher.start().toLogger().log(4, TAG, "onDestroy", new Object[0]);
        super.onDestroy();
    }

    public synchronized boolean isWakeLockHeld(Context ctx) {
        boolean z = false;
        synchronized (this) {
            getWakeLock(ctx);
            if (this.wakeLock.isHeld()) {
                if (this.wakeLock.isHeld() && lockAcquireTime == -1) {
                    z = true;
                } else if (SystemClock.elapsedRealtime() - lockAcquireTime > 60000) {
                    lockAcquireTime = -1;
                    this.wakeLock.release();
                    Snitcher.start().toLogger().log(3, TAG, "WakeLock force released, was held for over 60 seconds. Time now: %d", Long.valueOf(SystemClock.elapsedRealtime()));
                } else {
                    z = true;
                }
            }
        }
        return z;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}

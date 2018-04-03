package com.tado.android.rest.model;

import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.tado.BuildConfig;
import com.tado.android.location.LocationPostService;
import com.tado.android.location.LocationUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.joda.time.DateTime;

public class GeolocationUpdate implements Serializable {
    private static final String KEY_ID = "id";
    @Expose
    private int accuracy;
    @Expose
    private String acquisitionMode;
    @Expose
    private Device device;
    @Expose
    private String extraInfo;
    @Expose
    private LatLng geolocation;
    @Expose
    private String id;
    @Expose
    private Boolean isMock;
    private transient Location location;
    @Expose
    private Movement movement;
    @Expose
    private LatLng realGeolocation;
    @Expose
    private String timestamp;
    @Expose
    private String triggerMode;
    @Expose
    private String triggeringGeofence;
    @Expose
    private String triggeringGeofences;
    @Expose
    private Update update;

    public static class Device implements Serializable {
        final String appVersion = String.format("%s (%s)", new Object[]{BuildConfig.VERSION_NAME, Integer.valueOf(BuildConfig.VERSION_CODE)});
        final String model = Build.MODEL;
        final String osVersion = VERSION.RELEASE;
        final String platform = "ANDROID";
        Settings settings;
        Status status;

        public static class Settings implements Serializable {
            Boolean backgroundRefresh;
            Boolean batteryOptimization;
            Boolean idleMode;
            String locationProviders;
            String locationServices;
            Boolean mobileData;
            Boolean powerSaving;
            Boolean pushNotifications;
            Boolean wifi;

            public void setWifi(boolean r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r0 = java.lang.Boolean.valueOf(r2);
                r1.wifi = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setWifi(boolean):void");
            }

            public void setMobileData(boolean r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r0 = java.lang.Boolean.valueOf(r2);
                r1.mobileData = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setMobileData(boolean):void");
            }

            public void setPushNotifications(boolean r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r0 = java.lang.Boolean.valueOf(r2);
                r1.pushNotifications = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setPushNotifications(boolean):void");
            }

            public void setLocationServices(java.lang.String r1) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r0 = this;
                r0.locationServices = r1;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setLocationServices(java.lang.String):void");
            }

            public void setBackgroundRefresh(boolean r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r0 = java.lang.Boolean.valueOf(r2);
                r1.backgroundRefresh = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setBackgroundRefresh(boolean):void");
            }

            public void setBatteryOptimization(boolean r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r0 = java.lang.Boolean.valueOf(r2);
                r1.batteryOptimization = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setBatteryOptimization(boolean):void");
            }

            public void setPowerSaving(boolean r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r0 = java.lang.Boolean.valueOf(r2);
                r1.powerSaving = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setPowerSaving(boolean):void");
            }

            public void setIdleMode(boolean r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r0 = java.lang.Boolean.valueOf(r2);
                r1.idleMode = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setIdleMode(boolean):void");
            }

            public void setLocationProviders(java.lang.String r1) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r0 = this;
                r0.locationProviders = r1;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Settings.setLocationProviders(java.lang.String):void");
            }
        }

        public static class Status implements Serializable {
            Float batteryLevel;
            List<Connection> connections;
            Map<String, String> homeWifis = new LinkedHashMap();
            Map<String, String> ignoredHomeWifis = new LinkedHashMap();

            public static class Connection {
                String ssid;
                String type;
                String wiFiId;

                public Connection(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                    /*
                    r2 = this;
                    r2.<init>();
                    r2.type = r3;
                    if (r4 == 0) goto L_0x002f;
                L_0x0007:
                    r0 = "\"";
                    r1 = "";
                    r0 = r4.replace(r0, r1);
                L_0x0011:
                    r2.ssid = r0;
                    r0 = new java.lang.StringBuilder;
                    r0.<init>();
                    r0 = r0.append(r5);
                    r1 = com.tado.android.utils.UserConfig.getWifiHashId();
                    r0 = r0.append(r1);
                    r0 = r0.toString();
                    r0 = com.tado.android.utils.HashUtils.sha1Hash(r0);
                    r2.wiFiId = r0;
                    return;
                L_0x002f:
                    r0 = 0;
                    goto L_0x0011;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Status.Connection.<init>(java.lang.String, java.lang.String, java.lang.String):void");
                }
            }

            public Status(java.lang.Float r8, java.util.List<com.tado.android.rest.model.GeolocationUpdate.Device.Status.Connection> r9, java.util.Map<java.lang.String, java.lang.String> r10, java.util.Map<java.lang.String, java.lang.String> r11) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r7 = this;
                r7.<init>();
                r3 = new java.util.LinkedHashMap;
                r3.<init>();
                r7.homeWifis = r3;
                r3 = new java.util.LinkedHashMap;
                r3.<init>();
                r7.ignoredHomeWifis = r3;
                r7.batteryLevel = r8;
                r7.connections = r9;
                r1 = new java.lang.StringBuilder;
                r1.<init>();
                r3 = r10.keySet();
                r4 = r3.iterator();
            L_0x0022:
                r3 = r4.hasNext();
                if (r3 == 0) goto L_0x005d;
            L_0x0028:
                r0 = r4.next();
                r0 = (java.lang.String) r0;
                r3 = r10.get(r0);
                r3 = (java.lang.String) r3;
                r5 = "\"";
                r6 = "";
                r2 = r3.replace(r5, r6);
                r3 = r7.homeWifis;
                r5 = new java.lang.StringBuilder;
                r5.<init>();
                r5 = r5.append(r0);
                r6 = com.tado.android.utils.UserConfig.getWifiHashId();
                r5 = r5.append(r6);
                r5 = r5.toString();
                r5 = com.tado.android.utils.HashUtils.sha1Hash(r5);
                r3.put(r5, r2);
                goto L_0x0022;
            L_0x005d:
                r3 = r11.keySet();
                r3 = r3.iterator();
            L_0x0065:
                r4 = r3.hasNext();
                if (r4 == 0) goto L_0x0096;
            L_0x006b:
                r0 = r3.next();
                r0 = (java.lang.String) r0;
                r2 = r11.get(r0);
                r2 = (java.lang.String) r2;
                r4 = r7.ignoredHomeWifis;
                r5 = new java.lang.StringBuilder;
                r5.<init>();
                r5 = r5.append(r0);
                r6 = com.tado.android.utils.UserConfig.getWifiHashId();
                r5 = r5.append(r6);
                r5 = r5.toString();
                r5 = com.tado.android.utils.HashUtils.sha1Hash(r5);
                r4.put(r5, r2);
                goto L_0x0065;
            L_0x0096:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.Status.<init>(java.lang.Float, java.util.List, java.util.Map, java.util.Map):void");
            }
        }

        public Device(com.tado.android.rest.model.GeolocationUpdate.Device.Status r5, com.tado.android.rest.model.GeolocationUpdate.Device.Settings r6) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r4 = this;
            r4.<init>();
            r0 = "ANDROID";
            r4.platform = r0;
            r0 = android.os.Build.VERSION.RELEASE;
            r4.osVersion = r0;
            r0 = android.os.Build.MODEL;
            r4.model = r0;
            r0 = "%s (%s)";
            r1 = 2;
            r1 = new java.lang.Object[r1];
            r2 = 0;
            r3 = "4.9.3";
            r1[r2] = r3;
            r2 = 1;
            r3 = 1500409030; // 0x596e6cc6 float:4.19441513E15 double:7.413005564E-315;
            r3 = java.lang.Integer.valueOf(r3);
            r1[r2] = r3;
            r0 = java.lang.String.format(r0, r1);
            r4.appVersion = r0;
            r4.status = r5;
            r4.settings = r6;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.<init>(com.tado.android.rest.model.GeolocationUpdate$Device$Status, com.tado.android.rest.model.GeolocationUpdate$Device$Settings):void");
        }

        public com.tado.android.rest.model.GeolocationUpdate.Device.Status getStatus() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r1 = this;
            r0 = r1.status;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.getStatus():com.tado.android.rest.model.GeolocationUpdate$Device$Status");
        }

        public void setStatus(com.tado.android.rest.model.GeolocationUpdate.Device.Status r1) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r0 = this;
            r0.status = r1;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.rest.model.GeolocationUpdate.Device.setStatus(com.tado.android.rest.model.GeolocationUpdate$Device$Status):void");
        }
    }

    private static class Movement implements Serializable {
        String activity;
        Float course;
        Integer speed;

        private Movement() {
        }
    }

    public static class Update implements Serializable {
        int attempt;
        List<String> droppedLocations;
        int droppedStreak;

        public Update(int attempt, List<Location> droppedLocations) {
            this.attempt = attempt;
            if (!droppedLocations.isEmpty()) {
                this.droppedStreak = droppedLocations.size();
                this.droppedLocations = new ArrayList(droppedLocations.size());
                for (Location location : droppedLocations) {
                    this.droppedLocations.add(String.format(Locale.US, "%s %f, %f (%f) %s", new Object[]{new DateTime(location.getTime()).toString(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Float.valueOf(location.getAccuracy()), LocationUtil.getDroppedReasons(location)}));
                }
            }
        }
    }

    public GeolocationUpdate(Location location) {
        setLocation(location);
    }

    public void setAcquisitionMode(String acquisitionMode) {
        this.acquisitionMode = acquisitionMode;
    }

    public String getAcquisitionMode() {
        return this.acquisitionMode;
    }

    public void setTriggerMode(String triggerMode) {
        this.triggerMode = triggerMode;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Update getUpdate() {
        return this.update;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    @Nullable
    public Location getLocation() {
        return this.location;
    }

    public LatLng getGeolocation() {
        return this.geolocation;
    }

    public void setRealGeolocation(LatLng realGeolocation) {
        this.realGeolocation = realGeolocation;
    }

    public void setLocation(Location location) {
        Boolean bool = null;
        this.location = location;
        Bundle extras = location.getExtras();
        if (extras != null) {
            if (extras.containsKey("id")) {
                this.id = extras.getString("id");
            }
            setTriggeringGeofence(extras.getString(LocationPostService.EXTRA_GEOFENCE_TRANSITION, null));
            setTriggeringGeofences(extras.getString(LocationPostService.EXTRA_GEOFENCE_LIST, null));
            if (extras.containsKey(LocationPostService.EXTRA_LOCATION_OVERRIDE)) {
                setRealGeolocation(LocationUtil.extractRealLocation(location));
            }
        }
        this.geolocation = new LatLng(location.getLatitude(), location.getLongitude());
        this.timestamp = DateTime.now().toString();
        this.accuracy = Math.round(location.getAccuracy());
        if (!(location.getBearing() == 0.0f && location.getSpeed() == 0.0f)) {
            this.movement = new Movement();
            this.movement.course = Float.valueOf(location.getBearing());
            this.movement.speed = Integer.valueOf(Math.round(location.getSpeed() * 3.6f));
            this.movement.activity = null;
        }
        this.acquisitionMode = location.getProvider();
        if (LocationUtil.isMockLocation(location)) {
            bool = Boolean.valueOf(true);
        }
        this.isMock = bool;
    }

    public void setTriggeringGeofence(String triggeringGeofence) {
        this.triggeringGeofence = triggeringGeofence;
    }

    public void setTriggeringGeofences(String triggeringGeofences) {
        this.triggeringGeofences = triggeringGeofences;
    }

    public String getId() {
        return this.id;
    }
}

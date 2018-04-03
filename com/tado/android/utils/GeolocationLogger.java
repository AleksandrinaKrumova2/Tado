package com.tado.android.utils;

import android.location.Location;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GeolocationLogger {
    private static final String FILE = ((TextUtils.isEmpty(UserConfig.getUsername()) ? String.valueOf(System.currentTimeMillis()) : UserConfig.getUsername()) + ".log");
    private static final long MAX_SIZE = 10000000;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.GERMANY);

    static class C12501 implements Runnable {
        C12501() {
        }

        public void run() {
            GeolocationLogger.getLogFile().delete();
        }
    }

    public static synchronized void clearLogFile() {
        synchronized (GeolocationLogger.class) {
            new Thread(new C12501()).start();
        }
    }

    public static synchronized void logToFile(final BaseLogEntryInterface logEntry) {
        synchronized (GeolocationLogger.class) {
            if (TadoApplication.getTadoAppContext().getResources().getBoolean(C0676R.bool.logger)) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            FileOutputStream fos = TadoApplication.getTadoAppContext().openFileOutput(GeolocationLogger.FILE, 32768);
                            fos.write(logEntry.getBytes());
                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Log.e("GEOLocationLogger", "file not found exception");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            Log.e("GEOLocationLogger", "io exception");
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            Log.e("GEOLocationLogger", "general exception");
                        }
                        GeolocationLogger.checkFileSize();
                    }
                }).start();
            }
        }
    }

    private static synchronized void checkFileSize() {
        IOException e;
        Throwable th;
        synchronized (GeolocationLogger.class) {
            RandomAccessFile file = null;
            try {
                RandomAccessFile file2 = new RandomAccessFile(TadoApplication.getTadoAppContext().getFilesDir() + "/" + FILE, "rw");
                try {
                    if (file2.length() > MAX_SIZE) {
                        byte[] data = new byte[5000000];
                        file2.seek(5000000);
                        file2.readFully(data, 0, data.length);
                        file2.setLength(0);
                        file2.write(data);
                    }
                    if (file2 != null) {
                        try {
                            file2.close();
                            file = file2;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            file = file2;
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    file = file2;
                    try {
                        e2.printStackTrace();
                        if (file != null) {
                            try {
                                file.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (file != null) {
                            try {
                                file.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    file = file2;
                    if (file != null) {
                        file.close();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e222 = e4;
                e222.printStackTrace();
                if (file != null) {
                    file.close();
                }
            }
        }
    }

    public static GeolocationLogEntry getGeoLocationEntry(Location location) {
        GeolocationLogEntry geolocationLogEntry = new GeolocationLogEntry();
        try {
            geolocationLogEntry.setLongitude(location.getLongitude());
            geolocationLogEntry.setLatitude(location.getLatitude());
            geolocationLogEntry.setAccuracy(location.getAccuracy());
            geolocationLogEntry.setDate(dateFormat.format(new Date(System.currentTimeMillis())));
            geolocationLogEntry.setSpeed(location.getSpeed());
            geolocationLogEntry.setAltitude(location.getAltitude());
            DeviceStateLogEntry deviceStateLogEntry = new DeviceStateLogEntry();
            deviceStateLogEntry.setProvider(location.getProvider());
            deviceStateLogEntry.setBatteryLevel(DebugUtil.getBatteryLevel());
            geolocationLogEntry.setDeviceStateLogEntry(deviceStateLogEntry);
            ConnectivityStateLogEntry connectivityStateLogEntry = new ConnectivityStateLogEntry();
            NetworkInfo networkInfo = Util.getActiveNetwork();
            if (networkInfo != null) {
                if (networkInfo.getReason() != null) {
                    connectivityStateLogEntry.setReason(Util.getActiveNetwork().getReason());
                }
                if (networkInfo.getTypeName() != null) {
                    connectivityStateLogEntry.setTypeName(Util.getActiveNetwork().getTypeName());
                }
                if (networkInfo.getSubtypeName() != null) {
                    connectivityStateLogEntry.setSubtypeName(Util.getActiveNetwork().getSubtypeName());
                }
                if (networkInfo.getState() != null) {
                    connectivityStateLogEntry.setState(Util.getActiveNetwork().getState());
                }
            }
            if (Util.getConnectedWifiInfo() != null) {
                connectivityStateLogEntry.setCurrentWifi(Util.getConnectedWifiInfo().getSSID());
            }
            deviceStateLogEntry.setConnectivityStateLogEntry(connectivityStateLogEntry);
        } catch (NullPointerException npe) {
            geolocationLogEntry.setReason(npe.getLocalizedMessage());
        }
        return geolocationLogEntry;
    }

    public static String getCurrentFormatedTime() {
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static File getLogFile() {
        return new File(TadoApplication.getTadoAppContext().getFilesDir(), FILE);
    }

    public static synchronized void addSimpleLog(String message) {
        synchronized (GeolocationLogger.class) {
            SimpleLogEntry entry = new SimpleLogEntry();
            entry.setMessage(message);
            logToFile(entry);
        }
    }
}

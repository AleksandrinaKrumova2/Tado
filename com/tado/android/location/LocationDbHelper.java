package com.tado.android.location;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.os.Bundle;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.android.utils.Snitcher;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class LocationDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE locations (id TEXT PRIMARY KEY,lat DOUBLE,lon DOUBLE,acc FLOAT,time LONG,result CHAR,acq INT)";
    private static final String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS locations";
    private static final String SQL_DELETE_ENTRIES = "DELETE FROM locations";

    public LocationDbHelper(Context context) {
        super(context, "locations.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }

    public void addLocation(Location location, LocationTrigger trigger) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(ToolTipRelativeLayout.ID, location.getExtras().getString(ToolTipRelativeLayout.ID));
            values.put("lat", Double.valueOf(location.getLatitude()));
            values.put("lon", Double.valueOf(location.getLongitude()));
            values.put("acc", Float.valueOf(location.getAccuracy()));
            values.put("time", Long.valueOf(location.getTime()));
            values.put("result", location.getExtras().getSerializable("posted").toString());
            values.put("acq", Integer.valueOf(LocationTrigger.APP_TRIGGERED == trigger ? 1 : 0));
            db.insert("locations", null, values);
        } catch (SQLiteConstraintException e) {
            updateLocation(location.getExtras().getString(ToolTipRelativeLayout.ID), (PostState) location.getExtras().getSerializable("posted"));
        } finally {
            db.close();
        }
    }

    public void updateLocation(String id, PostState result) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("result", result.toString());
            if (db.update("locations", values, "id = ?", new String[]{id}) == 0) {
                Snitcher.start().log(4, "Tracking", "%d rows updated", Integer.valueOf(db.update("locations", values, "id = ?", new String[]{id})));
            } else {
                Snitcher.start().log(4, "Tracking", "%d rows updated", Integer.valueOf(db.update("locations", values, "id = ?", new String[]{id})));
            }
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().logException(e);
        } finally {
            db.close();
        }
    }

    public List<Location> getLocations(long timestamp, int filterMask) {
        List<Location> results = new ArrayList();
        String where = getWhere(filterMask);
        Cursor cursor = getReadableDatabase().query("locations", new String[]{ToolTipRelativeLayout.ID, "lat", "lon", "acc", "time", "result", "acq"}, where + "time > ?", new String[]{String.valueOf(timestamp)}, null, null, "time DESC");
        while (cursor.moveToNext()) {
            Location location = new Location("");
            String id = cursor.getString(cursor.getColumnIndexOrThrow(ToolTipRelativeLayout.ID));
            double lat = cursor.getDouble(cursor.getColumnIndexOrThrow("lat"));
            double lon = cursor.getDouble(cursor.getColumnIndexOrThrow("lon"));
            float acc = cursor.getFloat(cursor.getColumnIndexOrThrow("acc"));
            long time = cursor.getLong(cursor.getColumnIndexOrThrow("time"));
            String result = cursor.getString(cursor.getColumnIndexOrThrow("result"));
            int acq = cursor.getInt(cursor.getColumnIndexOrThrow("time"));
            location.setLatitude(lat);
            location.setLongitude(lon);
            location.setAccuracy(acc);
            location.setTime(time);
            Bundle data = new Bundle();
            data.putString(ToolTipRelativeLayout.ID, id);
            data.putSerializable("posted", PostState.fromValue(result));
            data.putInt("acq", acq);
            location.setExtras(data);
            results.add(location);
        }
        cursor.close();
        return results;
    }

    private String getWhere(int filterMask) {
        StringBuilder sb = new StringBuilder();
        if ((filterMask & 1) == 1) {
            sb.append("result == '+'");
        }
        if ((filterMask & 2) == 2) {
            if (sb.length() > 0) {
                sb.append(" OR ");
            }
            sb.append("result == '-'");
        }
        if ((filterMask & 4) == 4) {
            if (sb.length() > 0) {
                sb.append(" OR ");
            }
            sb.append("result == '!'");
        }
        if ((filterMask & 8) == 8) {
            if (sb.length() > 0) {
                sb.append(" OR ");
            }
            sb.append("result == '?'");
        }
        if (sb.length() > 0) {
            sb.append(") AND ").insert(0, "(");
        }
        return sb.toString();
    }

    public int purgeOldData(int days) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.add(5, -days);
            long startTime = (long) calendar.get(14);
            int rows = db.delete("locations", "time< ?", new String[]{String.valueOf(startTime)});
            Snitcher.start().toLogger().log(4, "JobScheduler", "Deleted %d entries of location data older than %s", Integer.valueOf(rows), SimpleDateFormat.getTimeInstance(3, Locale.UK).format(new Date(startTime)));
            return rows;
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().logException(e);
            return 0;
        } finally {
            db.close();
        }
    }

    public void clearAllData() {
        getWritableDatabase().execSQL(SQL_DELETE_ENTRIES);
    }

    public void closeDb() {
        close();
    }
}

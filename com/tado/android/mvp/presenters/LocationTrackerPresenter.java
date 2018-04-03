package com.tado.android.mvp.presenters;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.squareup.otto.Subscribe;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.LocationDbHelper;
import com.tado.android.location.PostState;
import com.tado.android.mvp.common.BasePresenter;
import com.tado.android.mvp.views.LocationTrackerView;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class LocationTrackerPresenter implements BasePresenter<LocationTrackerView> {
    public static final int FILTER_FAILED = 2;
    public static final int FILTER_FILTERED = 4;
    public static final int FILTER_POSTED = 1;
    public static final int FILTER_UNKNOWN = 8;
    private HashMap<PostState, Bitmap> bitmapCache = new HashMap(4);
    private LocationDbHelper db;
    private int filterMask = 15;
    private boolean mapReady = false;
    private LocationTrackerView view;

    public LocationTrackerPresenter() {
        new LocationDbHelper(TadoApplication.getTadoAppContext()).purgeOldData(7);
    }

    public void bindView(LocationTrackerView view) {
        this.view = view;
        this.db = new LocationDbHelper(TadoApplication.getTadoAppContext());
        TadoApplication.getBus().register(this);
    }

    public void unbindView() {
        TadoApplication.getBus().unregister(this);
        if (this.db != null) {
            this.db.closeDb();
        }
        for (Bitmap bitmap : this.bitmapCache.values()) {
            bitmap.recycle();
        }
        this.bitmapCache.clear();
        this.view = null;
    }

    @Subscribe
    public void onLocationReceived(Location location) {
        populate();
    }

    public void onMapReady() {
        this.mapReady = true;
        populate();
    }

    private void populate() {
        if (this.view != null && this.mapReady) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(5, -1);
            List<Location> locations = this.db.getLocations((long) calendar.get(14), this.filterMask);
            List<LatLng> points = new ArrayList(locations.size());
            Builder boundsBuilder = LatLngBounds.builder();
            this.view.clear();
            addHome();
            for (Location location : locations) {
                LatLng point = this.view.addPoint(location, getMarkerIcon(location));
                if (point != null) {
                    points.add(point);
                    boundsBuilder.include(point);
                }
            }
            this.view.addLine(points);
            try {
                this.view.zoomToArea(boundsBuilder.build());
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    private void addHome() {
        if (this.view != null && this.mapReady) {
            Location home = UserConfig.getHome();
            if (home != null) {
                LatLng homeLatLng = new LatLng(home.getLatitude(), home.getLongitude());
                this.view.addHome(homeLatLng, UserConfig.getHomeFence());
                if (UserConfig.getLocationConfiguration() != null) {
                    this.view.addFences(UserConfig.getLocationConfiguration().getRegions(), homeLatLng);
                }
            }
        }
    }

    private Bitmap getMarkerIcon(Location location) {
        PostState state = (PostState) location.getExtras().getSerializable("posted");
        if (this.bitmapCache.containsKey(state)) {
            return (Bitmap) this.bitmapCache.get(state);
        }
        float hue = BitmapDescriptorFactory.HUE_ORANGE;
        switch (state) {
            case UNKNOWN:
                hue = BitmapDescriptorFactory.HUE_ORANGE;
                break;
            case POSTED:
                hue = BitmapDescriptorFactory.HUE_GREEN;
                break;
            case FAILED:
                hue = 0.0f;
                break;
            case FILTERED:
                hue = BitmapDescriptorFactory.HUE_BLUE;
                break;
        }
        int color = Color.HSVToColor(new float[]{hue, 100.0f, 100.0f});
        Bitmap bmp = Bitmap.createBitmap(26, 26, Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint(1);
        p.setColor(color);
        canvas.drawCircle((float) 13, (float) 13, (float) 13, p);
        this.bitmapCache.put(state, bmp);
        return bmp;
    }

    public void onFilterChanged(int filter, boolean enabled) {
        if (enabled) {
            this.filterMask |= filter;
        } else {
            this.filterMask &= filter ^ -1;
        }
        populate();
    }
}

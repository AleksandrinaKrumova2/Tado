package com.tado.android.mvp.views;

import android.graphics.Bitmap;
import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.tado.android.mvp.common.BaseView;
import java.util.List;

public interface LocationTrackerView extends BaseView {
    void addFences(List<Float> list, LatLng latLng);

    void addHome(LatLng latLng, float f);

    void addLine(List<LatLng> list);

    LatLng addPoint(Location location, Bitmap bitmap);

    void clear();

    void zoomToArea(LatLngBounds latLngBounds);
}

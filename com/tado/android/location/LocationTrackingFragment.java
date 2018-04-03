package com.tado.android.location;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tado.C0676R;
import com.tado.android.controllers.LoggerController;
import com.tado.android.mvp.presenters.LocationTrackerPresenter;
import com.tado.android.mvp.views.LocationTrackerView;
import com.tado.android.notifications.LocationHistoryNotification;
import com.tado.android.utils.Constants;
import com.tado.android.utils.DebugConfig;
import com.tado.android.utils.UserConfig;
import java.util.List;

public class LocationTrackingFragment extends Fragment implements LocationTrackerView {
    @BindView(2131296411)
    CheckBox failedCheckBox;
    @BindView(2131296412)
    CheckBox filterCheckBox;
    private LocationTrackerPresenter mLocationTrackerPresenter;
    private GoogleMap map;
    @BindView(2131296413)
    CheckBox notificationCheckBox;
    @BindView(2131296414)
    CheckBox postedCheckBox;
    @BindView(2131296415)
    CheckBox unknownCheckBox;

    class C10051 implements OnClickListener {
        C10051() {
        }

        public void onClick(View v) {
            LoggerController.INSTANCE.showSendLogsDialog(LocationTrackingFragment.this.getActivity(), v, false);
        }
    }

    class C10062 implements OnClickListener {
        C10062() {
        }

        public void onClick(View v) {
            LocationTrackingFragment.this.map.clear();
            LocationDbHelper db = new LocationDbHelper(LocationTrackingFragment.this.getContext());
            db.clearAllData();
            db.closeDb();
        }
    }

    class C10073 implements OnMapReadyCallback {
        C10073() {
        }

        public void onMapReady(GoogleMap googleMap) {
            LocationTrackingFragment.this.map = googleMap;
            LocationTrackingFragment.this.map.setMyLocationEnabled(true);
            LocationTrackingFragment.this.mLocationTrackerPresenter.onMapReady();
        }
    }

    public static LocationTrackingFragment newInstance() {
        return new LocationTrackingFragment();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mLocationTrackerPresenter = new LocationTrackerPresenter();
        this.mLocationTrackerPresenter.bindView((LocationTrackerView) this);
    }

    public void onDetach() {
        super.onDetach();
        if (this.mLocationTrackerPresenter != null) {
            this.mLocationTrackerPresenter.unbindView();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View layer = inflater.inflate(C0676R.layout.location_testing_overlay, container, false);
        ButterKnife.bind((Object) this, layer);
        ((TextView) layer.findViewById(C0676R.id.text_mobile_user)).setText(UserConfig.getNickname());
        layer.findViewById(C0676R.id.button_send_logs).setOnClickListener(new C10051());
        ((Button) layer.findViewById(C0676R.id.button_clear)).setOnClickListener(new C10062());
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(C0676R.id.map)).getMapAsync(new C10073());
        return layer;
    }

    public LatLng addPoint(Location location, Bitmap bmp) {
        if (this.map == null) {
            return null;
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions opts = new MarkerOptions().draggable(false).flat(false).position(latLng).anchor(0.5f, 0.5f).title(((PostState) location.getExtras().getSerializable("posted")).name()).snippet(LocationUtil.formatLocation(location)).icon(BitmapDescriptorFactory.fromBitmap(bmp));
        CircleOptions circleOptions = new CircleOptions().center(latLng).radius((double) location.getAccuracy()).strokeWidth(1.0f).strokeColor(ViewCompat.MEASURED_STATE_MASK);
        this.map.addMarker(opts);
        this.map.addCircle(circleOptions);
        return latLng;
    }

    public void clear() {
        if (this.map != null) {
            this.map.clear();
        }
    }

    public void addLine(List<LatLng> points) {
        if (this.map != null) {
            this.map.addPolyline(new PolylineOptions().addAll(points).clickable(false).color(SupportMenu.CATEGORY_MASK).geodesic(true).width(5.0f));
        }
    }

    public void zoomToArea(LatLngBounds bounds) {
        if (this.map != null) {
            try {
                this.map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20));
            } catch (IllegalStateException e) {
                this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), 15.0f));
            }
        }
    }

    public void addHome(LatLng home, float fenceRadius) {
        if (this.map != null) {
            MarkerOptions opts = new MarkerOptions().draggable(false).flat(false).position(home).title(String.valueOf(fenceRadius)).icon(BitmapDescriptorFactory.defaultMarker());
            this.map.addCircle(new CircleOptions().radius((double) fenceRadius).strokeWidth(Constants.MAX_OFFSET_CELSIUS).strokeColor(-16711681).fillColor(1711341567).center(home).clickable(false));
            this.map.addMarker(opts);
        }
    }

    public void addFences(List<Float> distances, LatLng center) {
        if (this.map != null) {
            for (Float floatValue : distances) {
                this.map.addCircle(new CircleOptions().radius((double) floatValue.floatValue()).strokeWidth(Constants.MAX_OFFSET_CELSIUS).strokeColor(InputDeviceCompat.SOURCE_ANY).center(center).clickable(false));
            }
        }
    }

    @OnCheckedChanged({2131296411, 2131296415, 2131296414, 2131296413, 2131296412})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case C0676R.id.check_failed:
                this.mLocationTrackerPresenter.onFilterChanged(2, isChecked);
                return;
            case C0676R.id.check_filtered:
                this.mLocationTrackerPresenter.onFilterChanged(4, isChecked);
                return;
            case C0676R.id.check_notification:
                DebugConfig.setLocationNotificationEnabled(isChecked);
                if (!isChecked) {
                    NotificationManagerCompat.from(buttonView.getContext()).cancel(LocationHistoryNotification.NOTIFICATION_ID);
                    return;
                }
                return;
            case C0676R.id.check_posted:
                this.mLocationTrackerPresenter.onFilterChanged(1, isChecked);
                return;
            case C0676R.id.check_unknown:
                this.mLocationTrackerPresenter.onFilterChanged(8, isChecked);
                return;
            default:
                return;
        }
    }
}

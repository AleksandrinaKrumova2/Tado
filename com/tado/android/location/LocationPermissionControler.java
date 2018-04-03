package com.tado.android.location;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.tado.C0676R;
import com.tado.android.app.TadoApplication;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.settings.appsettings.AppSettingsActivity;
import com.tado.android.settings.model.PresenceDetectionSettings;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.Util;

public enum LocationPermissionControler {
    INSTANCE;
    
    private static final String TAG = "LocationPermissionCtl";

    public boolean areLocationPermissionsGranted(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    public boolean requestLocationPermissions(Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, "android.permission.ACCESS_FINE_LOCATION")) {
            return false;
        }
        ActivityCompat.requestPermissions((Activity) context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 0);
        return true;
    }

    public boolean checkLocationPermissionsAndRequest(Activity context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return true;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return false;
        }
        ActivityCompat.requestPermissions(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 0);
        return false;
    }

    public boolean checkLocationPermissionsAndInitLocationApi(Activity context) {
        if (!checkLocationPermissionsAndRequest(context)) {
            return false;
        }
        initLocationAPI();
        return true;
    }

    public boolean handlePermissionCallback(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults == null || grantResults.length <= 0 || grantResults[0] != 0) {
            return false;
        }
        initLocationAPI();
        return true;
    }

    public void showLocationPermissionInfoDialog(final Context context) {
        AlertDialog alertDialog = AlertDialogs.getCancelRetryAlert(context.getString(C0676R.string.signin_locationPermission_permissionSettingMismatch_title), context.getString(C0676R.string.signin_locationPermission_permissionSettingMismatch_message), context.getString(C0676R.string.signin_locationPermission_permissionSettingMismatch_allowAccessButton), context.getString(C0676R.string.signin_locationPermission_permissionSettingMismatch_deactivateButton), (Activity) context, new AlertChoiceDialogListener() {
            public void OnOKClicked() {
                PresenceDetectionSettings.updatePresenceDetectionSetting(true, null);
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", Util.getPackageName(), null));
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            public void OnCancelClicked() {
                if (context instanceof AppSettingsActivity) {
                    ((AppSettingsActivity) context).disableGeoLocationTracking();
                    return;
                }
                PresenceDetectionSettings.updatePresenceDetectionSetting(false, null);
                context.startActivity(new Intent(context, AppSettingsActivity.class));
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void initLocationAPI() {
        if (UserConfig.isLocationBasedControlEnabled()) {
            Snitcher.start().toLogger().log(3, TAG, "Starting tracking permission dialog", new Object[0]);
            TadoApplication.locationManager.startTrackingIfEnabled();
            TadoApplication.locationManager.postLastKnownLocation(LocationAcquisitionMode.LAST_KNOWN_LOCATION, LocationTrigger.APP_TRIGGERED);
            return;
        }
        TadoApplication.locationManager.stopTracking();
    }
}

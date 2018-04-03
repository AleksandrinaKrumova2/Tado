package com.tado.android.installation.barcode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.BarcodeDetector.Builder;
import com.tado.C0676R;
import com.tado.android.dialogs.AlertChoiceDialogListener;
import com.tado.android.dialogs.AlertDialogs;
import com.tado.android.dialogs.AlertWarningDialogListener;
import com.tado.android.entities.ServerError;
import com.tado.android.installation.srt.view.fragments.SrtHvacInstallationFragment;
import com.tado.android.installation.srt.view.fragments.SrtRegisterDeviceFragment.DeviceTypeEnum;
import com.tado.android.rest.callback.TadoCallback;
import com.tado.android.rest.callback.presenters.GeneralErrorAlertPresenter;
import com.tado.android.rest.model.DeviceInput;
import com.tado.android.rest.model.hvac.BridgeReplacementInstallation;
import com.tado.android.rest.model.hvac.BridgeReplacementRequest;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Constants;
import com.tado.android.utils.ResourceFactory;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UserConfig;
import com.tado.android.utils.ValidationUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import retrofit2.Call;
import retrofit2.Response;

public class BarcodeActivity extends AppCompatActivity {
    public static final String EXTRA_HARDWARE_DEVICE = "hardwareDevice";
    public static final String EXTRA_INSTALLATION = "extraInstallation";
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    public static final int REQ_BARCODE_SCAN = 239;
    private static final String TAG = "BarcodeActivity";
    private Processor<Barcode> barcodeProcessor = new C08751();
    private ArrayList<Rect> boundingBoxes = new ArrayList();
    private CameraPreview cameraPreview;
    private DeviceTypeEnum deviceType;
    private boolean isReplacement = false;
    ReentrantLock lock = new ReentrantLock();
    private CameraSource mCameraSource;
    private FrameLayout preview;
    private BarcodeMaskImageView scanFrame;

    class C08751 implements Processor<Barcode> {
        C08751() {
        }

        public void release() {
        }

        public void receiveDetections(final Detections<Barcode> detections) {
            BarcodeActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    SparseArray<Barcode> barcodes = detections.getDetectedItems();
                    BarcodeActivity.this.boundingBoxes.clear();
                    if (barcodes.size() > 0) {
                        for (int i = 0; i < barcodes.size(); i++) {
                            Barcode barcode = (Barcode) barcodes.get(barcodes.keyAt(i));
                            BarcodeActivity.this.boundingBoxes.add(barcode.getBoundingBox());
                            if (!BarcodeActivity.this.scanFrame.isCodeInFrameBounds(barcode.getBoundingBox())) {
                                Snitcher.start().log(4, BarcodeActivity.TAG, "Code is not inside the frame %s", barcode.displayValue);
                            } else if (!BarcodeActivity.this.lock.isLocked()) {
                                BarcodeActivity.this.lock.lock();
                                Snitcher.start().log(3, BarcodeActivity.TAG, "Found QR code %s", barcode.displayValue);
                                BarcodeActivity.this.runValidations(serialNumber);
                                BarcodeActivity.this.mCameraSource.stop();
                            }
                        }
                        BarcodeActivity.this.scanFrame.setDetectedBounds((Rect[]) BarcodeActivity.this.boundingBoxes.toArray(new Rect[BarcodeActivity.this.boundingBoxes.size()]));
                        return;
                    }
                    BarcodeActivity.this.scanFrame.setDetectedBounds(new Rect[0]);
                }
            });
        }
    }

    class C08762 implements AlertWarningDialogListener {
        C08762() {
        }

        public void OnOKClicked() {
            BarcodeActivity.this.cameraPreview.start();
            BarcodeActivity.this.lock.unlock();
        }
    }

    class C08794 extends TadoCallback<BridgeReplacementInstallation> {

        class C08781 implements AlertWarningDialogListener {
            C08781() {
            }

            public void OnOKClicked() {
                BarcodeActivity.this.cameraPreview.start();
                BarcodeActivity.this.lock.unlock();
            }
        }

        C08794() {
        }

        public void onResponse(Call<BridgeReplacementInstallation> call, Response<BridgeReplacementInstallation> response) {
            super.onResponse(call, response);
            if (response.isSuccessful()) {
                BarcodeActivity.this.onReplacementCreated((BridgeReplacementInstallation) response.body());
            } else if (response.code() == 422 && DeviceTypeEnum.GATEWAY == BarcodeActivity.this.deviceType) {
                BarcodeActivity.this.handleServerError(this.serverError);
            } else {
                AlertDialogs.showSimpleWarning(null, BarcodeActivity.this.getString(C0676R.string.installation_errors_wrongRegistrationInfo_message), BarcodeActivity.this.getString(C0676R.string.ok), BarcodeActivity.this, new C08781());
            }
        }

        public void onFailure(Call<BridgeReplacementInstallation> call, Throwable t) {
            super.onFailure(call, t);
            Snackbar.make(BarcodeActivity.this.preview, (int) C0676R.string.errors_noInternetConnection_message, 0).show();
            BarcodeActivity.this.cameraPreview.start();
            BarcodeActivity.this.lock.unlock();
        }
    }

    class C08826 implements AlertWarningDialogListener {
        C08826() {
        }

        public void OnOKClicked() {
            BarcodeActivity.this.cameraPreview.start();
            BarcodeActivity.this.lock.unlock();
        }
    }

    class C08837 implements AlertWarningDialogListener {
        C08837() {
        }

        public void OnOKClicked() {
            BarcodeActivity.this.cameraPreview.start();
            BarcodeActivity.this.lock.unlock();
        }
    }

    class C08848 implements AlertWarningDialogListener {
        C08848() {
        }

        public void OnOKClicked() {
            BarcodeActivity.this.cameraPreview.start();
            BarcodeActivity.this.lock.unlock();
        }
    }

    class C08859 implements AlertChoiceDialogListener {
        C08859() {
        }

        public void OnOKClicked() {
            ActivityCompat.requestPermissions(BarcodeActivity.this, new String[]{"android.permission.CAMERA"}, 2);
        }

        public void OnCancelClicked() {
            BarcodeActivity.this.onManualScan(null);
        }
    }

    public class CameraPreview extends SurfaceView implements Callback {
        private static final String TAG = "CameraPreview";
        private SurfaceHolder mHolder = getHolder();

        public CameraPreview(Context context) {
            super(context);
            this.mHolder.addCallback(this);
            this.mHolder.setType(3);
        }

        public void surfaceCreated(SurfaceHolder holder) {
            Size size = BarcodeActivity.this.mCameraSource.getPreviewSize();
            int min = Math.min(size.getWidth(), size.getHeight());
            int max = Math.max(size.getWidth(), size.getHeight());
            if (BarcodeActivity.this.isPortraitMode()) {
                BarcodeActivity.this.scanFrame.setCameraInfo(min, max);
            } else {
                BarcodeActivity.this.scanFrame.setCameraInfo(max, min);
            }
            start();
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (this.mHolder.getSurface() != null) {
                try {
                    BarcodeActivity.this.mCameraSource.stop();
                } catch (Exception e) {
                }
                start();
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            BarcodeActivity.this.mCameraSource.stop();
        }

        public void start() {
            try {
                BarcodeActivity.this.mCameraSource.start(this.mHolder);
            } catch (IOException e) {
                Snitcher.start().logException(TAG, e);
            }
        }

        public void stop() {
            if (BarcodeActivity.this.mCameraSource != null) {
                BarcodeActivity.this.mCameraSource.stop();
            }
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(128);
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_barcode_scan);
        TextView instructionsTextView = (TextView) findViewById(C0676R.id.barcode_instructions);
        this.deviceType = (DeviceTypeEnum) getIntent().getSerializableExtra(SrtHvacInstallationFragment.KEY_DEVICE_TYPE);
        this.isReplacement = getIntent().getBooleanExtra(SrtHvacInstallationFragment.KEY_IS_REPLACEMENT, false);
        instructionsTextView.setText(getString(C0676R.string.installation_scanToRegister_title, new Object[]{getString(ResourceFactory.getRegisterProductNameStringResource(this.deviceType))}));
        this.preview = (FrameLayout) findViewById(C0676R.id.camera_preview);
        this.scanFrame = (BarcodeMaskImageView) findViewById(C0676R.id.scan_mask);
        if (!checkCameraHardware(this)) {
            onManualScan(null);
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            onPermissionGranted();
        } else {
            requestCameraPermission();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.cameraPreview != null) {
            this.cameraPreview.start();
            if (this.lock.isLocked()) {
                this.lock.unlock();
            }
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.cameraPreview != null) {
            this.cameraPreview.stop();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mCameraSource != null) {
            this.mCameraSource.release();
        }
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.camera")) {
            return true;
        }
        return false;
    }

    private void onPermissionGranted() {
        BarcodeDetector barcodeDetector = new Builder(this).setBarcodeFormats(272).build();
        CameraSource.Builder builder = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(getResources().getDisplayMetrics().heightPixels, getResources().getDisplayMetrics().widthPixels).setAutoFocusEnabled(true).setFacing(0).setRequestedFps(15.0f);
        barcodeDetector.setProcessor(this.barcodeProcessor);
        this.mCameraSource = builder.build();
        this.cameraPreview = new CameraPreview(this);
        if (barcodeDetector.isOperational()) {
            this.preview.addView(this.cameraPreview);
        }
    }

    private boolean isPortraitMode() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == 2) {
            return false;
        }
        if (orientation == 1) {
            return true;
        }
        Snitcher.start().log(3, TAG, "isPortraitMode returning false by default", new Object[0]);
        return false;
    }

    private void runValidations(String code) {
        String serialNumber = null;
        String authCode = null;
        if (code != null) {
            String[] params = code.split("-");
            if (params.length > 0) {
                serialNumber = params[0];
            }
            if (params.length > 1) {
                authCode = params[1];
            }
        }
        if (ValidationUtils.isValidForRegistration(serialNumber, authCode, this.deviceType)) {
            showRegisterConfirmationDialog(serialNumber, authCode, this.deviceType);
            return;
        }
        String validationErrorMessage = ValidationUtils.validateAuthCode(authCode, true, getResources());
        if (validationErrorMessage != null && validationErrorMessage.isEmpty()) {
            validationErrorMessage = ValidationUtils.validateSerialNumber(serialNumber, true, getResources(), this.deviceType);
        }
        if (validationErrorMessage != null && !validationErrorMessage.isEmpty()) {
            AlertDialogs.showSimpleWarning(getString(C0676R.string.installation_errors_wrongRegistrationInfo_title), validationErrorMessage, getString(C0676R.string.ok), this, new C08762());
        }
    }

    private void showRegisterConfirmationDialog(final String serialNumber, final String authCode, final DeviceTypeEnum deviceType) {
        AlertDialogs.showChoiceAlert(null, getString(C0676R.string.installation_scanToRegister_confirm_message, new Object[]{getString(ResourceFactory.getConfirmProductNameStringResource(deviceType)), serialNumber}), getString(C0676R.string.installation_scanToRegister_confirm_registerButton), getString(C0676R.string.installation_scanToRegister_confirm_cancelButton), this, new AlertChoiceDialogListener() {
            public void OnOKClicked() {
                if (BarcodeActivity.this.isReplacement) {
                    BarcodeActivity.this.callReplaceBridge(new DeviceInput(serialNumber, authCode));
                } else {
                    BarcodeActivity.this.callRegisterSrt(serialNumber, authCode, deviceType);
                }
            }

            public void OnCancelClicked() {
                BarcodeActivity.this.cameraPreview.start();
                BarcodeActivity.this.lock.unlock();
            }
        });
    }

    private void callReplaceBridge(DeviceInput bridge) {
        RestServiceGenerator.getTadoInstallationRestService().createBridgeReplacementInstallation(UserConfig.getHomeId(), new BridgeReplacementRequest("REPLACE_BRIDGE", Integer.valueOf(1), bridge)).enqueue(new C08794());
    }

    private void callRegisterSrt(String serialNumber, String authCode, final DeviceTypeEnum deviceType) {
        RestServiceGenerator.getTadoRestService().registerDevice(UserConfig.getHomeId(), new DeviceInput(serialNumber, authCode), RestServiceGenerator.getCredentialsMap()).enqueue(new TadoCallback<GenericHardwareDevice>(new GeneralErrorAlertPresenter(this)) {

            class C08801 implements AlertWarningDialogListener {
                C08801() {
                }

                public void OnOKClicked() {
                    BarcodeActivity.this.cameraPreview.start();
                    BarcodeActivity.this.lock.unlock();
                }
            }

            public void onResponse(Call<GenericHardwareDevice> call, Response<GenericHardwareDevice> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {
                    BarcodeActivity.this.onRegisterDevice((GenericHardwareDevice) response.body());
                } else if (response.code() == 422 && DeviceTypeEnum.VALVE == deviceType) {
                    BarcodeActivity.this.handleServerError(this.serverError);
                } else {
                    AlertDialogs.showSimpleWarning(null, BarcodeActivity.this.getString(C0676R.string.installation_errors_wrongRegistrationInfo_message), BarcodeActivity.this.getString(C0676R.string.ok), BarcodeActivity.this, new C08801());
                }
            }

            public void onFailure(Call<GenericHardwareDevice> call, Throwable t) {
                super.onFailure(call, t);
                Snackbar.make(BarcodeActivity.this.preview, (int) C0676R.string.errors_noInternetConnection_message, 0).show();
                BarcodeActivity.this.cameraPreview.start();
                BarcodeActivity.this.lock.unlock();
            }
        });
    }

    private void handleServerError(ServerError serverError) {
        if (serverError == null) {
            return;
        }
        if (serverError.getCode().equals("hwDevice.alreadyRegistered")) {
            AlertDialogs.showSimpleWarning(null, getString(C0676R.string.installation_errors_deviceRegisteredToYourHome_message), getString(C0676R.string.ok), this, new C08826());
        } else if (serverError.getCode().equals(Constants.SERVER_ERROR_ALREADY_REGISTERED)) {
            AlertDialogs.showSimpleWarning(null, getString(C0676R.string.installation_errors_deviceRegisteredToAnotherHome_message), getString(C0676R.string.ok), this, new C08837());
        } else {
            AlertDialogs.showSimpleWarning(null, serverError.getTitle(), getString(C0676R.string.ok), this, new C08848());
        }
    }

    private void requestCameraPermission() {
        Snitcher.start().log(5, TAG, "Camera permission is not granted. Requesting permission", new Object[0]);
        String[] permissions = new String[]{"android.permission.CAMERA"};
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
            showCameraPermissionRationale();
        } else {
            ActivityCompat.requestPermissions(this, permissions, 2);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2 && grantResults.length > 0 && grantResults[0] == 0) {
            onPermissionGranted();
        } else {
            showCameraPermissionRationale();
        }
    }

    private void showCameraPermissionRationale() {
        AlertDialogs.showChoiceAlert(getString(C0676R.string.installation_scanToRegister_cameraPermission_title), getString(C0676R.string.installation_scanToRegister_cameraPermission_message), getString(C0676R.string.installation_scanToRegister_cameraPermission_confirmButton), getString(C0676R.string.installation_scanToRegister_cameraPermission_cancelButton), this, new C08859());
    }

    public void onManualScan(View view) {
        setResult(0, getIntent());
        finish();
    }

    public void onRegisterDevice(GenericHardwareDevice device) {
        if (device != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_HARDWARE_DEVICE, device);
            setResult(-1, intent);
            finish();
            return;
        }
        Snitcher.start().toCrashlytics().logException(new NullPointerException("Trying to register a null device"));
        this.cameraPreview.start();
        this.lock.unlock();
    }

    private void onReplacementCreated(BridgeReplacementInstallation installation) {
        if (installation != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_HARDWARE_DEVICE, (Serializable) installation.getDevices().get(0));
            intent.putExtra(EXTRA_INSTALLATION, installation);
            setResult(-1, intent);
            finish();
            return;
        }
        Snitcher.start().toCrashlytics().logException(new NullPointerException("Trying to register a null device"));
        this.cameraPreview.start();
        this.lock.unlock();
    }
}

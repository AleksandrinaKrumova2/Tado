package com.tado.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.rest.model.DeviceType;
import com.tado.android.rest.model.Zone.TypeEnum;
import com.tado.android.rest.model.installation.BatteryStateEnum;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.utils.ResourceFactory;

public class ActivityDeviceDetailsBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final LinearLayout activityDeviceDetails;
    @NonNull
    public final TextView batteryStatus;
    @NonNull
    public final Button buttonAsign;
    @NonNull
    public final Button buttonIdentify;
    @NonNull
    public final Button buttonRemoveDevice;
    @NonNull
    public final ImageView imageDevice;
    @Nullable
    private GenericHardwareDevice mDevice;
    private long mDirtyFlags = -1;
    @Nullable
    private TypeEnum mZoneType;
    @NonNull
    private final ScrollView mboundView0;
    @NonNull
    public final LinearLayout sectionBattery;
    @NonNull
    public final TextView textBattery;
    @NonNull
    public final TextView textDescription;
    @NonNull
    public final TextView textFirmware;
    @NonNull
    public final TextView textProductName;
    @NonNull
    public final TextView textSerialNumber;

    static {
        sViewsWithIds.put(C0676R.id.image_device, 11);
        sViewsWithIds.put(C0676R.id.button_remove_device, 12);
    }

    public ActivityDeviceDetailsBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.activityDeviceDetails = (LinearLayout) bindings[1];
        this.activityDeviceDetails.setTag(null);
        this.batteryStatus = (TextView) bindings[7];
        this.batteryStatus.setTag(null);
        this.buttonAsign = (Button) bindings[10];
        this.buttonAsign.setTag(null);
        this.buttonIdentify = (Button) bindings[9];
        this.buttonIdentify.setTag(null);
        this.buttonRemoveDevice = (Button) bindings[12];
        this.imageDevice = (ImageView) bindings[11];
        this.mboundView0 = (ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.sectionBattery = (LinearLayout) bindings[6];
        this.sectionBattery.setTag(null);
        this.textBattery = (TextView) bindings[8];
        this.textBattery.setTag(null);
        this.textDescription = (TextView) bindings[5];
        this.textDescription.setTag(null);
        this.textFirmware = (TextView) bindings[4];
        this.textFirmware.setTag(null);
        this.textProductName = (TextView) bindings[2];
        this.textProductName.setTag(null);
        this.textSerialNumber = (TextView) bindings[3];
        this.textSerialNumber.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (1 == variableId) {
            setDevice((GenericHardwareDevice) variable);
            return true;
        } else if (2 != variableId) {
            return false;
        } else {
            setZoneType((TypeEnum) variable);
            return true;
        }
    }

    public void setDevice(@Nullable GenericHardwareDevice Device) {
        this.mDevice = Device;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Nullable
    public GenericHardwareDevice getDevice() {
        return this.mDevice;
    }

    public void setZoneType(@Nullable TypeEnum ZoneType) {
        this.mZoneType = ZoneType;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Nullable
    public TypeEnum getZoneType() {
        return this.mZoneType;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        GenericHardwareDevice device = this.mDevice;
        boolean deviceShowBatteryText = false;
        TypeEnum zoneType = this.mZoneType;
        boolean deviceCanBeIdentified = false;
        int deviceValveViewVISIBLEViewGONE = 0;
        String deviceShortSerialNo = null;
        int deviceCanBeIdentifiedViewVISIBLEViewGONE = 0;
        String textFirmwareAndroidStringSettingsZoneSettingsDevicesDeviceDetailsFirmwareLabelJavaLangStringDeviceCurrentFwVersion = null;
        boolean deviceValve = false;
        int deviceBatteryStateBatteryStateEnumLOWBatteryStatusAndroidColorAcRedBatteryStatusAndroidColorBlack = 0;
        BatteryStateEnum deviceBatteryState = null;
        int resourceFactoryGetDescriptionStringDeviceZoneType = 0;
        String deviceBatteryStateBatteryStateEnumLOWBatteryStatusAndroidStringSettingsZoneSettingsDevicesDeviceDetailsBatteryLowStatusLabelBatteryStatusAndroidStringSettingsZoneSettingsDevicesDeviceDetailsBatteryGoodStatusLabel = null;
        DeviceType deviceGetDeviceType = null;
        int resourceFactoryGetDeviceNameDeviceGetDeviceType = 0;
        String deviceCurrentFwVersion = null;
        int deviceShowBatteryTextViewVISIBLEViewGONE = 0;
        String textSerialNumberAndroidStringSettingsZoneSettingsDevicesDeviceDetailsSerialNumberLabelJavaLangStringDeviceShortSerialNo = null;
        int deviceBatteryStateJavaLangObjectNullViewVISIBLEViewGONE = 0;
        if ((7 & dirtyFlags) != 0) {
            if ((5 & dirtyFlags) != 0) {
                if (device != null) {
                    deviceShowBatteryText = device.showBatteryText();
                    deviceCanBeIdentified = device.canBeIdentified();
                    deviceShortSerialNo = device.getShortSerialNo();
                    deviceValve = device.isValve();
                    deviceBatteryState = device.getBatteryState();
                    deviceGetDeviceType = device.getDeviceType();
                    deviceCurrentFwVersion = device.getCurrentFwVersion();
                }
                if ((5 & dirtyFlags) != 0) {
                    if (deviceShowBatteryText) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                    }
                }
                if ((5 & dirtyFlags) != 0) {
                    if (deviceCanBeIdentified) {
                        dirtyFlags |= 64;
                    } else {
                        dirtyFlags |= 32;
                    }
                }
                if ((5 & dirtyFlags) != 0) {
                    if (deviceValve) {
                        dirtyFlags |= 16;
                    } else {
                        dirtyFlags |= 8;
                    }
                }
                deviceShowBatteryTextViewVISIBLEViewGONE = deviceShowBatteryText ? 0 : 8;
                deviceCanBeIdentifiedViewVISIBLEViewGONE = deviceCanBeIdentified ? 0 : 8;
                textSerialNumberAndroidStringSettingsZoneSettingsDevicesDeviceDetailsSerialNumberLabelJavaLangStringDeviceShortSerialNo = this.textSerialNumber.getResources().getString(C0676R.string.settings_zoneSettings_devices_deviceDetails_serialNumberLabel) + " " + deviceShortSerialNo;
                deviceValveViewVISIBLEViewGONE = deviceValve ? 0 : 8;
                boolean deviceBatteryStateBatteryStateEnumLOW = deviceBatteryState == BatteryStateEnum.LOW;
                boolean deviceBatteryStateJavaLangObjectNull = deviceBatteryState != null;
                resourceFactoryGetDeviceNameDeviceGetDeviceType = ResourceFactory.getDeviceName(deviceGetDeviceType);
                textFirmwareAndroidStringSettingsZoneSettingsDevicesDeviceDetailsFirmwareLabelJavaLangStringDeviceCurrentFwVersion = this.textFirmware.getResources().getString(C0676R.string.settings_zoneSettings_devices_deviceDetails_firmwareLabel) + " " + deviceCurrentFwVersion;
                if ((5 & dirtyFlags) != 0) {
                    if (deviceBatteryStateBatteryStateEnumLOW) {
                        dirtyFlags = (dirtyFlags | 256) | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } else {
                        dirtyFlags = (dirtyFlags | 128) | 512;
                    }
                }
                if ((5 & dirtyFlags) != 0) {
                    if (deviceBatteryStateJavaLangObjectNull) {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
                    } else {
                        dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    }
                }
                deviceBatteryStateBatteryStateEnumLOWBatteryStatusAndroidColorAcRedBatteryStatusAndroidColorBlack = deviceBatteryStateBatteryStateEnumLOW ? ViewDataBinding.getColorFromResource(this.batteryStatus, C0676R.color.ac_red) : ViewDataBinding.getColorFromResource(this.batteryStatus, C0676R.color.black);
                deviceBatteryStateBatteryStateEnumLOWBatteryStatusAndroidStringSettingsZoneSettingsDevicesDeviceDetailsBatteryLowStatusLabelBatteryStatusAndroidStringSettingsZoneSettingsDevicesDeviceDetailsBatteryGoodStatusLabel = deviceBatteryStateBatteryStateEnumLOW ? this.batteryStatus.getResources().getString(C0676R.string.settings_zoneSettings_devices_deviceDetails_battery_lowStatusLabel) : this.batteryStatus.getResources().getString(C0676R.string.settings_zoneSettings_devices_deviceDetails_battery_goodStatusLabel);
                deviceBatteryStateJavaLangObjectNullViewVISIBLEViewGONE = deviceBatteryStateJavaLangObjectNull ? 0 : 8;
            }
            resourceFactoryGetDescriptionStringDeviceZoneType = ResourceFactory.getDescriptionString(device, zoneType);
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.batteryStatus, deviceBatteryStateBatteryStateEnumLOWBatteryStatusAndroidStringSettingsZoneSettingsDevicesDeviceDetailsBatteryLowStatusLabelBatteryStatusAndroidStringSettingsZoneSettingsDevicesDeviceDetailsBatteryGoodStatusLabel);
            this.batteryStatus.setTextColor(deviceBatteryStateBatteryStateEnumLOWBatteryStatusAndroidColorAcRedBatteryStatusAndroidColorBlack);
            this.buttonAsign.setVisibility(deviceValveViewVISIBLEViewGONE);
            this.buttonIdentify.setVisibility(deviceCanBeIdentifiedViewVISIBLEViewGONE);
            this.sectionBattery.setVisibility(deviceBatteryStateJavaLangObjectNullViewVISIBLEViewGONE);
            this.textBattery.setVisibility(deviceShowBatteryTextViewVISIBLEViewGONE);
            TextViewBindingAdapter.setText(this.textFirmware, textFirmwareAndroidStringSettingsZoneSettingsDevicesDeviceDetailsFirmwareLabelJavaLangStringDeviceCurrentFwVersion);
            this.textProductName.setText(resourceFactoryGetDeviceNameDeviceGetDeviceType);
            TextViewBindingAdapter.setText(this.textSerialNumber, textSerialNumberAndroidStringSettingsZoneSettingsDevicesDeviceDetailsSerialNumberLabelJavaLangStringDeviceShortSerialNo);
        }
        if ((7 & dirtyFlags) != 0) {
            this.textDescription.setText(resourceFactoryGetDescriptionStringDeviceZoneType);
        }
    }

    @NonNull
    public static ActivityDeviceDetailsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeviceDetailsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityDeviceDetailsBinding) DataBindingUtil.inflate(inflater, C0676R.layout.activity_device_details, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityDeviceDetailsBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeviceDetailsBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0676R.layout.activity_device_details, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityDeviceDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeviceDetailsBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_device_details_0".equals(view.getTag())) {
            return new ActivityDeviceDetailsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}

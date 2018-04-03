package com.tado.android.utils;

import android.content.res.Resources;
import com.tado.C0676R;
import com.tado.android.installation.srt.view.fragments.SrtRegisterDeviceFragment.DeviceTypeEnum;
import java.util.HashSet;
import java.util.Set;

public class ValidationUtils {
    private static final Set<String> GATEWAY_CODES_SET = new C12631();
    private static final int SERIAL_NUMBER_LENGTH = 12;
    private static final String VALVE_CODE = "VA";

    static class C12631 extends HashSet<String> {
        C12631() {
            add("GW");
            add("IB");
        }
    }

    public static boolean isValidForRegistration(String serialNumber, String authCode, DeviceTypeEnum deviceType) {
        if (serialNumber != null && authCode != null && serialNumber.length() == 12 && authCode.length() == 4 && isCorrectDeviceSerialNumber(serialNumber, deviceType)) {
            return true;
        }
        return false;
    }

    public static String validateSerialNumber(String serialNumber, boolean completed, Resources resources, DeviceTypeEnum deviceType) {
        if (isValveDeviceRegistration(deviceType) && isSerialCodeForGateway(serialNumber)) {
            return String.format(resources.getString(C0676R.string.installation_errors_registeringWrongDeviceType_message), new Object[]{resources.getString(C0676R.string.installation_errors_registeringWrongDeviceType_fromIB), resources.getString(C0676R.string.installation_errors_registeringWrongDeviceType_targetSRT)});
        } else if (!isValveDeviceRegistration(deviceType) && isSerialCodeForValve(serialNumber)) {
            return String.format(resources.getString(C0676R.string.installation_errors_registeringWrongDeviceType_message), new Object[]{resources.getString(C0676R.string.installation_errors_registeringWrongDeviceType_fromSRT), resources.getString(C0676R.string.installation_errors_registeringWrongDeviceType_targetIB)});
        } else if ((serialNumber.length() >= 12 || !completed) && serialNumber.length() <= 12 && (serialNumber.length() < 2 || isCorrectDeviceSerialNumber(serialNumber, deviceType))) {
            return "";
        } else {
            String string = resources.getString(C0676R.string.installation_errors_wrongSerialNo_message);
            Object[] objArr = new Object[1];
            objArr[0] = isValveDeviceRegistration(deviceType) ? resources.getString(C0676R.string.installation_errors_wrongSerialNo_typeSRT) : resources.getString(C0676R.string.installation_errors_wrongSerialNo_typeIB);
            return String.format(string, objArr);
        }
    }

    public static String validateAuthCode(String code, boolean completed, Resources resources) {
        if (code == null || ((code.length() < 4 && completed) || code.length() > 4)) {
            return resources.getString(C0676R.string.installation_errors_wrongRegistrationInfo_message);
        }
        return "";
    }

    private static boolean isCorrectDeviceSerialNumber(String serialNumber, DeviceTypeEnum deviceType) {
        return (isValveDeviceRegistration(deviceType) && isSerialCodeForValve(serialNumber)) || (isGatewayDeviceRegistration(deviceType) && isSerialCodeForGateway(serialNumber));
    }

    private static boolean isValveDeviceRegistration(DeviceTypeEnum deviceType) {
        return DeviceTypeEnum.VALVE == deviceType;
    }

    private static boolean isGatewayDeviceRegistration(DeviceTypeEnum deviceType) {
        return DeviceTypeEnum.GATEWAY == deviceType;
    }

    private static boolean isSerialCodeForGateway(String serialNumber) {
        return serialNumber != null && serialNumber.length() >= 2 && GATEWAY_CODES_SET.contains(serialNumber.substring(0, 2));
    }

    private static boolean isSerialCodeForValve(String serialNumber) {
        return serialNumber != null && serialNumber.length() >= 2 && VALVE_CODE.equalsIgnoreCase(serialNumber.substring(0, 2));
    }
}

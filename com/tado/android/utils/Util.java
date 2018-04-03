package com.tado.android.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import com.crashlytics.android.Crashlytics;
import com.tado.BuildConfig;
import com.tado.android.app.TadoApplication;
import com.tado.android.control_panel.OverlaySettingSeriliazer;
import com.tado.android.controllers.CapabilitiesController;
import com.tado.android.controllers.ZoneController;
import com.tado.android.demo.marketing.MarketingAlertsManager;
import com.tado.android.fcm.FCMInstanceService;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import com.tado.android.installation.InstallationProcessController;
import com.tado.android.mvp.model.HomeWifiRepository;
import com.tado.android.notifications.SettingsUtil;
import com.tado.android.rest.model.Block;
import com.tado.android.rest.model.ScheduleMode;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.settings.model.ScheduleSettings;
import com.tado.android.settings.model.TemperatureSettings;
import com.tado.android.times.view.model.ModeEnum;
import fi.iki.elonen.NanoHTTPD;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Callback;

public class Util {

    static class C12611 implements Comparator<Block> {
        C12611() {
        }

        public int compare(Block lhs, Block rhs) {
            return TimeUtils.convertStringHourAndMinutesToSeconds(lhs.getStart()) > TimeUtils.convertStringHourAndMinutesToSeconds(rhs.getStart()) ? 1 : -1;
        }
    }

    static class C12622 implements Comparator<ScheduleMode> {
        C12622() {
        }

        public int compare(ScheduleMode lhs, ScheduleMode rhs) {
            if (lhs.getType().equalsIgnoreCase("AIR_CONDITIONING")) {
                return Util.sortCoolingScheduleMode(lhs, rhs);
            }
            if (lhs.getType().equalsIgnoreCase("HOT_WATER")) {
                return Util.sortHotWaterScheduleMode(lhs, rhs);
            }
            return Util.sortHeatingScheduleMode(lhs, rhs);
        }
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            Snitcher.start().toCrashlytics().logException("Util.java", e);
            throw new RuntimeException("Could not get package name:" + e);
        }
    }

    public static String getPackageName() {
        return TadoApplication.getTadoAppContext().getPackageName();
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + model;
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        return !Character.isUpperCase(first) ? Character.toUpperCase(first) + s.substring(1) : s;
    }

    public static String getUuID(Context mContext) {
        TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(CreateHomeContactDetailsActivity.INTENT_PHONE);
        return "NONE";
    }

    public static boolean isNetworkAvailable() {
        return SettingsUtil.hasInternetConnection(TadoApplication.getTadoAppContext());
    }

    public static boolean isNetworkAvailable(Network network) {
        return SettingsUtil.hasInternetConnection(TadoApplication.getTadoAppContext(), network);
    }

    public static void deepTimeBlockCopy(List<Block> dest, List<Block> src) {
        dest.clear();
        for (Block block : src) {
            dest.add(Block.copy(block));
        }
    }

    public static void sortBlockList(List<Block> blocks) {
        Collections.sort(blocks, new C12611());
    }

    public static void sortTemperatureList(List<ScheduleMode> temperatures) {
        Collections.sort(temperatures, new C12622());
    }

    private static int sortCoolingScheduleMode(ScheduleMode lhs, ScheduleMode rhs) {
        int i = -1;
        if (lhs.getZoneSetting().getPowerBoolean() && rhs.getZoneSetting().getPowerBoolean()) {
            if (lhs.getZoneSetting().getMode() != null && rhs.getZoneSetting().getMode() != null && ModeEnum.getModeFromString(lhs.getZoneSetting().getMode()).getPosition() > ModeEnum.getModeFromString(rhs.getZoneSetting().getMode()).getPosition()) {
                return 1;
            }
            if (lhs.getZoneSetting().getMode() != null && rhs.getZoneSetting().getMode() != null && ModeEnum.getModeFromString(lhs.getZoneSetting().getMode()).getPosition() < ModeEnum.getModeFromString(rhs.getZoneSetting().getMode()).getPosition()) {
                return -1;
            }
            if (lhs.getZoneSetting().getTemperature() != null && rhs.getZoneSetting().getTemperature() != null && lhs.getZoneSetting().getTemperature().getTemperatureValue() > rhs.getZoneSetting().getTemperature().getTemperatureValue()) {
                return 1;
            }
            if (lhs.getZoneSetting().getTemperature() != null && rhs.getZoneSetting().getTemperature() != null && lhs.getZoneSetting().getTemperature().getTemperatureValue() < rhs.getZoneSetting().getTemperature().getTemperatureValue()) {
                return -1;
            }
            if (lhs.getId().intValue() <= rhs.getId().intValue()) {
                return -1;
            }
            return 1;
        } else if (lhs.getZoneSetting().getPowerBoolean() || rhs.getZoneSetting().getPowerBoolean()) {
            if (!lhs.getZoneSetting().getPowerBoolean()) {
                i = 1;
            }
            return i;
        } else if (lhs.getId().intValue() > rhs.getId().intValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    private static int sortHotWaterScheduleMode(ScheduleMode lhs, ScheduleMode rhs) {
        return sortHeatingScheduleMode(lhs, rhs);
    }

    private static int sortHeatingScheduleMode(ScheduleMode lhs, ScheduleMode rhs) {
        int i = -1;
        if (lhs.getZoneSetting().getPowerBoolean() && rhs.getZoneSetting().getPowerBoolean() && lhs.getZoneSetting().getTemperature() != null && rhs.getZoneSetting().getTemperature() != null && lhs.getZoneSetting().getTemperature().getTemperatureValue() > rhs.getZoneSetting().getTemperature().getTemperatureValue()) {
            return 1;
        }
        if (lhs.getZoneSetting().getPowerBoolean() && rhs.getZoneSetting().getPowerBoolean() && lhs.getZoneSetting().getTemperature() != null && rhs.getZoneSetting().getTemperature() != null && lhs.getZoneSetting().getTemperature().getTemperatureValue() < rhs.getZoneSetting().getTemperature().getTemperatureValue()) {
            return -1;
        }
        if ((lhs.getZoneSetting().getPowerBoolean() ^ rhs.getZoneSetting().getPowerBoolean()) != 0) {
            if (!lhs.getZoneSetting().getPowerBoolean()) {
                i = 1;
            }
            return i;
        } else if (lhs.getId().intValue() <= rhs.getId().intValue()) {
            return -1;
        } else {
            return 1;
        }
    }

    public static boolean verifyGapsAndOverlaps(List<Block> blocks) {
        if (blocks == null || blocks.size() == 0) {
            return false;
        }
        int size = blocks.size();
        for (int i = 0; i < size - 1; i++) {
            Block b = (Block) blocks.get(i);
            if (!b.isGeolocationOverride()) {
                Block nextBlock = (Block) blocks.get(i + 1);
                if (i == 0 && b.getStartSeconds() != 0) {
                    return false;
                }
                if (i == size - 2 && nextBlock.getEndSeconds() != 0) {
                    return false;
                }
                if (b.getEndSeconds() != nextBlock.getStartSeconds()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                } catch (NoSuchFieldException e) {
                    Snitcher.start().toCrashlytics().log(5, "NumberPickerTextColor", e.getLocalizedMessage(), new Object[0]);
                } catch (IllegalAccessException e2) {
                    Snitcher.start().toCrashlytics().log(5, "NumberPickerTextColor", e2);
                } catch (IllegalArgumentException e3) {
                    Snitcher.start().toCrashlytics().log(5, "NumberPickerTextColor", e3);
                }
            }
        }
    }

    public static boolean isCorrectSsid(String ssid) {
        if (TextUtils.isEmpty(ssid)) {
            return false;
        }
        String ssidWithoutQuotes = ssid.replace("\"", "");
        if (TextUtils.isEmpty(ssidWithoutQuotes)) {
            return false;
        }
        return ssidWithoutQuotes.equals(UserConfig.getDeviceSsid().replace("\"", ""));
    }

    public static final boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static final boolean isValidPhone(CharSequence target) {
        String regex = "^([\\+]|(00))[0-9\\.\\-\\(\\)\\ \\/]+$";
        return !TextUtils.isEmpty(target) && Pattern.compile("^([\\+]|(00))[0-9\\.\\-\\(\\)\\ \\/]+$").matcher(target).matches();
    }

    public static void hideKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static WifiInfo getConnectedWifiInfo() {
        WifiManager wifi = (WifiManager) TadoApplication.getTadoAppContext().getApplicationContext().getSystemService("wifi");
        if (wifi != null) {
            return wifi.getConnectionInfo();
        }
        return null;
    }

    public static NetworkInfo getActiveNetwork() {
        return ((ConnectivityManager) TadoApplication.getTadoAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static void clearUserDataOnLogin() {
        FCMInstanceService.unregisterPushNotification();
        UserConfig.clearUserData();
        CapabilitiesController.INSTANCE.clear();
        HomeWifiRepository.INSTANCE.setLocalDataSource(null);
        TemperatureSettings.removeTemperatures();
        RestServiceGenerator.destroyClient();
        ScheduleSettings.clearData();
    }

    public static void clearUserData() {
        clearUserDataOnLogin();
        ZoneController.INSTANCE.clear();
        InstallationProcessController.getInstallationProcessController().clear();
        OverlaySettingSeriliazer.INSTANCE.clear();
        Crashlytics.setUserIdentifier(null);
        Crashlytics.setInt("homeId", 0);
        MarketingAlertsManager.INSTANCE.reset();
    }

    public static void uploadLog(Callback<Void> callback) {
        File file = GeolocationLogger.getLogFile();
        RestServiceGenerator.getTadoRestService().uploadLocationLog(UserConfig.getHomeId(), file.getName(), "multipart/form-data", "uri=\"http://acs.amazonaws.com/groups/global/AuthenticatedUsers\"", RequestBody.create(MediaType.parse(NanoHTTPD.MIME_PLAINTEXT), file)).enqueue(callback);
    }

    public static float roundToStep(float value, float step) {
        return ((float) Math.round(value / step)) * step;
    }

    public static boolean equals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static String getTemperatureValueWithUnit(int temperature, boolean isCelsius) {
        Locale locale = Locale.US;
        String str = "%d°%s";
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(temperature);
        objArr[1] = isCelsius ? "C" : "F";
        return String.format(locale, str, objArr);
    }

    public static <T> T either(@Nullable T realValue, @NonNull T defaultValue) {
        return realValue != null ? realValue : defaultValue;
    }

    public String getStackTrace(StackTraceElement[] stackTraceElements) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stackTraceElements) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    public static String getOperatingSystemVersion() {
        try {
            return String.format(Locale.US, "App Version: %s (%d) / Android %s (API %d) / device:  %s %s", new Object[]{BuildConfig.VERSION_NAME, Integer.valueOf(BuildConfig.VERSION_CODE), VERSION.RELEASE, Integer.valueOf(VERSION.SDK_INT), Build.MANUFACTURER, Build.MODEL});
        } catch (Exception e) {
            return "";
        }
    }

    public static CharSequence getText(Context context, @StringRes int id, Object... args) {
        int i = 0;
        while (i < args.length) {
            args[i] = args[i] instanceof String ? TextUtils.htmlEncode((String) args[i]) : args[i];
            i++;
        }
        CharSequence text = Html.fromHtml(String.format(Html.toHtml(new SpannedString(context.getText(id))), args));
        i = text.length();
        while (true) {
            i--;
            if (i < 0 || text.charAt(i) != '\n') {
                return text;
            }
            text = text.subSequence(0, text.length() - 1);
        }
        return text;
    }

    public static boolean isDeviceIdle(Context context) {
        if (VERSION.SDK_INT >= 23) {
            return ((PowerManager) context.getSystemService("power")).isDeviceIdleMode();
        }
        return false;
    }

    public static boolean hasBatteryOptimization(Context context) {
        boolean isIgnoringBatteryOptimizations = true;
        if (VERSION.SDK_INT >= 23) {
            isIgnoringBatteryOptimizations = ((PowerManager) context.getSystemService("power")).isIgnoringBatteryOptimizations(BuildConfig.APPLICATION_ID);
        }
        return !isIgnoringBatteryOptimizations;
    }

    public static boolean isInPowerSavingMode(Context context) {
        if (VERSION.SDK_INT >= 21) {
            return ((PowerManager) context.getSystemService("power")).isPowerSaveMode();
        }
        return false;
    }

    @NotNull
    public static String getSupportedLanguage() {
        Locale locale;
        List<String> supportedLanguages = Arrays.asList(new String[]{"en", "de", "it", "es", "fr", "nl", "cs"});
        if (VERSION.SDK_INT >= 24) {
            locale = LocaleList.getDefault().getFirstMatch((String[]) supportedLanguages.toArray(new String[supportedLanguages.size()]));
        } else {
            locale = Locale.getDefault();
        }
        return getSupportedLanguage(locale, supportedLanguages);
    }

    @NotNull
    private static String getSupportedLanguage(Locale locale, List<String> supportedLanguages) {
        if (supportedLanguages.contains(locale.getLanguage())) {
            return locale.getLanguage();
        }
        return (String) supportedLanguages.get(0);
    }
}

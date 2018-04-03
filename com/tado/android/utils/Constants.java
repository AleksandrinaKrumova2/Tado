package com.tado.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import com.tado.C0676R;
import com.tado.android.MainZoneActivity;
import com.tado.android.PromoActivity;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.RateAppUtil;
import com.tado.android.controllers.ZoneController;
import com.tado.android.debug.DebugConfigPreferenceActivity;
import com.tado.android.entities.FeedbackItem;
import com.tado.android.installation.ChooseProductActivity;
import com.tado.android.installation.CongratulationsActivity;
import com.tado.android.installation.CreateHomeWelcomeActivity;
import com.tado.android.installation.InstallHeatingOverviewActivity;
import com.tado.android.installation.acsetup.CommandSetUpdateActivity;
import com.tado.android.installation.acsetup.PrePointToAcActivity;
import com.tado.android.installation.acsetup.RemoteNotSupportedActivity;
import com.tado.android.installation.acsetup.SelectControlTypeActivity;
import com.tado.android.installation.acsetup.SelectManufacturerActivity;
import com.tado.android.installation.acsetup.TestConfirmedCommandSetsOverviewActivity;
import com.tado.android.installation.clc.ThermostaticOperationIntroActivity;
import com.tado.android.installation.complexteaching.TeachSmartACActivity;
import com.tado.android.installation.complexteaching.TeachingModesOverviewActivity;
import com.tado.android.installation.connectwifi.PlugInDeviceActivity;
import com.tado.android.installation.connectwifi.UpdateFirmwareActivity;
import com.tado.android.installation.positioning.DevicePositionActivity;
import com.tado.android.installation.registerwr.RegisterDeviceActivity;
import com.tado.android.login.LoginActivity;
import com.tado.android.menu.ActionItem;
import com.tado.android.menu.ActionItem.Action;
import com.tado.android.menu.PremiumDrawerItem;
import com.tado.android.premium.PremiumCarouselActivity;
import com.tado.android.repairServices.RepairServicesActivity;
import com.tado.android.rest.model.installation.AcInstallation.StateEnum;
import com.tado.android.settings.BetaAccessActivity;
import com.tado.android.settings.EnergySavingsActivity;
import com.tado.android.settings.mainsettings.SettingsActivity2;
import com.tado.android.times.view.model.CoolingFanSpeedEnum;
import com.tado.android.times.view.model.CoolingSwingStateEnum;
import com.tado.android.times.view.model.ModeEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Constants {
    public static final HashMap<String, Class<? extends Activity>> ACTIVITY_FOR_INSTALLATION_STATUS = new C12361();
    public static final HashMap<String, Class<? extends Activity>> ACTIVITY_FOR_PROCESS_STATUS = new C12372();
    public static final HashMap<StateEnum, Class<? extends Activity>> ACTIVITY_FOR_PROCESS_STATUSENUM = new C12383();
    public static final String AC_SETUP = "SETUP_AC";
    public static final String AC_SETUP_MANUFACTURER_SELECTION = "SETUP_AC_SELECT_MANUFACTURER";
    public static final String AC_SETUP_MULTI_COMMAND_REDUCTION = "SETUP_AC_MULTI_COMMAND_REDUCTION";
    public static final String AC_SETUP_ON_OFF_REDUCTION = "SETUP_AC_ON_OFF_REDUCTION";
    public static final String AC_SETUP_UPLOAD_COMMAND_TABLE = "UPLOAD_COMMAND_TABLE";
    public static final String AC_SPECS = "AC_SPECS";
    public static final String ANALYTICS_OPT_OUT_PREFERENCE = "analytics_opt_out";
    public static final String AWAY = "AWAY";
    public static final int AWAY_MAX_TEMP = 25;
    public static final int AWAY_MIN_TEMP = 5;
    public static final String CELSIUS = "celsius";
    public static final long CHECK_FOR_REMOTE_CONTROL_SIGNAL = 3000;
    public static final long CHECK_FOR_SERVER_CONNECTION_POLLING_TIMEOUT = 20000;
    public static final long CHECK_FOR_SERVER_CONNECTION_TIMEOUT = 5000;
    public static final String COMPLETED = "COMPLETED";
    public static final String CONNECT_WIFI = "CONNECT_WIFI";
    public static final int COOLING_INSTALLATION_PROCESS_REVISION = 6;
    public static final int COOLING_MAX_TEMP = 31;
    public static final int COOLING_MAX_TEMP_FAHRENHEIT = 88;
    public static final int COOLING_MIN_TEMP = 15;
    public static final int COOLING_MIN_TEMP_FAHRENHEIT = 59;
    public static final CoolingFanSpeedEnum DEFAULT_AC_FAN = CoolingFanSpeedEnum.LOW;
    public static final ModeEnum DEFAULT_AC_MODE = ModeEnum.COOL;
    public static final CoolingSwingStateEnum DEFAULT_AC_SWING = CoolingSwingStateEnum.OFF;
    public static final int DEFAULT_AC_TEMPERATURE_IN_CELSIUS = 20;
    public static final int DEFAULT_AC_TEMPERATURE_IN_FAHRENHEIT = 68;
    public static final int DEFAULT_AWAY_HEATING_TEMPERATURE_FAHRENHEIT = 61;
    public static final int DEFAULT_AWAY_HEATING_TEMPERATURE_IN_CELSIUS = 16;
    public static final int DEFAULT_HEATING_TEMPERATURE_FAHRENHEIT = 70;
    public static final int DEFAULT_HEATING_TEMPERATURE_IN_CELSIUS = 21;
    public static final int DEFAULT_HOT_WATER_TEMPERATURE_FAHRENHEIT = 130;
    public static final int DEFAULT_HOT_WATER_TEMPERATURE_IN_CELSIUS = 55;
    public static final ModeEnum[] DEFAULT_MODE_ORDER = new ModeEnum[]{ModeEnum.COOL, ModeEnum.FAN, ModeEnum.DRY, ModeEnum.AUTO, ModeEnum.HEAT};
    public static final com.tado.android.rest.model.installation.ModeEnum[] DEFAULT_MODE_ORDER_V2 = new com.tado.android.rest.model.installation.ModeEnum[]{com.tado.android.rest.model.installation.ModeEnum.COOL, com.tado.android.rest.model.installation.ModeEnum.FAN, com.tado.android.rest.model.installation.ModeEnum.DRY, com.tado.android.rest.model.installation.ModeEnum.AUTO, com.tado.android.rest.model.installation.ModeEnum.HEAT};
    public static final String DEFAULT_OVERLAY_TERMINATION_CONDITION = "MANUAL";
    public static final int DEFAULT_OVERLAY_TIMER_MINUTES = 0;
    public static final long DEVICE_NOT_CONNECTING_TIMEOUT = 30000;
    public static final List<ActionItem> DRAWER_ACTION_ITEMS = new C12484();
    public static final String FAHRENHEIT = "fahrenheit";
    public static final long FIRMWARE_POLLING_TIMEOUT = 3000;
    public static final String GOOGLE_MAPS_REFERER = "android.tado.com";
    public static final int HEATING_INSTALLATION_PROCESS_REVISION = 1;
    public static final String HOME = "HOME";
    public static final String ILLEGAL_INSTALLATION_PROCESS_STATE = "illegalInstallationProcessState";
    public static final String ILLEGAL_STATE = "illegalState";
    public static final String INSTALLATION_STATUS_HEATING_OVERVIEW = "HEATING_OVERVIEW";
    public static final String INSTALLATION_STATUS_HOME_NOT_FOUND = "homeNotFound";
    public static final String INSTALLATION_STATUS_MAIN_SCREEN = "MAIN_SCREEN";
    public static final String INSTALLATION_STATUS_NO_USER = "nouser";
    public static final String INSTALLATION_STATUS_START = "start";
    public static final String INSTALLATION_TYPE_INSTALL = "INSTALL";
    public static final float INVALID_FLOAT_VALUE = -999.0f;
    public static final String KEY_EXTRA_CHALLENGE = "challenge";
    public static final String KEY_EXTRA_PASSWORD = "password";
    public static final String KEY_EXTRA_USERNAME = "username";
    public static final String KEY_LEGACY_ACCURACY_HIGH = "SERVICE_ACCURACY_FINE";
    public static final String KEY_SERVICE_ACCURACY_LOW = "SERVICE_ACCURACY_LOW";
    public static final int MAX_HOME_NAME_LENGTH = 40;
    public static final int MAX_HOME_TEMP = Integer.MAX_VALUE;
    public static final float MAX_OFFSET_CELSIUS = 10.0f;
    public static final float MAX_OFFSET_FAHRENHEIT = 18.0f;
    public static final int MAX_TEMP = 25;
    public static final int MAX_ZONE_NAME_LENGTH = 40;
    public static final float MIN_OFFSET_CELSIUS = -9.9f;
    public static final float MIN_OFFSET_FAHRENHEIT = -17.9f;
    public static final int MIN_TEMP = 10;
    public static final String NOT_COMPATIBLE = "NOT_COMPATIBLE";
    public static final long NO_INTERNET_CONNECTION_TIMEOUT = 20000;
    public static final String POSITION_DEVICE = "POSITION_DEVICE";
    public static final String PRODUCT_TYPE_AC_G1 = "AC_G1";
    public static final String PUSH_NOTIFICATION_ACTION_COMPLETE = "pushNotificationActionComplete";
    public static final int RATE_APP_DIALOG_MIN_THRESHOLD = 10;
    public static final String REGISTER_WR = "REGISTER_WR";
    public static final String REVISION_NOT_SUPPORTED = "revision.notSupported";
    public static final String SERVER_API_MOBILE = "mobile/1.7/";
    public static final String SERVER_API_SCHEDULE = "api/v1/";
    public static final String SERVER_ERROR_ALREADY_REGISTERED = "hwDevice.alreadyInstalled";
    public static final String SERVER_ERROR_AUTH_CODE_INVALID = "hwDevice.authKey.invalid";
    public static final String SERVER_ERROR_HW_DEVICE_NOT_REACHABLE_EXCEPTION = "hwDeviceNotReachable";
    public static final String SERVER_ERROR_IR_COMMAND_SEND_EXCEPTION = "irCommandSendException";
    public static final String SERVER_ERROR_MAIL_NOT_UNIQUE = "email.not.unique";
    public static final String SERVER_ERROR_MAIL_NOT_VALID = "email.invalid";
    public static final String SERVER_ERROR_MANUFACTURER_NOT_FOUND = "manufacturerNotFound";
    public static final String SERVER_ERROR_NOT_FOUND = "notFound";
    public static final String SERVER_ERROR_UNAUTHORIZED = "unauthorized";
    public static final String SERVER_ERROR_WRONG_DEVICE_TYPE = "wrongDeviceType";
    public static final String SERVER_HVAC_API = "https://hvactool.tado.com/";
    public static final String SERVER_HVAC_API_CI = "https://ci-hvactool.tado.com/";
    public static final String SETUP_AC_CLC_RECORDING = "SETUP_AC_CLC_RECORDING";
    public static final String SETUP_AC_CREATE_SETTING_RECORDING = "SETUP_AC_CREATE_AC_SETTING_RECORDING";
    public static final String SETUP_AC_RECORD_AC_SETTING_COMMANDS = "SETUP_AC_RECORD_AC_SETTING_COMMANDS";
    public static final String SETUP_AC_SELECT_COMMAND_SET = "SETUP_AC_SELECT_COMMAND_SET";
    public static final String SLEEP = "SLEEP";
    public static final int TIMES_BLOCK_MIN_LENGTH_IN_MINUTES = 15;
    public static final int TIMES_BLOCK_MIN_LENGTH_IN_SECONDS = 900;
    public static final int TIMES_BLOCK_STEP_IN_MINUTES = 5;
    public static final int TIMES_BLOCK_STEP_IN_SECONDS = 300;
    public static final String TRANSITIONAL_APP_USERNAME = "appUserUsername";
    public static final String UNDEFINED = "UNDEFINED";
    public static final String UPDATE_FW = "UPDATE_FW";
    public static final String UPDATE_FW_SUCCESSFUL = "UPDATE_FW_SUCCESSFUL";
    public static final String URL_AC_LEARNING = "acLearning";
    public static final String URL_AC_LEARNING_COMMAND_SET_RECORDING = "acLearning/acSettingCommandSetRecording";
    public static final String URL_DEVICE = "http://192.168.1.1/";
    public static final String URL_DEVICES = "devices";
    public static final String URL_DEVICE_BEEP = "/beep";
    public static final String URL_DEVICE_POST_CREDENTIALS = "profiles_add.html";
    public static final String URL_DEVICE_WIFI_LIST = "list.json";
    public static final String URL_GETHVACSTATE = "/hvacState";
    public static final String URL_GOOGLE_GEOCODING = "https://maps.googleapis.com/maps/api/geocode/json";
    public static final String URL_HOME = "home/";
    public static final String URL_POST_INVITATION_ROOMMATE = "/invitation";
    public static final String URL_REGISTERDEVICETOKEN = "registerDeviceToken";
    public static final String URL_UPDATETODAYHEATINGCOSTSPARAMS = "updateTodaysHeatingCostParams";
    public static final String URL_USERS = "users";
    public static final String USER = "USER";
    public static final long WAIT_FOR_COMMANDSET_TEST = 1500;
    public static final long WAIT_FOR_COMMANDSET_TEST_AFTER_BEEP = 2000;
    public static final long WAIT_FOR_COMMANDSET_UPDATE_TIMEOUT = 180000;
    public static final long WAIT_FOR_FIRMWARE_UPDATE_TIMEOUT = 300000;
    public static final long WIFI_CONNECTION_RETRY_TIMEOUT = 10000;
    public static final long WIFI_CONNECTION_TIMEOUT = 30000;
    public static final int WIFI_RECONNECT_TRIES = 5;
    public static final long WIFI_RECONNECT_WAIT = 2000;
    public static final String WS_KEY_ACCURACY = "accuracy";
    public static final String WS_KEY_BATTERY_LEVEL = "batteryLevel";
    public static final String WS_KEY_DEBUG_STING = "debugString";
    public static final String WS_KEY_LAT = "latitude";
    public static final String WS_KEY_LON = "longitude";
    public static final String WS_KEY_MODE = "mode";

    static class C12361 extends HashMap<String, Class<? extends Activity>> {
        C12361() {
            put(Constants.INSTALLATION_STATUS_MAIN_SCREEN, MainZoneActivity.class);
            put(Constants.INSTALLATION_STATUS_HEATING_OVERVIEW, InstallHeatingOverviewActivity.class);
            put(Constants.INSTALLATION_STATUS_NO_USER, PromoActivity.class);
            put(Constants.INSTALLATION_STATUS_HOME_NOT_FOUND, CreateHomeWelcomeActivity.class);
            put(Constants.INSTALLATION_STATUS_START, LoginActivity.class);
        }
    }

    static class C12372 extends HashMap<String, Class<? extends Activity>> {
        C12372() {
            put(Constants.INSTALLATION_STATUS_START, ChooseProductActivity.class);
            put(Constants.REGISTER_WR, RegisterDeviceActivity.class);
            put(Constants.CONNECT_WIFI, PlugInDeviceActivity.class);
            put(Constants.UPDATE_FW, UpdateFirmwareActivity.class);
            put("AC_SPECS", SelectControlTypeActivity.class);
            put(Constants.NOT_COMPATIBLE, RemoteNotSupportedActivity.class);
            put(Constants.AC_SETUP_MANUFACTURER_SELECTION, SelectManufacturerActivity.class);
            put(Constants.SETUP_AC_CLC_RECORDING, ThermostaticOperationIntroActivity.class);
            put(Constants.AC_SETUP_ON_OFF_REDUCTION, PrePointToAcActivity.class);
            put(Constants.AC_SETUP_MULTI_COMMAND_REDUCTION, PrePointToAcActivity.class);
            put(Constants.AC_SETUP_UPLOAD_COMMAND_TABLE, CommandSetUpdateActivity.class);
            put(Constants.POSITION_DEVICE, DevicePositionActivity.class);
            put(Constants.SETUP_AC_CREATE_SETTING_RECORDING, TeachSmartACActivity.class);
            put(Constants.SETUP_AC_RECORD_AC_SETTING_COMMANDS, TeachingModesOverviewActivity.class);
            put(Constants.SETUP_AC_SELECT_COMMAND_SET, TestConfirmedCommandSetsOverviewActivity.class);
            put(Constants.COMPLETED, CongratulationsActivity.class);
        }
    }

    static class C12383 extends HashMap<StateEnum, Class<? extends Activity>> {
        C12383() {
            put(StateEnum.REGISTER_WR, RegisterDeviceActivity.class);
            put(StateEnum.CONNECT_WIFI, PlugInDeviceActivity.class);
            put(StateEnum.UPDATE_FW, UpdateFirmwareActivity.class);
            put(StateEnum.AC_SPECS, SelectControlTypeActivity.class);
            put(StateEnum.NOT_COMPATIBLE, RemoteNotSupportedActivity.class);
            put(StateEnum.SETUP_AC_SELECT_MANUFACTURER, SelectManufacturerActivity.class);
            put(StateEnum.SETUP_AC_CLC_RECORDING, ThermostaticOperationIntroActivity.class);
            put(StateEnum.SETUP_AC_ON_OFF_REDUCTION, PrePointToAcActivity.class);
            put(StateEnum.SETUP_AC_CREATE_AC_SETTING_RECORDING, TeachSmartACActivity.class);
            put(StateEnum.SETUP_AC_RECORD_AC_SETTING_COMMANDS, TeachingModesOverviewActivity.class);
            put(StateEnum.SETUP_AC_SELECT_COMMAND_SET, TestConfirmedCommandSetsOverviewActivity.class);
            put(StateEnum.POSITION_DEVICE, DevicePositionActivity.class);
            put(StateEnum.UPLOAD_COMMAND_TABLE, CommandSetUpdateActivity.class);
            put(StateEnum.COMPLETED, CongratulationsActivity.class);
        }
    }

    static class C12484 extends ArrayList<ActionItem> {

        class C12391 implements Action {
            C12391() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent(context, PremiumCarouselActivity.class));
            }
        }

        class C12402 implements Action {
            C12402() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent(context, DebugConfigPreferenceActivity.class));
            }
        }

        class C12413 implements Action {
            C12413() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent(context, ChooseProductActivity.class));
            }
        }

        class C12424 implements Action {
            C12424() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent(context, RepairServicesActivity.class));
            }
        }

        class C12435 implements Action {
            C12435() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent(context, EnergySavingsActivity.class));
            }
        }

        class C12446 implements Action {
            C12446() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent(context, SettingsActivity2.class));
            }
        }

        class C12457 implements Action {
            C12457() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent(context, BetaAccessActivity.class));
            }
        }

        class C12468 implements Action {
            C12468() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(context.getString(C0676R.string.menu_supportLink))));
            }
        }

        class C12479 implements Action {
            C12479() {
            }

            public void performAction(Context context) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(context.getString(C0676R.string.menu_referralLink))));
                AnalyticsHelper.trackEvent(context instanceof Activity ? (Activity) context : null, Screen.MENU, "ReferralProgramOpened", Locale.getDefault().getLanguage());
            }
        }

        C12484() {
            add(new PremiumDrawerItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_upgradeToPremiumButton), new C12391()));
            add(new ActionItem("Debug Settings", new C12402()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_addDeviceButton), new C12413()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_repairServicesButton), new C12424()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_energySavingsReportButton), new C12435()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_settingsButton), new C12446(), ZoneController.INSTANCE.getBatteryState()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_appsBetaButton), new C12457()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_feedbackButton), new FeedbackItem()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_supportButton), new C12468()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_referralButton), new C12479()));
            add(new ActionItem(TadoApplication.getTadoAppContext().getResources().getString(C0676R.string.menu_enjoyingTadoButton), new Action() {
                public void performAction(Context context) {
                    if (context instanceof MainZoneActivity) {
                        ((MainZoneActivity) context).showRateAppDialog();
                    } else if (context instanceof AppCompatActivity) {
                        new RateAppUtil().showRateAppDialog(((AppCompatActivity) context).getSupportFragmentManager());
                    } else if (context instanceof FragmentActivity) {
                        new RateAppUtil().showRateAppDialog(((FragmentActivity) context).getSupportFragmentManager());
                    } else {
                        Snitcher.start().toCrashlytics().log(6, "RateApp", "Could not find proper class to show the Rate the app dialog", new Object[0]);
                    }
                }
            }));
        }
    }
}

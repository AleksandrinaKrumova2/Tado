package com.tado.android.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.databinding.ObservableMap;
import android.location.Location;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.tado.android.app.TadoApplication;
import com.tado.android.location.SimpleLocation;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.rest.model.GeolocationConfig;
import com.tado.android.rest.model.HomeInfo.LicenseEnum;
import com.tado.android.rest.model.HomeInfo.PartnerEnum;
import com.tado.android.rest.model.HomeLocation;
import com.tado.android.rest.model.installation.TemperatureUnitEnum;
import com.tado.android.security.TadoSecurity;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.joda.time.DateTimeZone;

public class UserConfig {
    private static final String AC_MANUFACTURER = "acManufacturer";
    private static final String AC_MANUFACTURER_NAME = "acManufacturerName";
    private static final String APP_VERSION = "appVersion";
    private static final String BOOT_FILTER = "bootFilter";
    private static final String CHRISTMAS_MODE_ENABLED = "setChristmasModeEnabled";
    private static final String CURRENT_ZONE = "currentZone";
    private static final String CURRENT_ZONE_NAME = "currentZoneName";
    private static final String DEVICE_SSID = "deviceSsid";
    private static final String ENCRYPTION_DEACTIVATED = "ENCRYPTION_DEACTIVATED_";
    private static final String FCM_TOKEN = "FCM_TOKEN";
    private static final String GEO_TRACKING_ENABLED = "geoTrackingEnabled";
    private static final String GEO_TRACKING_START_TIME = "geoTrackingStartTime";
    private static final String HOME_ID = "homeId";
    private static final String HOME_LOCATION = "HOME_LOCATION";
    private static final String HOME_NAME = "homeName";
    private static final String HOME_TIMEZONE = "homeTimezone";
    private static final String HOME_WIFI_DETECTION_ENABLED = "homeWifiDetectionEnabled";
    private static final String HOT_WATER_PRODUCTION_CONTROL = "hotWaterProductionControl";
    private static final String HOT_WATER_PRODUCTION_CONTROL_INIT = "hotWaterProductionControlInit";
    private static final String HOT_WATER_PRODUCTION_TEMP_CONTROL = "hotWaterProductionTempControl";
    private static final String IN_HOME_WIFI = "inHomeWifi";
    private static final String LAST_ONBOARDING_VERSION_SHOWN = "lastOnboardingVersionShown";
    private static final String LAST_POSTED_LOCATION = "lastPostedLocation";
    private static final String LAST_SCHEDULED_LOCATION = "LAST_SCHEDULED_LOCATION";
    private static final String LAST_SEEN_LOCATION = "lastSeenLocation";
    private static final String LAST_TIME_AIRPLANE_MODE_TURNED_OFF = "LAST_TIME_AIRPLANE_MODE_TURNED_OFF";
    private static final String LAST_TIME_BOOTED = "LAST_TIME_BOOTED";
    private static final String LOCATION_CONFIGURATION = "LOCATION_CONFIGURATION";
    private static final String LOCATION_SERVICES_MODE = "locationServicesMode";
    private static final String MOBILE_DEVICE_ID = "mobileDeviceId";
    private static final String NICKNAME = "nickname";
    private static final String PARTNER = "partner";
    private static final String PASSWORD = "password";
    private static final String PERMISSION_INTRO_SHOWN = "permissionIntroShown";
    private static final String PREFERENCES = "MyPreferences";
    private static final String RATE_APP_COUNTER = "rateAppCounter";
    private static final String RATE_APP_SHOWN = "rateAppShown";
    private static final String RATE_APP_VOTED = "rateAppVoted";
    private static final String RATE_APP_VOTED_VERSION = "rateAppVotedVersion";
    private static final String SERIAL_NO = "serialNo";
    private static final String SERVER_ADDRESS = "server_address";
    private static final String SIMULATOR_SUGGESTED_NAME = "simulatorSuggestedName";
    private static final String TADO_SYSTEM_TYPE = "tadoSystemType";
    private static final String TEMPERATURE_UNIT = "acTemperatureUnit";
    private static final String USERNAME = "username";
    private static final String WIFI_HASH_ID = "wifiHashId";
    private static String acManufacturer;
    private static String acManufacturerName;
    private static Boolean bootFilter;
    private static Boolean christmasModeEnabled;
    private static Integer currentZone;
    private static String currentZoneName;
    private static String deviceSsid;
    private static Editor editor;
    private static String fcmToken;
    private static Boolean geoTrackingEnabled;
    private static Long homeCreation;
    private static Integer homeId;
    private static HomeLocation homeLocation;
    private static String homeName;
    private static String homeTimezone;
    private static ObservableMap<String, String> homeWifis;
    private static Boolean hotWaterProductionControl;
    private static Boolean hotWaterProductionControlInit;
    private static Boolean hotWaterProductionTempControl;
    private static Map<String, String> ignoredWifis;
    private static Boolean inHomeWifi;
    private static Long lastBootTime;
    private static Integer lastOnboardingVersionShown;
    private static Location lastPostedLocation;
    private static Location lastScheduledLocation;
    private static Location lastSeenLocation;
    private static Long lastTimeAirplaneModeTurnedOff;
    private static LicenseEnum license;
    private static GeolocationConfig locationConfiguration;
    private static String locationServicesMode;
    private static Integer mobileDeviceId;
    private static String nickname;
    private static PartnerEnum partner;
    private static String password;
    private static Boolean permissionIntroShown;
    private static SharedPreferences preferences;
    private static Integer rateAppCounter;
    private static Boolean rateAppShown;
    private static Boolean rateAppVoted;
    private static String rateAppVotedVersion;
    private static Long rateDate;
    private static String serialNo;
    private static String serverAddress;
    private static String tadoSystemType;
    private static TemperatureUnitEnum temperatureUnit;
    private static String username;
    private static String wifiHashId;

    private static String getDecryptedString(String key, String defaultValue) {
        String value = getPreferences().getString(key, defaultValue);
        if (VERSION.SDK_INT >= 18 && !isEncryptionDeactivated(key)) {
            try {
                value = TadoSecurity.INSTANCE.decryptData(value);
                if (VERSION.SDK_INT < 23) {
                    setEncryptedString(key, value);
                }
            } catch (IllegalArgumentException e) {
                Snitcher.start().toCrashlytics().logException(e);
                setEncryptedString(key, value);
            }
        }
        return value;
    }

    private static void setEncryptedString(String key, String value) {
        boolean encryptionDeactivated = isEncryptionDeactivated(key);
        if (VERSION.SDK_INT >= 23) {
            try {
                value = TadoSecurity.INSTANCE.encryptData(value);
                if (encryptionDeactivated) {
                    setEncryptionDeactivation(key, false);
                }
            } catch (IllegalArgumentException e) {
                Snitcher.start().toCrashlytics().logException(e);
            }
        } else if (!encryptionDeactivated) {
            setEncryptionDeactivation(key, true);
        }
        putString(key, value);
    }

    private static boolean isEncryptionDeactivated(String key) {
        return getBoolean(ENCRYPTION_DEACTIVATED + key, false);
    }

    public static void setEncryptionDeactivation(String key, boolean deactivated) {
        putBoolean(ENCRYPTION_DEACTIVATED + key, deactivated);
    }

    public static float getFloat(String name, float defaultValue) {
        return getPreferences().getFloat(name, defaultValue);
    }

    public static int getInt(String name, int defaultValue) {
        return getPreferences().getInt(name, defaultValue);
    }

    static void putString(String name, String value) {
        getEditor().putString(name, value);
        getEditor().apply();
    }

    public static void putBoolean(String name, boolean value) {
        getEditor().putBoolean(name, value);
        getEditor().apply();
    }

    public static boolean getBoolean(String name, boolean defaultValue) {
        return getPreferences().getBoolean(name, defaultValue);
    }

    public static void putInteger(String name, int value) {
        getEditor().putInt(name, value);
        getEditor().apply();
    }

    public static void putLong(String name, long value) {
        getEditor().putLong(name, value);
        getEditor().apply();
    }

    private static void putFloat(String name, float value) {
        getEditor().putFloat(name, value);
        getEditor().apply();
    }

    public static boolean putFloatArray(String[] names, Float[] values) {
        for (int i = 0; i < names.length; i++) {
            getEditor().putFloat(names[i], values[i].floatValue());
        }
        return getEditor().commit();
    }

    public static Long getLong(String name, long value) {
        return Long.valueOf(getPreferences().getLong(name, value));
    }

    private static SharedPreferences getPreferences() {
        if (preferences == null) {
            preferences = TadoApplication.getTadoAppContext().getSharedPreferences(PREFERENCES, 0);
        }
        return preferences;
    }

    private static Editor getEditor() {
        if (editor == null) {
            editor = getPreferences().edit();
        }
        return editor;
    }

    public static String getServerAddress() {
        if (serverAddress == null) {
            serverAddress = getPreferences().getString(SERVER_ADDRESS, Server.getProduction());
        }
        return serverAddress;
    }

    public static void setServerAddress(String serverAddress) {
        serverAddress = serverAddress;
        putString(SERVER_ADDRESS, serverAddress);
    }

    public static String getUsername() {
        if (username == null) {
            username = getDecryptedString("username", "");
        }
        return username;
    }

    public static void setUsername(String username) {
        setEncryptedString("username", username);
        username = null;
    }

    public static String getPassword() {
        if (password == null) {
            password = getDecryptedString("password", "");
        }
        return password;
    }

    public static void setPassword(String password) {
        setEncryptedString("password", password);
        password = null;
    }

    public static String getNickname() {
        if (nickname == null) {
            nickname = getPreferences().getString(NICKNAME, null);
        }
        return nickname;
    }

    public static void setNickname(String nickname) {
        nickname = nickname;
        putString(NICKNAME, nickname);
    }

    public static boolean isHomeWifiDetectionEnabled() {
        return getPreferences().getBoolean(HOME_WIFI_DETECTION_ENABLED, true);
    }

    public static void setHomeWifiDetectionEnabled(boolean homeWifiDetectionEnabled) {
        putBoolean(HOME_WIFI_DETECTION_ENABLED, homeWifiDetectionEnabled);
    }

    public static boolean isInHomeWifi() {
        if (inHomeWifi == null) {
            inHomeWifi = Boolean.valueOf(getPreferences().getBoolean(IN_HOME_WIFI, false));
        }
        return inHomeWifi.booleanValue();
    }

    public static void setInHomeWifi(boolean inHomeWifi) {
        inHomeWifi = Boolean.valueOf(inHomeWifi);
        putBoolean(IN_HOME_WIFI, inHomeWifi);
    }

    public static String getWifiHashId() {
        if (wifiHashId == null) {
            wifiHashId = getPreferences().getString(WIFI_HASH_ID, null);
            if (wifiHashId == null) {
                wifiHashId = UUID.randomUUID().toString();
                putString(WIFI_HASH_ID, wifiHashId);
            }
        }
        return wifiHashId;
    }

    public static String getSSIDWithoutQuotes(String ssid) {
        if (ssid != null && ssid.startsWith("\"") && ssid.endsWith("\"")) {
            return ssid.substring(1, ssid.length() - 1);
        }
        return ssid;
    }

    public static synchronized void setLastSeenLocation(Location location) {
        synchronized (UserConfig.class) {
            lastSeenLocation = location;
            putString(LAST_SEEN_LOCATION, new Gson().toJson(new SimpleLocation(location)));
        }
    }

    @Nullable
    public static synchronized Location getLastSeenLocation() {
        Location location;
        synchronized (UserConfig.class) {
            if (lastSeenLocation == null) {
                lastSeenLocation = getLocation(LAST_SEEN_LOCATION);
            }
            location = lastSeenLocation;
        }
        return location;
    }

    public static synchronized void setLastPostedLocation(Location location) {
        synchronized (UserConfig.class) {
            if (location != null) {
                lastPostedLocation = location;
                putString(LAST_POSTED_LOCATION, new Gson().toJson(new SimpleLocation(location)));
            }
        }
    }

    @Nullable
    public static synchronized Location getLastPostedLocation() {
        Location location;
        synchronized (UserConfig.class) {
            if (lastPostedLocation == null) {
                lastPostedLocation = getLocation(LAST_POSTED_LOCATION);
            }
            location = lastPostedLocation;
        }
        return location;
    }

    public static synchronized void setLastScheduledLocation(Location location) {
        synchronized (UserConfig.class) {
            lastScheduledLocation = location;
            if (location != null) {
                putString(LAST_SCHEDULED_LOCATION, new Gson().toJson(new SimpleLocation(location)));
            } else {
                putString(LAST_SCHEDULED_LOCATION, null);
            }
        }
    }

    @Nullable
    public static synchronized Location getLastScheduledLocation() {
        Location location;
        synchronized (UserConfig.class) {
            if (lastScheduledLocation == null) {
                lastScheduledLocation = getLocation(LAST_SCHEDULED_LOCATION);
            }
            location = lastScheduledLocation;
        }
        return location;
    }

    @Nullable
    private static Location getLocation(@NonNull String key) {
        String locationJson = getPreferences().getString(key, null);
        if (locationJson != null) {
            return ((SimpleLocation) new Gson().fromJson(locationJson, SimpleLocation.class)).toLocation();
        }
        return null;
    }

    public static void setHomeLocation(HomeLocation homeLocation) {
        homeLocation = homeLocation;
        putString(HOME_LOCATION, new Gson().toJson((Object) homeLocation));
    }

    @Nullable
    public static HomeLocation getHomeLocation() {
        if (homeLocation == null) {
            homeLocation = (HomeLocation) new Gson().fromJson(getPreferences().getString(HOME_LOCATION, null), HomeLocation.class);
        }
        return homeLocation;
    }

    public static boolean isBootFilter() {
        if (bootFilter == null) {
            bootFilter = Boolean.valueOf(getPreferences().getBoolean(BOOT_FILTER, true));
        }
        return bootFilter.booleanValue();
    }

    public static void setBootFilter(boolean bootFilter) {
        bootFilter = Boolean.valueOf(bootFilter);
        putBoolean(BOOT_FILTER, bootFilter);
    }

    public static float getHomeFence() {
        if (getHomeLocation() != null) {
            return getHomeLocation().region;
        }
        return 0.0f;
    }

    public static void setHomeFence(float radius) {
        if (getHomeLocation() != null) {
            getHomeLocation().region = radius;
        }
    }

    public static boolean isLocationBasedControlEnabled() {
        if (geoTrackingEnabled == null) {
            geoTrackingEnabled = Boolean.valueOf(getPreferences().getBoolean(GEO_TRACKING_ENABLED, false));
        }
        return geoTrackingEnabled.booleanValue();
    }

    public static void setLocationBasedControlEnabled(boolean enabled) {
        geoTrackingEnabled = Boolean.valueOf(enabled);
        putBoolean(GEO_TRACKING_ENABLED, enabled);
    }

    public static Boolean isHotWaterProductionTempControl() {
        if (hotWaterProductionTempControl == null) {
            hotWaterProductionTempControl = Boolean.valueOf(getPreferences().getBoolean(HOT_WATER_PRODUCTION_TEMP_CONTROL, false));
        }
        return hotWaterProductionTempControl;
    }

    public static void setHotWaterProductionTempControl(Boolean hotWaterProductionTempControl) {
        if (hotWaterProductionTempControl == null) {
            hotWaterProductionTempControl = Boolean.valueOf(false);
        }
        hotWaterProductionTempControl = hotWaterProductionTempControl;
        putBoolean(HOT_WATER_PRODUCTION_TEMP_CONTROL, hotWaterProductionTempControl.booleanValue());
    }

    public static Boolean isHotWaterProductionControl() {
        if (hotWaterProductionControl == null) {
            hotWaterProductionControl = Boolean.valueOf(getPreferences().getBoolean(HOT_WATER_PRODUCTION_CONTROL, false));
        }
        return hotWaterProductionControl;
    }

    public static void setHotWaterProductionControl(Boolean hotWaterProductionControl) {
        if (hotWaterProductionControl == null) {
            hotWaterProductionControl = Boolean.valueOf(false);
        }
        hotWaterProductionControl = hotWaterProductionControl;
        putBoolean(HOT_WATER_PRODUCTION_CONTROL, hotWaterProductionControl.booleanValue());
    }

    public static Boolean isHotWaterProductionControlInit() {
        if (hotWaterProductionControlInit == null) {
            hotWaterProductionControlInit = Boolean.valueOf(getPreferences().getBoolean(HOT_WATER_PRODUCTION_CONTROL_INIT, false));
        }
        return hotWaterProductionControlInit;
    }

    public static void setHotWaterProductionControlInit(Boolean hotWaterProductionControlInit) {
        hotWaterProductionControlInit = hotWaterProductionControlInit;
        putBoolean(HOT_WATER_PRODUCTION_CONTROL_INIT, hotWaterProductionControlInit.booleanValue());
    }

    public static void setPartner(PartnerEnum partner) {
        partner = partner;
        putString(PARTNER, partner.name());
    }

    public static PartnerEnum getPartner() {
        if (partner == null) {
            partner = PartnerEnum.valueOf(getPreferences().getString(PARTNER, PartnerEnum.NONE.name()));
        }
        return partner;
    }

    public static int getHomeId() {
        if (homeId == null) {
            homeId = Integer.valueOf(getPreferences().getInt(HOME_ID, -1));
        }
        return homeId.intValue();
    }

    public static void setHomeId(int homeId) {
        homeId = Integer.valueOf(homeId);
        putInteger(HOME_ID, homeId);
    }

    public static void setHomeName(String homeName) {
        homeName = homeName;
        putString("homeName", homeName);
    }

    public static String getHomeName() {
        if (homeName == null) {
            homeName = getPreferences().getString("homeName", "");
        }
        return homeName;
    }

    public static void setHomeTimezone(String homeTimezone) {
        homeTimezone = homeTimezone;
        putString(HOME_TIMEZONE, homeTimezone);
    }

    public static String getHomeTimezone() {
        if (homeTimezone == null || homeTimezone.isEmpty()) {
            homeTimezone = getPreferences().getString(HOME_TIMEZONE, DateTimeZone.getDefault().getID());
        }
        return homeTimezone;
    }

    public static String getSerialNo() {
        if (serialNo == null) {
            serialNo = getPreferences().getString(SERIAL_NO, "");
        }
        return serialNo;
    }

    public static void setSerialNo(String serialNo) {
        serialNo = serialNo;
        putString(SERIAL_NO, serialNo);
    }

    public static String getDeviceSsid() {
        if (deviceSsid == null) {
            deviceSsid = getPreferences().getString(DEVICE_SSID, "");
        }
        return getCorrectSSID(deviceSsid);
    }

    public static String getDeviceSsid(boolean invertedQuotation) {
        if (deviceSsid == null) {
            deviceSsid = getPreferences().getString(DEVICE_SSID, "");
        }
        return getCorrectSSID(deviceSsid, invertedQuotation);
    }

    public static void setDeviceSsid(String deviceSsid) {
        deviceSsid = getCorrectSSID(deviceSsid);
        putString(DEVICE_SSID, deviceSsid);
    }

    private static String getCorrectSSID(String ssid, boolean quotationInverted) {
        if (ssid.isEmpty()) {
            return ssid;
        }
        boolean quoted;
        if ((VERSION.SDK_INT < 23 || !quotationInverted) && (VERSION.SDK_INT >= 23 || quotationInverted)) {
            quoted = false;
        } else {
            quoted = true;
        }
        if (quoted) {
            if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                return ssid;
            }
            return String.format("\"%s\"", new Object[]{ssid});
        } else if (ssid.contains("\"")) {
            return ssid.replace("\"", "");
        } else {
            return ssid;
        }
    }

    private static String getCorrectSSID(String ssid) {
        return getCorrectSSID(ssid, false);
    }

    public static String getAcManufacturer() {
        if (acManufacturer == null) {
            acManufacturer = getPreferences().getString(AC_MANUFACTURER, "");
        }
        return acManufacturer;
    }

    public static void setAcManufacturer(String acManufacturer) {
        acManufacturer = acManufacturer;
        putString(AC_MANUFACTURER, acManufacturer);
    }

    public static String getTadoSystemType() {
        if (tadoSystemType == null) {
            tadoSystemType = getPreferences().getString(TADO_SYSTEM_TYPE, "");
        }
        return tadoSystemType;
    }

    public static void setTadoSystemType(String tadoSystemType) {
        tadoSystemType = tadoSystemType;
        putString(TADO_SYSTEM_TYPE, tadoSystemType);
    }

    public static void setPermissionIntroShown(Boolean permissionIntroShown) {
        permissionIntroShown = permissionIntroShown;
        putBoolean(PERMISSION_INTRO_SHOWN, permissionIntroShown.booleanValue());
    }

    public static Boolean isPermissionIntroShown() {
        if (permissionIntroShown == null) {
            permissionIntroShown = Boolean.valueOf(getPreferences().getBoolean(PERMISSION_INTRO_SHOWN, false));
        }
        return permissionIntroShown;
    }

    public static String getAcManufacturerName() {
        if (acManufacturerName == null) {
            acManufacturerName = getPreferences().getString(AC_MANUFACTURER_NAME, "");
        }
        return acManufacturerName;
    }

    public static void setAcManufacturerName(String acManufacturerName) {
        acManufacturerName = acManufacturerName;
        putString(AC_MANUFACTURER_NAME, acManufacturerName);
    }

    public static TemperatureUnitEnum getTemperatureUnit() {
        if (temperatureUnit == null) {
            temperatureUnit = TemperatureUnitEnum.valueOf(getPreferences().getString(TEMPERATURE_UNIT, TemperatureUnitEnum.CELSIUS.name()));
        }
        return temperatureUnit;
    }

    public static void setTemperatureUnit(TemperatureUnitEnum temperatureUnit) {
        if (temperatureUnit != null) {
            temperatureUnit = temperatureUnit;
            putString(TEMPERATURE_UNIT, temperatureUnit.name());
        }
    }

    public static Integer getCurrentZone() {
        if (currentZone == null) {
            currentZone = Integer.valueOf(getPreferences().getInt(CURRENT_ZONE, -1));
        }
        return currentZone;
    }

    public static void setCurrentZone(Integer currentZone) {
        currentZone = currentZone;
        putInteger(CURRENT_ZONE, currentZone.intValue());
    }

    public static String getCurrentZoneName() {
        if (currentZoneName == null) {
            currentZoneName = getPreferences().getString(CURRENT_ZONE_NAME, getHomeName());
        }
        return currentZoneName;
    }

    public static void setCurrentZoneName(String currentZoneName) {
        currentZoneName = currentZoneName;
        putString(CURRENT_ZONE_NAME, currentZoneName);
    }

    public static int getMobileDeviceId() {
        if (mobileDeviceId == null) {
            mobileDeviceId = Integer.valueOf(getPreferences().getInt(MOBILE_DEVICE_ID, -1));
        }
        return mobileDeviceId.intValue();
    }

    public static void setMobileDeviceId(int id) {
        mobileDeviceId = Integer.valueOf(id);
        putInteger(MOBILE_DEVICE_ID, mobileDeviceId.intValue());
    }

    @Deprecated
    public static int getRateAppCounter() {
        if (rateAppCounter == null) {
            rateAppCounter = Integer.valueOf(getPreferences().getInt(RATE_APP_COUNTER, -1));
        }
        return rateAppCounter.intValue();
    }

    @Deprecated
    public static void setRateAppCounter(int counter) {
        rateAppCounter = Integer.valueOf(counter);
        putInteger(RATE_APP_COUNTER, rateAppCounter.intValue());
    }

    public static boolean getRateAppVoted() {
        if (rateAppVoted == null) {
            rateAppVoted = Boolean.valueOf(getPreferences().getBoolean(RATE_APP_VOTED, false));
        }
        return rateAppVoted.booleanValue();
    }

    public static void setRateAppVoted(boolean voted) {
        rateAppVoted = Boolean.valueOf(voted);
        putBoolean(RATE_APP_VOTED, rateAppVoted.booleanValue());
    }

    public static int getLastOnboardingVersionShown() {
        if (lastOnboardingVersionShown == null) {
            lastOnboardingVersionShown = Integer.valueOf(getPreferences().getInt(LAST_ONBOARDING_VERSION_SHOWN, 0));
        }
        return lastOnboardingVersionShown.intValue();
    }

    public static void setLastOnboardingVersionShown(int lastOnboardingVersionShown) {
        lastOnboardingVersionShown = Integer.valueOf(lastOnboardingVersionShown);
        putInteger(LAST_ONBOARDING_VERSION_SHOWN, lastOnboardingVersionShown);
    }

    public static void addCapabilities(String zoneId, String capabilities) {
        putString(zoneId, capabilities);
    }

    public static String getCapabilities(String zoneId) {
        return getPreferences().getString(zoneId, null);
    }

    public static void setChristmasModeEnabled(Boolean christmasModeEnabled) {
        christmasModeEnabled = christmasModeEnabled;
        putBoolean(CHRISTMAS_MODE_ENABLED, christmasModeEnabled.booleanValue());
    }

    public static Boolean isChristmasModeEnabled() {
        if (christmasModeEnabled == null) {
            christmasModeEnabled = Boolean.valueOf(getPreferences().getBoolean(CHRISTMAS_MODE_ENABLED, false));
        }
        return christmasModeEnabled;
    }

    public static String getLocationServicesMode() {
        if (locationServicesMode == null) {
            locationServicesMode = getPreferences().getString(LOCATION_SERVICES_MODE, "UNAVAILABLE");
        }
        return locationServicesMode;
    }

    public static void setLocationServicesMode(String mode) {
        locationServicesMode = mode;
        putString(LOCATION_SERVICES_MODE, mode);
    }

    public static synchronized void saveLocationConfiguration(GeolocationConfig config) {
        synchronized (UserConfig.class) {
            locationConfiguration = config;
            putString(LOCATION_CONFIGURATION, new Gson().toJson((Object) config));
            setHomeLocation(config.getHome());
        }
    }

    @Nullable
    public static synchronized GeolocationConfig getLocationConfiguration() {
        GeolocationConfig geolocationConfig;
        synchronized (UserConfig.class) {
            if (locationConfiguration == null) {
                locationConfiguration = (GeolocationConfig) new Gson().fromJson(getPreferences().getString(LOCATION_CONFIGURATION, null), GeolocationConfig.class);
                if (locationConfiguration != null) {
                    setHomeLocation(locationConfiguration.getHome());
                }
            }
            geolocationConfig = locationConfiguration;
        }
        return geolocationConfig;
    }

    @Nullable
    public static Location getHome() {
        Location location = new Location("");
        try {
            location.setLatitude(getHomeLocation().geolocation.latitude);
            location.setLongitude(getHomeLocation().geolocation.longitude);
            return location;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static int getLastTutorialVersionShown(TutorialDataSourceRepositoryEnum tutorialKeyEnum, int defaultVersion) {
        return getPreferences().getInt(tutorialKeyEnum.name(), defaultVersion);
    }

    public static void setLastTutorialVersionShown(TutorialDataSourceRepositoryEnum tutorialKetEnum, int version) {
        putInteger(tutorialKetEnum.name(), version);
    }

    public static long getLastTimeAirplaneModeTurnedOff() {
        if (lastTimeAirplaneModeTurnedOff == null) {
            lastTimeAirplaneModeTurnedOff = Long.valueOf(getPreferences().getLong(LAST_TIME_AIRPLANE_MODE_TURNED_OFF, 0));
            if (lastTimeAirplaneModeTurnedOff.longValue() == 0) {
                long nowMillis = System.currentTimeMillis();
                setLastTimeAirplaneModeTurnedOff(nowMillis);
                lastTimeAirplaneModeTurnedOff = Long.valueOf(nowMillis);
            }
        }
        return lastTimeAirplaneModeTurnedOff.longValue();
    }

    public static void setLastTimeAirplaneModeTurnedOff(long time) {
        lastTimeAirplaneModeTurnedOff = Long.valueOf(time);
        putLong(LAST_TIME_AIRPLANE_MODE_TURNED_OFF, time);
    }

    public static long getLastTimeBooted() {
        if (lastBootTime == null) {
            lastBootTime = Long.valueOf(getPreferences().getLong(LAST_TIME_BOOTED, 0));
            if (lastBootTime.longValue() == 0) {
                long nowMillis = System.currentTimeMillis();
                setLastTimeBooted(nowMillis);
                lastBootTime = Long.valueOf(nowMillis);
            }
        }
        return lastBootTime.longValue();
    }

    public static void setLastTimeBooted(long time) {
        lastBootTime = Long.valueOf(time);
        putLong(LAST_TIME_BOOTED, time);
    }

    public static boolean getCurrentZoneReportToolbarButtonState(String buttonType, boolean defaultState) {
        return getBoolean(String.format("%s_%s", new Object[]{currentZone, buttonType}), defaultState);
    }

    public static void setCurrentZoneReportToolbarButtonState(String buttonType, boolean state) {
        putBoolean(String.format("%s_%s", new Object[]{currentZone, buttonType}), state);
    }

    public static void clearUserData() {
        password = null;
        username = null;
        nickname = null;
        license = null;
        inHomeWifi = null;
        homeWifis = null;
        ignoredWifis = null;
        wifiHashId = null;
        bootFilter = null;
        geoTrackingEnabled = null;
        hotWaterProductionControl = null;
        hotWaterProductionControlInit = null;
        homeId = null;
        serialNo = null;
        deviceSsid = null;
        acManufacturer = null;
        tadoSystemType = null;
        homeName = null;
        hotWaterProductionTempControl = null;
        homeTimezone = null;
        acManufacturerName = null;
        temperatureUnit = null;
        currentZone = null;
        currentZoneName = null;
        mobileDeviceId = null;
        permissionIntroShown = null;
        rateAppCounter = null;
        rateAppShown = null;
        rateAppVoted = null;
        rateAppVotedVersion = null;
        christmasModeEnabled = null;
        locationServicesMode = null;
        lastPostedLocation = null;
        lastSeenLocation = null;
        lastTimeAirplaneModeTurnedOff = null;
        getEditor().clear().apply();
    }

    public static long getRateDialogDueDate() {
        if (rateDate == null) {
            rateDate = getLong("rate_date", 0);
        }
        return rateDate.longValue();
    }

    public static void setRateDialogDueDate(long rateDate) {
        rateDate = Long.valueOf(rateDate);
        putLong("rate_date", rateDate);
    }

    public static void setHomeCreation(long homeCreation) {
        homeCreation = Long.valueOf(homeCreation);
        putLong("homeCreation", homeCreation);
    }

    public static long getHomeCreation() {
        if (homeCreation == null) {
            homeCreation = getLong("homeCreation", new Date().getTime());
        }
        return homeCreation.longValue();
    }

    public static void setHomeCountry(String homeCountry) {
        putString("homeCountry", homeCountry);
    }

    public static String getHomeCountry() {
        return getPreferences().getString("homeCountry", "");
    }

    public static void setLicense(LicenseEnum license) {
        license = license;
        putString("license", license.name());
    }

    public static LicenseEnum getLicense() {
        if (license == null) {
            try {
                license = LicenseEnum.valueOf(getPreferences().getString("license", LicenseEnum.STANDARD.toString()));
            } catch (IllegalArgumentException e) {
                license = LicenseEnum.STANDARD;
            }
        }
        return license;
    }

    @Nullable
    public static String getFcmToken() {
        if (fcmToken == null) {
            fcmToken = getPreferences().getString(FCM_TOKEN, null);
        }
        return fcmToken;
    }

    public static void setFcmToken(String fcmToken) {
        fcmToken = fcmToken;
        putString(FCM_TOKEN, fcmToken);
    }

    public static LegacyUserConfig getLegacyConfiguration() {
        return new LegacyUserConfig(getPreferences());
    }

    public static LegacyUserConfig getLegacyConfiguration(SharedPreferences preferences) {
        return new LegacyUserConfig(preferences);
    }
}

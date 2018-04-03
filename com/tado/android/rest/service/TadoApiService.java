package com.tado.android.rest.service;

import com.tado.android.entities.HomeSignup;
import com.tado.android.entities.UserDetails;
import com.tado.android.entities.Weather;
import com.tado.android.entities.ZoneOrderInput;
import com.tado.android.rest.model.AcDriver;
import com.tado.android.rest.model.AirConditioningControl;
import com.tado.android.rest.model.BetaAccessRequest;
import com.tado.android.rest.model.Block;
import com.tado.android.rest.model.ClosedLoopControlConfig;
import com.tado.android.rest.model.DayReport;
import com.tado.android.rest.model.DazzleMode;
import com.tado.android.rest.model.DeviceInput;
import com.tado.android.rest.model.DriverSelection;
import com.tado.android.rest.model.EarlyStart;
import com.tado.android.rest.model.Feedback;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.HomeDetails;
import com.tado.android.rest.model.HomeInfo;
import com.tado.android.rest.model.HomeLicense;
import com.tado.android.rest.model.HomePresenceWrapper;
import com.tado.android.rest.model.HomeState;
import com.tado.android.rest.model.Invitation;
import com.tado.android.rest.model.LegacyMobileDevice;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.MobileDeviceInput;
import com.tado.android.rest.model.MobileDeviceMetadata;
import com.tado.android.rest.model.MobileDeviceSettings;
import com.tado.android.rest.model.OpenWindowDetectionConfiguration;
import com.tado.android.rest.model.OverlayTerminationConditionResponse;
import com.tado.android.rest.model.PushNotificationRegistration;
import com.tado.android.rest.model.ScheduleMode;
import com.tado.android.rest.model.Temperature;
import com.tado.android.rest.model.Timetable;
import com.tado.android.rest.model.User;
import com.tado.android.rest.model.Zone;
import com.tado.android.rest.model.ZoneCapabilities;
import com.tado.android.rest.model.ZoneName;
import com.tado.android.rest.model.ZoneOverlay;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.rest.model.hvac.SrtDevice;
import com.tado.android.rest.model.hvac.ZoneWithImplicitControl;
import com.tado.android.rest.model.installation.GenericHardwareDevice;
import com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.UpdateAwayDistanceModel;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TadoApiService {
    @POST("/api/v2/invitations/{invitation_token}/accept")
    Call<Void> acceptInvitation(@Path("invitation_token") String str, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/zones")
    Call<Zone> addNewZone(@Path("home_id") int i, @Body ZoneWithImplicitControl zoneWithImplicitControl, @Query("force") boolean z, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/zones/{zone_id}/devices")
    Call<GenericHardwareDevice> assignDeviceToZone(@Path("home_id") int i, @Path("zone_id") int i2, @Body SrtDevice srtDevice, @Query("force") boolean z, @QueryMap Map<String, String> map);

    @POST("/api/v2/app/betaAccessRequest")
    Call<Void> betaAccessRequest(@Body BetaAccessRequest betaAccessRequest, @QueryMap Map<String, String> map);

    @DELETE("/api/v2/homes/{home_id}/invitations/{invitation_token}")
    Call<Void> cancelInvitation(@Path("home_id") int i, @Path("invitation_token") String str, @QueryMap Map<String, String> map);

    @POST("api/v1/homes")
    Call<HomeInfo> createHome(@Body HomeSignup homeSignup, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/mobileDevices")
    Call<MobileDevice> createMobileDevice(@Path("home_id") int i, @Body MobileDeviceInput mobileDeviceInput, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/mobileDevices/{device_id}/legacyAuthCredentials")
    Call<LegacyMobileDevice> createMobileDeviceWithLegacyAuth(@Path("home_id") int i, @Path("device_id") int i2, @Body MobileDeviceInput mobileDeviceInput, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/modes")
    Call<ScheduleMode> createScheduleMode(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map, @Body ScheduleMode scheduleMode);

    @DELETE("/api/v2/homes/{home_id}/mobileDevices/{mobile_device_id}")
    Call<Void> deleteMobileDevice(@Path("home_id") int i, @Path("mobile_device_id") int i2, @QueryMap Map<String, String> map);

    @DELETE("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/modes/{schedule_mode_id}")
    Call<Void> deleteScheduleMode(@Path("home_id") int i, @Path("zone_id") int i2, @Path("schedule_mode_id") int i3, @QueryMap Map<String, String> map);

    @DELETE("/api/v2/homes/{home_id}/zones/{zone_id}/overlay")
    Call<Void> deleteZoneOverlay(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/control/drivers")
    Call<List<AcDriver>> getAcDrivers(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/activeTimetable")
    Call<Timetable> getActiveTimetable(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/awayConfiguration")
    Call<GenericAwayConfiguration> getAwayConfiguration(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/capabilities")
    Call<ZoneCapabilities> getCapabilities(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/dayReport")
    Call<DayReport> getDayReport(@Path("home_id") int i, @Path("zone_id") int i2, @Query("date") String str, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/timetables/{timetable_id}/blocks/{day_type}")
    Call<List<Block>> getDayTypeBlocks(@Path("home_id") int i, @Path("zone_id") int i2, @Path("timetable_id") int i3, @Path("day_type") String str, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/defaultOverlay")
    Call<OverlayTerminationConditionResponse> getDefaultOverlaySettings(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/devices")
    Call<List<GenericHardwareDevice>> getDevices(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/earlyStart")
    Call<EarlyStart> getEarlyStart(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}")
    Call<HomeInfo> getHome(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/state")
    Call<HomeState> getHomeState(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @GET("/api/v2/invitations/{invitation_token}")
    Call<Invitation> getInvitationDetails(@Path("invitation_token") String str);

    @GET("/api/v2/me")
    Call<User> getMe(@QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/measuringDevice")
    Call<GenericHardwareDevice> getMeasuringDevice(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/mobileDevices/{device_id}/settings")
    Call<MobileDeviceSettings> getMobileDeviceSettings(@Path("home_id") int i, @Path("device_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/mobileDevices")
    Call<MobileDevice> getMobileDeviceTransitional(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/mobileDevices")
    Call<List<MobileDevice>> getMobileDevices(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/mobileDevices")
    Call<List<MobileDevice>> getMobileDevicesForZone(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/modes/{schedule_mode_id}")
    Call<ScheduleMode> getScheduleMode(@Path("home_id") int i, @Path("zone_id") int i2, @Path("schedule_mode_id") int i3, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/modes")
    Call<List<ScheduleMode>> getScheduleModes(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/devices/{serial_number}/temperatureOffset")
    Call<Temperature> getTemperatureOffset(@Path("serial_number") String str, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/timetables/{timetable_id}/blocks")
    Call<List<Block>> getTimetableBlocks(@Path("home_id") int i, @Path("zone_id") int i2, @Path("timetable_id") int i3, @QueryMap Map<String, String> map);

    @GET("/api/v1/users/{username}")
    Call<UserDetails> getUser(@Path("username") String str, @Query("username") String str2, @Query("password") String str3);

    @GET("/api/v2/homes/{home_id}/weather")
    Call<Weather> getWeather(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/control")
    Call<AirConditioningControl> getZoneControl(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/devices")
    Call<List<GenericHardwareDevice>> getZoneDevices(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/details")
    Call<Zone> getZoneName(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/overlay")
    Call<ZoneOverlay> getZoneOverlay(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones/{zone_id}/state")
    Call<ZoneState> getZoneState(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/zones")
    Call<List<Zone>> getZones(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @POST("/api/v2/devices/{serialNo}/identify")
    Call<Void> identifyDevice(@Path("serialNo") String str, @QueryMap Map<String, String> map);

    @DELETE("/api/v2/homes/{home_id}/zones/{zone_id}/state/openWindow")
    Call<Void> ignoreOpenWindowDetection(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/invitations")
    Call<Invitation> inviteUser(@Path("home_id") int i, @Body Invitation invitation, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/invitations")
    Call<List<Invitation>> listInvitations(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @GET("/api/v2/homes/{home_id}/users")
    Call<List<User>> listUsers(@Path("home_id") int i, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/awayRadiusInMeters")
    Call<Void> putAwayRadius(@Path("home_id") int i, @Body UpdateAwayDistanceModel updateAwayDistanceModel, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/defaultOverlay")
    Call<OverlayTerminationConditionResponse> putDefaultOverlaySettings(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map, @Body OverlayTerminationConditionResponse overlayTerminationConditionResponse);

    @PUT("/api/v2/homes/{home_id}/details")
    Call<Void> putHomeDetails(@Path("home_id") int i, @QueryMap Map<String, String> map, @Body HomeDetails homeDetails);

    @PUT("/api/v2/homes/{home_id}/license")
    Call<Void> putHomeLicense(@Path("home_id") int i, @QueryMap Map<String, String> map, @Body HomeLicense homeLicense);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/details")
    Call<Zone> putZoneName(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map, @Body ZoneName zoneName);

    @PUT("/api/v2/homes/{home_id}/zoneOrder")
    Call<Void> putZoneOrder(@Path("home_id") int i, @Body List<ZoneOrderInput> list, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/overlay")
    Call<ZoneOverlay> putZoneOverlay(@Path("home_id") int i, @Path("zone_id") int i2, @Body ZoneOverlay zoneOverlay, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/devices")
    Call<GenericHardwareDevice> registerDevice(@Path("home_id") int i, @Body DeviceInput deviceInput, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/mobileDevices/{mobile_device_id}/pushNotificationRegistration")
    Call<Void> registerPushNotification(@Path("home_id") int i, @Path("mobile_device_id") int i2, @Body PushNotificationRegistration pushNotificationRegistration, @QueryMap Map<String, String> map);

    @POST("/api/v2/invitations/{invitation_token}/reject")
    Call<Void> rejectInvitation(@Path("invitation_token") String str, @QueryMap Map<String, String> map);

    @DELETE("/api/v2/homes/{home_id}/devices/{serial_number}")
    Call<Void> removeDevice(@Path("home_id") int i, @Path("serial_number") String str, @Query("force") boolean z, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/invitations/{invitation_token}/resend")
    Call<Void> resendInvitation(@Path("home_id") int i, @Path("invitation_token") String str, @QueryMap Map<String, String> map);

    @DELETE("/api/v2/homes/{home_id}/users/{user_name}")
    Call<Void> revokeAccess(@Path("home_id") int i, @Path("user_name") String str, @QueryMap Map<String, String> map);

    @POST("/api/v2/search/devices")
    Call<GenericHardwareDevice> searchDevice(@Body DeviceInput deviceInput, @QueryMap Map<String, String> map);

    @POST("/api/v2/app/feedback")
    Call<Void> sendFeedback(@Body Feedback feedback, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/activeTimetable")
    Call<Timetable> setActiveTimetable(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map, @Body Timetable timetable);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/measuringDevice")
    Call<GenericHardwareDevice> setMeasuringDevice(@Path("home_id") int i, @Path("zone_id") int i2, @Body GenericHardwareDevice genericHardwareDevice, @QueryMap Map<String, String> map);

    @PUT("/api/v2/devices/{serial_number}/temperatureOffset")
    Call<Temperature> setTemperatureOffset(@Path("serial_number") String str, @Body Temperature temperature, @QueryMap Map<String, String> map);

    @DELETE("/api/v2/homes/{home_id}/mobileDevices/{mobile_device_id}/pushNotificationRegistration")
    Call<Void> unRegisterPushNotification(@Path("home_id") int i, @Path("mobile_device_id") int i2, @QueryMap Map<String, String> map);

    @POST("/api/v2/homes/{home_id}/zones/{zone_id}/state/openWindow/unmask")
    Call<Void> unmaskOpenWindow(@Path("home_id") int i, @Path("zone_id") int i2, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/control/driver")
    Call<Void> updateAcDriver(@Path("home_id") int i, @Path("zone_id") int i2, @Body DriverSelection driverSelection, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/awayConfiguration")
    Call<Void> updateAwayConfiguration(@Path("home_id") int i, @Path("zone_id") int i2, @Body GenericAwayConfiguration genericAwayConfiguration, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/timetables/{timetable_id}/blocks/{day_type}")
    Call<List<Block>> updateDayTypeBlocks(@Path("home_id") int i, @Path("zone_id") int i2, @Path("timetable_id") int i3, @Path("day_type") String str, @QueryMap Map<String, String> map, @Body List<Block> list);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/dazzle")
    Call<Void> updateDazzleMode(@Path("home_id") int i, @Path("zone_id") int i2, @Body DazzleMode dazzleMode, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/control/drivers/{driver_discr}/config")
    Call<Void> updateDriverConfig(@Path("home_id") int i, @Path("zone_id") int i2, @Path("driver_discr") int i3, @Body ClosedLoopControlConfig closedLoopControlConfig, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/earlyStart")
    Call<EarlyStart> updateEarlyStart(@Path("home_id") int i, @Path("zone_id") int i2, @Body EarlyStart earlyStart, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/presence")
    Call<Void> updateHomePresence(@Path("home_id") int i, @Body HomePresenceWrapper homePresenceWrapper, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/mobileDevices/{device_id}/metadata")
    Call<Void> updateMobileDeviceMetadata(@Path("home_id") int i, @Path("device_id") int i2, @Body MobileDeviceMetadata mobileDeviceMetadata, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/mobileDevices/{device_id}/settings")
    Call<MobileDeviceSettings> updateMobileDeviceSettings(@Path("home_id") int i, @Path("device_id") int i2, @Body MobileDeviceSettings mobileDeviceSettings, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/openWindowDetection")
    Call<Void> updateOpenWindowDetectionConfiguration(@Path("home_id") int i, @Path("zone_id") int i2, @Body OpenWindowDetectionConfiguration openWindowDetectionConfiguration, @QueryMap Map<String, String> map);

    @PUT("/api/v2/homes/{home_id}/zones/{zone_id}/schedule/modes/{schedule_mode_id}")
    Call<ScheduleMode> updateScheduleMode(@Path("home_id") int i, @Path("zone_id") int i2, @Path("schedule_mode_id") int i3, @QueryMap Map<String, String> map, @Body ScheduleMode scheduleMode);

    @PUT("http://tado-app-logs.s3.amazonaws.com/location/{home_id}/{filename}")
    @Multipart
    Call<Void> uploadLocationLog(@Path("home_id") int i, @Path("filename") String str, @Header("Content-Type") String str2, @Header("x-amz-grant-full-control") String str3, @Part("f") RequestBody requestBody);

    @PUT("http://tado-app-logs.s3.amazonaws.com/location/login/android/{filename}")
    @Multipart
    Call<Void> uploadLoginLog(@Path("filename") String str, @Header("Content-Type") String str2, @Header("x-amz-grant-full-control") String str3, @Part("f") RequestBody requestBody);
}

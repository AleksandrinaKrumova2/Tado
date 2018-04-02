package com.google.android.gms.internal;

import com.tado.android.utils.Constants;
import com.tado.android.views.MorphingButton;
import org.joda.time.DateTimeConstants;

public final class zzarl {
    private static zzarm<Boolean> zzdvw = zzarm.zza("analytics.service_enabled", false, false);
    public static zzarm<Boolean> zzdvx = zzarm.zza("analytics.service_client_enabled", true, true);
    public static zzarm<String> zzdvy = zzarm.zzc("analytics.log_tag", "GAv4", "GAv4-SVC");
    private static zzarm<Long> zzdvz = zzarm.zza("analytics.max_tokens", 60, 60);
    private static zzarm<Float> zzdwa = zzarm.zza("analytics.tokens_per_sec", 0.5f, 0.5f);
    public static zzarm<Integer> zzdwb = zzarm.zza("analytics.max_stored_hits", (int) MorphingButton.COLLAPSE_ANIMATION_DELAY, 20000);
    private static zzarm<Integer> zzdwc = zzarm.zza("analytics.max_stored_hits_per_app", (int) MorphingButton.COLLAPSE_ANIMATION_DELAY, (int) MorphingButton.COLLAPSE_ANIMATION_DELAY);
    public static zzarm<Integer> zzdwd = zzarm.zza("analytics.max_stored_properties_per_app", 100, 100);
    public static zzarm<Long> zzdwe = zzarm.zza("analytics.local_dispatch_millis", 1800000, 120000);
    public static zzarm<Long> zzdwf = zzarm.zza("analytics.initial_local_dispatch_millis", (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT, (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
    private static zzarm<Long> zzdwg = zzarm.zza("analytics.min_local_dispatch_millis", 120000, 120000);
    private static zzarm<Long> zzdwh = zzarm.zza("analytics.max_local_dispatch_millis", 7200000, 7200000);
    public static zzarm<Long> zzdwi = zzarm.zza("analytics.dispatch_alarm_millis", 7200000, 7200000);
    public static zzarm<Long> zzdwj = zzarm.zza("analytics.max_dispatch_alarm_millis", 32400000, 32400000);
    public static zzarm<Integer> zzdwk = zzarm.zza("analytics.max_hits_per_dispatch", 20, 20);
    public static zzarm<Integer> zzdwl = zzarm.zza("analytics.max_hits_per_batch", 20, 20);
    public static zzarm<String> zzdwm;
    public static zzarm<String> zzdwn;
    public static zzarm<String> zzdwo;
    public static zzarm<String> zzdwp;
    public static zzarm<Integer> zzdwq = zzarm.zza("analytics.max_get_length", 2036, 2036);
    public static zzarm<String> zzdwr = zzarm.zzc("analytics.batching_strategy.k", zzaqt.BATCH_BY_COUNT.name(), zzaqt.BATCH_BY_COUNT.name());
    public static zzarm<String> zzdws;
    private static zzarm<Integer> zzdwt = zzarm.zza("analytics.max_hits_per_request.k", 20, 20);
    public static zzarm<Integer> zzdwu = zzarm.zza("analytics.max_hit_length.k", 8192, 8192);
    public static zzarm<Integer> zzdwv = zzarm.zza("analytics.max_post_length.k", 8192, 8192);
    public static zzarm<Integer> zzdww = zzarm.zza("analytics.max_batch_post_length", 8192, 8192);
    public static zzarm<String> zzdwx;
    public static zzarm<Integer> zzdwy = zzarm.zza("analytics.batch_retry_interval.seconds.k", 3600, 3600);
    private static zzarm<Long> zzdwz = zzarm.zza("analytics.service_monitor_interval", 86400000, 86400000);
    public static zzarm<Integer> zzdxa = zzarm.zza("analytics.http_connection.connect_timeout_millis", (int) DateTimeConstants.MILLIS_PER_MINUTE, (int) DateTimeConstants.MILLIS_PER_MINUTE);
    public static zzarm<Integer> zzdxb = zzarm.zza("analytics.http_connection.read_timeout_millis", 61000, 61000);
    public static zzarm<Long> zzdxc = zzarm.zza("analytics.campaigns.time_limit", 86400000, 86400000);
    private static zzarm<String> zzdxd;
    private static zzarm<Integer> zzdxe = zzarm.zza("analytics.first_party_experiment_variant", 0, 0);
    public static zzarm<Boolean> zzdxf = zzarm.zza("analytics.test.disable_receiver", false, false);
    public static zzarm<Long> zzdxg = zzarm.zza("analytics.service_client.idle_disconnect_millis", (long) Constants.WIFI_CONNECTION_RETRY_TIMEOUT, (long) Constants.WIFI_CONNECTION_RETRY_TIMEOUT);
    public static zzarm<Long> zzdxh = zzarm.zza("analytics.service_client.connect_timeout_millis", (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT, (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
    private static zzarm<Long> zzdxi = zzarm.zza("analytics.service_client.second_connect_delay_millis", (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT, (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);
    private static zzarm<Long> zzdxj = zzarm.zza("analytics.service_client.unexpected_reconnect_millis", 60000, 60000);
    public static zzarm<Long> zzdxk = zzarm.zza("analytics.service_client.reconnect_throttle_millis", 1800000, 1800000);
    public static zzarm<Long> zzdxl = zzarm.zza("analytics.monitoring.sample_period_millis", 86400000, 86400000);
    public static zzarm<Long> zzdxm = zzarm.zza("analytics.initialization_warning_threshold", (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT, (long) Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT);

    static {
        String str = "http://www.google-analytics.com";
        zzdwm = zzarm.zzc("analytics.insecure_host", str, str);
        str = "https://ssl.google-analytics.com";
        zzdwn = zzarm.zzc("analytics.secure_host", str, str);
        str = "/collect";
        zzdwo = zzarm.zzc("analytics.simple_endpoint", str, str);
        str = "/batch";
        zzdwp = zzarm.zzc("analytics.batching_endpoint", str, str);
        str = zzaqz.GZIP.name();
        zzdws = zzarm.zzc("analytics.compression_strategy.k", str, str);
        str = "404,502";
        zzdwx = zzarm.zzc("analytics.fallback_responses.k", str, str);
        str = "";
        zzdxd = zzarm.zzc("analytics.first_party_experiment_id", str, str);
    }
}

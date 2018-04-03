package com.tado.android.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.tado.android.rest.model.PushNotificationRegistration;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/tado/android/fcm/FCMInstanceService;", "Lcom/google/firebase/iid/FirebaseInstanceIdService;", "()V", "onTokenRefresh", "", "Companion", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: FCMInstanceService.kt */
public final class FCMInstanceService extends FirebaseInstanceIdService {
    public static final Companion Companion = new Companion();

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007¨\u0006\b"}, d2 = {"Lcom/tado/android/fcm/FCMInstanceService$Companion;", "", "()V", "sendRegistrationToServer", "", "refreshedToken", "", "unregisterPushNotification", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: FCMInstanceService.kt */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void sendRegistrationToServer(@NotNull String refreshedToken) {
            Intrinsics.checkParameterIsNotNull(refreshedToken, "refreshedToken");
            RestServiceGenerator.getTadoRestService().registerPushNotification(UserConfig.getHomeId(), UserConfig.getMobileDeviceId(), new PushNotificationRegistration(refreshedToken), RestServiceGenerator.getCredentialsMap()).enqueue(new FCMInstanceService$Companion$sendRegistrationToServer$1(refreshedToken));
        }

        @JvmStatic
        public final void unregisterPushNotification() {
            RestServiceGenerator.getTadoRestService().unRegisterPushNotification(UserConfig.getHomeId(), UserConfig.getMobileDeviceId(), RestServiceGenerator.getCredentialsMap()).enqueue(new FCMInstanceService$Companion$unregisterPushNotification$1());
        }
    }

    @JvmStatic
    public static final void sendRegistrationToServer(@NotNull String refreshedToken) {
        Companion.sendRegistrationToServer(refreshedToken);
    }

    @JvmStatic
    public static final void unregisterPushNotification() {
        Companion.unregisterPushNotification();
    }

    public void onTokenRefresh() {
        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "FirebaseInstanceId.getInstance()");
        String refreshedToken = instance.getToken();
        Builder start = Snitcher.start();
        String str = "FCMInstanceService";
        StringBuilder append = new StringBuilder().append("Refreshed token: ");
        if (refreshedToken == null) {
            Intrinsics.throwNpe();
        }
        start.log(3, str, append.append(refreshedToken).toString(), new Object[0]);
        Companion.sendRegistrationToServer(refreshedToken);
    }
}

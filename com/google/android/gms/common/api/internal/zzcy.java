package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import java.util.Set;

@WorkerThread
public interface zzcy {
    void zzb(zzan com_google_android_gms_common_internal_zzan, Set<Scope> set);

    void zzh(ConnectionResult connectionResult);
}

package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class PreviewActivity extends Activity {
    public void onCreate(Bundle bundle) {
        String valueOf;
        String str;
        try {
            super.onCreate(bundle);
            zzdj.zzct("Preview activity");
            Uri data = getIntent().getData();
            if (!TagManager.getInstance(this).zzq(data)) {
                valueOf = String.valueOf(data);
                CharSequence stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 73).append("Cannot preview the app with the uri: ").append(valueOf).append(". Launching current version instead.").toString();
                zzdj.zzcu(stringBuilder);
                AlertDialog create = new Builder(this).create();
                create.setTitle("Preview failure");
                create.setMessage(stringBuilder);
                create.setButton(-1, "Continue", new zzeh(this));
                create.show();
            }
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage != null) {
                String str2 = "Invoke the launch activity for package name: ";
                valueOf = String.valueOf(getPackageName());
                zzdj.zzct(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                startActivity(launchIntentForPackage);
                return;
            }
            str = "No launch activity found for package name: ";
            valueOf = String.valueOf(getPackageName());
            zzdj.zzct(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        } catch (Exception e) {
            str = "Calling preview threw an exception: ";
            valueOf = String.valueOf(e.getMessage());
            zzdj.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }
}

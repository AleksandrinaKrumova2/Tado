package com.tado.android.settings.mainsettings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import com.tado.C0676R;
import com.tado.android.PromoActivity;
import com.tado.android.analytics.AnalyticsConstants.Screen;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.settings.model.PresenceDetectionSettings;
import com.tado.android.utils.Util;

public class SignOutDialog extends DialogFragment {

    class C11221 implements OnClickListener {
        C11221() {
        }

        public void onClick(DialogInterface dialog, int which) {
            SignOutDialog.this.dismiss();
        }
    }

    class C11232 implements OnClickListener {
        C11232() {
        }

        public void onClick(DialogInterface dialog, int which) {
            PresenceDetectionSettings.updatePresenceDetectionSetting(false, null);
            Util.clearUserData();
            Intent intent = new Intent(SignOutDialog.this.getActivity(), PromoActivity.class);
            intent.setFlags(1409318912);
            SignOutDialog.this.startActivity(intent);
            AnalyticsHelper.trackEvent(SignOutDialog.this.getActivity().getApplication(), Screen.SETTINGS, "SignOut");
            SignOutDialog.this.dismiss();
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new Builder(getActivity());
        builder.setMessage((int) C0676R.string.settings_signOut_message).setPositiveButton((int) C0676R.string.settings_signOut_signOutButton, new C11232()).setNegativeButton((int) C0676R.string.settings_signOut_cancelButton, new C11221());
        return builder.create();
    }
}

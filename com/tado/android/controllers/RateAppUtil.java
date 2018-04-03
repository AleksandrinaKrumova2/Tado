package com.tado.android.controllers;

import android.support.v4.app.FragmentManager;
import com.crashlytics.android.Crashlytics;
import com.tado.C0676R;
import com.tado.android.alert_dialogs.RateDialogFragment;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Snitcher.Builder;
import com.tado.android.utils.UserConfig;
import org.joda.time.DateTime;

public class RateAppUtil {
    public static VersionState versionState = VersionState.UNKNOWN;

    public boolean checkRateAppDialog() {
        return checkRateAppDialog(DateTime.now());
    }

    public boolean checkRateAppDialog(DateTime datetime) {
        if (isFlagEnabled()) {
            long lastPrompt = UserConfig.getRateDialogDueDate();
            Builder start = Snitcher.start();
            String str = "RateUtil";
            String str2 = "rate date set to %s";
            Object[] objArr = new Object[1];
            objArr[0] = lastPrompt != 0 ? new DateTime(lastPrompt).toString() : "never";
            start.log(3, str, str2, objArr);
            if (isOldLogic()) {
                resetOldLogic();
                scheduleRateDialog(getDateMillisIfRecentlyRated(datetime));
                Snitcher.start().log(3, "RateUtil", "reset old logic, scheduled for %s", new DateTime(UserConfig.getRateDialogDueDate()).toString());
            } else if (versionState == VersionState.MAYOR_UPDATE) {
                scheduleRateDialog(getDateMillisIfNotRated(datetime));
                Snitcher.start().log(3, "RateUtil", "major update, scheduled for %s", new DateTime(UserConfig.getRateDialogDueDate()).toString());
            } else if (versionState == VersionState.NEW_INSTALL) {
                scheduleRateDialog(getDateMillisIfNotRated(datetime));
                Snitcher.start().log(3, "RateUtil", "clean install, scheduled for %s", new DateTime(UserConfig.getRateDialogDueDate()).toString());
            }
            if (shouldShowRateByDate(lastPrompt, datetime.getMillis())) {
                Snitcher.start().log(3, "RateUtil", "showing rate dialog", new Object[0]);
                return true;
            }
        }
        return false;
    }

    private long getDateMillisIfRecentlyRated(DateTime datetime) {
        return datetime.plusMonths(2).getMillis();
    }

    private long getDateMillisIfNotRated(DateTime datetime) {
        return datetime.plusWeeks(2).getMillis();
    }

    private boolean shouldShowRateByDate(long lastTimeRateMillis, long currentTimeMillis) {
        return lastTimeRateMillis != 0 && new DateTime(lastTimeRateMillis).isBefore(currentTimeMillis);
    }

    private void scheduleRateDialog(long milliseconds) {
        UserConfig.setRateDialogDueDate(Math.max(UserConfig.getRateDialogDueDate(), milliseconds));
    }

    private boolean isOldLogic() {
        return UserConfig.getRateAppCounter() > -1;
    }

    private void resetOldLogic() {
        UserConfig.setRateAppCounter(-1);
    }

    private boolean isFlagEnabled() {
        return TadoApplication.getTadoAppContext().getResources().getBoolean(C0676R.bool.rateApp);
    }

    public boolean showRateAppItem() {
        return isFlagEnabled() && shouldShowRateByDate(UserConfig.getRateDialogDueDate(), DateTime.now().getMillis()) && !UserConfig.getRateAppVoted();
    }

    public void shown(DateTime dateTime) {
        scheduleRateDialog(getDateMillisIfRecentlyRated(dateTime));
        Crashlytics.setBool("rateAppShown", true);
    }

    public void voted() {
        UserConfig.setRateAppVoted(true);
    }

    public void showRateAppDialog(FragmentManager supportFragmentManager) {
        new RateDialogFragment().show(supportFragmentManager, "rateDialog");
    }
}

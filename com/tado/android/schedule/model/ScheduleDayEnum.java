package com.tado.android.schedule.model;

import com.tado.C0676R;
import com.tado.android.app.TadoApplication;

public enum ScheduleDayEnum {
    MONDAY("MONDAY", 0),
    TUESDAY("TUESDAY", 1),
    WEDNESDAY("WEDNESDAY", 2),
    THURSDAY("THURSDAY", 3),
    FRIDAY("FRIDAY", 4),
    SATURDAY("SATURDAY", 5),
    SUNDAY("SUNDAY", 6),
    MONDAY_TO_SUNDAY("MONDAY_TO_SUNDAY", 0),
    MONDAY_TO_FRIDAY("MONDAY_TO_FRIDAY", 0);
    
    private String day;
    private int position;

    private ScheduleDayEnum(String day, int position) {
        this.day = day;
        this.position = position;
    }

    public String getDay() {
        return this.day;
    }

    public String getLocalizedDay() {
        if (this.day.equals(MONDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_mondayLabel);
        }
        if (this.day.equals(MONDAY_TO_SUNDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_monToSundLabel);
        }
        if (this.day.equals(MONDAY_TO_FRIDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_monToFriLabel);
        }
        if (this.day.equals(TUESDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_tuesdayLabel);
        }
        if (this.day.equals(WEDNESDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_wednesdayLabel);
        }
        if (this.day.equals(THURSDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_thursdayLabel);
        }
        if (this.day.equals(FRIDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_fridayLabel);
        }
        if (this.day.equals(SATURDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_saturdayLabel);
        }
        if (this.day.equals(SUNDAY.getDay())) {
            return TadoApplication.getTadoAppContext().getString(C0676R.string.smartSchedule_home_sundayLabel);
        }
        return "";
    }

    public int getPosition() {
        return this.position;
    }
}

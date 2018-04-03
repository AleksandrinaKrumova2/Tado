package com.tado.android.entities;

import java.util.Date;

public class DatePeriod {
    private Date end;
    private Date start;

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}

package com.tado.android.report.model;

public class ChartXValueRange {
    private long end;
    private long start;

    public ChartXValueRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isInRangeWithBoundaries(long x) {
        return x >= this.start && x <= this.end;
    }

    public boolean isInRangeExludingEnd(long x) {
        return x >= this.start && x < this.end;
    }
}

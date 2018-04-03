package com.wdullaer.materialdatetimepicker.time;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.wdullaer.materialdatetimepicker.time.Timepoint.TYPE;
import java.util.Arrays;
import java.util.TreeSet;

class DefaultTimepointLimiter implements TimepointLimiter {
    public static final Creator<DefaultTimepointLimiter> CREATOR = new C13191();
    private TreeSet<Timepoint> exclusiveSelectableTimes = new TreeSet();
    private TreeSet<Timepoint> mDisabledTimes = new TreeSet();
    private Timepoint mMaxTime;
    private Timepoint mMinTime;
    private TreeSet<Timepoint> mSelectableTimes = new TreeSet();

    static class C13191 implements Creator<DefaultTimepointLimiter> {
        C13191() {
        }

        public DefaultTimepointLimiter createFromParcel(Parcel in) {
            return new DefaultTimepointLimiter(in);
        }

        public DefaultTimepointLimiter[] newArray(int size) {
            return new DefaultTimepointLimiter[size];
        }
    }

    DefaultTimepointLimiter() {
    }

    public DefaultTimepointLimiter(Parcel in) {
        this.mMinTime = (Timepoint) in.readParcelable(Timepoint.class.getClassLoader());
        this.mMaxTime = (Timepoint) in.readParcelable(Timepoint.class.getClassLoader());
        this.mSelectableTimes.addAll(Arrays.asList(in.createTypedArray(Timepoint.CREATOR)));
        this.mDisabledTimes.addAll(Arrays.asList(in.createTypedArray(Timepoint.CREATOR)));
        this.exclusiveSelectableTimes = getExclusiveSelectableTimes(this.mSelectableTimes, this.mDisabledTimes);
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.mMinTime, flags);
        out.writeParcelable(this.mMaxTime, flags);
        out.writeTypedArray((Parcelable[]) this.mSelectableTimes.toArray(new Timepoint[this.mSelectableTimes.size()]), flags);
        out.writeTypedArray((Parcelable[]) this.mDisabledTimes.toArray(new Timepoint[this.mDisabledTimes.size()]), flags);
    }

    public int describeContents() {
        return 0;
    }

    void setMinTime(@NonNull Timepoint minTime) {
        if (this.mMaxTime == null || minTime.compareTo(this.mMaxTime) <= 0) {
            this.mMinTime = minTime;
            return;
        }
        throw new IllegalArgumentException("Minimum time must be smaller than the maximum time");
    }

    void setMaxTime(@NonNull Timepoint maxTime) {
        if (this.mMinTime == null || maxTime.compareTo(this.mMinTime) >= 0) {
            this.mMaxTime = maxTime;
            return;
        }
        throw new IllegalArgumentException("Maximum time must be greater than the minimum time");
    }

    void setSelectableTimes(@NonNull Timepoint[] selectableTimes) {
        this.mSelectableTimes.addAll(Arrays.asList(selectableTimes));
        this.exclusiveSelectableTimes = getExclusiveSelectableTimes(this.mSelectableTimes, this.mDisabledTimes);
    }

    void setDisabledTimes(@NonNull Timepoint[] disabledTimes) {
        this.mDisabledTimes.addAll(Arrays.asList(disabledTimes));
        this.exclusiveSelectableTimes = getExclusiveSelectableTimes(this.mSelectableTimes, this.mDisabledTimes);
    }

    @Nullable
    Timepoint getMinTime() {
        return this.mMinTime;
    }

    @Nullable
    Timepoint getMaxTime() {
        return this.mMaxTime;
    }

    @NonNull
    Timepoint[] getSelectableTimes() {
        return (Timepoint[]) this.mSelectableTimes.toArray(new Timepoint[this.mSelectableTimes.size()]);
    }

    @NonNull
    Timepoint[] getDisabledTimes() {
        return (Timepoint[]) this.mDisabledTimes.toArray(new Timepoint[this.mDisabledTimes.size()]);
    }

    private TreeSet<Timepoint> getExclusiveSelectableTimes(TreeSet<Timepoint> selectable, TreeSet<Timepoint> disabled) {
        TreeSet<Timepoint> output = new TreeSet(selectable);
        output.removeAll(disabled);
        return output;
    }

    public boolean isOutOfRange(@Nullable Timepoint current, int index, @NonNull TYPE resolution) {
        boolean z = false;
        if (current == null) {
            return false;
        }
        Timepoint floor;
        if (index == 0) {
            if (this.mMinTime != null && this.mMinTime.getHour() > current.getHour()) {
                return true;
            }
            if (this.mMaxTime != null && this.mMaxTime.getHour() + 1 <= current.getHour()) {
                return true;
            }
            if (!this.exclusiveSelectableTimes.isEmpty()) {
                floor = (Timepoint) this.exclusiveSelectableTimes.floor(current);
                if (current.equals((Timepoint) this.exclusiveSelectableTimes.ceiling(current), TYPE.HOUR) || current.equals(floor, TYPE.HOUR)) {
                    return false;
                }
                return true;
            } else if (this.mDisabledTimes.isEmpty() || resolution != TYPE.HOUR) {
                return false;
            } else {
                floor = (Timepoint) this.mDisabledTimes.floor(current);
                if (current.equals((Timepoint) this.mDisabledTimes.ceiling(current), TYPE.HOUR) || current.equals(floor, TYPE.HOUR)) {
                    z = true;
                }
                return z;
            }
        } else if (index != 1) {
            return isOutOfRange(current);
        } else {
            if (this.mMinTime != null && new Timepoint(this.mMinTime.getHour(), this.mMinTime.getMinute()).compareTo(current) > 0) {
                return true;
            }
            if (this.mMaxTime != null && new Timepoint(this.mMaxTime.getHour(), this.mMaxTime.getMinute(), 59).compareTo(current) < 0) {
                return true;
            }
            if (!this.exclusiveSelectableTimes.isEmpty()) {
                floor = (Timepoint) this.exclusiveSelectableTimes.floor(current);
                if (current.equals((Timepoint) this.exclusiveSelectableTimes.ceiling(current), TYPE.MINUTE) || current.equals(floor, TYPE.MINUTE)) {
                    return false;
                }
                return true;
            } else if (this.mDisabledTimes.isEmpty() || resolution != TYPE.MINUTE) {
                return false;
            } else {
                floor = (Timepoint) this.mDisabledTimes.floor(current);
                boolean ceilExclude = current.equals((Timepoint) this.mDisabledTimes.ceiling(current), TYPE.MINUTE);
                boolean floorExclude = current.equals(floor, TYPE.MINUTE);
                if (ceilExclude || floorExclude) {
                    z = true;
                }
                return z;
            }
        }
    }

    public boolean isOutOfRange(@NonNull Timepoint current) {
        if (this.mMinTime != null && this.mMinTime.compareTo(current) > 0) {
            return true;
        }
        if (this.mMaxTime != null && this.mMaxTime.compareTo(current) < 0) {
            return true;
        }
        if (this.exclusiveSelectableTimes.isEmpty()) {
            return this.mDisabledTimes.contains(current);
        }
        if (this.exclusiveSelectableTimes.contains(current)) {
            return false;
        }
        return true;
    }

    public boolean isAmDisabled() {
        Timepoint midday = new Timepoint(12);
        if (this.mMinTime != null && this.mMinTime.compareTo(midday) >= 0) {
            return true;
        }
        if (this.exclusiveSelectableTimes.isEmpty()) {
            return false;
        }
        return ((Timepoint) this.exclusiveSelectableTimes.first()).compareTo(midday) >= 0;
    }

    public boolean isPmDisabled() {
        Timepoint midday = new Timepoint(12);
        if (this.mMaxTime != null && this.mMaxTime.compareTo(midday) < 0) {
            return true;
        }
        if (this.exclusiveSelectableTimes.isEmpty()) {
            return false;
        }
        return ((Timepoint) this.exclusiveSelectableTimes.last()).compareTo(midday) < 0;
    }

    @NonNull
    public Timepoint roundToNearest(@NonNull Timepoint time, @Nullable TYPE type, @NonNull TYPE resolution) {
        if (this.mMinTime != null && this.mMinTime.compareTo(time) > 0) {
            return this.mMinTime;
        }
        if (this.mMaxTime != null && this.mMaxTime.compareTo(time) < 0) {
            return this.mMaxTime;
        }
        if (type == TYPE.SECOND) {
            return time;
        }
        Timepoint floor;
        if (!this.exclusiveSelectableTimes.isEmpty()) {
            floor = (Timepoint) this.exclusiveSelectableTimes.floor(time);
            Timepoint ceil = (Timepoint) this.exclusiveSelectableTimes.ceiling(time);
            if (floor == null || ceil == null) {
                Timepoint t;
                if (floor == null) {
                    t = ceil;
                } else {
                    t = floor;
                }
                if (type == null) {
                    return t;
                }
                if (t.getHour() != time.getHour()) {
                    return time;
                }
                if (type != TYPE.MINUTE || t.getMinute() == time.getMinute()) {
                    return t;
                }
                return time;
            }
            if (type == TYPE.HOUR) {
                if (floor.getHour() != time.getHour() && ceil.getHour() == time.getHour()) {
                    return ceil;
                }
                if (floor.getHour() == time.getHour() && ceil.getHour() != time.getHour()) {
                    return floor;
                }
                if (!(floor.getHour() == time.getHour() || ceil.getHour() == time.getHour())) {
                    return time;
                }
            }
            if (type == TYPE.MINUTE) {
                if (floor.getHour() != time.getHour() && ceil.getHour() != time.getHour()) {
                    return time;
                }
                if (floor.getHour() != time.getHour() && ceil.getHour() == time.getHour()) {
                    if (ceil.getMinute() != time.getMinute()) {
                        ceil = time;
                    }
                    return ceil;
                } else if (floor.getHour() == time.getHour() && ceil.getHour() != time.getHour()) {
                    if (floor.getMinute() != time.getMinute()) {
                        floor = time;
                    }
                    return floor;
                } else if (floor.getMinute() != time.getMinute() && ceil.getMinute() == time.getMinute()) {
                    return ceil;
                } else {
                    if (floor.getMinute() == time.getMinute() && ceil.getMinute() != time.getMinute()) {
                        return floor;
                    }
                    if (!(floor.getMinute() == time.getMinute() || ceil.getMinute() == time.getMinute())) {
                        return time;
                    }
                }
            }
            if (Math.abs(time.compareTo(floor)) >= Math.abs(time.compareTo(ceil))) {
                floor = ceil;
            }
            return floor;
        } else if (this.mDisabledTimes.isEmpty()) {
            return time;
        } else {
            if (type != null && type == resolution) {
                return time;
            }
            if (resolution == TYPE.SECOND) {
                if (this.mDisabledTimes.contains(time)) {
                    return searchValidTimePoint(time, type, resolution);
                }
                return time;
            } else if (resolution == TYPE.MINUTE) {
                floor = (Timepoint) this.mDisabledTimes.floor(time);
                ceilDisabled = time.equals((Timepoint) this.mDisabledTimes.ceiling(time), TYPE.MINUTE);
                floorDisabled = time.equals(floor, TYPE.MINUTE);
                if (ceilDisabled || floorDisabled) {
                    return searchValidTimePoint(time, type, resolution);
                }
                return time;
            } else if (resolution != TYPE.HOUR) {
                return time;
            } else {
                floor = (Timepoint) this.mDisabledTimes.floor(time);
                ceilDisabled = time.equals((Timepoint) this.mDisabledTimes.ceiling(time), TYPE.HOUR);
                floorDisabled = time.equals(floor, TYPE.HOUR);
                if (ceilDisabled || floorDisabled) {
                    return searchValidTimePoint(time, type, resolution);
                }
                return time;
            }
        }
    }

    private Timepoint searchValidTimePoint(@NonNull Timepoint time, @Nullable TYPE type, @NonNull TYPE resolution) {
        Timepoint forward = new Timepoint(time);
        Timepoint backward = new Timepoint(time);
        int iteration = 0;
        int resolutionMultiplier = 1;
        if (resolution == TYPE.MINUTE) {
            resolutionMultiplier = 60;
        }
        if (resolution == TYPE.SECOND) {
            resolutionMultiplier = 3600;
        }
        while (iteration < resolutionMultiplier * 24) {
            iteration++;
            forward.add(resolution, 1);
            backward.add(resolution, -1);
            if (type == null || forward.get(type) == time.get(type)) {
                Timepoint forwardFloor = (Timepoint) this.mDisabledTimes.floor(forward);
                if (!(forward.equals((Timepoint) this.mDisabledTimes.ceiling(forward), resolution) || forward.equals(forwardFloor, resolution))) {
                    return forward;
                }
            }
            if (type == null || backward.get(type) == time.get(type)) {
                Timepoint backwardFloor = (Timepoint) this.mDisabledTimes.floor(backward);
                if (!(backward.equals((Timepoint) this.mDisabledTimes.ceiling(backward), resolution) || backward.equals(backwardFloor, resolution))) {
                    return backward;
                }
            }
            if (type != null && backward.get(type) != time.get(type) && forward.get(type) != time.get(type)) {
                break;
            }
        }
        return time;
    }
}

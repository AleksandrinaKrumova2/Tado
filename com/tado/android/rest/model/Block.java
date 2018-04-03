package com.tado.android.rest.model;

import com.google.gson.annotations.SerializedName;
import com.tado.android.rest.model.installation.CoolingZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.utils.Constants;
import com.tado.android.utils.TimeUtils;

public class Block {
    private transient boolean deleteCandidate;
    @SerializedName("dayType")
    private String mDayType;
    @SerializedName("end")
    private String mEnd;
    private transient int mEndSeconds = -1;
    @SerializedName("geolocationOverride")
    private boolean mGeolocationOverride;
    private transient String mId;
    private transient boolean mInEditMode;
    @SerializedName("setting")
    private GenericZoneSetting mSetting;
    @SerializedName("start")
    private String mStart;
    private transient int mStartSeconds = -1;
    private transient String weekdaysType;

    public String getId() {
        return this.mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getWeekdaysType() {
        return this.weekdaysType;
    }

    public void setWeekdaysType(String weekdaysType) {
        this.weekdaysType = weekdaysType;
    }

    public String getDayType() {
        return this.mDayType;
    }

    public void setDayType(String dayType) {
        this.mDayType = dayType;
    }

    public String getStart() {
        if (this.mStartSeconds > -1) {
            return TimeUtils.convertSecondsToStringHourAndMinutes(this.mStartSeconds);
        }
        return null;
    }

    public String getEnd() {
        if (this.mEndSeconds > -1) {
            return TimeUtils.convertSecondsToStringHourAndMinutes(this.mEndSeconds);
        }
        return null;
    }

    public boolean isGeolocationOverride() {
        return this.mGeolocationOverride;
    }

    public void setGeolocationOverride(boolean geolocationOverride) {
        this.mGeolocationOverride = geolocationOverride;
    }

    public boolean isInEditMode() {
        return this.mInEditMode;
    }

    public void setInEditMode(boolean inEditMode) {
        this.mInEditMode = inEditMode;
    }

    public boolean isDeleteCandidate() {
        return this.deleteCandidate;
    }

    public void setDeleteCandidate(boolean deleteCandidate) {
        this.deleteCandidate = deleteCandidate;
    }

    public int getStartSeconds() {
        return this.mStartSeconds;
    }

    public void setStartSeconds(int startSeconds) {
        this.mStartSeconds = startSeconds;
        this.mStart = TimeUtils.convertSecondsToStringHourAndMinutes(startSeconds);
    }

    public int getEndSeconds() {
        if (this.mEndSeconds == 0) {
            return 86400;
        }
        return this.mEndSeconds;
    }

    public void setEndSeconds(int endSeconds) {
        if (endSeconds == 0) {
            this.mEndSeconds = 86400;
        } else {
            this.mEndSeconds = endSeconds;
        }
        this.mEnd = TimeUtils.convertSecondsToStringHourAndMinutes(this.mEndSeconds);
    }

    public GenericZoneSetting getSetting() {
        return this.mSetting;
    }

    public void setSetting(GenericZoneSetting setting) {
        this.mSetting = setting;
    }

    public void calculate() {
        if (this.mStart != null) {
            this.mStartSeconds = TimeUtils.convertStringHourAndMinutesToSeconds(this.mStart);
        }
        if (this.mEnd != null) {
            if (this.mEnd.equals("00:00")) {
                this.mEndSeconds = 86400;
            }
            this.mEndSeconds = TimeUtils.convertStringHourAndMinutesToSeconds(this.mEnd);
        }
    }

    public static Block copy(Block b) {
        Block block = new Block();
        block.setId(b.getId());
        block.setGeolocationOverride(b.isGeolocationOverride());
        block.setStartSeconds(b.getStartSeconds());
        block.setInEditMode(b.isInEditMode());
        block.setDeleteCandidate(b.isDeleteCandidate());
        block.setEndSeconds(b.getEndSeconds());
        block.setWeekdaysType(b.getWeekdaysType());
        block.setDayType(b.getDayType());
        block.setSetting(b.getSetting());
        return block;
    }

    public Block splitBlock(String newId) {
        int editBlockEnd = getEndSeconds();
        int end = getEndSeconds();
        if (end == 0) {
            end = 86400;
        }
        setEndSeconds(getStartSeconds() + ((((end - getStartSeconds()) / 300) / 2) * 300));
        setInEditMode(false);
        Block newBlock = new Block();
        newBlock.setSetting(new GenericZoneSetting());
        if (this.mSetting instanceof CoolingZoneSetting) {
            CoolingZoneSetting setting = new CoolingZoneSetting();
            setting.setFanSpeed(((CoolingZoneSetting) this.mSetting).getFanSpeed());
            setting.setMode(((CoolingZoneSetting) this.mSetting).getMode());
            setting.setSwing(((CoolingZoneSetting) this.mSetting).getSwing());
            newBlock.setSetting(setting);
        }
        newBlock.getSetting().setPower(getSetting().getPower());
        newBlock.getSetting().setType(getSetting().getType());
        newBlock.getSetting().setTemperature(getSetting().getTemperature());
        newBlock.setInEditMode(true);
        newBlock.setId(newId);
        newBlock.setGeolocationOverride(isGeolocationOverride());
        newBlock.setWeekdaysType(getWeekdaysType());
        newBlock.setDayType(getDayType());
        newBlock.setStartSeconds(getEndSeconds());
        newBlock.setEndSeconds(editBlockEnd);
        return newBlock;
    }

    public boolean canSplit() {
        return (getEndSeconds() - getStartSeconds()) / 2 >= Constants.TIMES_BLOCK_MIN_LENGTH_IN_SECONDS;
    }

    public String toString() {
        try {
            return String.format("id:%s %s -> %s delete:%b duration:%d", new Object[]{this.mId, this.mStart, this.mEnd, Boolean.valueOf(this.deleteCandidate), Integer.valueOf(this.mEndSeconds - this.mStartSeconds)});
        } catch (Exception e) {
            return e.toString();
        }
    }
}

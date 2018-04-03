package com.tado.android.user_radar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.C0676R;
import com.tado.android.controllers.ZoneController;
import com.tado.android.report.ChartUtils;
import com.tado.android.rest.model.MobileDevice;
import com.tado.android.rest.model.ZoneState;
import com.tado.android.times.view.model.ModeEnum;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.UserConfig;
import java.util.ArrayList;
import java.util.List;

public class UserRadarLayout extends RelativeLayout {
    private List<MobileDevice> atHomeUsers;
    private int mCenterX;
    private int mCenterY;
    private int mEdgeCircleRadius;
    private int mHeight;
    private Bitmap mHouseBitmap;
    @DrawableRes
    private Integer mHouseBitmapId;
    private int mInnerCircleRadius;
    private int mInvisibleCircleRadius;
    private List<MobileDevice> mMobileDevice;
    private int mOuterCircleRadius;
    private Paint mTempPaint;
    private int mTempX;
    private int mTempY;
    private ToolTipRelativeLayout mToolTipRelativeLayout;
    private List<UserLayout> mUserLayouts;
    private Paint mWhitePaint;
    private int mWidth;
    private List<MobileDevice> noGeoLocationUsers;
    private List<MobileDevice> otherUsers;
    private List<MobileDevice> staleUsersWithoutLocation;

    public UserRadarLayout(Context context) {
        super(context);
        init();
    }

    public UserRadarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserRadarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public UserRadarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        initDimensions();
        initResource();
        this.noGeoLocationUsers = new ArrayList();
        this.atHomeUsers = new ArrayList();
        this.staleUsersWithoutLocation = new ArrayList();
        this.otherUsers = new ArrayList();
    }

    private void initDimensions() {
        this.mHeight = getMeasuredHeight();
        this.mWidth = getMeasuredWidth();
        this.mCenterX = this.mWidth / 2;
        this.mCenterY = this.mHeight / 2;
        this.mInnerCircleRadius = (getHouseBitmap().getWidth() / 2) + ((int) getDIPValue(16.0f));
        this.mOuterCircleRadius = this.mInnerCircleRadius + ((int) (((double) this.mInnerCircleRadius) * 0.4d));
        this.mEdgeCircleRadius = this.mHeight >= this.mWidth ? this.mWidth / 2 : this.mHeight / 2;
        this.mInvisibleCircleRadius = this.mOuterCircleRadius + ((this.mEdgeCircleRadius - this.mOuterCircleRadius) / 2);
    }

    private void initResource() {
        this.mTempPaint = ChartUtils.getPaint("#33FFFFFF", getDIPValue(2.0f), Style.STROKE);
        this.mWhitePaint = ChartUtils.getPaint("#FFFFFF", getDIPValue(2.0f), Style.STROKE);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initDimensions();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private float getDIPValue(float value) {
        return getResources().getDisplayMetrics().density * value;
    }

    protected void onDraw(Canvas canvas) {
        drawHouse(canvas);
        drawCircle(canvas);
        super.onDraw(canvas);
    }

    private void drawHouse(Canvas canvas) {
        canvas.drawBitmap(getHouseBitmap(), (float) calcAbsoluteX(this.mCenterX, getHouseBitmap().getWidth()), (float) calcAbsoluteY(this.mCenterY, getHouseBitmap().getHeight()), this.mWhitePaint);
    }

    private Bitmap getHouseBitmap() {
        if (this.mHouseBitmap == null || isChristmasHouseRequired() || isNonChristmasHouseRequired()) {
            this.mHouseBitmapId = Integer.valueOf(isChristmasTime() ? C0676R.drawable.winter_house : C0676R.drawable.cooling_radar_house);
            this.mHouseBitmap = getBitmap(this.mHouseBitmapId.intValue());
        }
        return this.mHouseBitmap;
    }

    private boolean isNonChristmasHouseRequired() {
        return (this.mHouseBitmap == null || this.mHouseBitmapId.intValue() != C0676R.drawable.winter_house || isChristmasTime()) ? false : true;
    }

    private boolean isChristmasHouseRequired() {
        return this.mHouseBitmap != null && this.mHouseBitmapId.intValue() == C0676R.drawable.cooling_radar_house && isChristmasTime();
    }

    private Bitmap getBitmap(int drawableId) {
        return BitmapFactory.decodeResource(getContext().getResources(), drawableId);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle((float) this.mCenterX, (float) this.mCenterY, (float) this.mInnerCircleRadius, this.mTempPaint);
        canvas.drawCircle((float) this.mCenterX, (float) this.mCenterY, (float) this.mOuterCircleRadius, this.mTempPaint);
    }

    private int calcAbsoluteX(int centerX, int width) {
        return centerX - (width / 2);
    }

    private int calcAbsoluteY(int centerY, int height) {
        return centerY - (height / 2);
    }

    public int getAtHomeRightPositionX() {
        return this.mCenterX;
    }

    public int getAtHomeRightPositionY() {
        return this.mCenterY;
    }

    public int getAtHomeLeftPositionX() {
        return this.mCenterX - ((int) getDIPValue(48.0f));
    }

    public int getAtHomeLeftPositionY() {
        return this.mCenterY;
    }

    public int getAtHomeCenterPositionX(int widht) {
        return this.mCenterX - (widht / 2);
    }

    public int getAtHomeCenterPositionY(int height) {
        return this.mCenterY - (height / 2);
    }

    public int calculateUserPositionX(int width, double relativeDistanceFromHomeFence, double radians) {
        return ((int) ((Math.sin(radians) * ((double) getPositionFromRelativeDistanceFromHomeFence(relativeDistanceFromHomeFence))) + ((double) this.mCenterX))) - (width / 2);
    }

    public int calculateUserPositionY(int height, double relativeDistanceFromHomeFence, double radians) {
        return ((int) (((-1.0d * Math.cos(radians)) * ((double) getPositionFromRelativeDistanceFromHomeFence(relativeDistanceFromHomeFence))) + ((double) this.mCenterY))) - (height / 2);
    }

    public int getNoGeolocationPositionX() {
        return this.mWidth - ((int) getDIPValue(48.0f));
    }

    public int getStaleUsersWithoutLocationPositionX() {
        return (int) getDIPValue(0.0f);
    }

    public int getStaleUsersWithoutLocationPositionY() {
        return (int) getDIPValue(BitmapDescriptorFactory.HUE_ORANGE);
    }

    public int getNoGeolocationPositionY() {
        return (int) getDIPValue(BitmapDescriptorFactory.HUE_ORANGE);
    }

    private int getPositionFromRelativeDistanceFromHomeFence(double relativeDistanceFromHomeFence) {
        return this.mOuterCircleRadius + ((int) (((double) (this.mInvisibleCircleRadius - this.mOuterCircleRadius)) * relativeDistanceFromHomeFence));
    }

    public int getCenterX() {
        return this.mCenterX;
    }

    public int getCenterY() {
        return this.mCenterY;
    }

    public int getEdgeCircleRadius() {
        return this.mEdgeCircleRadius;
    }

    public int getInvisibleCircleRadius() {
        return this.mInvisibleCircleRadius;
    }

    public int getOuterCircleRadius() {
        return this.mOuterCircleRadius;
    }

    public int getInnerCircleRadius() {
        return this.mInnerCircleRadius;
    }

    public void setInvisibleCircleRadius(int invisibleCircleRadius) {
        this.mInvisibleCircleRadius = invisibleCircleRadius;
    }

    private void placeUsersAtHome() {
        if (this.atHomeUsers != null && this.atHomeUsers.size() != 0) {
            MobileDevice me = null;
            boolean singleUserAtHome = false;
            List<MobileDevice> staleUsers = new ArrayList();
            List<MobileDevice> normalUsers = new ArrayList();
            String name = "";
            int x = 0;
            int y = 0;
            UserIconEnum icon = UserIconEnum.SINGLE_USER;
            boolean isMe = false;
            boolean isStale = true;
            for (MobileDevice mobileDevice : this.atHomeUsers) {
                if (mobileDevice.getId() == UserConfig.getMobileDeviceId()) {
                    isMe = true;
                    me = mobileDevice;
                } else {
                    if (!mobileDevice.getLocation().isStale()) {
                        isStale = false;
                    }
                    if (mobileDevice.getLocation().isStale()) {
                        staleUsers.add(mobileDevice);
                    } else {
                        normalUsers.add(mobileDevice);
                    }
                }
            }
            if (this.atHomeUsers.size() == 1) {
                x = getAtHomeCenterPositionX((int) getDIPValue(48.0f));
                y = getAtHomeCenterPositionY((int) getDIPValue(48.0f));
                singleUserAtHome = true;
            }
            if (isMe) {
                if (!singleUserAtHome) {
                    x = getAtHomeRightPositionX();
                    y = getAtHomeRightPositionY();
                }
                List<MobileDevice> mobileDeviceList = new ArrayList();
                mobileDeviceList.add(me);
                addView(getUserLayout(getContext().getString(C0676R.string.components_radar_meLabel), x, y, UserIconEnum.ME, mobileDeviceList));
                if (this.atHomeUsers.size() > 1) {
                    if (this.atHomeUsers.size() > 2) {
                        if (isStale) {
                            icon = UserIconEnum.GROUP_OF_STALE_USERS;
                        } else {
                            icon = UserIconEnum.GROUP_OF_USERS;
                        }
                        name = "";
                    } else if (isStale) {
                        icon = UserIconEnum.SINGLE_STALE_USER;
                        name = ((MobileDevice) staleUsers.get(0)).getName();
                    } else {
                        icon = UserIconEnum.SINGLE_USER;
                        name = ((MobileDevice) normalUsers.get(0)).getName();
                    }
                    normalUsers.addAll(staleUsers);
                    addView(getUserLayout(name, getAtHomeLeftPositionX(), getAtHomeLeftPositionY(), icon, normalUsers));
                    return;
                }
                return;
            }
            if (staleUsers.size() > 0) {
                if (staleUsers.size() > 1) {
                    icon = UserIconEnum.GROUP_OF_STALE_USERS;
                    name = "";
                } else {
                    icon = UserIconEnum.SINGLE_STALE_USER;
                    name = ((MobileDevice) staleUsers.get(0)).getName();
                }
                if (!singleUserAtHome) {
                    x = getAtHomeRightPositionX();
                    y = getAtHomeRightPositionY();
                }
                addView(getUserLayout(name, x, y, icon, staleUsers));
            }
            if (normalUsers.size() > 0) {
                if (normalUsers.size() > 1) {
                    icon = UserIconEnum.GROUP_OF_USERS;
                    name = "";
                } else {
                    icon = UserIconEnum.SINGLE_USER;
                    name = ((MobileDevice) normalUsers.get(0)).getName();
                }
                if (!singleUserAtHome) {
                    x = getAtHomeLeftPositionX();
                    y = getAtHomeLeftPositionY();
                }
                addView(getUserLayout(name, x, y, icon, normalUsers));
            }
        }
    }

    private void placeStaleUsersWithoutLocation() {
        if (this.staleUsersWithoutLocation != null && this.staleUsersWithoutLocation.size() != 0) {
            UserIconEnum icon = UserIconEnum.SINGLE_STALE_USER;
            if (this.staleUsersWithoutLocation.size() > 1) {
                icon = UserIconEnum.GROUP_OF_STALE_USERS;
            }
            addView(getUserLayout("", getStaleUsersWithoutLocationPositionX(), getStaleUsersWithoutLocationPositionY(), icon, this.staleUsersWithoutLocation));
        }
    }

    private void placeNoGeolocationUsers() {
        if (this.noGeoLocationUsers != null && this.noGeoLocationUsers.size() != 0) {
            UserIconEnum icon = UserIconEnum.SINGLE_USER;
            if (this.noGeoLocationUsers.size() > 1) {
                icon = UserIconEnum.GROUP_OF_USERS;
            }
            addView(getUserLayout("", getNoGeolocationPositionX(), getNoGeolocationPositionY(), icon, this.noGeoLocationUsers));
        }
    }

    private void placeOtherUsers() {
        if (this.otherUsers != null && this.otherUsers.size() != 0) {
            List<RadarItemPosition> items = new ArrayList();
            items = calculateRadarItemPositions(this.otherUsers);
            if (items.size() > 1) {
                List<List<RadarItemPosition>> collidingClusters = collisionDetection(items);
                items = fanOutAndGroup(collidingClusters, items);
                List<List<RadarItemPosition>> postCollidingClusters = collisionDetection(items);
                if (postCollidingClusters.size() > 0) {
                    items = grouping(items, collidingClusters, postCollidingClusters);
                }
            }
            String name = "";
            for (RadarItemPosition itemPosition : items) {
                name = itemPosition.getName();
                if (itemPosition.isMe()) {
                    itemPosition.setIcon(UserIconEnum.ME);
                    name = getContext().getString(C0676R.string.components_radar_meLabel);
                } else if (itemPosition.getUsers() != null) {
                    for (MobileDevice mobileDevice : itemPosition.getUsers()) {
                        if (mobileDevice.getLocation() == null || mobileDevice.getLocation().isStale()) {
                            if (itemPosition.getUsers().size() > 1) {
                                itemPosition.setIcon(UserIconEnum.GROUP_OF_STALE_USERS);
                            } else {
                                itemPosition.setIcon(UserIconEnum.SINGLE_STALE_USER);
                            }
                        }
                    }
                }
                addView(getUserLayout(name, itemPosition.getX() - ((int) getDIPValue(24.0f)), itemPosition.getY() - ((int) getDIPValue(24.0f)) < 0 ? 0 : itemPosition.getY() - ((int) getDIPValue(24.0f)), itemPosition.getIcon(), itemPosition.getUsers()));
            }
        }
    }

    private void placeSanta() {
        if (isSantaTime()) {
            MobileDevice santa = new SantaMobileDevice();
            List<MobileDevice> santas = new ArrayList();
            int santaX = calculateUserPositionX((int) getDIPValue(48.0f), (double) getSantasRelativeDistanceFromHomeFence(), 0.0d);
            int santaY = calculateUserPositionY((int) getDIPValue(48.0f), (double) getSantasRelativeDistanceFromHomeFence(), 0.0d) + ((int) getDIPValue(24.0f));
            santas.add(santa);
            addView(getUserLayout(santa.getName(), santaX, santaY, UserIconEnum.SANTA, santas));
        }
    }

    private float getSantasRelativeDistanceFromHomeFence() {
        return (((float) 24) - ((float) TimeUtils.getCurrentDayOfMonthInHomeTimeZone())) / (((float) 24) * 1.0f);
    }

    private List<RadarItemPosition> grouping(List<RadarItemPosition> items, List<List<RadarItemPosition>> collidingClusters, List<List<RadarItemPosition>> list) {
        int radius = (int) getDIPValue(24.0f);
        List<RadarItemPosition> itemsToRemove = new ArrayList();
        int i = 0;
        while (i < items.size() - 1) {
            RadarItemPosition currentItem = (RadarItemPosition) items.get(i);
            int j = i + 1;
            while (j < items.size()) {
                RadarItemPosition collidingCandidateItem = (RadarItemPosition) items.get(j);
                if (!(circlesColliding(currentItem.getX(), currentItem.getY(), radius, collidingCandidateItem.getX(), collidingCandidateItem.getY()) == 0 || i == j)) {
                    if (currentItem.isMe() || collidingCandidateItem.isMe()) {
                        RadarItemPosition me;
                        if (currentItem.isMe()) {
                            me = currentItem;
                        } else {
                            me = collidingCandidateItem;
                            collidingCandidateItem = currentItem;
                        }
                        if (isMemeberOfCluster(collidingClusters, collidingCandidateItem)) {
                            items = groupItemInOwnCluster(collidingClusters, collidingCandidateItem, items);
                        } else {
                            items = groupItemInMeCluster(collidingClusters, me, collidingCandidateItem, items);
                        }
                    } else {
                        collidingCandidateItem.setX((collidingCandidateItem.getX() + currentItem.getX()) / 2);
                        collidingCandidateItem.setY((collidingCandidateItem.getY() + currentItem.getY()) / 2);
                        collidingCandidateItem.getUsers().addAll(currentItem.getUsers());
                        collidingCandidateItem.setIcon(UserIconEnum.GROUP_OF_USERS);
                        collidingCandidateItem.setName("");
                        itemsToRemove.add(currentItem);
                    }
                }
                j++;
            }
            i++;
        }
        items.removeAll(itemsToRemove);
        return items;
    }

    private List<RadarItemPosition> groupItemInOwnCluster(List<List<RadarItemPosition>> collidingClusters, RadarItemPosition collidingItem, List<RadarItemPosition> items) {
        for (List<RadarItemPosition> cluster : collidingClusters) {
            if (cluster.contains(collidingItem)) {
                for (RadarItemPosition item : cluster) {
                    if (item != collidingItem) {
                        item.getUsers().addAll(collidingItem.getUsers());
                        item.setName("");
                        item.setIcon(UserIconEnum.GROUP_OF_USERS);
                        break;
                    }
                }
                items.remove(collidingItem);
                return items;
            }
        }
        items.remove(collidingItem);
        return items;
    }

    private List<RadarItemPosition> groupItemInMeCluster(List<List<RadarItemPosition>> collidingClusters, RadarItemPosition me, RadarItemPosition collidingItem, List<RadarItemPosition> items) {
        for (List<RadarItemPosition> cluster : collidingClusters) {
            if (cluster.contains(me)) {
                for (RadarItemPosition item : cluster) {
                    if (item != me) {
                        item.getUsers().addAll(collidingItem.getUsers());
                        item.setName("");
                        item.setIcon(UserIconEnum.GROUP_OF_USERS);
                        break;
                    }
                }
                items.remove(collidingItem);
                return items;
            }
        }
        items.remove(collidingItem);
        return items;
    }

    private boolean isMemeberOfCluster(List<List<RadarItemPosition>> clusters, RadarItemPosition item) {
        for (List<RadarItemPosition> cluster : clusters) {
            if (cluster.contains(item)) {
                return true;
            }
        }
        return false;
    }

    private List<RadarItemPosition> fanOutAndGroup(List<List<RadarItemPosition>> clusters, List<RadarItemPosition> items) {
        int avgX = 0;
        int avgY = 0;
        int diameter = (int) getDIPValue(BitmapDescriptorFactory.HUE_YELLOW);
        RadarItemPosition centerOfCluster = null;
        RadarItemPosition left = null;
        RadarItemPosition right = null;
        boolean isMeCluster = false;
        for (List<RadarItemPosition> cluster : clusters) {
            for (RadarItemPosition radarItemPosition : cluster) {
                if (items.contains(radarItemPosition)) {
                    items.remove(radarItemPosition);
                }
                avgX += radarItemPosition.getX();
                avgY += radarItemPosition.getY();
                if (radarItemPosition.isMe()) {
                    isMeCluster = true;
                }
            }
            avgX /= cluster.size();
            avgY /= cluster.size();
            for (RadarItemPosition radarItemPosition2 : cluster) {
                if (isMeCluster) {
                    if (radarItemPosition2.isMe()) {
                        centerOfCluster = radarItemPosition2;
                        centerOfCluster.setX(avgX);
                        centerOfCluster.setY(avgY);
                        centerOfCluster.setName(getContext().getString(C0676R.string.components_radar_meLabel));
                        centerOfCluster.setIcon(UserIconEnum.ME);
                    }
                } else if (centerOfCluster == null) {
                    centerOfCluster = radarItemPosition2;
                    centerOfCluster.setX(avgX);
                    centerOfCluster.setY(avgY);
                }
                if (cluster.size() > 2 && right == null) {
                    right = radarItemPosition2;
                } else if (left == null) {
                    left = radarItemPosition2;
                    if (cluster.size() > 3) {
                        left.setName("");
                        left.setIcon(UserIconEnum.GROUP_OF_USERS);
                    }
                } else {
                    left.getUsers().addAll(radarItemPosition2.getUsers());
                }
            }
            int[] points = new int[4];
            points = intersectionBetweenTwoCircles(getCenterX(), getCenterY(), (float) getOuterCircleRadius(), avgX, avgY, diameter);
            if (centerOfCluster != null) {
                items.add(centerOfCluster);
            }
            if (left != null) {
                left.setX(points[0]);
                left.setY(points[1]);
                items.add(left);
            }
            if (right != null) {
                right.setX(points[2]);
                right.setY(points[3]);
                items.add(right);
            }
            isMeCluster = false;
            centerOfCluster = null;
            left = null;
            right = null;
            avgY = 0;
            avgX = 0;
        }
        return items;
    }

    private int[] intersectionBetweenTwoCircles(int x1, int y1, float r1, int x2, int y2, int r2) {
        int[] points = new int[4];
        float dx = (float) (x2 - x1);
        float dy = (float) (y2 - y1);
        float distance = (float) ((int) Math.sqrt(Math.pow((double) dx, 2.0d) + Math.pow((double) dy, 2.0d)));
        r1 = distance;
        float k = (float) (((Math.pow((double) distance, 2.0d) + Math.pow((double) r1, 2.0d)) - Math.pow((double) r2, 2.0d)) / ((double) (2.0f * distance)));
        float e = (float) Math.sqrt(Math.pow((double) r1, 2.0d) - Math.pow((double) k, 2.0d));
        points[0] = (int) ((((float) x1) + (((dx * k) / distance) * 1.0f)) + (((dy / distance) * 1.0f) * e));
        points[1] = (int) ((((float) y1) + (((dy * k) / distance) * 1.0f)) - (((dx / distance) * 1.0f) * e));
        points[2] = (int) ((((float) x1) + (((dx * k) / distance) * 1.0f)) - (((dy / distance) * 1.0f) * e));
        points[3] = (int) ((((float) y1) + (((dy * k) / distance) * 1.0f)) + (((dx / distance) * 1.0f) * e));
        return points;
    }

    private List<List<RadarItemPosition>> collisionDetection(List<RadarItemPosition> items) {
        List<List<RadarItemPosition>> collidingClusters = new ArrayList();
        int radius = (int) getDIPValue(24.0f);
        boolean addedToCluster = false;
        int i = 0;
        while (i < items.size() - 1) {
            RadarItemPosition currentItem = (RadarItemPosition) items.get(i);
            int j = i + 1;
            while (j < items.size()) {
                RadarItemPosition collidingCandidateItem = (RadarItemPosition) items.get(j);
                if (!(circlesColliding(currentItem.getX(), currentItem.getY(), radius, collidingCandidateItem.getX(), collidingCandidateItem.getY()) == 0 || i == j)) {
                    List<RadarItemPosition> cluster;
                    if (!collidingClusters.isEmpty()) {
                        for (List<RadarItemPosition> cluster2 : collidingClusters) {
                            if (!cluster2.contains(currentItem) || cluster2.contains(collidingCandidateItem)) {
                                if (cluster2.contains(currentItem) && cluster2.contains(collidingCandidateItem)) {
                                    addedToCluster = true;
                                    break;
                                }
                            } else {
                                cluster2.add(collidingCandidateItem);
                                addedToCluster = true;
                                break;
                            }
                        }
                    }
                    if (addedToCluster) {
                        addedToCluster = false;
                    } else {
                        cluster2 = new ArrayList();
                        cluster2.add(currentItem);
                        cluster2.add(collidingCandidateItem);
                        collidingClusters.add(cluster2);
                    }
                }
                j++;
            }
            i++;
        }
        return collidingClusters;
    }

    private int circlesColliding(int x1, int y1, int radius, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int distance = (int) Math.sqrt((double) ((dx * dx) + (dy * dy)));
        if (distance < radius * 2) {
            return Math.abs(radius - distance);
        }
        return 0;
    }

    private List<RadarItemPosition> calculateRadarItemPositions(List<MobileDevice> mobileDevices) {
        List<RadarItemPosition> items = new ArrayList();
        for (MobileDevice mobileDevice : mobileDevices) {
            if (mobileDevice.getLocation() != null) {
                UserIconEnum icon;
                if (mobileDevice.getLocation().isStale()) {
                    icon = UserIconEnum.SINGLE_STALE_USER;
                } else {
                    icon = UserIconEnum.SINGLE_USER;
                }
                int x = calculateUserPositionX((int) getDIPValue(48.0f), mobileDevice.getLocation().getRelativeDistanceFromHomeFence(), mobileDevice.getLocation().getBearing().getRadians()) + ((int) getDIPValue(24.0f));
                int y = calculateUserPositionY((int) getDIPValue(48.0f), mobileDevice.getLocation().getRelativeDistanceFromHomeFence(), mobileDevice.getLocation().getBearing().getRadians()) + ((int) getDIPValue(24.0f));
                List<MobileDevice> users = new ArrayList();
                users.add(mobileDevice);
                items.add(new RadarItemPosition(mobileDevice.getName(), mobileDevice.getId(), icon, users, x, y));
            }
        }
        return items;
    }

    private UserLayout getUserLayout(String name, int x, int y, UserIconEnum icon, List<MobileDevice> mobileDevices) {
        List<UserRadarItem> users = new ArrayList();
        for (MobileDevice mobileDevice : mobileDevices) {
            boolean stale;
            if (mobileDevice.getLocation() == null) {
                stale = true;
            } else {
                stale = mobileDevice.getLocation().isStale();
            }
            users.add(new UserRadarItem(mobileDevice.getName(), stale, mobileDevice.getSettings().isGeoTrackingEnabled(), mobileDevice.getLocation() != null));
        }
        UserLayout userLayout = new UserLayout(getContext(), name, icon, this.mToolTipRelativeLayout, users, getBackgroundColor());
        userLayout.setLayoutParams(new LayoutParams(-2, -2));
        userLayout.setX((float) x);
        userLayout.setY((float) y);
        this.mUserLayouts.add(userLayout);
        return userLayout;
    }

    private void clearUserRadarTooltips() {
        if (this.mUserLayouts != null) {
            for (UserLayout userLayout : this.mUserLayouts) {
                userLayout.closeTooltip();
            }
        }
    }

    public void dispatchTouchEventToUserRadar(MotionEvent ev) {
        if (this.mUserLayouts != null) {
            for (UserLayout userLayout : this.mUserLayouts) {
                userLayout.dispatchTouchEvent(ev);
                userLayout.closeTooltip();
            }
        }
    }

    public void updateUserRadar(List<MobileDevice> mobileDevices) {
        this.mMobileDevice = mobileDevices;
        if (this.mMobileDevice != null) {
            removeAllViews();
            this.mMobileDevice = mobileDevices;
            this.noGeoLocationUsers.clear();
            this.atHomeUsers.clear();
            this.staleUsersWithoutLocation.clear();
            this.otherUsers.clear();
            for (MobileDevice mobileDevice : this.mMobileDevice) {
                if (mobileDevice.getSettings() != null && !mobileDevice.getSettings().isGeoTrackingEnabled()) {
                    this.noGeoLocationUsers.add(mobileDevice);
                } else if (mobileDevice.getLocation() != null && mobileDevice.getLocation().isAtHome()) {
                    this.atHomeUsers.add(mobileDevice);
                } else if (mobileDevice.getSettings() != null && mobileDevice.getSettings().isGeoTrackingEnabled() && mobileDevice.getLocation() == null) {
                    this.staleUsersWithoutLocation.add(mobileDevice);
                } else {
                    this.otherUsers.add(mobileDevice);
                }
            }
            Snitcher.start().log(3, UserRadarLayout.class.getSimpleName(), "Radar width: " + this.mWidth + " Radar height: " + this.mHeight, new Object[0]);
            if (this.mWidth != 0 || this.mHeight != 0) {
                placeNoGeolocationUsers();
                placeUsersAtHome();
                placeStaleUsersWithoutLocation();
                placeOtherUsers();
                placeSanta();
            }
        }
    }

    private int getBackgroundColor() {
        ZoneState state = ZoneController.INSTANCE.getCurrentZoneState();
        if (state == null) {
            return C0676R.color.cooling_tooltip_home;
        }
        if (state.isTadoModeAway()) {
            return C0676R.color.cooling_tooltip_away;
        }
        if (state.getSetting() == null || state.getSetting().getMode() == null || !state.getSetting().getMode().equalsIgnoreCase(ModeEnum.HEAT.getMode())) {
            return C0676R.color.cooling_tooltip_home;
        }
        return C0676R.color.cooling_tooltip_home_heat_mode;
    }

    public void updateMobileDevices(List<MobileDevice> mobileDevices) {
        clearUserRadarTooltips();
        this.mUserLayouts = new ArrayList();
        updateUserRadar(mobileDevices);
    }

    public void setTooltipRelativeLayout(ToolTipRelativeLayout tooltipRelativeLayout) {
        this.mToolTipRelativeLayout = tooltipRelativeLayout;
    }

    private boolean isXmasTime(int startDay, int endDay) {
        int day = TimeUtils.getCurrentDayOfMonthInHomeTimeZone();
        return UserConfig.isChristmasModeEnabled().booleanValue() && 12 == TimeUtils.getMonthOfYearInHomeTimeZone() && day >= startDay && day <= endDay;
    }

    private boolean isChristmasTime() {
        return isXmasTime(24, 26);
    }

    private boolean isSantaTime() {
        return isXmasTime(1, 24);
    }
}

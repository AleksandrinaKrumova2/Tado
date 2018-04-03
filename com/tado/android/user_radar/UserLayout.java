package com.tado.android.user_radar;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTip.AnimationType;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;
import com.tado.C0676R;
import com.tado.android.report.ChartUtils;
import com.tado.android.utils.Constants;
import java.util.List;

public class UserLayout extends RelativeLayout {
    private int mBackgroundColor;
    private int mFixedHeight;
    private int mFixedWidth;
    private UserIconEnum mIcon;
    private String mName;
    private ToolTipRelativeLayout mToolTipRelativeLayout;
    private ToolTipView mToolTipView;
    private List<UserRadarItem> mUsers;
    private int touchPadding = ((int) ChartUtils.getDIPValue(8.0f));

    class C12331 implements OnClickListener {
        C12331() {
        }

        public void onClick(View v) {
            if (UserLayout.this.mToolTipView != null) {
                UserLayout.this.mToolTipView.remove();
                UserLayout.this.mToolTipView = null;
                return;
            }
            boolean z;
            boolean maxLimitExceded;
            Paint paint;
            String noLocationText;
            float noLocationWidth;
            String geolocationDisabledText;
            float geolocationDisabledWidth;
            String noRecentLocation;
            float noRecentLocationWidth;
            int maxWidth;
            View vi;
            boolean hasNoRecentLocation;
            boolean hasImage;
            boolean geolocationDisabled;
            boolean hasNoLocation;
            ToolTip toolTip;
            View tooltipLayout = LayoutInflater.from(UserLayout.this.getContext()).inflate(C0676R.layout.user_radar_tooltip_layout, null);
            ListView userLayoutTooltipList = (ListView) tooltipLayout.findViewById(C0676R.id.user_radar_layout_tooltip_list_layout);
            Context context = UserLayout.this.getContext();
            List access$100 = UserLayout.this.mUsers;
            int access$200 = UserLayout.this.mBackgroundColor;
            if (((UserRadarItem) UserLayout.this.mUsers.get(0)).geolocationEnabled) {
                if (!UserLayout.this.isPhantom((UserRadarItem) UserLayout.this.mUsers.get(0))) {
                    z = false;
                    userLayoutTooltipList.setAdapter(new UserRadarTooltipAdapter(context, C0676R.layout.user_radar_tooltip_list_item, access$100, access$200, z));
                    maxLimitExceded = UserLayout.this.mUsers.size() <= 5;
                    paint = ChartUtils.getPaint((int) C0676R.color.white);
                    paint.setTextSize(ChartUtils.getDIPValue(Constants.MAX_OFFSET_CELSIUS));
                    noLocationText = UserLayout.this.getContext().getString(C0676R.string.components_radar_noLocationLabel);
                    noLocationWidth = paint.measureText(noLocationText, 0, noLocationText.length());
                    geolocationDisabledText = UserLayout.this.getContext().getString(C0676R.string.components_radar_locationNotUsedLabel);
                    geolocationDisabledWidth = paint.measureText(geolocationDisabledText, 0, geolocationDisabledText.length());
                    noRecentLocation = UserLayout.this.getContext().getString(C0676R.string.components_radar_noRecentLocation);
                    noRecentLocationWidth = paint.measureText(noRecentLocation, 0, noRecentLocation.length());
                    maxWidth = 0;
                    vi = LayoutInflater.from(UserLayout.this.getContext()).inflate(C0676R.layout.user_radar_tooltip_list_item, null);
                    paint.setTextSize(ChartUtils.getDIPValue(13.0f));
                    hasNoRecentLocation = false;
                    hasImage = false;
                    geolocationDisabled = false;
                    hasNoLocation = false;
                    for (UserRadarItem item : UserLayout.this.mUsers) {
                        if (UserLayout.this.mUsers.size() <= 1) {
                            vi.findViewById(C0676R.id.user_radar_tooltip_list_item_icon).setVisibility(0);
                            hasImage = true;
                        } else {
                            vi.findViewById(C0676R.id.user_radar_tooltip_list_item_icon).setVisibility(8);
                        }
                        if (item.isStale()) {
                            vi.findViewById(C0676R.id.user_radar_tooltip_list_item_info).setVisibility(8);
                        } else {
                            vi.findViewById(C0676R.id.user_radar_tooltip_list_item_info).setVisibility(0);
                            hasNoRecentLocation = true;
                        }
                        if (!item.geolocationEnabled) {
                            geolocationDisabled = true;
                        }
                        if (!item.isHasLocation()) {
                            hasNoLocation = true;
                        }
                        ((TextView) vi.findViewById(C0676R.id.user_radar_tooltip_list_item_name)).setText(item.getName());
                        maxWidth = Math.max(maxWidth, (int) paint.measureText(item.getName(), 0, item.getName().length()));
                    }
                    if (hasNoLocation) {
                        maxWidth = Math.max(maxWidth, (int) noLocationWidth);
                    }
                    if (hasNoRecentLocation) {
                        maxWidth = Math.max(maxWidth, (int) noRecentLocationWidth);
                    }
                    if (geolocationDisabled) {
                        maxWidth = Math.max(maxWidth, (int) geolocationDisabledWidth);
                    }
                    if (hasImage) {
                        maxWidth += (int) ChartUtils.getDIPValue(8.0f);
                    } else {
                        maxWidth += (int) ChartUtils.getDIPValue(52.0f);
                    }
                    maxWidth += (int) ChartUtils.getDIPValue(20.0f);
                    toolTip = new ToolTip().withText(UserLayout.this.mName).withColor(ContextCompat.getColor(UserLayout.this.getContext(), UserLayout.this.mBackgroundColor)).withContentView(tooltipLayout).withShadow().withAnimationType(AnimationType.NONE);
                    toolTip.setItemsLimitExceded(maxLimitExceded);
                    UserLayout.this.mToolTipView = UserLayout.this.mToolTipRelativeLayout.showToolTipForView(toolTip, v);
                    UserLayout.this.mToolTipView.getLayoutParams().width = maxWidth;
                }
            }
            z = true;
            userLayoutTooltipList.setAdapter(new UserRadarTooltipAdapter(context, C0676R.layout.user_radar_tooltip_list_item, access$100, access$200, z));
            if (UserLayout.this.mUsers.size() <= 5) {
            }
            paint = ChartUtils.getPaint((int) C0676R.color.white);
            paint.setTextSize(ChartUtils.getDIPValue(Constants.MAX_OFFSET_CELSIUS));
            noLocationText = UserLayout.this.getContext().getString(C0676R.string.components_radar_noLocationLabel);
            noLocationWidth = paint.measureText(noLocationText, 0, noLocationText.length());
            geolocationDisabledText = UserLayout.this.getContext().getString(C0676R.string.components_radar_locationNotUsedLabel);
            geolocationDisabledWidth = paint.measureText(geolocationDisabledText, 0, geolocationDisabledText.length());
            noRecentLocation = UserLayout.this.getContext().getString(C0676R.string.components_radar_noRecentLocation);
            noRecentLocationWidth = paint.measureText(noRecentLocation, 0, noRecentLocation.length());
            maxWidth = 0;
            vi = LayoutInflater.from(UserLayout.this.getContext()).inflate(C0676R.layout.user_radar_tooltip_list_item, null);
            paint.setTextSize(ChartUtils.getDIPValue(13.0f));
            hasNoRecentLocation = false;
            hasImage = false;
            geolocationDisabled = false;
            hasNoLocation = false;
            for (UserRadarItem item2 : UserLayout.this.mUsers) {
                if (UserLayout.this.mUsers.size() <= 1) {
                    vi.findViewById(C0676R.id.user_radar_tooltip_list_item_icon).setVisibility(8);
                } else {
                    vi.findViewById(C0676R.id.user_radar_tooltip_list_item_icon).setVisibility(0);
                    hasImage = true;
                }
                if (item2.isStale()) {
                    vi.findViewById(C0676R.id.user_radar_tooltip_list_item_info).setVisibility(8);
                } else {
                    vi.findViewById(C0676R.id.user_radar_tooltip_list_item_info).setVisibility(0);
                    hasNoRecentLocation = true;
                }
                if (item2.geolocationEnabled) {
                    geolocationDisabled = true;
                }
                if (!item2.isHasLocation()) {
                    hasNoLocation = true;
                }
                ((TextView) vi.findViewById(C0676R.id.user_radar_tooltip_list_item_name)).setText(item2.getName());
                maxWidth = Math.max(maxWidth, (int) paint.measureText(item2.getName(), 0, item2.getName().length()));
            }
            if (hasNoLocation) {
                maxWidth = Math.max(maxWidth, (int) noLocationWidth);
            }
            if (hasNoRecentLocation) {
                maxWidth = Math.max(maxWidth, (int) noRecentLocationWidth);
            }
            if (geolocationDisabled) {
                maxWidth = Math.max(maxWidth, (int) geolocationDisabledWidth);
            }
            if (hasImage) {
                maxWidth += (int) ChartUtils.getDIPValue(8.0f);
            } else {
                maxWidth += (int) ChartUtils.getDIPValue(52.0f);
            }
            maxWidth += (int) ChartUtils.getDIPValue(20.0f);
            toolTip = new ToolTip().withText(UserLayout.this.mName).withColor(ContextCompat.getColor(UserLayout.this.getContext(), UserLayout.this.mBackgroundColor)).withContentView(tooltipLayout).withShadow().withAnimationType(AnimationType.NONE);
            toolTip.setItemsLimitExceded(maxLimitExceded);
            UserLayout.this.mToolTipView = UserLayout.this.mToolTipRelativeLayout.showToolTipForView(toolTip, v);
            UserLayout.this.mToolTipView.getLayoutParams().width = maxWidth;
        }
    }

    public UserLayout(Context context, String name, UserIconEnum icon, ToolTipRelativeLayout toolTipRelativeLayout, List<UserRadarItem> users, int backgroundColor) {
        super(context);
        this.mName = name;
        this.mIcon = icon;
        this.mToolTipRelativeLayout = toolTipRelativeLayout;
        this.mUsers = users;
        this.mBackgroundColor = C0676R.color.white;
        initView();
    }

    public UserLayout(Context context) {
        super(context);
        initView();
    }

    public UserLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public UserLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public UserLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        this.mFixedHeight = (int) ChartUtils.getDIPValue(48.0f);
        this.mFixedWidth = (int) ChartUtils.getDIPValue(48.0f);
        View.inflate(getContext(), C0676R.layout.user_radar_layout, this);
        ImageView userIcon = (ImageView) findViewById(C0676R.id.user_icon);
        ImageView userCircle = (ImageView) findViewById(C0676R.id.user_circle);
        TextView userName = (TextView) findViewById(C0676R.id.user_name);
        ((RelativeLayout) findViewById(C0676R.id.user_image_clickable)).setOnClickListener(new C12331());
        userName.setText(this.mName);
        if (UserIconEnum.ME == this.mIcon) {
            userCircle.setVisibility(0);
        } else {
            userCircle.setVisibility(4);
        }
        if (UserIconEnum.SINGLE_USER == this.mIcon) {
            userIcon.setImageResource(C0676R.drawable.cooling_radar_one_person);
        } else if (UserIconEnum.GROUP_OF_USERS == this.mIcon) {
            userIcon.setImageResource(C0676R.drawable.cooling_radar_two_persons);
        } else if (UserIconEnum.SINGLE_STALE_USER == this.mIcon) {
            userIcon.setImageResource(C0676R.drawable.cooling_radar_one_person_questionmark);
        } else if (UserIconEnum.GROUP_OF_STALE_USERS == this.mIcon) {
            userIcon.setImageResource(C0676R.drawable.cooling_radar_two_persons_questionmark);
        } else if (UserIconEnum.SANTA == this.mIcon) {
            userIcon.setImageResource(C0676R.drawable.santa);
            userName.setVisibility(4);
        }
        if (this.mUsers == null) {
            return;
        }
        if (!((UserRadarItem) this.mUsers.get(0)).geolocationEnabled || (((UserRadarItem) this.mUsers.get(0)).geolocationEnabled && !((UserRadarItem) this.mUsers.get(0)).isHasLocation())) {
            userIcon.setAlpha(0.3f);
        }
    }

    private boolean isPhantom(UserRadarItem userItem) {
        return userItem.isGeolocationEnabled() && !userItem.isHasLocation();
    }

    public int getFixedWidth() {
        return this.mFixedWidth;
    }

    public int getFixedHeight() {
        return this.mFixedHeight;
    }

    public void closeTooltip() {
        if (this.mToolTipView != null) {
            this.mToolTipView.remove();
            this.mToolTipView = null;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mToolTipView != null && (event.getX() < this.mToolTipView.getX() || event.getX() > this.mToolTipView.getX() + ((float) this.mToolTipView.getWidth()) || event.getY() < this.mToolTipView.getY() - ((float) this.touchPadding) || event.getY() > (this.mToolTipView.getY() + ((float) this.mToolTipView.getHeight())) + ((float) this.touchPadding))) {
            closeTooltip();
        }
        return super.onTouchEvent(event);
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
    }
}

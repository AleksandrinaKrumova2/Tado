package com.tado.android.user_radar;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;
import java.util.List;

public class UserRadarTooltipAdapter extends ArrayAdapter<UserRadarItem> {
    int backgroundColor;
    List<UserRadarItem> items;
    boolean noGeoLocation;
    boolean singleUser = true;

    private class UserTooltipView {
        ImageView icon;
        TextView info;
        TextView name;

        private UserTooltipView() {
        }

        public TextView getName() {
            return this.name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getInfo() {
            return this.info;
        }

        public void setInfo(TextView info) {
            this.info = info;
        }

        public ImageView getIcon() {
            return this.icon;
        }

        public void setIcon(ImageView icon) {
            this.icon = icon;
        }
    }

    public UserRadarTooltipAdapter(Context context, int resource, List<UserRadarItem> items, int backgroundColor, boolean noGeoLocation) {
        boolean z = true;
        super(context, resource);
        this.items = items;
        this.backgroundColor = backgroundColor;
        if (items.size() > 1) {
            z = false;
        }
        this.singleUser = z;
        this.noGeoLocation = noGeoLocation;
    }

    public int getCount() {
        return this.noGeoLocation ? this.items.size() + 1 : this.items.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.noGeoLocation && position == 0) {
            return LayoutInflater.from(getContext()).inflate(C0676R.layout.user_radar_tooltip_title_layout, parent, false);
        }
        UserTooltipView userTooltipView;
        if (this.noGeoLocation) {
            position--;
        }
        UserRadarItem item = (UserRadarItem) this.items.get(position);
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(getContext()).inflate(C0676R.layout.user_radar_tooltip_list_item, parent, false);
            userTooltipView = new UserTooltipView();
            userTooltipView.setIcon((ImageView) convertView.findViewById(C0676R.id.user_radar_tooltip_list_item_icon));
            userTooltipView.setName((TextView) convertView.findViewById(C0676R.id.user_radar_tooltip_list_item_name));
            userTooltipView.setInfo((TextView) convertView.findViewById(C0676R.id.user_radar_tooltip_list_item_info));
            convertView.setTag(userTooltipView);
        } else {
            userTooltipView = (UserTooltipView) convertView.getTag();
        }
        userTooltipView.getName().setText(item.getName());
        if (!item.isStale() || this.noGeoLocation) {
            userTooltipView.getInfo().setVisibility(8);
        } else {
            userTooltipView.getInfo().setVisibility(0);
            if (item.isHasLocation()) {
                userTooltipView.getInfo().setText(getContext().getResources().getString(C0676R.string.components_radar_noRecentLocation));
            } else {
                userTooltipView.getInfo().setText(getContext().getResources().getString(C0676R.string.components_radar_noLocationLabel));
            }
        }
        if (this.singleUser) {
            userTooltipView.getIcon().setVisibility(8);
        } else {
            userTooltipView.getIcon().setVisibility(0);
            if (item.isStale()) {
                userTooltipView.getIcon().setImageResource(C0676R.drawable.cooling_radar_one_person_questionmark);
            } else {
                userTooltipView.getIcon().setImageResource(C0676R.drawable.cooling_radar_one_person);
            }
        }
        userTooltipView.getIcon().setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.cooling_tooltip_text), Mode.SRC_ATOP);
        if (!item.geolocationEnabled) {
            userTooltipView.getIcon().setAlpha(0.3f);
        }
        convertView.setBackgroundColor(ContextCompat.getColor(getContext(), this.backgroundColor));
        return convertView;
    }
}

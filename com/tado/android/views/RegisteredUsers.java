package com.tado.android.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.rest.model.MobileDevice;
import java.util.ArrayList;
import java.util.Iterator;

public class RegisteredUsers {
    public RegisteredUsersOnClickListener onClickListener;

    public RegisteredUsers(Context context, FrameLayout view, ArrayList<MobileDevice> mobileDevices, final RegisteredUsersOnClickListener onClickListener) {
        LinearLayout linLayout = new LinearLayout(context);
        linLayout.setOrientation(1);
        LayoutParams linLayoutParam = new LayoutParams(-1, -2);
        linLayout.setGravity(3);
        linLayout.setLayoutParams(linLayoutParam);
        view.addView(linLayout);
        Iterator it = mobileDevices.iterator();
        while (it.hasNext()) {
            final MobileDevice mobileDevice = (MobileDevice) it.next();
            View customView = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0676R.layout.registered_users, null);
            ((TextView) customView.findViewById(C0676R.id.user_name)).setText(mobileDevice.getName());
            customView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    onClickListener.OnClickListener(mobileDevice);
                }
            });
            linLayout.addView(customView);
        }
    }
}

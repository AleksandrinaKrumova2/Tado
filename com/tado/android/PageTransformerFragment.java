package com.tado.android;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import com.tado.C0676R;

public class PageTransformerFragment {
    public static void doTransformation(int page, Activity activity) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(activity, C0676R.anim.fade_in);
        RelativeLayout text1;
        RelativeLayout text2;
        RelativeLayout text3;
        switch (page) {
            case 0:
                text1 = (RelativeLayout) activity.findViewById(C0676R.id.text1);
                text2 = (RelativeLayout) activity.findViewById(C0676R.id.text2);
                text3 = (RelativeLayout) activity.findViewById(C0676R.id.text3);
                text1.startAnimation(fadeInAnimation);
                text1.setVisibility(0);
                text2.setVisibility(4);
                text3.setVisibility(4);
                return;
            case 1:
                text1 = (RelativeLayout) activity.findViewById(C0676R.id.text1);
                text2 = (RelativeLayout) activity.findViewById(C0676R.id.text2);
                text3 = (RelativeLayout) activity.findViewById(C0676R.id.text3);
                text2.startAnimation(fadeInAnimation);
                text2.setVisibility(0);
                text1.setVisibility(4);
                text3.setVisibility(4);
                return;
            case 2:
                text1 = (RelativeLayout) activity.findViewById(C0676R.id.text1);
                text2 = (RelativeLayout) activity.findViewById(C0676R.id.text2);
                text3 = (RelativeLayout) activity.findViewById(C0676R.id.text3);
                text3.startAnimation(fadeInAnimation);
                text3.setVisibility(0);
                text2.setVisibility(4);
                text1.setVisibility(4);
                return;
            default:
                return;
        }
    }
}

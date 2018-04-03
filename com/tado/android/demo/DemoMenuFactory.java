package com.tado.android.demo;

import android.support.v7.app.AppCompatActivity;
import com.tado.C0676R;
import com.tado.android.adapters.demo.DemoBottomSheetCallItem;
import com.tado.android.adapters.demo.DemoBottomSheetExitItem;
import com.tado.android.adapters.demo.DemoBottomSheetItem;
import com.tado.android.adapters.demo.DemoBottomSheetURLItem;
import java.util.ArrayList;
import java.util.List;

public class DemoMenuFactory {
    public static List<DemoBottomSheetItem> createDemoListEntries(AppCompatActivity activity) {
        List<DemoBottomSheetItem> items = new ArrayList();
        items.add(new DemoBottomSheetCallItem(activity.getString(C0676R.string.demoMode_menu_callUsButton), C0676R.drawable.ic_demo_menu_call_us, activity));
        items.add(new DemoBottomSheetURLItem(activity.getString(C0676R.string.demoMode_menu_requestConsultationButton), C0676R.drawable.ic_demo_menu_consultation, activity.getString(C0676R.string.demoMode_menu_requestConsultationURL), activity, "requestConsultation"));
        items.add(new DemoBottomSheetURLItem(activity.getString(C0676R.string.demoMode_menu_learnMoreButton), C0676R.drawable.ic_demo_menu_learn_more, activity.getString(C0676R.string.demoMode_menu_learnMoreURL), activity, "learnMore"));
        items.add(new DemoBottomSheetURLItem(activity.getString(C0676R.string.demoMode_menu_getTadoButton), C0676R.drawable.ic_demo_menu_get_tado, activity.getString(C0676R.string.demoMode_menu_getTadoURL), activity, "getTado"));
        items.add(new DemoBottomSheetExitItem(activity.getString(C0676R.string.demoMode_menu_exitButton), C0676R.drawable.ic_demo_menu_exit, activity));
        return items;
    }
}

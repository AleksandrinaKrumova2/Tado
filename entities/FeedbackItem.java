package com.tado.android.entities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.tado.C0676R;
import com.tado.android.menu.ActionItem.Action;

public class FeedbackItem implements Action {
    public void performAction(Context context) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(context.getString(C0676R.string.menu_feedbackLink))));
    }
}

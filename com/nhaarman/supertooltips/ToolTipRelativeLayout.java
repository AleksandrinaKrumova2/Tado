package com.nhaarman.supertooltips;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.nhaarman.supertooltips.exception.NoOverflowMenuRuntimeException;
import com.nhaarman.supertooltips.exception.NoTitleViewRuntimeException;
import com.nhaarman.supertooltips.exception.ViewNotFoundRuntimeException;

public class ToolTipRelativeLayout extends RelativeLayout {
    public static final String ACTION_BAR = "action_bar";
    public static final String ACTION_BAR_TITLE = "action_bar_title";
    public static final String ACTION_MENU_VIEW = "ActionMenuView";
    public static final String ANDROID = "android";
    public static final String ID = "id";
    public static final String OVERFLOW_MENU_BUTTON = "OverflowMenuButton";

    public ToolTipRelativeLayout(Context context) {
        super(context);
    }

    public ToolTipRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolTipRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ToolTipView showToolTipForView(ToolTip toolTip, View view) {
        ToolTipView toolTipView = new ToolTipView(getContext(), toolTip.isItemsLimitExceded());
        toolTipView.setToolTip(toolTip, view);
        addView(toolTipView);
        return toolTipView;
    }

    public ToolTipView showToolTipForViewResId(Activity activity, ToolTip toolTip, int resId) {
        ToolTipView toolTipView = new ToolTipView(getContext());
        View view = activity.getWindow().getDecorView().findViewById(resId);
        if (view == null) {
            throw new ViewNotFoundRuntimeException();
        }
        toolTipView.setToolTip(toolTip, view);
        addView(toolTipView);
        return toolTipView;
    }

    @TargetApi(11)
    public ToolTipView showToolTipForActionBarHome(Activity activity, ToolTip toolTip) {
        return showToolTipForViewResId(activity, toolTip, 16908332);
    }

    @TargetApi(11)
    public ToolTipView showToolTipForActionBarTitle(Activity activity, ToolTip toolTip) {
        int titleResId = Resources.getSystem().getIdentifier(ACTION_BAR_TITLE, ID, "android");
        if (titleResId != 0) {
            return showToolTipForViewResId(activity, toolTip, titleResId);
        }
        throw new NoTitleViewRuntimeException();
    }

    @TargetApi(11)
    public ToolTipView showToolTipForActionBarOverflowMenu(Activity activity, ToolTip toolTip) {
        return showToolTipForView(toolTip, findActionBarOverflowMenuView(activity));
    }

    @TargetApi(11)
    private static View findActionBarOverflowMenuView(Activity activity) {
        int i;
        ViewGroup actionBarView = (ViewGroup) ((ViewGroup) activity.getWindow().getDecorView()).findViewById(Resources.getSystem().getIdentifier(ACTION_BAR, ID, "android"));
        ViewGroup actionMenuView = null;
        int actionBarViewChildCount = actionBarView.getChildCount();
        for (i = 0; i < actionBarViewChildCount; i++) {
            if (actionBarView.getChildAt(i).getClass().getSimpleName().equals(ACTION_MENU_VIEW)) {
                actionMenuView = (ViewGroup) actionBarView.getChildAt(i);
            }
        }
        if (actionMenuView == null) {
            throw new NoOverflowMenuRuntimeException();
        }
        int actionMenuChildCount = actionMenuView.getChildCount();
        View overflowMenuButton = null;
        for (i = 0; i < actionMenuChildCount; i++) {
            if (actionMenuView.getChildAt(i).getClass().getSimpleName().equals(OVERFLOW_MENU_BUTTON)) {
                overflowMenuButton = actionMenuView.getChildAt(i);
            }
        }
        if (overflowMenuButton != null) {
            return overflowMenuButton;
        }
        throw new NoOverflowMenuRuntimeException();
    }
}

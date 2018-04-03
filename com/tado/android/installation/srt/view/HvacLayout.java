package com.tado.android.installation.srt.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.tado.C0676R;
import com.tado.android.installation.srt.common.HvacMarkdownConverter;
import com.tado.android.rest.model.hvac.ContentTypeEnum;
import com.tado.android.rest.model.hvac.StateLookup.StateLookupEnum;
import com.tado.android.rest.model.hvac.SubStep;
import com.wdullaer.materialdatetimepicker.Utils;
import java.util.List;

public class HvacLayout extends RelativeLayout {
    private LinearLayout devicePropertyPanelLayout;
    private List<SubStep> mSubSteps;
    private LinearLayout mainContentLayout;
    private TextView titleView;

    public HvacLayout(Context context) {
        super(context);
        init();
    }

    public HvacLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HvacLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HvacLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        int padding = Utils.dpToPx(16.0f, getResources());
        this.devicePropertyPanelLayout = new LinearLayout(getContext());
        LayoutParams devicePropertyPanelParams = new LayoutParams(-1, -2);
        this.devicePropertyPanelLayout.setId(C0676R.id.bottom_bar);
        this.devicePropertyPanelLayout.setOrientation(1);
        devicePropertyPanelParams.addRule(12, -1);
        addView(this.devicePropertyPanelLayout, devicePropertyPanelParams);
        this.titleView = new TextView(getContext());
        LayoutParams titleViewParams = getTitleViewLayoutParams();
        this.titleView.setTextColor(ContextCompat.getColor(getContext(), C0676R.color.srt_text_color));
        this.titleView.setTextSize(2, 24.0f);
        this.titleView.setGravity(17);
        this.titleView.setPadding(padding, padding, padding, padding);
        ScrollView scrollView = new ScrollView(getContext());
        LayoutParams scrollViewParams = new LayoutParams(-1, -1);
        scrollViewParams.addRule(2, this.devicePropertyPanelLayout.getId());
        addView(scrollView, scrollViewParams);
        this.mainContentLayout = new LinearLayout(getContext());
        LayoutParams mainContentLayoutParams = new LayoutParams(-1, -2);
        this.mainContentLayout.setOrientation(1);
        scrollView.addView(this.mainContentLayout, mainContentLayoutParams);
        this.mainContentLayout.addView(this.titleView, titleViewParams);
    }

    @NonNull
    private LayoutParams getTitleViewLayoutParams() {
        LayoutParams titleViewParams = new LayoutParams(-1, -2);
        titleViewParams.addRule(10, -1);
        return titleViewParams;
    }

    public void setTitle(String title) {
        if (this.titleView != null) {
            this.titleView.setText(title);
        }
    }

    public void setSubSteps(List<SubStep> subSteps) {
        resetLayouts();
        this.mSubSteps = subSteps;
        for (SubStep subStep : this.mSubSteps) {
            if (ContentTypeEnum.DEVICE_PROPERTY_PANEL == subStep.getContentType()) {
                this.devicePropertyPanelLayout.addView(getView(subStep), getParams(subStep.getContentType()));
            } else {
                this.mainContentLayout.addView(getView(subStep), getParams(subStep.getContentType()));
            }
        }
    }

    private void resetLayouts() {
        if (this.mainContentLayout != null) {
            this.mainContentLayout.removeAllViews();
            this.mainContentLayout.addView(this.titleView, getTitleViewLayoutParams());
        }
        if (this.devicePropertyPanelLayout != null) {
            this.devicePropertyPanelLayout.removeAllViews();
        }
    }

    private View getView(SubStep subStep) {
        if (ContentTypeEnum.TEXT == subStep.getContentType()) {
            return getTextView(subStep);
        }
        if (ContentTypeEnum.PICTURE == subStep.getContentType()) {
            return getImageView(subStep);
        }
        if (ContentTypeEnum.DEVICE_PROPERTY_PANEL == subStep.getContentType()) {
            return new DevicePropertyPanel(getContext());
        }
        return null;
    }

    @NonNull
    private ImageView getImageView(SubStep subStep) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        Picasso.with(getContext()).load("https:" + subStep.getPictureURL()).into(imageView);
        return imageView;
    }

    @NonNull
    private TextView getTextView(SubStep subStep) {
        TextView textView = new TextView(getContext());
        textView.setText(new HvacMarkdownConverter().convertMarkdown(subStep.getText()));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextSize(2, 16.0f);
        textView.setTextColor(ContextCompat.getColor(getContext(), C0676R.color.srt_text_color));
        return textView;
    }

    private LinearLayout.LayoutParams getParams(ContentTypeEnum type) {
        int margin = Utils.dpToPx(16.0f, getResources());
        int bottomMargin = Utils.dpToPx(16.0f, getResources());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        params.gravity = 17;
        params.setMargins(margin, margin, margin, bottomMargin);
        if (ContentTypeEnum.PICTURE == type) {
            params.width = -1;
        } else if (ContentTypeEnum.DEVICE_PROPERTY_PANEL == type) {
            params.width = -1;
            params.setMargins(0, 0, 0, 0);
        }
        return params;
    }

    public void updateDevicePropertyPanel(String text, StateLookupEnum type) {
        if (this.devicePropertyPanelLayout != null) {
            for (int i = 0; i < this.devicePropertyPanelLayout.getChildCount(); i++) {
                if (this.devicePropertyPanelLayout.getChildAt(i) instanceof DevicePropertyPanel) {
                    ((DevicePropertyPanel) this.devicePropertyPanelLayout.getChildAt(i)).setDevicePropertyState(new HvacMarkdownConverter().convertMarkdown(text), type == StateLookupEnum.IN_PROGRESS);
                }
            }
        }
    }
}

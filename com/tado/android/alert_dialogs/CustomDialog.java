package com.tado.android.alert_dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.BadTokenException;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.report.ChartUtils;

public class CustomDialog {
    private boolean cancelButtonVisible;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private CustomDialogButton mButtonEnum;
    private Button mCancelButton;
    private OnClickListener mDefaultListener;
    private Dialog mDialog;
    private ProgressBar mProgressBar;
    private TextView mText1;
    private TextView mText2;
    private CustomDialogText mTextEnum;
    private TextView mTitle;
    private TextView mUpdateText;

    class C07101 implements OnClickListener {
        C07101() {
        }

        public void onClick(View v) {
            CustomDialog.this.mDialog.dismiss();
        }
    }

    public enum CustomDialogButton {
        CUSTOM_DIALOG_ONE_BUTTON,
        CUSTOM_DIALOG_TWO_BUTTONS,
        CUSTOM_DIALOG_THREE_BUTTONS
    }

    public enum CustomDialogText {
        CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH,
        CUSTOM_DIALOG_TWO_TEXT_PARAGRAPHS
    }

    public CustomDialog(Context context) {
        this.mTextEnum = CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH;
        this.mButtonEnum = CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON;
        init(context);
    }

    public CustomDialog(Context context, CustomDialogText textEnum, CustomDialogButton buttonEnum) {
        this.mTextEnum = textEnum;
        this.mButtonEnum = buttonEnum;
        init(context);
    }

    private void init(Context context) {
        this.cancelButtonVisible = true;
        this.mDialog = new Dialog(context, C0676R.style.Theme.CustomDialog);
        this.mDialog.setContentView(C0676R.layout.dialog_template);
        this.mTitle = (TextView) this.mDialog.findViewById(C0676R.id.dialog_title);
        this.mText1 = (TextView) this.mDialog.findViewById(C0676R.id.dialog_text_1);
        this.mText2 = (TextView) this.mDialog.findViewById(C0676R.id.dialog_text_2);
        this.mButton1 = (Button) this.mDialog.findViewById(C0676R.id.dialog_button_1);
        this.mButton2 = (Button) this.mDialog.findViewById(C0676R.id.dialog_button_2);
        this.mButton3 = (Button) this.mDialog.findViewById(C0676R.id.dialog_button_3);
        this.mCancelButton = (Button) this.mDialog.findViewById(C0676R.id.dialog_cancel_button);
        this.mProgressBar = (ProgressBar) this.mDialog.findViewById(C0676R.id.dialog_progress);
        this.mProgressBar.getIndeterminateDrawable().mutate().setColorFilter(ContextCompat.getColor(context, C0676R.color.progress_blue_color), Mode.SRC_ATOP);
        initVisibility();
    }

    private void initVisibility() {
        int i = 0;
        if (CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH != this.mTextEnum || this.mText2 == null) {
            this.mText1.setVisibility(0);
            this.mText2.setVisibility(0);
        } else {
            this.mText2.setVisibility(8);
            this.mText1.setVisibility(0);
        }
        if (CustomDialogButton.CUSTOM_DIALOG_ONE_BUTTON == this.mButtonEnum && this.mButton1 != null && this.mButton2 != null) {
            this.mButton1.setVisibility(0);
            this.mButton2.setVisibility(8);
            this.mButton3.setVisibility(8);
        } else if (CustomDialogButton.CUSTOM_DIALOG_TWO_BUTTONS != this.mButtonEnum || this.mButton3 == null) {
            this.mButton1.setVisibility(0);
            this.mButton2.setVisibility(0);
            this.mButton3.setVisibility(0);
        } else {
            this.mButton1.setVisibility(0);
            this.mButton2.setVisibility(0);
            this.mButton3.setVisibility(8);
        }
        if (this.mCancelButton != null) {
            Button button = this.mCancelButton;
            if (!this.cancelButtonVisible) {
                i = 8;
            }
            button.setVisibility(i);
        }
    }

    private void initListeners() {
        this.mDefaultListener = new C07101();
        this.mButton1.setOnClickListener(this.mDefaultListener);
        this.mButton2.setOnClickListener(this.mDefaultListener);
        this.mButton3.setOnClickListener(this.mDefaultListener);
        this.mCancelButton.setOnClickListener(this.mDefaultListener);
    }

    public static void showComplexTeachingErrorDialog(String title, String bodyText, String button1Text, String button2Text, final OnClickListener button1Listener, final OnClickListener button2Listener, Context context) {
        final CustomDialog customDialog = new CustomDialog(context, CustomDialogText.CUSTOM_DIALOG_ONE_TEXT_PARAGRAPH, CustomDialogButton.CUSTOM_DIALOG_TWO_BUTTONS);
        customDialog.setTitle(title);
        customDialog.setBodyText1(bodyText);
        customDialog.setButton1Text(button1Text);
        customDialog.setButton2Text(button2Text);
        customDialog.setButton1Listener(new OnClickListener() {
            public void onClick(View v) {
                customDialog.dismiss();
                button1Listener.onClick(v);
            }
        });
        customDialog.setButton2Listener(new OnClickListener() {
            public void onClick(View v) {
                customDialog.dismiss();
                button2Listener.onClick(v);
            }
        });
        customDialog.setCancelButtonVisible(false);
        try {
            customDialog.show();
        } catch (BadTokenException e) {
        }
    }

    public void setTitle(String text) {
        setTextViewText(this.mTitle, text);
    }

    public void setBodyText1(String text) {
        setTextViewText(this.mText1, text);
    }

    public void setBodyText2(String text) {
        setTextViewText(this.mText2, text);
    }

    public void setButton1Text(String text) {
        setButtonText(this.mButton1, text);
    }

    public void setButton2Text(String text) {
        setButtonText(this.mButton2, text);
    }

    public void setButton3Text(String text) {
        setButtonText(this.mButton3, text);
    }

    public void setCancelButtonText(String text) {
        setButtonText(this.mCancelButton, text);
    }

    public void setButton1Listener(OnClickListener listener) {
        setButtonListener(this.mButton1, listener);
    }

    public void setButton2Listener(OnClickListener listener) {
        setButtonListener(this.mButton2, listener);
    }

    public void setButton3Listener(OnClickListener listener) {
        setButtonListener(this.mButton3, listener);
    }

    public void setCancelButtonListener(OnClickListener listener) {
        setButtonListener(this.mCancelButton, listener);
    }

    private void setButtonListener(Button button, OnClickListener listener) {
        if (button != null) {
            button.setOnClickListener(listener);
        }
    }

    private void setButtonText(Button button, String text) {
        if (button != null) {
            if (TextUtils.isEmpty(text)) {
                button.setVisibility(8);
            }
            button.setText(text);
        }
    }

    private void setTextViewText(TextView textView, String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void show() {
        if (this.mDialog != null) {
            this.mDialog.show();
        }
    }

    public void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }

    public void setTextEnum(CustomDialogText textEnum) {
        this.mTextEnum = textEnum;
        initVisibility();
    }

    public void setButtonEnum(CustomDialogButton buttonEnum) {
        this.mButtonEnum = buttonEnum;
        initVisibility();
    }

    public void setCancelButtonVisible(boolean visible) {
        this.cancelButtonVisible = visible;
        initVisibility();
    }

    public void resetLayout() {
        this.mProgressBar.setVisibility(8);
        ((LayoutParams) this.mTitle.getLayoutParams()).addRule(10);
        ((LayoutParams) this.mTitle.getLayoutParams()).addRule(9);
        ((LayoutParams) this.mTitle.getLayoutParams()).addRule(14, 0);
        ((LayoutParams) this.mTitle.getLayoutParams()).topMargin = (int) ChartUtils.getDIPValue(16.0f);
        initVisibility();
    }

    public void showProgressBar(String updateText) {
        this.mProgressBar.setVisibility(0);
        this.mTitle.setText(updateText);
        ((LayoutParams) this.mTitle.getLayoutParams()).addRule(10, 0);
        ((LayoutParams) this.mTitle.getLayoutParams()).addRule(9, 0);
        ((LayoutParams) this.mTitle.getLayoutParams()).addRule(14, -1);
        ((LayoutParams) this.mTitle.getLayoutParams()).topMargin = (int) ChartUtils.getDIPValue(24.0f);
        this.mButton1.setVisibility(8);
        this.mButton2.setVisibility(8);
        this.mButton3.setVisibility(8);
        this.mCancelButton.setVisibility(8);
        this.mText1.setVisibility(8);
        this.mText2.setVisibility(8);
    }

    public void setCancelable(boolean cancalable) {
        this.mDialog.setCancelable(cancalable);
    }
}

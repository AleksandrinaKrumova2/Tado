package com.tado.android.alert_dialogs;

import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;

public class RestrictionDialogFragment extends DialogFragment {
    @BindView(2131296573)
    ImageView mCloseView;
    private int mContentImageResourceId = C0676R.drawable.settings_logo_homeserve;
    @BindView(2131296575)
    ImageView mContentImageView;
    private int mContentTextResourceId = C0676R.string.errors_homeRestricted_message;
    @BindView(2131296574)
    TextView mContentView;
    private boolean mIsContentImageVisible = false;
    @BindView(2131296577)
    View mTitleBarBackground;
    private int mTitleTextResourceId = C0676R.string.errors_homeRestricted_title;
    @BindView(2131296576)
    TextView mTitleView;
    private Unbinder unbinder;

    class C07201 implements OnClickListener {
        C07201() {
        }

        public void onClick(View v) {
            RestrictionDialogFragment.this.dismiss();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.dialog_home_restriction, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onResume() {
        super.onResume();
        init();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void init() {
        this.mCloseView.setClickable(true);
        this.mCloseView.setOnClickListener(new C07201());
        this.mTitleView.setText(this.mTitleTextResourceId);
        this.mContentView.setText(this.mContentTextResourceId);
        this.mContentView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mContentImageView.setImageResource(this.mContentImageResourceId);
        if (this.mIsContentImageVisible) {
            this.mContentImageView.setVisibility(0);
        } else {
            this.mContentImageView.setVisibility(8);
        }
        this.mTitleBarBackground.setBackgroundColor(ContextCompat.getColor(getContext(), C0676R.color.ac_red));
        this.mTitleView.setTextColor(ContextCompat.getColor(getContext(), C0676R.color.white));
        this.mCloseView.getDrawable().mutate().setColorFilter(ContextCompat.getColor(getContext(), C0676R.color.white), Mode.SRC_ATOP);
    }

    public void setContentTextResourceId(int contentTextResourceId) {
        this.mContentTextResourceId = contentTextResourceId;
    }

    public void setContentImageResourceId(int contentImageResourceId) {
        this.mContentImageResourceId = contentImageResourceId;
    }

    public void setTitleTextResourceId(int titleTextResourceId) {
        this.mTitleTextResourceId = titleTextResourceId;
    }

    public void setIsContentImageVisible(boolean isContentImageVisible) {
        this.mIsContentImageVisible = isContentImageVisible;
    }
}

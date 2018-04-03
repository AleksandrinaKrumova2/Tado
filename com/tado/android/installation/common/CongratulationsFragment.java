package com.tado.android.installation.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tado.C0676R;

public class CongratulationsFragment extends Fragment {
    @BindView(2131296432)
    Button addDevicesButton;
    @BindView(2131296433)
    Button doneButton;
    @BindView(2131296435)
    Button inviteButton;
    @BindView(2131296434)
    ImageView ribbon;
    Unbinder unbinder;

    class C08871 implements OnClickListener {
        C08871() {
        }

        public void onClick(View v) {
            if (CongratulationsFragment.this.isAdded()) {
                ((CongratulationsScreenCallback) CongratulationsFragment.this.getActivity()).onFinishInstallation();
            }
        }
    }

    class C08882 implements OnClickListener {
        C08882() {
        }

        public void onClick(View v) {
            if (CongratulationsFragment.this.isAdded()) {
                ((CongratulationsScreenCallback) CongratulationsFragment.this.getActivity()).invitePeople();
            }
        }
    }

    class C08893 implements OnClickListener {
        C08893() {
        }

        public void onClick(View v) {
            if (CongratulationsFragment.this.isAdded()) {
                ((CongratulationsScreenCallback) CongratulationsFragment.this.getActivity()).onAddNewDevice();
            }
        }
    }

    public CongratulationsFragment() {
        setRetainInstance(true);
    }

    public static CongratulationsFragment newInstance() {
        return new CongratulationsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0676R.layout.fragment_congratulations, container, false);
        this.unbinder = ButterKnife.bind((Object) this, view);
        initButtons();
        this.ribbon.setImageResource(C0676R.drawable.congratulations);
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    private void initButtons() {
        this.doneButton.setOnClickListener(new C08871());
        this.inviteButton.setOnClickListener(new C08882());
        this.addDevicesButton.setOnClickListener(new C08893());
    }
}

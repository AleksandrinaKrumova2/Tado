package com.tado.android;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.demo.DemoUtils;

public class PromoPageFragment extends Fragment {
    public static final String ARG_PAGE = "page";
    PageTransformerFragment delegate;
    private int mPageNumber;
    ViewGroup rootView;

    public static Fragment create(int pageNumber) {
        PromoPageFragment fragment = new PromoPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (this.mPageNumber) {
            case 0:
                this.rootView = (ViewGroup) inflater.inflate(C0676R.layout.fragment_screen_slide_page1, container, false);
                break;
            case 1:
                this.rootView = (ViewGroup) inflater.inflate(C0676R.layout.fragment_screen_slide_page2, container, false);
                break;
            case 2:
                this.rootView = (ViewGroup) inflater.inflate(C0676R.layout.fragment_screen_slide_page3, container, false);
                break;
        }
        return this.rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/avenirltstdmedium.ttf");
        switch (this.mPageNumber) {
            case 0:
                TextView text12 = (TextView) this.rootView.findViewById(C0676R.id.promo_1_2);
                ((TextView) this.rootView.findViewById(C0676R.id.promo_1_1)).setTypeface(tf);
                text12.setTypeface(tf);
                if (((PromoActivity) getActivity()).firstStart) {
                    RelativeLayout text1 = (RelativeLayout) this.rootView.findViewById(C0676R.id.text1);
                    text1.startAnimation(AnimationUtils.loadAnimation(getActivity(), C0676R.anim.fade_in));
                    text1.setVisibility(0);
                    ((PromoActivity) getActivity()).firstStart = false;
                    return;
                }
                return;
            case 1:
                ((TextView) this.rootView.findViewById(C0676R.id.promo_2_1)).setTypeface(tf);
                DemoUtils.showHideDemoButton((Button) this.rootView.findViewById(C0676R.id.demo_button), getContext());
                return;
            case 2:
                ((TextView) this.rootView.findViewById(C0676R.id.promo_3_1)).setTypeface(tf);
                DemoUtils.showHideDemoButton((Button) this.rootView.findViewById(C0676R.id.demo_button), getContext());
                return;
            default:
                return;
        }
    }

    public int getPageNumber() {
        return this.mPageNumber;
    }
}

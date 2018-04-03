package com.tado.android.onboarding;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeatureCallback;
import com.tado.android.onboarding.data.TutorialDataSourceRepository;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.onboarding.data.model.FeatureInfo;
import java.util.Locale;

public class OnboardingPageFragment extends Fragment {
    public static final String FEATURE_KEY = "key";
    private static final String FEATURE_TUTORIAL_DATA_SOURCE = "tutorialDataSource";
    private Button button;
    private ImageView image;
    private TextView text;

    class C10411 implements LoadFeatureCallback {
        C10411() {
        }

        public void onFeatureLoaded(FeatureInfo feature) {
            OnboardingPageFragment.this.bindView(feature);
        }

        public void onLoadingError(String msg) {
        }
    }

    public static OnboardingPageFragment getInstance(String key, TutorialDataSourceRepositoryEnum tutorialEnum) {
        Bundle args = new Bundle(1);
        args.putString(FEATURE_KEY, key);
        args.putSerializable(FEATURE_TUTORIAL_DATA_SOURCE, tutorialEnum);
        OnboardingPageFragment fragment = new OnboardingPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(C0676R.layout.fragment_onboarding_page, container, false);
        this.image = (ImageView) view.findViewById(C0676R.id.image_feature);
        this.text = (TextView) view.findViewById(C0676R.id.text_feature);
        this.button = (Button) view.findViewById(C0676R.id.button_feature);
        TutorialDataSourceRepository.getDataSource((TutorialDataSourceRepositoryEnum) getArguments().getSerializable(FEATURE_TUTORIAL_DATA_SOURCE), getActivity(), Locale.getDefault()).getFeature(getArguments().getString(FEATURE_KEY), new C10411());
        return view;
    }

    private void bindView(final FeatureInfo feature) {
        if (feature.getDrawable() != null) {
            this.image.setImageDrawable(feature.getDrawable());
            if (feature.getText() != null) {
                this.text.setText(feature.getText());
                this.text.setVisibility(0);
            } else {
                this.text.setVisibility(4);
            }
            if (feature.getLink() != null) {
                this.button.setVisibility(0);
                if (feature.getLink().text != null) {
                    this.button.setText(feature.getLink().text);
                } else {
                    this.button.setText(C0676R.string.components_featureIntro_readMoreLabel);
                }
                if (feature.getLink().url != null) {
                    this.button.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            OnboardingPageFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(feature.getLink().url)));
                        }
                    });
                    return;
                } else {
                    this.button.setVisibility(4);
                    return;
                }
            }
            this.button.setVisibility(4);
        }
    }
}

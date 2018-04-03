package com.tado.android.times;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.tado.C0676R;
import com.tado.android.rest.model.GenericAwayConfiguration;
import com.tado.android.rest.model.HeatingAwayConfiguration;
import com.tado.android.rest.model.ZoneSetting;
import com.tado.android.rest.model.installation.GenericZoneSetting;
import com.tado.android.views.SettingModelAdapter;
import com.tado.android.views.TadoZoneSettingViewConfiguration;
import com.tado.android.views.TadoZoneSettingsView;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.io.Serializable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J&\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u001a\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/tado/android/times/AwayTemperatureBottomSheet;", "Landroid/support/design/widget/BottomSheetDialogFragment;", "()V", "mAwayConfiguration", "Lcom/tado/android/rest/model/GenericAwayConfiguration;", "getMAwayConfiguration", "()Lcom/tado/android/rest/model/GenericAwayConfiguration;", "setMAwayConfiguration", "(Lcom/tado/android/rest/model/GenericAwayConfiguration;)V", "mTadoZoneSettingsView", "Lcom/tado/android/views/TadoZoneSettingsView;", "getMTadoZoneSettingsView", "()Lcom/tado/android/views/TadoZoneSettingsView;", "setMTadoZoneSettingsView", "(Lcom/tado/android/views/TadoZoneSettingsView;)V", "progressBarComponent", "Lcom/tado/android/views/progressbar/ProgressBarComponent;", "getProgressBarComponent", "()Lcom/tado/android/views/progressbar/ProgressBarComponent;", "setProgressBarComponent", "(Lcom/tado/android/views/progressbar/ProgressBarComponent;)V", "viewConfiguration", "Lcom/tado/android/views/TadoZoneSettingViewConfiguration;", "getViewConfiguration", "()Lcom/tado/android/views/TadoZoneSettingViewConfiguration;", "setViewConfiguration", "(Lcom/tado/android/views/TadoZoneSettingViewConfiguration;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Instance", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AwayTemperatureBottomSheet.kt */
public final class AwayTemperatureBottomSheet extends BottomSheetDialogFragment {
    public static final Instance Instance = new Instance();
    @NotNull
    private static final String KEY_AWAY_CONFIGURATION = KEY_AWAY_CONFIGURATION;
    @NotNull
    private static final String KEY_VIEW_CONFIGURATION = KEY_VIEW_CONFIGURATION;
    private HashMap _$_findViewCache;
    @Nullable
    private GenericAwayConfiguration mAwayConfiguration;
    @Nullable
    private TadoZoneSettingsView mTadoZoneSettingsView;
    @Nullable
    private ProgressBarComponent progressBarComponent;
    @Nullable
    private TadoZoneSettingViewConfiguration viewConfiguration;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/tado/android/times/AwayTemperatureBottomSheet$Instance;", "", "()V", "KEY_AWAY_CONFIGURATION", "", "getKEY_AWAY_CONFIGURATION", "()Ljava/lang/String;", "KEY_VIEW_CONFIGURATION", "getKEY_VIEW_CONFIGURATION", "getInstance", "Lcom/tado/android/times/AwayTemperatureBottomSheet;", "awayConfiguration", "Lcom/tado/android/rest/model/GenericAwayConfiguration;", "viewConfiguration", "Lcom/tado/android/views/TadoZoneSettingViewConfiguration;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: AwayTemperatureBottomSheet.kt */
    public static final class Instance {
        private Instance() {
        }

        @NotNull
        public final String getKEY_AWAY_CONFIGURATION() {
            return AwayTemperatureBottomSheet.KEY_AWAY_CONFIGURATION;
        }

        @NotNull
        public final String getKEY_VIEW_CONFIGURATION() {
            return AwayTemperatureBottomSheet.KEY_VIEW_CONFIGURATION;
        }

        @NotNull
        public final AwayTemperatureBottomSheet getInstance(@Nullable GenericAwayConfiguration awayConfiguration, @Nullable TadoZoneSettingViewConfiguration viewConfiguration) {
            AwayTemperatureBottomSheet fragment = new AwayTemperatureBottomSheet();
            if (awayConfiguration != null) {
                fragment.setArguments(new Bundle());
                Bundle arguments = fragment.getArguments();
                if (arguments == null) {
                    Intrinsics.throwNpe();
                }
                arguments.putSerializable(getKEY_AWAY_CONFIGURATION(), awayConfiguration);
                Bundle arguments2 = fragment.getArguments();
                if (arguments2 == null) {
                    Intrinsics.throwNpe();
                }
                arguments2.putSerializable(getKEY_VIEW_CONFIGURATION(), viewConfiguration);
            }
            return fragment;
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public final TadoZoneSettingsView getMTadoZoneSettingsView() {
        return this.mTadoZoneSettingsView;
    }

    public final void setMTadoZoneSettingsView(@Nullable TadoZoneSettingsView <set-?>) {
        this.mTadoZoneSettingsView = <set-?>;
    }

    @Nullable
    public final ProgressBarComponent getProgressBarComponent() {
        return this.progressBarComponent;
    }

    public final void setProgressBarComponent(@Nullable ProgressBarComponent <set-?>) {
        this.progressBarComponent = <set-?>;
    }

    @Nullable
    public final TadoZoneSettingViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final void setViewConfiguration(@Nullable TadoZoneSettingViewConfiguration <set-?>) {
        this.viewConfiguration = <set-?>;
    }

    @Nullable
    public final GenericAwayConfiguration getMAwayConfiguration() {
        return this.mAwayConfiguration;
    }

    public final void setMAwayConfiguration(@Nullable GenericAwayConfiguration <set-?>) {
        this.mAwayConfiguration = <set-?>;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TadoZoneSettingsView tadoZoneSettingsView;
        ProgressBarComponent progressBarComponent;
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View view = inflater.inflate(C0676R.layout.fragment_away_temperature, container, false);
        if (view != null) {
            tadoZoneSettingsView = (TadoZoneSettingsView) view.findViewById(C0676R.id.away_configuration_zone_settings_view);
        } else {
            tadoZoneSettingsView = null;
        }
        this.mTadoZoneSettingsView = tadoZoneSettingsView;
        if (view != null) {
            progressBarComponent = (ProgressBarComponent) view.findViewById(C0676R.id.progressBar);
        } else {
            progressBarComponent = null;
        }
        this.progressBarComponent = progressBarComponent;
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        Serializable serializable;
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            serializable = arguments.getSerializable(Instance.getKEY_AWAY_CONFIGURATION());
        } else {
            serializable = null;
        }
        this.mAwayConfiguration = (GenericAwayConfiguration) serializable;
        arguments = getArguments();
        if (arguments != null) {
            serializable = arguments.getSerializable(Instance.getKEY_VIEW_CONFIGURATION());
        } else {
            serializable = null;
        }
        this.viewConfiguration = (TadoZoneSettingViewConfiguration) serializable;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        TadoZoneSettingsView tadoZoneSettingsView;
        Intrinsics.checkParameterIsNotNull(view, Promotion.ACTION_VIEW);
        super.onViewCreated(view, savedInstanceState);
        ZoneSetting zoneSetting = new ZoneSetting();
        zoneSetting.setPowerBoolean(true);
        GenericAwayConfiguration genericAwayConfiguration;
        if (this.mAwayConfiguration instanceof HeatingAwayConfiguration) {
            genericAwayConfiguration = this.mAwayConfiguration;
            if (genericAwayConfiguration == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.tado.android.rest.model.HeatingAwayConfiguration");
            }
            zoneSetting.setTemperature(((HeatingAwayConfiguration) genericAwayConfiguration).getTemperature());
            tadoZoneSettingsView = this.mTadoZoneSettingsView;
            if (tadoZoneSettingsView != null) {
                tadoZoneSettingsView.hidePowerSwitch();
            }
        } else {
            genericAwayConfiguration = this.mAwayConfiguration;
            GenericZoneSetting setting = genericAwayConfiguration != null ? genericAwayConfiguration.getSetting() : null;
            if (setting == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.tado.android.rest.model.installation.GenericZoneSetting");
            }
            zoneSetting = SettingModelAdapter.getZoneSetting(setting);
            Intrinsics.checkExpressionValueIsNotNull(zoneSetting, "SettingModelAdapter.getZ…ng as GenericZoneSetting)");
        }
        tadoZoneSettingsView = this.mTadoZoneSettingsView;
        if (tadoZoneSettingsView != null) {
            tadoZoneSettingsView.initViewModel(this.viewConfiguration, zoneSetting);
        }
        TadoZoneSettingsView tadoZoneSettingsView2 = this.mTadoZoneSettingsView;
        if (tadoZoneSettingsView2 != null) {
            tadoZoneSettingsView2.setOnZoneSettingChangedListener(new AwayTemperatureBottomSheet$onViewCreated$1(this));
        }
    }
}

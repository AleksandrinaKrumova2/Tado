package com.tado.android.onboarding.data;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tado.android.controllers.ZoneController;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeatureCallback;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeaturesCallback;
import com.tado.android.onboarding.data.FeatureDataSource.LoadFeaturesOrderCallback;
import com.tado.android.onboarding.data.FeatureDataSource.LoadVersionCallback;
import com.tado.android.onboarding.data.TutorialDataSourceRepository.TutorialDataSourceRepositoryEnum;
import com.tado.android.onboarding.data.model.FeatureInfo;
import com.tado.android.onboarding.data.model.FeatureOrderItem;
import com.tado.android.onboarding.data.model.Features;
import com.tado.android.onboarding.data.model.FeaturesOrder;
import com.tado.android.utils.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.jetbrains.anko.DimensionsKt;

public class FeatureAssetsDataSource implements FeatureDataSource {
    private static final String FILE_PATH = "newfeatures";
    private static final String INFO_JSON = "info.json";
    private static FeatureAssetsDataSource instance;
    private AssetManager assetManager;
    private Features featuresCache;
    private FeaturesOrder featuresOrderCache;
    private String languageCode;
    private Resources resources;

    private FeatureAssetsDataSource(@NonNull Resources resources, @NonNull AssetManager assetManager, @NonNull Locale locale) {
        this.assetManager = assetManager;
        this.languageCode = locale.getLanguage();
        this.resources = resources;
    }

    public static FeatureAssetsDataSource getInstance(@NonNull Resources resources, @NonNull AssetManager assetManager, @NonNull Locale locale) {
        if (instance == null) {
            synchronized (FeatureAssetsDataSource.class) {
                if (instance == null) {
                    instance = new FeatureAssetsDataSource(resources, assetManager, locale);
                }
            }
        }
        return instance;
    }

    public void release() {
        instance = null;
    }

    public TutorialDataSourceRepositoryEnum getType() {
        return TutorialDataSourceRepositoryEnum.ON_BOARDING;
    }

    public void getFeatures(@NonNull LoadFeaturesCallback callback) {
        if (this.featuresCache == null) {
            try {
                this.featuresCache = getFeatures(this.languageCode);
            } catch (IOException e) {
                callback.onLoadingError(e.getMessage());
            }
        }
        callback.onFeaturesLoaded(this.featuresCache);
    }

    private Features getFeatures(@NonNull String languageCode) throws IOException {
        String featuresJson = FileUtils.loadJSONFromAsset(this.assetManager, FILE_PATH, String.format("%s.json", new Object[]{languageCode}));
        if (featuresJson == null) {
            String langFilename = String.format("%s.json", new Object[]{"en"});
            featuresJson = FileUtils.loadJSONFromAsset(this.assetManager, FILE_PATH, langFilename);
            if (featuresJson == null) {
                throw new IOException("Language file not found: " + langFilename);
            }
        }
        this.featuresCache = (Features) new GsonBuilder().create().fromJson(featuresJson, Features.class);
        return this.featuresCache;
    }

    private FeatureInfo getFeature(String id) {
        if (this.featuresCache != null) {
            FeatureInfo featureInfo = (FeatureInfo) this.featuresCache.getIntroScreens().get(id);
            if (featureInfo != null) {
                return featureInfo;
            }
        }
        return null;
    }

    public void getFeature(String id, @NonNull LoadFeatureCallback callback) {
        if (this.featuresCache == null) {
            try {
                this.featuresCache = getFeatures(this.languageCode);
            } catch (IOException e) {
                callback.onLoadingError(e.getMessage());
                return;
            }
        }
        FeatureInfo featureInfo = getFeature(id);
        if (featureInfo != null) {
            if (featureInfo.getDrawable() == null) {
                featureInfo.setDrawable(getDrawableFromAssets(id));
            }
            callback.onFeatureLoaded(featureInfo);
            return;
        }
        callback.onLoadingError("Can't find the key " + id);
    }

    public void getFeaturesOrder(@NonNull LoadFeaturesOrderCallback callback) {
        if (this.featuresOrderCache != null) {
            callback.onFeaturesOrderLoaded(this.featuresOrderCache);
        } else if (FileUtils.fileExistInAssets(this.assetManager, FILE_PATH, INFO_JSON)) {
            String infoJson = FileUtils.loadJSONFromAsset(this.assetManager, FILE_PATH, INFO_JSON);
            if (infoJson == null) {
                callback.onLoadingError(String.format("%s is not found in %s", new Object[]{INFO_JSON, FILE_PATH}));
            }
            try {
                this.featuresOrderCache = (FeaturesOrder) new Gson().fromJson(infoJson, FeaturesOrder.class);
                getFeatures(this.languageCode);
                Iterator<FeatureOrderItem> featureIterator = this.featuresOrderCache.getFeatureOrder().iterator();
                while (featureIterator.hasNext()) {
                    FeatureOrderItem featureItem = (FeatureOrderItem) featureIterator.next();
                    if (!this.featuresCache.getIntroScreens().containsKey(featureItem.getKey()) || !isTargetZoneTypeValid(featureItem.getTargetZoneTypes())) {
                        featureIterator.remove();
                    }
                }
                callback.onFeaturesOrderLoaded(this.featuresOrderCache);
            } catch (JsonSyntaxException e) {
                callback.onLoadingError(e.getMessage());
            } catch (IOException e2) {
                callback.onLoadingError(e2.getMessage());
            }
        } else {
            callback.onLoadingError(String.format("The specified file %s doesn't exist in %s", new Object[]{INFO_JSON, FILE_PATH}));
        }
    }

    public void getVersion(@NonNull final LoadVersionCallback callback) {
        if (this.featuresOrderCache != null) {
            loadVersion(callback);
        } else {
            getFeaturesOrder(new LoadFeaturesOrderCallback() {
                public void onFeaturesOrderLoaded(FeaturesOrder order) {
                    FeatureAssetsDataSource.this.featuresOrderCache = order;
                    FeatureAssetsDataSource.this.loadVersion(callback);
                }

                public void onLoadingError(String msg) {
                    callback.onLoadingError(msg);
                }
            });
        }
    }

    private void loadVersion(LoadVersionCallback callback) {
        if (this.featuresOrderCache.getFeatureOrder().isEmpty()) {
            callback.onVersionNoFeaturesToShow();
        } else {
            callback.onVersionLoaded(this.featuresOrderCache.getVersion());
        }
    }

    private boolean isTargetZoneTypeValid(ArrayList<String> targetZoneTypes) {
        if (targetZoneTypes == null) {
            return true;
        }
        Iterator it = targetZoneTypes.iterator();
        while (it.hasNext()) {
            if (ZoneController.INSTANCE.containsZoneType((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    private Drawable getDrawableFromAssets(String name) {
        Drawable drawable = null;
        try {
            InputStream ims = this.assetManager.open("newfeatures/" + name + ".png");
            Options opts = new Options();
            opts.inDensity = DimensionsKt.HDPI;
            drawable = Drawable.createFromResourceStream(this.resources, null, ims, "newfeatures/" + name + ".png", opts);
        } catch (IOException e) {
        }
        return drawable;
    }
}

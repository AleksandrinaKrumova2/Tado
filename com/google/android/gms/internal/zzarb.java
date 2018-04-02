package com.google.android.gms.internal;

import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

class zzarb<T extends zzara> extends zzapz {
    private zzarc<T> zzdvk;

    public zzarb(zzaqc com_google_android_gms_internal_zzaqc, zzarc<T> com_google_android_gms_internal_zzarc_T) {
        super(com_google_android_gms_internal_zzaqc);
        this.zzdvk = com_google_android_gms_internal_zzarc_T;
    }

    private final T zza(XmlResourceParser xmlResourceParser) {
        try {
            xmlResourceParser.next();
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (xmlResourceParser.getEventType() == 2) {
                    String toLowerCase = xmlResourceParser.getName().toLowerCase();
                    Object attributeValue;
                    Object trim;
                    if (toLowerCase.equals("screenname")) {
                        attributeValue = xmlResourceParser.getAttributeValue(null, CreateHomeContactDetailsActivity.INTENT_NAME);
                        trim = xmlResourceParser.nextText().trim();
                        if (!(TextUtils.isEmpty(attributeValue) || TextUtils.isEmpty(trim))) {
                            this.zzdvk.zzi(attributeValue, trim);
                        }
                    } else if (toLowerCase.equals("string")) {
                        attributeValue = xmlResourceParser.getAttributeValue(null, CreateHomeContactDetailsActivity.INTENT_NAME);
                        String trim2 = xmlResourceParser.nextText().trim();
                        if (!(TextUtils.isEmpty(attributeValue) || trim2 == null)) {
                            this.zzdvk.zzj(attributeValue, trim2);
                        }
                    } else if (toLowerCase.equals("bool")) {
                        attributeValue = xmlResourceParser.getAttributeValue(null, CreateHomeContactDetailsActivity.INTENT_NAME);
                        trim = xmlResourceParser.nextText().trim();
                        if (!(TextUtils.isEmpty(attributeValue) || TextUtils.isEmpty(trim))) {
                            try {
                                this.zzdvk.zzc(attributeValue, Boolean.parseBoolean(trim));
                            } catch (NumberFormatException e) {
                                zzc("Error parsing bool configuration value", trim, e);
                            }
                        }
                    } else if (toLowerCase.equals("integer")) {
                        attributeValue = xmlResourceParser.getAttributeValue(null, CreateHomeContactDetailsActivity.INTENT_NAME);
                        trim = xmlResourceParser.nextText().trim();
                        if (!(TextUtils.isEmpty(attributeValue) || TextUtils.isEmpty(trim))) {
                            try {
                                this.zzdvk.zzd(attributeValue, Integer.parseInt(trim));
                            } catch (NumberFormatException e2) {
                                zzc("Error parsing int configuration value", trim, e2);
                            }
                        }
                    } else {
                        continue;
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (XmlPullParserException e3) {
            zze("Error parsing tracker configuration file", e3);
        } catch (IOException e4) {
            zze("Error parsing tracker configuration file", e4);
        }
        return this.zzdvk.zzyo();
    }

    public final T zzav(int i) {
        try {
            return zza(zzwr().zzxg().getResources().getXml(i));
        } catch (NotFoundException e) {
            zzd("inflate() called with unknown resourceId", e);
            return null;
        }
    }
}

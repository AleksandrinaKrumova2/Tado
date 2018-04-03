package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.vision.barcode.Barcode.CalendarEvent;
import com.google.android.gms.vision.barcode.Barcode.ContactInfo;
import com.google.android.gms.vision.barcode.Barcode.DriverLicense;
import com.google.android.gms.vision.barcode.Barcode.Email;
import com.google.android.gms.vision.barcode.Barcode.GeoPoint;
import com.google.android.gms.vision.barcode.Barcode.Phone;
import com.google.android.gms.vision.barcode.Barcode.Sms;
import com.google.android.gms.vision.barcode.Barcode.UrlBookmark;
import com.google.android.gms.vision.barcode.Barcode.WiFi;

public final class zzb implements Creator<Barcode> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbfn.zzd(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        int i2 = 0;
        Point[] pointArr = null;
        Email email = null;
        Phone phone = null;
        Sms sms = null;
        WiFi wiFi = null;
        UrlBookmark urlBookmark = null;
        GeoPoint geoPoint = null;
        CalendarEvent calendarEvent = null;
        ContactInfo contactInfo = null;
        DriverLicense driverLicense = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    i = zzbfn.zzg(parcel, readInt);
                    break;
                case 3:
                    str = zzbfn.zzq(parcel, readInt);
                    break;
                case 4:
                    str2 = zzbfn.zzq(parcel, readInt);
                    break;
                case 5:
                    i2 = zzbfn.zzg(parcel, readInt);
                    break;
                case 6:
                    pointArr = (Point[]) zzbfn.zzb(parcel, readInt, Point.CREATOR);
                    break;
                case 7:
                    email = (Email) zzbfn.zza(parcel, readInt, Email.CREATOR);
                    break;
                case 8:
                    phone = (Phone) zzbfn.zza(parcel, readInt, Phone.CREATOR);
                    break;
                case 9:
                    sms = (Sms) zzbfn.zza(parcel, readInt, Sms.CREATOR);
                    break;
                case 10:
                    wiFi = (WiFi) zzbfn.zza(parcel, readInt, WiFi.CREATOR);
                    break;
                case 11:
                    urlBookmark = (UrlBookmark) zzbfn.zza(parcel, readInt, UrlBookmark.CREATOR);
                    break;
                case 12:
                    geoPoint = (GeoPoint) zzbfn.zza(parcel, readInt, GeoPoint.CREATOR);
                    break;
                case 13:
                    calendarEvent = (CalendarEvent) zzbfn.zza(parcel, readInt, CalendarEvent.CREATOR);
                    break;
                case 14:
                    contactInfo = (ContactInfo) zzbfn.zza(parcel, readInt, ContactInfo.CREATOR);
                    break;
                case 15:
                    driverLicense = (DriverLicense) zzbfn.zza(parcel, readInt, DriverLicense.CREATOR);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new Barcode(i, str, str2, i2, pointArr, email, phone, sms, wiFi, urlBookmark, geoPoint, calendarEvent, contactInfo, driverLicense);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode[i];
    }
}

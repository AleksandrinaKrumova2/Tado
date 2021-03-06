package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.vision.barcode.Barcode.Address;
import com.google.android.gms.vision.barcode.Barcode.ContactInfo;
import com.google.android.gms.vision.barcode.Barcode.Email;
import com.google.android.gms.vision.barcode.Barcode.PersonName;
import com.google.android.gms.vision.barcode.Barcode.Phone;

public final class zzf implements Creator<ContactInfo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Address[] addressArr = null;
        int zzd = zzbfn.zzd(parcel);
        String[] strArr = null;
        Email[] emailArr = null;
        Phone[] phoneArr = null;
        String str = null;
        String str2 = null;
        PersonName personName = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case 2:
                    personName = (PersonName) zzbfn.zza(parcel, readInt, PersonName.CREATOR);
                    break;
                case 3:
                    str2 = zzbfn.zzq(parcel, readInt);
                    break;
                case 4:
                    str = zzbfn.zzq(parcel, readInt);
                    break;
                case 5:
                    phoneArr = (Phone[]) zzbfn.zzb(parcel, readInt, Phone.CREATOR);
                    break;
                case 6:
                    emailArr = (Email[]) zzbfn.zzb(parcel, readInt, Email.CREATOR);
                    break;
                case 7:
                    strArr = zzbfn.zzaa(parcel, readInt);
                    break;
                case 8:
                    addressArr = (Address[]) zzbfn.zzb(parcel, readInt, Address.CREATOR);
                    break;
                default:
                    zzbfn.zzb(parcel, readInt);
                    break;
            }
        }
        zzbfn.zzaf(parcel, zzd);
        return new ContactInfo(personName, str2, str, phoneArr, emailArr, strArr, addressArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ContactInfo[i];
    }
}

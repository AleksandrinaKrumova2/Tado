package com.tado.android.repairServices;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002*\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001j\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003`\u0005B\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/tado/android/repairServices/RepairServicesCountryMap;", "Ljava/util/HashMap;", "Lcom/tado/android/repairServices/Country;", "", "Lcom/tado/android/repairServices/RepairService;", "Lkotlin/collections/HashMap;", "()V", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: RepairServicesLoader.kt */
public final class RepairServicesCountryMap extends HashMap<Country, List<? extends RepairService>> {
    public /* bridge */ boolean containsKey(Country country) {
        return super.containsKey(country);
    }

    public final /* bridge */ boolean containsKey(Object obj) {
        return obj instanceof Country ? containsKey((Country) obj) : false;
    }

    public final /* bridge */ boolean containsValue(Object obj) {
        return obj instanceof List ? containsValue((List) obj) : false;
    }

    public /* bridge */ boolean containsValue(List list) {
        return super.containsValue(list);
    }

    public final /* bridge */ Set<Entry<Country, List<RepairService>>> entrySet() {
        return getEntries();
    }

    public final /* bridge */ List<RepairService> get(Object obj) {
        return obj instanceof Country ? get((Country) obj) : null;
    }

    public /* bridge */ List get(Country country) {
        return (List) super.get(country);
    }

    public /* bridge */ Set getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set getKeys() {
        return super.keySet();
    }

    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return obj instanceof Country ? getOrDefault((Country) obj, (List) obj2) : obj2;
    }

    public /* bridge */ List getOrDefault(Country country, List list) {
        return (List) super.getOrDefault(country, list);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection getValues() {
        return super.values();
    }

    public final /* bridge */ Set<Country> keySet() {
        return getKeys();
    }

    public final /* bridge */ List<RepairService> remove(Object obj) {
        return obj instanceof Country ? remove((Country) obj) : null;
    }

    public /* bridge */ List remove(Country country) {
        return (List) super.remove(country);
    }

    public /* bridge */ boolean remove(Country country, List list) {
        return super.remove(country, list);
    }

    public final /* bridge */ boolean remove(Object obj, Object obj2) {
        return ((obj instanceof Country) && (obj2 instanceof List)) ? remove((Country) obj, (List) obj2) : false;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ Collection<List<RepairService>> values() {
        return getValues();
    }
}

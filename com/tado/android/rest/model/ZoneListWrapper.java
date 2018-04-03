package com.tado.android.rest.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tado.android.entities.ZoneOrderInput;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ZoneListWrapper {
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private LinkedHashMap<Integer, Zone> zoneMap;

    public ZoneListWrapper(@NonNull List<Zone> list) {
        this.lock.writeLock().lock();
        this.zoneMap = new LinkedHashMap(list.size());
        for (Zone zone : list) {
            this.zoneMap.put(Integer.valueOf(zone.getId()), zone);
        }
        this.lock.writeLock().unlock();
    }

    public void updateZoneState(int id, ZoneState state) {
        this.lock.readLock().lock();
        if (this.zoneMap.containsKey(Integer.valueOf(id))) {
            ((Zone) this.zoneMap.get(Integer.valueOf(id))).setZoneState(state);
        }
        this.lock.readLock().unlock();
    }

    @Nullable
    public ZoneState getZoneState(int zoneId) {
        this.lock.readLock().lock();
        Zone zone = (Zone) this.zoneMap.get(Integer.valueOf(zoneId));
        this.lock.readLock().unlock();
        if (zone == null || zone.getZoneState() == null) {
            return null;
        }
        return zone.getZoneState();
    }

    public String getZoneName(int zoneId, String defaultName) {
        this.lock.readLock().lock();
        Zone zone = (Zone) this.zoneMap.get(Integer.valueOf(zoneId));
        this.lock.readLock().unlock();
        if (zone != null) {
            return zone.getName();
        }
        return defaultName;
    }

    public boolean isEmpty() {
        try {
            this.lock.readLock().lock();
            boolean z = this.zoneMap != null && this.zoneMap.isEmpty();
            this.lock.readLock().unlock();
            return z;
        } catch (Throwable th) {
            this.lock.readLock().unlock();
        }
    }

    @Nullable
    public Zone getZone(int zoneId) {
        try {
            this.lock.readLock().lock();
            Zone zone = (Zone) this.zoneMap.get(Integer.valueOf(zoneId));
            return zone;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public void updateZone(int zoneId, Zone zone) {
        this.lock.writeLock().lock();
        if (this.zoneMap.containsKey(Integer.valueOf(zoneId))) {
            zone.setZoneState(getZoneState(zoneId));
            this.zoneMap.put(Integer.valueOf(zoneId), zone);
        }
        this.lock.writeLock().unlock();
    }

    @NonNull
    public List<Zone> getZoneList() {
        try {
            this.lock.readLock().lock();
            List<Zone> arrayList = new ArrayList(this.zoneMap.values());
            return arrayList;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public void removeZone(int zoneId) {
        this.lock.writeLock().lock();
        this.zoneMap.remove(Integer.valueOf(zoneId));
        this.lock.writeLock().unlock();
    }

    public void clear() {
        this.lock.writeLock().lock();
        if (this.zoneMap == null) {
            this.zoneMap = new LinkedHashMap();
        } else {
            this.zoneMap.clear();
        }
        this.lock.writeLock().unlock();
    }

    public void updateZoneOrder(List<ZoneOrderInput> order) {
        this.lock.writeLock().lock();
        if (isValidZoneOrder(order)) {
            LinkedHashMap<Integer, Zone> map = new LinkedHashMap(order.size());
            for (ZoneOrderInput input : order) {
                map.put(Integer.valueOf(input.getId()), this.zoneMap.get(Integer.valueOf(input.getId())));
            }
            this.zoneMap = map;
        }
        this.lock.writeLock().unlock();
    }

    public boolean isValidZoneOrder(List<ZoneOrderInput> order) {
        if (order == null || this.zoneMap == null || order.size() != this.zoneMap.keySet().size()) {
            return false;
        }
        for (ZoneOrderInput input : order) {
            if (!this.zoneMap.containsKey(Integer.valueOf(input.getId()))) {
                return false;
            }
        }
        return true;
    }
}

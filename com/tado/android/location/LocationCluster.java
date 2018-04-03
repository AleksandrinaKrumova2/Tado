package com.tado.android.location;

import android.location.Location;
import com.tado.android.utils.Snitcher;
import java.util.Iterator;
import java.util.LinkedList;

public class LocationCluster {
    public static final String TAG = ("TADO-" + LocationCluster.class.getSimpleName());
    private Location centroid;
    private float eps;
    private LinkedList<Location> locations;
    private float radius;

    public LocationCluster(Location initialLocation) {
        this.eps = 25.0f;
        this.locations = new LinkedList();
        if (initialLocation != null) {
            addLocation(initialLocation);
        }
    }

    public LocationCluster() {
        this.eps = 25.0f;
        LocationCluster locationCluster = new LocationCluster(null);
    }

    public void addLocation(Location loc) {
        updateLocations();
        Iterator it = this.locations.iterator();
        while (it.hasNext()) {
            Location cell = (Location) it.next();
            if (LocationUtil.sameLocation(cell, loc)) {
                this.locations.remove(cell);
                this.locations.add(loc);
                updateCentroid();
                Snitcher.start().log(3, TAG, "Replaced location in Cluster, now at " + this.locations.size() + " locations", new Object[0]);
                return;
            }
        }
        this.locations.add(loc);
        updateCentroid();
        Snitcher.start().log(3, TAG, "Added location to Cluster, now at " + this.locations.size() + " locations", new Object[0]);
    }

    public boolean isValid() {
        updateLocations();
        updateCentroid();
        return this.locations != null && this.locations.size() > 0;
    }

    public boolean contains(Location loc) {
        if (this.centroid != null) {
            Snitcher.start().log(3, TAG, "Distance to centroid is " + loc.distanceTo(this.centroid), new Object[0]);
            if (loc.distanceTo(this.centroid) < this.radius) {
                return true;
            }
        }
        if (this.locations != null && this.locations.size() > 0) {
            Iterator it = this.locations.iterator();
            while (it.hasNext()) {
                Location cLoc = (Location) it.next();
                Snitcher.start().log(3, TAG, "DBScan Distance is " + cLoc.distanceTo(loc), new Object[0]);
                if (cLoc.distanceTo(loc) < this.eps) {
                    Snitcher.start().log(3, TAG, "Location Contained in DBSCAN cluster", new Object[0]);
                    return true;
                }
            }
        }
        return false;
    }

    private void updateCentroid() {
        if (this.locations != null && this.locations.size() != 0) {
            float sumLat = 0.0f;
            float sumLon = 0.0f;
            Iterator it = this.locations.iterator();
            while (it.hasNext()) {
                Location loc = (Location) it.next();
                sumLat = (float) (((double) sumLat) + loc.getLatitude());
                sumLon = (float) (((double) sumLon) + loc.getLongitude());
            }
            this.centroid = new Location("centroid");
            this.centroid.setLatitude((double) (sumLat / ((float) this.locations.size())));
            this.centroid.setLongitude((double) (sumLon / ((float) this.locations.size())));
            float distSum = 0.0f;
            it = this.locations.iterator();
            while (it.hasNext()) {
                distSum += ((Location) it.next()).distanceTo(this.centroid);
            }
            this.radius = 20.0f + ((distSum / (this.radius * ((float) this.locations.size()))) * 40.0f);
            Snitcher.start().log(3, TAG, "Setting new cluster radius to " + this.radius + " m", new Object[0]);
        }
    }

    private void updateLocations() {
        if (this.locations == null || this.locations.size() == 0) {
            Snitcher.start().log(5, TAG, "Locations of a cell cluster are null or empty", new Object[0]);
            return;
        }
        while (LocationUtil.getLocationAgeMs((Location) this.locations.getFirst()) > 3600000) {
            this.locations.removeFirst();
            Snitcher.start().log(3, TAG, "Removing old location from cluster...", new Object[0]);
            if (this.locations.size() <= 0) {
                return;
            }
        }
        Snitcher.start().log(3, TAG, "All " + this.locations.size() + " locations are recent", new Object[0]);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
        sb.append("Cluster of size " + this.locations.size() + "\n");
        Iterator it = this.locations.iterator();
        while (it.hasNext()) {
            Location loc = (Location) it.next();
            sb.append("Radius is " + this.radius + "\n");
        }
        sb.append(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        return sb.toString();
    }
}

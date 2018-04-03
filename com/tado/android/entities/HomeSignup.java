package com.tado.android.entities;

public class HomeSignup {
    Address address;
    Location geolocation;
    String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getGeolocation() {
        return this.geolocation;
    }

    public void setGeolocation(Location geolocation) {
        this.geolocation = geolocation;
    }
}

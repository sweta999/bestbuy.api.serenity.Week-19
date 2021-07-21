package com.bestbuy.app.model;

import java.util.HashMap;
import java.util.List;

public class StoresPojo {

    private String name;
    private String type;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private int lat;
    private int lng;
    private String hours;
    private HashMap<String, Object> services;


    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String Address2) {
        this.address2 = Address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public String getState() {
        return state;
    }

    public void setState(String State) {
        this.state = State;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String  Zip) {
        this.zip = Zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double Lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double Lng) {
        this.lng = lng;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String Hours) {
        this.hours = Hours;
    }

    public HashMap<String, Object> getServices() {
        return services;
    }

    public void setServices(HashMap<String, Object> Services) {
        this.services = Services;
    }


}


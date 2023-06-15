package com.example.demo;

public class Place {
    private String country;
    private String landmark;

    public Place(String country, String landmark) {
        this.country = country;
        this.landmark = landmark;
    }

    public String getCountry() {
        return country;
    }

    public String getLandmark() {
        return landmark;
    }
}

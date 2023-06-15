package com.example.demo;

import javax.validation.constraints.NotBlank;

public class Place {
    @NotBlank
    private String country;

    @NotBlank
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

package com.stepan.foresight.model;

import java.io.Serializable;

/**
 * Created by Stepan on 1. 3. 2015.
 */
public class Coordinates implements Serializable{
    double latitude;
    double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

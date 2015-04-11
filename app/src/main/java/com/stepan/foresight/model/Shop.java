package com.stepan.foresight.model;

import java.io.Serializable;

/**
 * Created by Stepan on 1. 3. 2015.
 */
public class Shop implements Serializable{
    long id;
    String name;
    Coordinates coordinates;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}

package com.stepan.foresight.model;

import java.io.Serializable;

/**
 * Created by Stepan on 1. 3. 2015.
 */
public class Category implements Serializable{
    long id;
    String name;
    String picture;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

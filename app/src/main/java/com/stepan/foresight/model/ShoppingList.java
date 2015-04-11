package com.stepan.foresight.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stepan on 29. 3. 2015.
 */
public class ShoppingList implements Serializable{
    private Date date;
    private Shop shop;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}

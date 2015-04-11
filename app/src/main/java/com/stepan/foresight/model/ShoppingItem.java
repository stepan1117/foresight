package com.stepan.foresight.model;

import com.stepan.foresight.model.enumeration.UnitsEnum;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Stepan on 29. 3. 2015.
 */
public class ShoppingItem implements Serializable {
    private Price price;
    private double amount;
    private ShoppingList shoppingList;
    private UUID uid;
    private UnitsEnum units;

    public ShoppingItem(){
        this.uid = UUID.randomUUID();
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public UnitsEnum getUnits() {
        return units;
    }

    public void setUnits(UnitsEnum units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingItem that = (ShoppingItem) o;

        return uid.equals(that.uid);

    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }
}

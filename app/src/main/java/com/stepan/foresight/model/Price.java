package com.stepan.foresight.model;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;

/**
 * Created by Stepan on 1. 3. 2015.
 */
public class Price implements Serializable{
    /* Can be null */
    Product product;
    /* Can be null */
    String productDescription;
    Shop shop;
    Date validFrom;
    Date validTo;
    float minimumPieces;
    Currency currency;
    double price;
    float weight;
    String discount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public float getMinimumPieces() {
        return minimumPieces;
    }

    public void setMinimumPieces(float minimumPieces) {
        this.minimumPieces = minimumPieces;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

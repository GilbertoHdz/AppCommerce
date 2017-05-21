package com.example.developer.appcommerce.products.domain.model;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by developer on 5/21/17.
 */

public class Product {

    private String code;
    private String name;
    private String description;
    private String brand;
    private float price;
    private int unitInStock;
    private String imageUrl;

    public Product(String name, float price, String imageUrl) {
        this.code = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFormatedPrice() {
        return String.format("$%s", this.price);
    }
    public String getFormattedUnitsInStock() {
        return String.format(Locale.getDefault(), "%d u.", this.unitInStock);
    }
}

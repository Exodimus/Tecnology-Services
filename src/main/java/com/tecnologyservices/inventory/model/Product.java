package com.tecnologyservices.inventory.model;

public class Product {
    private int id;
    private String code;
    private String name;
    private String brand;
    private String type;
    private String color;
    private String capacity;
    private int categoryId;
    private double purchasePrice;
    private double taxPercentage;
    private double gainPercentage;
    private int stock;

    public Product() {
    }
    public Product(int id, String code, String name, String brand, String type, String color, String capacity, int categoryId, double purchasePrice, double taxPercentage, double gainPercentage, int stock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.capacity = capacity;
        this.categoryId = categoryId;
        this.purchasePrice = purchasePrice;
        this.taxPercentage = taxPercentage;
        this.gainPercentage = gainPercentage;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public double getGainPercentage() {
        return gainPercentage;
    }

    public void setGainPercentage(double gainPercentage) {
        this.gainPercentage = gainPercentage;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

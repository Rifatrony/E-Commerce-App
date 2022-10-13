package com.rony.e_commerceapp.Response;

public class TopSellingResponse {

    int image;
    String name, unit;
    double amount, price;

    public TopSellingResponse(int image, String name, String unit, double amount, double price) {
        this.image = image;
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

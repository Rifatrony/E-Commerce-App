package com.rony.e_commerceapp.Response;

public class CartResponse {

    int image;
    String name, price, amount;

    public CartResponse(int image, String name, String price, String amount) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.amount = amount;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

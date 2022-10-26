package com.rony.e_commerceapp.Response;

import java.util.ArrayList;

public class CartResponse {

    public ArrayList<Datum> data;

    public CartResponse(ArrayList<Datum> data) {
        this.data = data;
    }

    public class Datum{
        public String quantity;
        public String price;
        public double total;
        public Product product;

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }
    }

    public class Product{
        public String id;
        public String name;
        public String slug;
        public String thumbnail;
        public String discount;
        public String price;
        public double discounted_price;
        public boolean has_attribute;
    }
}

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

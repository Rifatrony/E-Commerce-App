package com.rony.e_commerceapp.Response;

import java.util.ArrayList;

public class CommonApiResponse {

    public Products products;

    public CommonApiResponse(Products products) {
        this.products = products;
    }

    public class Datum{
        public String id;
        public String name;
        public String slug;
        public String thumbnail;
        public String discount;
        public String price;
        public double discounted_price;
        public boolean has_attribute;
    }

    public class Pagination{
        public int total;
        public int count;
        public int per_page;
        public int current_page;
        public int total_pages;
    }

    public class Products{
        public ArrayList<Datum> data;
        public Pagination pagination;
    }



}

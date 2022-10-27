package com.rony.e_commerceapp.Response;

import java.util.ArrayList;

public class OrderResponse {

    public ArrayList<Datum> data;
    public Pagination pagination;

    public OrderResponse(ArrayList<Datum> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public class Datum{
        public String id;
        public String orderid;
        public String status;
        public int amount;
        public String payment;
        public int quantity;
    }

    public class Pagination{
        public int total;
        public int count;
        public int per_page;
        public int current_page;
        public int total_pages;
    }

}

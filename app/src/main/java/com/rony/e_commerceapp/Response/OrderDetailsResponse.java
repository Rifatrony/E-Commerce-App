package com.rony.e_commerceapp.Response;

import java.util.ArrayList;

public class OrderDetailsResponse {

    public String id;
    public String name;
    public String email;
    public String phone;
    public String address;
    public String orderid;
    public String status;
    public String sub_total;
    public String total;
    public Object payment_method;
    public String payment;
    public String delivery;
    public String delivery_rate;
    public ArrayList<Item> items;


    public OrderDetailsResponse(String id, String name, String email, String phone, String address, String orderid, String status, String sub_total, String total, Object payment_method, String payment, String delivery, String delivery_rate, ArrayList<Item> items) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.orderid = orderid;
        this.status = status;
        this.sub_total = sub_total;
        this.total = total;
        this.payment_method = payment_method;
        this.payment = payment;
        this.delivery = delivery;
        this.delivery_rate = delivery_rate;
        this.items = items;
    }

    public class Item{
        public String id;
        public String name;
        public String quantity;
        public String price;
        public int total;

        public Item(String id, String name, String quantity, String price, int total) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.total = total;
        }
    }

}

package com.rony.e_commerceapp.Response;

public class AddCartBodyResponse {

    public int quantity;
    public Options options;

    public AddCartBodyResponse(int quantity, Options options) {
        this.quantity = quantity;
        this.options = options;
    }

    public static class Options{
        public String size;
        public String length;

        public Options(String size, String length) {
            this.size = size;
            this.length = length;
        }
    }

}

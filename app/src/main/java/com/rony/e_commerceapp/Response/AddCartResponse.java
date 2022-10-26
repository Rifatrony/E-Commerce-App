package com.rony.e_commerceapp.Response;

public class AddCartResponse {

    public int quantity;
    public Options options;

    public AddCartResponse() {
    }

    public AddCartResponse(int quantity, Options options) {
        this.quantity = quantity;
        this.options = options;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public class Options{
        public String size;
        public String length;
    }

}

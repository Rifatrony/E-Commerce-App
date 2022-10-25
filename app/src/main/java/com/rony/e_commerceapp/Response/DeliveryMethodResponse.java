package com.rony.e_commerceapp.Response;

public class DeliveryMethodResponse {

    public String id;
    public String name;
    public String rate;

    public DeliveryMethodResponse() {
    }

    public DeliveryMethodResponse(String id, String name, String rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

package com.rony.e_commerceapp.Response;

public class RegistrationResponse {

    public String status;
    public String message;

    public RegistrationResponse() {
    }

    public RegistrationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

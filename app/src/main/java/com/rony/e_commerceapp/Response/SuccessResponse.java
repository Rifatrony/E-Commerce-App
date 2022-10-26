package com.rony.e_commerceapp.Response;

public class SuccessResponse {

    public Success success;

    public SuccessResponse(Success success) {
        this.success = success;
    }

    public class Success{
        public String message;
    }

}

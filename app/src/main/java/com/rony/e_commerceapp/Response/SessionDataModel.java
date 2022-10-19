package com.rony.e_commerceapp.Response;

public class SessionDataModel {

    String accessToken;
    String userPhone;
    String password;

    public SessionDataModel(String accessToken, String userPhone, String password) {
        this.accessToken = accessToken;
        this.userPhone = userPhone;
        this.password = password;
    }

    public SessionDataModel() {
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

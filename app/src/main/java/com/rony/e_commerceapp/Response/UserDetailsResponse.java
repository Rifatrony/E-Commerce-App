package com.rony.e_commerceapp.Response;

public class UserDetailsResponse {

    public User user;

    public UserDetailsResponse() {
    }

    public UserDetailsResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User{
        public String name;
        public String phone;
        public String email;
        public String address;
    }

}

package com.rony.e_commerceapp.Response;

public class UserResponse {

    public User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public class User{
        public String name;
        public String phone;
        public String email;
        public String address;
    }
}

package com.example.dao;

public class User {
    private int user_id;
    private String user_name;
    private String email;
    private String password;
    private String address;
    
    public User(int user_id, String user_name, String email, String password, String address){
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}

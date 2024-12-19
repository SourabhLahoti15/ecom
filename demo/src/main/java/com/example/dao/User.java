package com.example.dao;
import java.sql.Timestamp;

public class User {
    private int user_id;
    private String user_name;
    private String gender;
    private String email;
    private String password;
    private String address;
    private int phone;
    private Timestamp created_at;
    
    public User(int user_id, String user_name, String gender, String email, String password, String address, int phone){
        this.user_id = user_id;
        this.user_name = user_name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
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

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
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

    public int getPhone(){
        return phone;
    }
    
    public void setPhone(int phone){
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("user_id=").append(user_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}

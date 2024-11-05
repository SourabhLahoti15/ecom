package com.example.dao;

public class Order {
    private int order_id;
    private String user_id;
    private int total_amount;
    private String order_status;
    public Order(int order_id, String user_id, int total_amount, String order_status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.total_amount = total_amount;
        this.order_status = order_status;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public int getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }
    public String getOrder_status() {
        return order_status;
    }
    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}

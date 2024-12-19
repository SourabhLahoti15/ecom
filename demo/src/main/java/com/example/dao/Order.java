package com.example.dao;
import java.sql.Timestamp;

public class Order {
    private int order_id;
    private int user_id;
    private double order_amount;
    private String order_status;
    private int quantity;
    private Timestamp ordered_at;

    public Order(int order_id, int user_id, double  order_amount, String order_status, int quantity, Timestamp ordered_at) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_amount = order_amount;
        this.order_status = order_status;
        this.quantity = quantity;
        this.ordered_at = ordered_at;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public double getorder_amount() {
        return order_amount;
    }
    public void setorder_amount(double order_amount) {
        this.order_amount = order_amount;
    }
    public String getOrder_status() {
        return order_status;
    }
    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Timestamp getOrdered_at() {
        return ordered_at;
    }
    public void setOrdered_at(Timestamp ordered_at) {
        this.ordered_at = ordered_at;
    }
}

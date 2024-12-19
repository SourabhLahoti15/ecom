package com.example.dao;
import java.sql.Timestamp;

public class Product {
    private int product_id;
    private String product_name;
    private String product_description;
    private double product_price;
    private int product_stock;
    private Timestamp created_at;
    
    public Product(int product_id, String product_name, String product_description, double product_price, int product_stock) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.product_stock = product_stock;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public String getProduct_description() {
        return product_description;
    }
    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }
    public double getProduct_price() {
        return product_price;
    }
    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
    public int getProduct_stock() {
        return product_stock;
    }
    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("product_id=").append(product_id);
        sb.append(", product_name=").append(product_name);
        sb.append(", product_description=").append(product_description);
        sb.append(", product_price=").append(product_price);
        sb.append(", product_stock=").append(product_stock);
        sb.append('}');
        return sb.toString();
    }
}

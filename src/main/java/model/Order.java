package model;
import java.sql.Timestamp;

public class Order {
    private int order_id;
    private int user_id;
    private int product_id;
    private int address_id;
    private double order_amount;
    private String order_status;
    private int quantity;
    private Timestamp ordered_at;

    public Order(int order_id, int user_id, int product_id, int address_id, double order_amount, String order_status, int quantity, Timestamp ordered_at) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.address_id = address_id;
        this.order_amount = order_amount;
        this.order_status = order_status;
        this.quantity = quantity;
        this.ordered_at = ordered_at;
    }
    public Order(int user_id,int product_id, int address_id, double order_amount, String order_status, int quantity) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.address_id = address_id;
        this.order_amount = order_amount;
        this.order_status = order_status;
        this.quantity = quantity;
    }
    public int getOrderId() {
        return order_id;
    }
    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }
    public int getUserId() {
        return user_id;
    }
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    public int getProductId() {
        return product_id;
    }
    public void setProductId(int product_id) {
        this.product_id = product_id;
    }
    public int getAddressId() {
        return address_id;
    }
    public void setAddressId(int address_id) {
        this.address_id = address_id;
    }
    public double getorderAmount() {
        return order_amount;
    }
    public void setorderAmount(double order_amount) {
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
    public Timestamp getOrderedAt() {
        return ordered_at;
    }
    public void setOrderedAt(Timestamp ordered_at) {
        this.ordered_at = ordered_at;
    }
}

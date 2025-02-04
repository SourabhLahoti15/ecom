package model;

import java.sql.Timestamp;

public class Cart {
    private int userId;
    private int productId;
    private Timestamp addedDate;
    private boolean isChecked;

    // Constructor
    public Cart(int userId, int productId, Timestamp addedDate, boolean isChecked) {
        this.userId = userId;
        this.productId = productId;
        this.addedDate = addedDate;
        this.isChecked = isChecked;
    }
    public Cart(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

}

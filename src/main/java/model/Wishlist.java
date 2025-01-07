package model;

import java.sql.Timestamp;

public class Wishlist {
    private int userId;
    private int productId;
    private Timestamp addedDate;

    // Constructor
    public Wishlist(int userId, int productId, Timestamp addedDate) {
        this.userId = userId;
        this.productId = productId;
        this.addedDate = addedDate;
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
}

package model;

import java.sql.Timestamp;

public class Savelater {
    private int userId;
    private int productId;
    private Timestamp addedDate;

    // Constructor
    public Savelater(int userId, int productId, Timestamp addedDate) {
        this.userId = userId;
        this.productId = productId;
        this.addedDate = addedDate;
    }
    public Savelater(int userId, int productId) {
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
}

package model;

import java.sql.Timestamp;

public class Product {
    private int productId;
    private String imagePath;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productStock;
    private int user_id;
    private Timestamp createdAt;

    public Product(int productId, String imagePath, String productName, String productDescription, double productPrice, int productStock, int user_id) {
        this.productId = productId;
        this.imagePath = imagePath;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.user_id = user_id;
    }
    public Product(String imagePath, String productName, String productDescription, double productPrice, int productStock, int user_id) {
        this.imagePath = imagePath;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.user_id = user_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                '}';
    }
}

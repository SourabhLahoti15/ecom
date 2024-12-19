package com.example.dao;

import java.util.List;


public interface ProductDAO {
    boolean addProduct(Product product);    
    boolean updateProduct(Product product);    
    boolean deleteProduct(int productId);    
    List<Product> getAllProducts();    
    Product getProductById(int productId);    
    List<Product> searchProductsByName(String name);
}

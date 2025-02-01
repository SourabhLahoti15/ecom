package dao;

import java.util.List;

import model.Product;

public interface ProductDAO {
    boolean addProduct(Product product);    
    boolean updateProduct(Product product);    
    boolean deleteProduct(int productId);    
    List<Product> getAllProducts();    
    Product getProductById(int productId);    
    List<Product> getProductByUserId(int userId);
    // User getUserByProductId(int productId);
    List<Product> searchProductsByName(String name);
}

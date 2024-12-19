package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public boolean addProduct(Product product) {
        String query = "INSERT INTO product (product_id, product_name, product_description, product_price, product_stock) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1,product.getProduct_id());
            ps.setString(2, product.getProduct_name());
            ps.setString(3, product.getProduct_description());
            ps.setDouble(4, product.getProduct_price());
            ps.setInt(5, product.getProduct_stock());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        String query = "UPDATE product SET product_name = ?, product_description = ?, product_price = ?, product_stock = ? WHERE product_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, product.getProduct_name());
            ps.setString(2, product.getProduct_description());
            ps.setDouble(3, product.getProduct_price());
            ps.setInt(4, product.getProduct_stock());
            ps.setInt(7, product.getProduct_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE product_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, productId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pc = connection.prepareStatement(query);
             ResultSet rs = pc.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Double price = rs.getDouble("product_price");
                int stock = rs.getInt("product_stock");

                Product product = new Product(id, name, description, price, stock);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Double price = rs.getDouble("product_price");
                int stock = rs.getInt("product_stock");

                product = new Product(id, name, description, price, stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String description = rs.getString("product_description");
                Double price = rs.getDouble("product_price");
                int stock = rs.getInt("product_stock");

                Product product = new Product(id, productName, description, price, stock);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

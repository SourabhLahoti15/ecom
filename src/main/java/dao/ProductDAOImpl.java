package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAOImpl implements ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.");
        }
    }

    @Override
    public boolean addProduct(Product product) {
        String query = "INSERT INTO product (product_name, product_description, product_price, product_stock, user_id, image_path) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductDescription());
            ps.setDouble(3, product.getProductPrice());
            ps.setInt(4, product.getProductStock());
            ps.setInt(5, product.getUserId());
            ps.setString(6, product.getImagePath()); // Include image_path

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        String query = "UPDATE product SET product_name = ?, product_description = ?, product_price = ?, product_stock = ?, image_path = ? WHERE product_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductDescription());
            ps.setDouble(3, product.getProductPrice());
            ps.setInt(4, product.getProductStock());
            ps.setString(5, product.getImagePath()); // Include image_path
            ps.setInt(6, product.getProductId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM product WHERE product_id = ?";

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
        String query = "SELECT * FROM product";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String imagePath = rs.getString("image_path");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Double price = rs.getDouble("product_price");
                int stock = rs.getInt("product_stock");
                int uid = rs.getInt("user_id");

                Product product = new Product(id, imagePath, name, description, price, stock, uid);
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
        String query = "SELECT * FROM product WHERE product_id = ?";

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
                int uid = rs.getInt("user_id");
                String imagePath = rs.getString("image_path"); 

                product = new Product(id, imagePath, name, description, price, stock, uid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getProductByUserId(int userId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE user_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int pid = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Double price = rs.getDouble("product_price");
                int stock = rs.getInt("product_stock");
                int uid = rs.getInt("user_id");
                String imagePath = rs.getString("image_path");

                Product product = new Product(pid, imagePath, name, description, price, stock, uid);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE product_name LIKE ?";

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
                int uid = rs.getInt("user_id");
                String imagePath = rs.getString("image_path"); 

                Product product = new Product(imagePath, productName, description, price, stock, uid);
                product.setImagePath(imagePath);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

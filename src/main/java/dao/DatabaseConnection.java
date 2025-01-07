package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        // JDBC URL, username, and password
        String url = "jdbc:mysql://localhost:3306/project"; // Replace with your database name
        String username = "root"; // Replace with your MySQL username
        String password = "root"; // Replace with your MySQL password

        // Establishing connection to the database
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("Connection to the database established successfully!");
            String query = "INSERT INTO product (product_id, product_name, product_description, product_price, product_stock) VALUES (?, ?, ?, ?)";
            // Close the connection when done
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbc {
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project", 
                "root",  
                "root"     
            );
            System.out.println("Database connected!");
        } catch (Exception e) {
            System.out.println("Database connection error: " + e);
        }
    }
}
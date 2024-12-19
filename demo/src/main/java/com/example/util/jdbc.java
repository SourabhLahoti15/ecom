package com.example.util;

import com.example.dao.User;
import com.example.dao.UserDAOImpl;

public class jdbc {
    public static void main(String[] args) {
        // Connection con = null;
        // try {
        //     Class.forName("com.mysql.cj.jdbc.Driver");
        //     con = DriverManager.getConnection(
        //         "jdbc:mysql://localhost:3306/project", 
        //         "root",  
        //         "root"     
        //     );
        //     System.out.println("Database connected!");
        // } catch (Exception e) {
        //     System.out.println("Database connection error: " + e);
        // }

        User user = new User(7, "thala", "thala@gmail.com", "raju123", "o road");
        UserDAOImpl d = new UserDAOImpl();
        // d.addUser(user);
        System.out.println(d.getUserbyId(7));
    }
}
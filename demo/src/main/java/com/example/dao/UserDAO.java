package com.example.dao;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserbyId(int user_id);
    List<User> getAllUsers();
    boolean updateUser(User user);
    boolean deleteUser(int id);
}

package dao;

import java.util.List;

import model.User;

public interface UserDAO {
    boolean addUser(User user);
    User getUserbyId(int user_id);
    List<User> getAllUsers();
    boolean updateUser(User user);
    boolean deleteUser(int user_id);
    User loginCheck(String email, String password);
}

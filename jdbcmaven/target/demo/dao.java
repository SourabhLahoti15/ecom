public interface dao {
    void addUser(User user);
    User getUserbyId(int user_id);
    // List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.UserDAOImpl;
import model.User;

public class UserDAOImplTest {

    private UserDAOImpl userDAO;

    @BeforeEach
    void setup() {
        userDAO = new UserDAOImpl();
    }

    @Test
    void testAddUser() {
        User user = new User("Ravi Kumar", "Male", "ravi.kumar@email.com", "password123", 9876543210L, "1990-05-15");
        boolean isAdded = userDAO.addUser(user);
        assertTrue(isAdded);
    }

    @Test
    void testGetUserbyId() {
        int userId = 1;
        User user = userDAO.getUserbyId(userId);
        assertNotNull(user);
        assertEquals("Ravi Kumar", user.getName());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User("Ravi Kumar", "Male", "ravi.kumar@email.com", "password123", 9876543210L, "1990-05-15");
        userDAO.addUser(user1);

        User user2 = new User("Amit Sharma", "Male", "amit.sharma@email.com", "password456", 9876543211L, "1992-07-20");
        userDAO.addUser(user2);

        List<User> users = userDAO.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("Ravi Kumar")));
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("Amit Sharma")));
    }

    @Test
    void testUpdateUser() {
        User user = new User(1, "Ravi Kumar", "Male", "ravi.kumar@email.com", "password123", 9876543210L, "1990-05-15");
        boolean isUpdated = userDAO.updateUser(user);
        assertTrue(isUpdated);
    }

    @Test
    void testDeleteUser() {
        int userId = 1;
        boolean isDeleted = userDAO.deleteUser(userId);
        assertTrue(isDeleted);
    }

    @Test
    void testLoginCheck() {
        User user = userDAO.loginCheck("ravi.kumar@email.com", "password123");
        assertNotNull(user);
        assertEquals("Ravi Kumar", user.getName());
    }
}

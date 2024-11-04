import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class jdbc {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project?useSSL=false&serverTimezone=UTC",
                "root",
                "root"
            );
            // Use the connection...
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // User user = new User(8,"raju","raju@gmail.com","raju32","w road");
        // dao userdao = new daoImpl();
        // userdao.addUser(user);
        // System.out.println("New user added.");
    }
}

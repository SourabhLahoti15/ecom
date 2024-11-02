import java.sql.*;

public class App{

    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String user = "root";
    private static final String password = "root";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
            Statement statement = connection.createStatement();
            String query = "select * from user";
            ResultSet rs =  statement.executeQuery(query);
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");
                // String created_at = rs.getTimestamp("created_at");
                // String updated_at = rs.getTimestamp("updated_at");
            }
            System.out.println("Connection successful.");
        }
        catch(SQLException e) {
            System.out.println("Error while connecting: "+e.getMessage());
        }        
    }
}




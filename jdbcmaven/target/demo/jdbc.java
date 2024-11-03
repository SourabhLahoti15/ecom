public class jdbc {
    public static void main(String[] args) {
        User user = new User(8,"raju","raju@gmail.com","raju32","w road");
        dao userdao = new daoImpl();
        userdao.addUser(user);
        System.out.println("New user added.");
    }
    
}

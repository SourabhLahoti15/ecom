package model;
import java.sql.Timestamp;

public class User {
    private int user_id;
    private String name;
    private String gender;
    private String email;
    private String password;
    private long phone;
    private String dob;
    private Timestamp created_at;

    public User(int user_id, String name, String gender, String email, String password, long phone, String dob) {
        this.user_id = user_id;  
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dob = dob;
    }
    
    public User(String name, String gender, String email, String password, long phone, String dob){
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dob = dob;
    }
    // uid
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }    
    // user name
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    // gender
    public String getGender(){
        return gender;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    // email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // password
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    // phone
    public long getPhone(){
        return phone;
    }
    
    public void setPhone(long phone){
        this.phone = phone;
    }
    
    // dob
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("user_id=").append(user_id);
        sb.append(", user_name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
}

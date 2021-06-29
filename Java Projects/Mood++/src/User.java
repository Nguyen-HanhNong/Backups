/*
 * Class: User
 * Author: Tharusha
 * Start: Tuesday, January 19th, 2021
 * End: Monday, January 25th, 2021
 * Explanation: Creates the user account and stores data for different users.
 */
import java.util.ArrayList;

public class User implements java.io.Serializable {

    //User variables
    //first name variable
    String fName;
    //last name variable
    String lName;
    String userName;
    String password;
    byte age;

    //Default constructor
    public User(){
        this.fName="-";
        this.lName="-";
        this.age=-1;
        this.password = "";
        this.userName = "";
    }

    //Overloaded constructor
    public User(String fName, String lName, byte age, String password, String userName){
        this.fName=fName;
        this.lName=lName;
        this.age=age;
        this.password = password;
        this.userName = userName;
    }

    //toString of all the instance variables
    public String toString(){
        return "Name: " + this.fName + ", " + this.lName + ". Age: " + this.age + ". Username: " + this.userName + ". Password: " + this.password;
    }


    //Getters and setters for all the instance variables
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
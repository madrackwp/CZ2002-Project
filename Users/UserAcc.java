package Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserAcc {
    private String userName, name, school;
    private int password;

    // constructor
    public UserAcc(String userName, String password, String name, String school) {
        this.userName = userName;
        this.password = password.hashCode();
        this.name = name;
        this.school = school;
    }

    // getters
    public String getUserName() {
        return this.userName;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    // setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    // change password
    public boolean changePassword(String newPassword) {
        this.password = newPassword.hashCode();
        return true;
    }
}

package Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    private String userName, name, school;
    private int password;

    // constructor
    public User(String userName, String password, String name, String school) {
        this.userName = userName;
        this.password = password.hashCode();
        this.name = name;
        this.school = school;
    }

    // getters
    public String getUserName() {
        return this.userName;
    }

    public int getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    // change password
    public boolean changePassword(String newPassword) {
        setPassword(newPassword);
        return true;
    }

    // logout
    public boolean logout() {
        System.out.println("Are you sure you want to logout?");
        System.out.println("Y - Yes, N - No");
        Scanner sc = new Scanner(System.in);
        char input = sc.nextLine().charAt(0);
        if (input == 'Y' || input == 'y') {
            System.out.println("Logout successful");
            return true;
        } else {
            System.out.println("Logout cancelled");
            return false;
        }
    }
}

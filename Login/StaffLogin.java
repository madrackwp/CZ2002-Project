package Login;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import ReadWriteFile.StaffReader;
import Users.StaffAcc;

public class StaffLogin {
    public StaffLogin() {
    }

    public StaffAcc login() {
        StaffReader s = new StaffReader();
        ArrayList<StaffAcc> staffList = s.ReadFile();
        Scanner sc = new Scanner(System.in);
        Console cs = System.console();
        StaffAcc sa;

        System.out.println("Enter Username: ");
        String userName = sc.nextLine();
        String password = null;
        boolean foundUser = false;

        if (cs != null) {
            // cs.printf("Testing password%n");
            char[] passwordArray = cs.readPassword("Enter your password: ");
            String newString = new String(passwordArray);
            // cs.printf("Password entered was: %s%n", newString);
            password = Integer.toString(newString.hashCode());
        }

        for (StaffAcc saZ : staffList) {
            sa = saZ;
            if (sa.getUserName().equals(userName)) {
                foundUser = true;
                if(sa.getPassword().equals(password)) {
                    System.out.println("Login Successful!");
                    return sa;
                }
            }
        }
        if (foundUser == true) {
            System.out.println("Invalid Password");
        } else {
            System.out.println("Invalid Username");
        }
        return null;
    }

}

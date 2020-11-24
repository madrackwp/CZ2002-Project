package Login;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import Users.StaffAcc;
import Users.UserAcc;

public class StaffLogin implements UserLogin {
    public StaffLogin() {
    }

    @Override
    public StaffAcc login(Object staffList) {
        // StaffReader s = new StaffReader();
        // ArrayList<StaffAcc> staffList = s.ReadFile();
        Scanner sc = new Scanner(System.in);
        Console cs = System.console();
        StaffAcc sa;

        System.out.println("Enter Username: ");
        String userName = sc.nextLine();
        String password = null;
        boolean foundUser = false;

        if (cs != null) {
            // cs.printf("Testing password%n");
            char[] passwordArray = cs.readPassword("Enter Password: ");
            String newString = new String(passwordArray);
            // cs.printf("Password entered was: %s%n", newString);
            password = Integer.toString(newString.hashCode());
        }

        for (StaffAcc saZ : (ArrayList<StaffAcc>) staffList) {
            sa = saZ;
            if (sa.getUserName().equals(userName)) {
                foundUser = true;
                if (sa.getPassword().equals(password)) {
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

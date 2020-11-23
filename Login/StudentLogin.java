package Login;

import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Users.StudentAcc;

public class StudentLogin {

    public StudentLogin() {
    }

    public StudentAcc login(ArrayList<StudentAcc> studentList) {

        Scanner sc = new Scanner(System.in);
        Console cs = System.console();
        StudentAcc sa;
        String currentDate;
        boolean foundUser = false;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        currentDate = dtf.format(now);

        System.out.println("Enter username: ");
        String userName = sc.nextLine();
        String password = null;
        if (cs != null) {
            // cs.printf("Testing password%n");
            char[] passwordArray = cs.readPassword("Enter your password: ");
            String newString = new String(passwordArray);
            // cs.printf("Password entered was: %s%n", newString);
            password = Integer.toString(newString.hashCode());
        }
        // System.out.println("Enter password");
        // String password = Integer.toString(passwordArray.hashCode());

        for (StudentAcc saZ : studentList) {
            sa = saZ;
            if (sa.getUserName().equals(userName)) {
                foundUser = true;
                if (sa.getPassword().equals(password)) {
                    if (sa.getAccessDate().equals(currentDate)) {
                        System.out.println("Login Successful!");
                        return sa;
                    } else {
                        System.out.println("Wrong access date");
                        return null;
                    }
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

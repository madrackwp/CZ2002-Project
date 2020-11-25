package Login;

import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Users.StudentAcc;

/**
 * This class implements the UserLogin interface to allow objects from the StudentAcc class
 * to login to the STARS system
 * @author Chong Jing Hong
 * @since 25/11/2020
 */

public class StudentLogin implements UserLogin {

    public StudentLogin() {
    }

    /**
     * This method logs students into their account
     * @param studentList the list of student accounts in the database that will be verified against
     * @return the StudentAcc object that has logged in
     */

    public StudentAcc login(Object studentList) {

        Scanner sc = new Scanner(System.in);
        Console cs = System.console();
        StudentAcc sa;
        String currentDate;
        boolean foundUser = false;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        currentDate = dtf.format(now);

        System.out.println("Enter Username: ");
        String userName = sc.nextLine();
        String password = null;
        if (cs != null) {
            char[] passwordArray = cs.readPassword("Enter Password: ");
            String newString = new String(passwordArray);
            password = Integer.toString(newString.hashCode());
        }

        for (StudentAcc saZ : (ArrayList<StudentAcc>) studentList) {
            sa = saZ;
            if (sa.getUserName().equals(userName)) {
                foundUser = true;
                if (sa.getPassword().equals(password)) {
                    if (sa.getAccessDate().equals(currentDate)) {
                        System.out.println("Login Successful!");
                        return sa;
                    } else {
                        System.out.println("Wrong access date, access date: " + sa.getAccessDate());
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

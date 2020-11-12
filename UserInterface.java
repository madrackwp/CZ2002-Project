import java.util.ArrayList;
import java.util.Scanner;

import Course.*;
import Users.*;
import ReadWriteFile.*;

public class UserInterface {
    int userChoice;

    // public UserInterface() {
    // System.out.println("Welcome to STARS");
    // System.out.println("Select login: 1. Student 2. Staff");
    // Scanner sc = new Scanner(System.in);
    // this.userChoice = sc.nextInt();
    // }

    public static void main(String[] args) {
        // UserInterface u = new UserInterface();
        Reader r = new Reader();
        // if (u.userChoice == 1) {
        // Student s = r.studentLogin();
        // System.out.println(s.getUserName());
        // } else if (u.userChoice == 2) {
        // r.staffLogin();
        // }

        // System.out.println(r.getAllCourses());
        ArrayList<CourseIndex> courseIndexes = r.getAllCourses();
        for (CourseIndex courseIndex : courseIndexes) {
            System.out.println(courseIndex);
        }
    }
}

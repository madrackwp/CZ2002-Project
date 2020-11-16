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
        UserReader r = new UserReader();
        ArrayList<Student> s = r.ReadFile();
        System.out.println(s);
        Student s1 = s.get(0);
        System.out.println(s1.getSchool());
    }
}

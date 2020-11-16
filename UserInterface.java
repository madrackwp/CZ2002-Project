import java.util.ArrayList;
import java.util.Scanner;

import Users.*;

public class UserInterface {
    int userChoice;

    public UserInterface() {
        System.out.println("Welcome to STARS");
        System.out.println("Select login: 1. StudentAcc 2. StaffAcc");
        Scanner sc = new Scanner(System.in);
        this.userChoice = sc.nextInt();
    }

    public static void main(String[] args) {
        UserReader r = new UserReader();
        ArrayList<StudentAcc> s = r.ReadFile();
        System.out.println(s);
        StudentAcc s1 = s.get(0);
        System.out.println(s1.getSchool());
    }
}

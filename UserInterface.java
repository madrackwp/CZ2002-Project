import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import LocalDatabase.*;
import ReadWriteFile.*;
import Users.*;

public class UserInterface {

    public UserInterface() {
        int userChoice;
        StudentAcc SA = null;
        System.out.println("Welcome to STARS");
        System.out.println("Select login: 1. StudentAcc 2. StaffAcc");
        Scanner sc = new Scanner(System.in);
        userChoice = sc.nextInt();
        if (userChoice == 1) {
            SA = this.studentLogin();
            // System.out.println(SA.getUserName());
            if (SA != null) {
                CourseIndexReader cir = new CourseIndexReader();
                ArrayList<CourseIndex> courseIndexes = cir.ReadFile();
                for (CourseIndex ci : courseIndexes) {
                    System.out.println(ci.toString());
                }
            }
        }
         else if(userChoice == 2){
         this.staffLogin();
         }

    }

    public StudentAcc studentLogin() {
        StudentReader ur = new StudentReader();
        ArrayList<StudentAcc> studentList = ur.ReadFile();
        Scanner sc = new Scanner(System.in);
        StudentAcc sa;
        String currentDate;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        currentDate = dtf.format(now);

        System.out.println("Enter username");
        String userName = sc.nextLine();
        System.out.println("Enter password");
        String password = Integer.toString(sc.nextLine().hashCode());

        for (StudentAcc saZ : studentList) {
            sa = saZ;
            if (sa.getUserName().equals(userName) && sa.getPassword().equals(password)) {
                if (sa.getAccessDate().equals(currentDate)) {
                    System.out.println("Login Success!");
                    return sa;
                } else {
                    System.out.println("Wrong access date");
                }

            }
        }
        System.out.println("Login failed");
        return null;
    }

    public StaffAcc staffLogin(){
        StaffReader s = new StaffReader();
        ArrayList<StaffAcc> staffList = s.ReadFile();
        Scanner sc = new Scanner(System.in);
        StaffAcc sa;

        System.out.println("Enter Username: ");
        String userName = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = Integer.toString(sc.nextLine().hashCode());

        for(StaffAcc saZ : staffList){
            sa = saZ;
            if(sa.getUserName().equals(userName) && sa.getPassword().equals(password)){
                System.out.println("Login Successful!");
                return sa;
            }
        }
        System.out.println("Login Failed");
        return null;
    }

    public static void main(String[] args) {
        UserInterface u = new UserInterface();

    }
}

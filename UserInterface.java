import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import CourseController.AddDropCtrl;
import CourseController.ShowAllCoursesCtrl;
import CourseController.SwapIndexCtrl;
import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;
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
            CourseIndexReader CIR = new CourseIndexReader();
            ArrayList<CourseIndex> temp = CIR.ReadFile();
            CourseIndexDB indexDB = new CourseIndexDB(temp);
            CourseIndexDBManager indexDBManager = new CourseIndexDBManager(indexDB);

            StudentReader ur = new StudentReader();
            ArrayList<StudentAcc> studentList = ur.ReadFile(indexDBManager);

            SA = this.studentLogin(studentList);
            // System.out.println(SA.getUserName());
            if (SA != null) {

                System.out.println("Choose option:");
                System.out.println("1. Add course");
                System.out.println("2. Drop course");
                System.out.println("3. Swap index with peer");
                userChoice = sc.nextInt();

                AddDropCtrl addDropCtrl = new AddDropCtrl();
                ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

                switch (userChoice) {
                    case 1:
                        SA.getTimetable().printTimetable();
                        CourseIndex toAdd = showAllCoursesCtrl.selectCourse(indexDBManager);
                        addDropCtrl.addCourse(SA, toAdd);
                        SA.getTimetable().printTimetable();
                        break;
                    case 2:
                        SA.getTimetable().printTimetable();
                        System.out.println("Enter course to drop");
                        String courseToDrop = sc.next();
                        addDropCtrl.dropCourse(SA, courseToDrop);
                        SA.getTimetable().printTimetable();
                        break;
                    case 3:
                        StudentAcc student2 = studentLogin(studentList);
                        System.out.println("Enter course to swap");
                        String courseToSwap = sc.next();
                        SwapIndexCtrl sic = new SwapIndexCtrl();
                        sic.swapIndex(SA, student2, courseToSwap, addDropCtrl);
                        System.out.println("Student1 timetable");
                        SA.getTimetable().printTimetable();
                        System.out.println("");
                        System.out.println("Student2 timetable");
                        student2.getTimetable().printTimetable();

                        break;

                }

                // addDropCtrl.addCourse(SA, courseToAdd)
                // CourseIndexReader cir = new CourseIndexReader();
                // ArrayList<CourseIndex> courseIndexes = cir.ReadFile();
                // for (CourseIndex ci : courseIndexes) {
                // System.out.println(ci.toString());
                // }
            }
        } else if (userChoice == 2) {
            this.staffLogin();
        }

    }

    public StudentAcc studentLogin(ArrayList<StudentAcc> studentList) {
        // CourseIndexReader CIR = new CourseIndexReader();
        // ArrayList<CourseIndex> temp = CIR.ReadFile();
        // CourseIndexDB indexDB = new CourseIndexDB(temp);
        // CourseIndexDBManager indexDBManager = new CourseIndexDBManager(indexDB);

        // StudentReader ur = new StudentReader();
        // ArrayList<StudentAcc> studentList = ur.ReadFile(indexDBManager);

        Scanner sc = new Scanner(System.in);
        StudentAcc sa;
        String currentDate;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        currentDate = dtf.format(now);

        System.out.println("Enter username");
        String userName = sc.nextLine();
        Console console = System.console();
        if (console != null) {
            System.out.println("Enter password");
        }
        String password = Integer.toString(sc.nextLine().hashCode());

        for (StudentAcc saZ : studentList) {
            sa = saZ;
            if (sa.getUserName().equals(userName) && sa.getPassword().equals(password)) {
                if (sa.getAccessDate().equals(currentDate)) {
                    System.out.println("Login Success!");
                    // System.out.println(sa.getRegisteredCourseIndex());
                    return sa;
                } else {
                    System.out.println("Wrong access date");
                }

            }
        }
        System.out.println("Login failed");
        return null;
    }

    public StaffAcc staffLogin() {
        StaffReader s = new StaffReader();
        ArrayList<StaffAcc> staffList = s.ReadFile();
        Scanner sc = new Scanner(System.in);
        StaffAcc sa;

        System.out.println("Enter Username: ");
        String userName = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = Integer.toString(sc.nextLine().hashCode());

        for (StaffAcc saZ : staffList) {
            sa = saZ;
            if (sa.getUserName().equals(userName) && sa.getPassword().equals(password)) {
                System.out.println("Login Successful!");
                return sa;
            }
        }
        System.out.println("Login Failed");
        return null;
    }

}

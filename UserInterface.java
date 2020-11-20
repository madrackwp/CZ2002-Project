import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import CourseController.*;
import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;
import DatabaseManager.StudentDBManager;
import LocalDatabase.*;
import ReadWriteFile.*;
import Users.*;

public class UserInterface {

    public UserInterface() {
        int userChoice;
        StudentAcc SA = null;

        CourseIndexReader CIR = new CourseIndexReader();
        ArrayList<CourseIndex> courseList = CIR.ReadFile();
        CourseIndexDB indexDB = new CourseIndexDB(courseList);
        CourseIndexDBManager indexDBManager = new CourseIndexDBManager(indexDB);

        StudentReader ur = new StudentReader();
        ArrayList<StudentAcc> studentList = ur.ReadFile(indexDBManager);
        StudentDB studentDB = new StudentDB(studentList);
        StudentDBManager studentDBManager = new StudentDBManager(studentDB);
        StudentWriter studentWriter = new StudentWriter();
        CourseIndexWriter courseIndexWriter = new CourseIndexWriter();

        System.out.println("Welcome to STARS");
        System.out.println("Select login: 1. StudentAcc 2. StaffAcc");
        Scanner sc = new Scanner(System.in);
        userChoice = sc.nextInt();
        if (userChoice == 1) {
            SA = this.studentLogin(studentList);
            // System.out.println(SA.getUserName());
            if (SA != null) {
                boolean login = true;
                while (login) {
                    System.out.println("Choose option:");
                    System.out.println("1. Add course");
                    System.out.println("2. Drop course");
                    System.out.println("3. Check Registered Courses");
                    System.out.println("4. Change Index");
                    System.out.println("5. Swap index with peer");
                    System.out.println("6. Check Vacancies Available");
                    System.out.println("7. Reclassify mod type");
                    System.out.println("8. Logout");
                    System.out.println("===========================================");

                    userChoice = sc.nextInt();

                    AddDropCtrl addDropCtrl = new AddDropCtrl();
                    ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

                    switch (userChoice) {
                        case 1:
                            // SA.getTimetable().printTimetable();

                            for (StudentAcc s : studentList) {
                                System.out.println(s.getName());
                            }

                            studentList.remove(SA);

                            CourseIndex toAdd = showAllCoursesCtrl.selectCourse(indexDBManager);
                            courseList.remove(toAdd);
                            addDropCtrl.addCourse(SA, toAdd);
                            SA.getTimetable().printTimetable();
                            System.out.println("");

                            studentList.add(SA);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

                            courseList.add(toAdd);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);

                            for (StudentAcc s : studentList) {
                                System.out.println(s.getName());
                            }

                            break;
                        case 2:
                            // SA.getTimetable().printTimetable();
                            System.out.println("Enter course to drop");
                            String courseToDrop = sc.next();
                            addDropCtrl.dropCourse(SA, courseToDrop);
                            SA.getTimetable().printTimetable();
                            System.out.println("");
                            break;
                        case 3:
                            SA.getTimetable().printTimetable();
                            System.out.println("");
                            break;
                        case 4: 
                            ChangeIndexCtrl cic = new ChangeIndexCtrl();
                            System.out.println("Enter course to change index");
                            String courseToChange = sc.next();
                            cic.changeIndex(SA, courseToChange, indexDBManager, addDropCtrl);
                            System.out.println("");
                            System.out.println("Student timetable");
                            SA.getTimetable().printTimetable();
                            break;
                        case 5:
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
                            System.out.println("");
                            break;
                        case 6:
                            System.out.println("Enter course code to check: ");
                            String ccCheck = sc.next();
                            System.out.println("Enter index number to check: ");
                            int iCheck = sc.nextInt();
                            CheckVacancyCtrl cvc = new CheckVacancyCtrl();
                            int vacancy = cvc.getVacancies(ccCheck, iCheck, indexDBManager);
                            if (vacancy == -1) {
                                System.out.println("Invalid Course/Index");
                            } else {
                                System.out.println("Number of vacancies: " + vacancy);
                            }
                            System.out.println("");
                            break;
                        case 7:
                            ReclassifyCtrl reclassifyCtrl = new ReclassifyCtrl();
                            reclassifyCtrl.reclassifyCourse(SA, indexDBManager);
                            SA.getTimetable().printTimetable();
                            System.out.println("");
                            break;
                        case 8:
                            System.out.println("Bye bye!");
                            login = false;
                            break;
                        default:
                            System.out.println("Invalid option, try again idiot");
                            break;

                    }

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
        Console cs = System.console();
        StudentAcc sa;
        String currentDate;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        currentDate = dtf.format(now);

        System.out.println("Enter username");
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

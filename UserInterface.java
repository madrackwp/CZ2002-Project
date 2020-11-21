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
import StaffDuties.*;

public class UserInterface {

    public UserInterface() {
        int userChoice;
        StudentAcc SA = null;
        StaffAcc StA = null;

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
        System.out.println("Select Login Domain: 1. STUDENT 2. STAFF");
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
                    System.out.println("5. Swap Index With Peer");
                    System.out.println("6. Check Vacancies Available");
                    System.out.println("7. Reclassify Mod Type");
                    System.out.println("8. Logout");
                    System.out.println("===========================================");

                    userChoice = sc.nextInt();

                    AddDropCtrl addDropCtrl = new AddDropCtrl();
                    ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

                    switch (userChoice) {
                        case 1:
                            // SA.getTimetable().printTimetable();

                            // for (StudentAcc s : studentList) {
                            // System.out.println(s.getName());
                            // }

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

                            // for (StudentAcc s : studentList) {
                            // System.out.println(s.getName());
                            // }
                            SA.getTimetable().printTimetable();
                            System.out.println("");

                            break;
                        case 2:
                            // SA.getTimetable().printTimetable();
                            System.out.println("Enter course to drop");
                            String courseToDrop = sc.next();

                            studentList.remove(SA);

                            CourseIndex toDrop = SA.getCourseIndex(courseToDrop);
                            courseList.remove(toDrop);
                            CourseIndex droppedCourse = addDropCtrl.dropCourse(SA, courseToDrop);

                            ArrayList<String> indexWaitList = toDrop.getIndexWaitList().getWaitList();
                            if (!indexWaitList.get(0).equals("null")) {
                                String indexWaitListMatricNo = indexWaitList.remove(0);
                                System.out.println(indexWaitList);
                                StudentAcc waitingStudent = studentDBManager
                                        .getStudentByMatricNo(indexWaitListMatricNo);
                                studentList.remove(waitingStudent);
                                addDropCtrl.addCourse(waitingStudent, droppedCourse);
                                studentList.add(waitingStudent);
                                // send notification
                            }

                            studentList.add(SA);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

                            courseList.add(droppedCourse);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);

                            SA.getTimetable().printTimetable();
                            System.out.println("");
                            break;
                        case 3:
                            SA.getTimetable().printTimetable();
                            System.out.println("");
                            break;
                        case 4:
                            ChangeIndexCtrl cic = new ChangeIndexCtrl();

                            System.out.println("Enter course code to change index: ");
                            String courseToChange = sc.next();
                            if (!SA.takingCourse(courseToChange)) {
                                System.out.println("Invalid input");
                                break;
                            }

                            CourseIndex indexToDrop = SA.getCourseIndex(courseToChange);
                            cic.displayValidCourseToChange(courseToChange, indexDBManager, SA);
                            System.out.println("Enter new course index: ");
                            int newCourseIndex = sc.nextInt();
                            CourseIndex indexToChangeTo = indexDBManager.getCourseIndexInfo(courseToChange,
                                    newCourseIndex);

                            studentList.remove(SA);
                            courseList.remove(indexToDrop);
                            courseList.remove(indexToChangeTo);

                            ArrayList<String> IndexWaitList = indexToDrop.getIndexWaitList().getWaitList();
                            StudentAcc waitingStudent = null;
                            if (!IndexWaitList.get(0).equals("null")) {
                                String indexWaitListMatricNo = IndexWaitList.get(0);
                                // System.out.println(IndexWaitList);
                                waitingStudent = studentDBManager.getStudentByMatricNo(indexWaitListMatricNo);

                                // studentList.(waitingStudent);
                                // addDropCtrl.addCourse(waitingStudent, indexToDrop);
                                // studentList.add(waitingStudent);

                                // send notification
                            }

                            ArrayList<CourseIndex> oldNewCourseIndex = cic.changeIndex(indexToChangeTo, SA, indexToDrop,
                                    indexDBManager, addDropCtrl);
                            // check old course if SA is still inside
                            // if no, add waitingStudent into old course
                            if (!oldNewCourseIndex.get(0).getRegisteredStudentMatricNo().contains(SA.getMatricNo())
                                    && waitingStudent != null) {
                                studentList.remove(waitingStudent);
                                oldNewCourseIndex.get(0).getIndexWaitList().removeStudent(waitingStudent.getMatricNo());
                                addDropCtrl.addCourse(waitingStudent, oldNewCourseIndex.get(0));
                                studentList.add(waitingStudent);
                            }

                            CourseIndex toChange_Drop = oldNewCourseIndex.get(0);
                            CourseIndex toChange_Add = oldNewCourseIndex.get(1);

                            studentList.add(SA);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

                            courseList.add(toChange_Drop);
                            courseList.add(toChange_Add);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);

                            System.out.println("");
                            System.out.println("Student timetable");
                            SA.getTimetable().printTimetable();
                            break;
                        case 5:
                            StudentAcc student2 = studentLogin(studentList);
                            studentList.remove(student2);
                            studentList.remove(SA);

                            System.out.println("Enter course code to swap");
                            String courseToSwap = sc.next();

                            CourseIndex SACourse = SA.getCourseIndex(courseToSwap);
                            CourseIndex student2Course = student2.getCourseIndex(courseToSwap);

                            courseList.remove(student2Course);
                            courseList.remove(SACourse);

                            SwapIndexCtrl sic = new SwapIndexCtrl();
                            ArrayList<CourseIndex> courseIndexes = sic.swapIndex(SA, student2, SACourse, student2Course,
                                    addDropCtrl);

                            studentList.add(SA);
                            studentList.add(student2);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

                            courseList.add(courseIndexes.get(0));
                            courseList.add(courseIndexes.get(1));
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);

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

                            SA.getTimetable().printTimetable();
                            System.out.println("=====================================");
                            System.out.println("Select mod to reclassify:");
                            String userInput = sc.next();

                            studentList.remove(SA);

                            reclassifyCtrl.reclassifyCourse(userInput, SA);

                            studentList.add(SA);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

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
            StA = this.staffLogin();
            if (StA != null) {
                boolean login = true;
                while (login) {
                    System.out.println("Choose option:");
                    System.out.println("1. Add course");
                    System.out.println("2. Drop course");
                    System.out.println("3. Overwrite Vacancies");
                    System.out.println("4. Print students by Index Number");
                    System.out.println("5. Print students by Course");
                    // System.out.println("6. Reclassify mod type");
                    // System.out.println("7. Logout");
                    System.out.println("===========================================");

                    userChoice = sc.nextInt();

                    AddDropCtrl addDropCtrl = new AddDropCtrl();
                    StaffAddDrop addDropStaff = new StaffAddDrop();
                    ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

                    switch (userChoice) {
                        case 1:
                            SA = studentOverwrite(studentList);

                            for (StudentAcc s : studentList) {
                                System.out.println(s.getName());
                            }
                            studentList.remove(SA);
                            CourseIndex toAdd = showAllCoursesCtrl.selectCourse(indexDBManager);
                            addDropStaff.addCourse(SA, toAdd);
                            SA.getTimetable().printTimetable();
                            System.out.println("");
                            studentList.add(SA);
                            studentDBManager.updateDatabase(studentList, studentDB);

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

                        // case 3:
                        //     indexDB.print();
                        //     System.out.println("Enter course to change vacancies");
                        //     String courseToChange = sc.next();
                        //     System.out.println("Enter index of course to change vacancies");
                        //     int courseIndexToChange = sc.nextInt();
                        //     System.out.println("Value to change to:");
                        //     int vacancy = sc.nextInt();
                        //     addDropStaff.changeVacancies(indexDB, courseToChange, courseIndexToChange, vacancy);
                        //     indexDB.print();
                        //     System.out.println("");
                        //     break;

                        case 4:
                            System.out.println("Enter Course");
                            String course1 = sc.next();
                            System.out.println("Enter Index");
                            int index1 = sc.nextInt();
                            CourseIndex courseIndex = indexDBManager.getCourseIndexInfo(course1, index1);
                            ArrayList<String> mat = courseIndex.getRegisteredStudentMatricNo();
                            for (int i = 0; i < mat.size(); i++) {
                                StudentAcc temp = studentDBManager.getStudents(mat.get(i));
                                System.out.println(temp);
                                temp.print();
                            }
                            break;

                        case 5:
                            System.out.println("Enter Course");
                            String course2 = sc.next();
                            ArrayList<CourseIndex> courseIndex1 = indexDBManager.getCourseIndexInfo1(course2);
                            ArrayList<String> mat1 = new ArrayList<String>();
                            for (int i = 0; i < courseIndex1.size(); i++) {
                                System.out.println(courseIndex1.size());
                                for (int j = 0; j < courseIndex1.get(i).getRegisteredStudentMatricNo().size(); j++) {
                                    if (!courseIndex1.get(i).getRegisteredStudentMatricNo().get(j).equals("null"))
                                    mat1.add((courseIndex1.get(i).getRegisteredStudentMatricNo().get(j)));
                                }
                            }
                            for (int z = 0; z < mat1.size(); z++) {
                                StudentAcc temp = studentDBManager.getStudents(mat1.get(z));
                                System.out.println(temp);
                                temp.print();
                            }
                            break;
                        }
                    }
                }
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
        boolean foundUser = false;

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
            if (sa.getUserName().equals(userName)) {
                foundUser = true;
                if (sa.getPassword().equals(password)) {
                    if (sa.getAccessDate().equals(currentDate)) {
                        System.out.println("Login Success!");
                        // System.out.println(sa.getRegisteredCourseIndex());
                        return sa;
                    } else {
                        System.out.println("Wrong access date");
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

    public StudentAcc studentOverwrite(ArrayList<StudentAcc> studentList) {

        Scanner sc = new Scanner(System.in);
        StudentAcc sa;

        System.out.println("Enter username");
        String userName = sc.nextLine();

        for (StudentAcc saZ : studentList) {
            sa = saZ;
            if (sa.getUserName().equals(userName)) {
                System.out.println("User Found");
                return sa;
            }
        }
        System.out.println("Invalid User");
        return null;
    }

}

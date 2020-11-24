package UserInterfaces;

import java.util.ArrayList;
import java.util.Scanner;

import CourseController.AddDropCtrl;
import CourseController.ChangeIndexCtrl;
import CourseController.ReclassifyCtrl;
import CourseController.ShowAllCoursesCtrl;
import CourseController.SwapIndexCtrl;
import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;
import DatabaseManager.StudentDBManager;
import LocalDatabase.CourseIndexDB;
import LocalDatabase.StudentDB;
import Login.StudentLogin;
import Notification.NotificationManager;
import ReadWriteFile.CourseIndexReader;
import ReadWriteFile.CourseIndexWriter;
import ReadWriteFile.StudentReader;
import ReadWriteFile.StudentWriter;
import Users.StudentAcc;

public class StudentUI implements UserUI {
    public StudentUI() {
    }

    public void runUI() {
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
        StudentLogin studentLogin = new StudentLogin();

        NotificationManager notificationManager = new NotificationManager();
        Scanner sc = new Scanner(System.in);
        SA = studentLogin.login(studentList);
        // System.out.println(SA.getUserName());
        if (SA != null) {
            boolean login_access_student = true;
            while (login_access_student) {
                System.out.println("");
                System.out.println("=================MAIN MENU=================");
                System.out.println("Choose option:");
                System.out.println("1. Add Course");
                System.out.println("2. Drop Course");
                System.out.println("3. Check Registered Courses");
                System.out.println("4. Change Index");
                System.out.println("5. Swap Index With Peer");
                System.out.println("6. Check Vacancies Available");
                System.out.println("7. Reclassify Mod Type");
                System.out.println("8. Change Password");
                System.out.println("9. Logout");
                System.out.println("===========================================");

                int userChoice = sc.nextInt();
                AddDropCtrl addDropCtrl = new AddDropCtrl(); // AddDropCtrl is created here as it is used in
                                                             // multiple places and not just inside add/drop
                ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

                switch (userChoice) {
                    case 1:
                        studentList.remove(SA);
                        CourseIndex toAdd = showAllCoursesCtrl.selectCourseThatStudentNotTaking(SA, indexDBManager);
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
                        break;
                    case 2:
                        SA.getTimetable().printTimetable();
                        System.out.println("");
                        if (SA.getRegisteredCourseIndex().isEmpty()) {
                            System.out.println("No Course Taken");
                            break;
                        }
                        String courseToDrop;
                        System.out.println("Enter Course to Drop: ");
                        while (true) {
                            courseToDrop = sc.next().toUpperCase();
                            if (SA.takingCourse(courseToDrop)) {
                                break;
                            }
                            System.out.println("Course Not Taken");
                            System.out.println("Please Re-enter Course to Drop: ");
                        }

                        studentList.remove(SA);

                        CourseIndex toDrop = SA.getCourseIndex(courseToDrop);
                        courseList.remove(toDrop);
                        CourseIndex droppedCourse = addDropCtrl.dropCourse(SA, courseToDrop);

                        ArrayList<String> indexWaitList = toDrop.getIndexWaitList().getWaitList();
                        if (!indexWaitList.get(0).equals("null")) {
                            String indexWaitListMatricNo = indexWaitList.remove(0);
                            // System.out.println(indexWaitList);
                            StudentAcc waitingStudent = studentDBManager.getStudentByMatricNo(indexWaitListMatricNo);
                            studentList.remove(waitingStudent);
                            addDropCtrl.addCourse(waitingStudent, droppedCourse);
                            studentList.add(waitingStudent);
                            // send notification
                            notificationManager.sendEmail(waitingStudent.getUserName(), waitingStudent.getName(),
                                    droppedCourse);

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
                        SA.getTimetable().printTimetable();
                        System.out.println("");
                        if (SA.getRegisteredCourseIndex().isEmpty()) {
                            System.out.println("No Course Taken");
                            break;
                        }
                        ChangeIndexCtrl cic = new ChangeIndexCtrl();
                        System.out.println("Enter Course Code to Change Index: ");
                        String courseToChange;
                        while (true) {
                            courseToChange = sc.next().toUpperCase();
                            if (SA.takingCourse(courseToChange)) {
                                break;
                            }
                            System.out.println("Course Not Taken");
                            System.out.println("Please Re-enter Course Code to Change Index: ");
                        }

                        CourseIndex indexToDrop = SA.getCourseIndex(courseToChange);
                        int newCourseIndex = cic.displayValidCourseToChange(courseToChange, indexDBManager, SA);
                        CourseIndex indexToChangeTo = indexDBManager.getCourseIndexInfo(courseToChange, newCourseIndex);

                        studentList.remove(SA);
                        courseList.remove(indexToDrop);
                        courseList.remove(indexToChangeTo);

                        ArrayList<String> IndexWaitList = indexToDrop.getIndexWaitList().getWaitList();
                        StudentAcc waitingStudent = null;
                        if (!IndexWaitList.get(0).equals("null")) {
                            String indexWaitListMatricNo = IndexWaitList.get(0);
                            waitingStudent = studentDBManager.getStudentByMatricNo(indexWaitListMatricNo);
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
                            // send notification
                            notificationManager.sendEmail(waitingStudent.getUserName(), waitingStudent.getName(),
                                    oldNewCourseIndex.get(0));
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

                        SA.getTimetable().printTimetable();
                        System.out.println("");
                        break;
                    case 5:
                        System.out.println("STUDENT1:");
                        SA.getTimetable().printTimetable();
                        System.out.println("");
                        if (SA.getRegisteredCourseIndex().isEmpty()) {
                            System.out.println("No Course Taken");
                            break;
                        }
                        
                        StudentAcc student2 = studentLogin.login(studentList);
                        if (student2 == null) {
                            System.out.println("Student 2 login failed");
                            break;
                        }
                        if (student2.equals(SA)) {
                            System.out.println("Cannot swap same user");
                            break;
                        }
                        studentList.remove(student2);
                        studentList.remove(SA);
                        System.out.println("STUDENT2:");
                        student2.getTimetable().printTimetable();
                        System.out.println("");
                        System.out.println("Enter Course Code to Swap: ");
                        String courseToSwap;
                        while (true) {
                            courseToSwap = sc.next().toUpperCase();
                            if (SA.takingCourse(courseToSwap) && student2.takingCourse(courseToSwap)) {
                                break;
                            }
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

                            System.out.println("");
                            System.out.println("STUDENT1:");
                            SA.getTimetable().printTimetable();
                            System.out.println("");
                            System.out.println("STUDENT2:");
                            student2.getTimetable().printTimetable();
                            System.out.println("");
                        }
                            break;
                    case 6:
                        indexDBManager.printIndexes();
                        break;
                    case 7:
                        ReclassifyCtrl reclassifyCtrl = new ReclassifyCtrl();
                        System.out.println("Enter Course Code to Reclassify:");
                        String userInput = sc.next().toUpperCase();

                        studentList.remove(SA);

                        reclassifyCtrl.reclassifyCourse(userInput, SA);

                        studentList.add(SA);
                        studentDBManager.updateDatabase(studentList, studentDB);
                        studentWriter.writeFile(studentDBManager);

                        SA.getTimetable().printTimetable();
                        System.out.println("");
                        break;
                    case 8:
                        studentList.remove(SA);
                        System.out.println("Enter your new password");
                        String newPassword = sc.next();
                        System.out.println("Re-enter your new password");
                        String renewPassword = sc.next();
                        if (renewPassword.equals(newPassword)) {
                            SA.setPassword(newPassword);
                            studentList.add(SA);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);
                        } else {
                            System.out.println("Password Chanage Unsuccessful");
                        }
                        break;
                    case 9:
                        System.out.println("Bye bye!");
                        login_access_student = false;
                        break;
                    default:
                        System.out.println("Invalid Option, Try Again! ");
                        break;
                }
            }
        }
    }
}
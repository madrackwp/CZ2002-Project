import java.util.Scanner;

import UserInterfaces.AdminUI;
import UserInterfaces.StudentUI;

// import java.util.ArrayList;
// import java.util.Scanner;

// import CourseController.*;
// import CourseIndex.CourseIndex;
// import CourseIndex.IndexWaitList;

// import DatabaseManager.CourseIndexDBManager;
// import DatabaseManager.StudentDBManager;
// import LocalDatabase.*;
// import Login.StaffLogin;
// import Login.StudentLogin;
// import Notification.NotificationManager;
// import ReadWriteFile.*;
// import Users.*;
// import StaffDuties.*;

public class UserInterface {

    public static void main(String[] args) {
        boolean run = true;
        Scanner sc = new Scanner(System.in);

        while (run) {
            System.out.println("========================");
            System.out.println("====Welcome to STARS====");
            System.out.println("Login domain:");
            System.out.println("1. Student");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.println("========================");

            int userChoice;
            while (true) {
                while (!sc.hasNextInt()) {
                    System.out.println("Integers only!");
                    sc.next();
                }
                userChoice = sc.nextInt();
                if (userChoice > 3 || userChoice <= 0) {
                    System.out.println("Input 1-3 only");
                } else {
                    break;
                }
            }

            switch (userChoice) {
                case 1:
                    StudentUI studentUI = new StudentUI();
                    studentUI.runUI();
                    break;
                case 2:
                    AdminUI adminUI = new AdminUI();
                    adminUI.runUI();
                    break;
                case 3:
                    System.out.println("Logging off~");
                    run = false;
                    break;
                default:
                    break;
            }

        }
    }

    // public UserInterface() {
    // int userChoice;
    // StudentAcc SA = null;
    // StaffAcc StA = null;

    // CourseIndexReader CIR = new CourseIndexReader();
    // ArrayList<CourseIndex> courseList = CIR.ReadFile();
    // CourseIndexDB indexDB = new CourseIndexDB(courseList);
    // CourseIndexDBManager indexDBManager = new CourseIndexDBManager(indexDB);

    // StudentReader ur = new StudentReader();
    // ArrayList<StudentAcc> studentList = ur.ReadFile(indexDBManager);
    // StudentDB studentDB = new StudentDB(studentList);
    // StudentDBManager studentDBManager = new StudentDBManager(studentDB);
    // StudentWriter studentWriter = new StudentWriter();
    // CourseIndexWriter courseIndexWriter = new CourseIndexWriter();

    // NotificationManager notificationManager = new NotificationManager();

    // System.out.println("Welcome to STARS");
    // System.out.println("Select Login Domain: 1. STUDENT 2. STAFF");
    // Scanner sc = new Scanner(System.in);
    // userChoice = sc.nextInt();
    // if (userChoice == 1) {
    // StudentLogin studentLogin = new StudentLogin();
    // SA = studentLogin.login(studentList);
    // if (SA != null) {
    // boolean login_access_student = true;
    // while (login_access_student) {
    // System.out.println("");
    // System.out.println("=================MAIN MENU=================");
    // System.out.println("Choose option:");
    // System.out.println("1. Add Course");
    // System.out.println("2. Drop Course");
    // System.out.println("3. Check Registered Courses");
    // System.out.println("4. Change Index");
    // System.out.println("5. Swap Index With Peer");
    // System.out.println("6. Check Vacancies Available");
    // System.out.println("7. Reclassify Mod Type");
    // System.out.println("8. Change Password");
    // System.out.println("9. Logout");
    // System.out.println("===========================================");

    // userChoice = sc.nextInt();
    // AddDropCtrl addDropCtrl = new AddDropCtrl(); // AddDropCtrl is created here
    // as it is used in
    // // multiple places and not just inside add/drop
    // ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

    // switch (userChoice) {
    // case 1:
    // studentList.remove(SA);
    // CourseIndex toAdd = showAllCoursesCtrl.selectCourseThatStudentNotTaking(SA,
    // indexDBManager);
    // courseList.remove(toAdd);
    // addDropCtrl.addCourse(SA, toAdd);
    // SA.getTimetable().printTimetable();
    // System.out.println("");

    // studentList.add(SA);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // courseList.add(toAdd);
    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);
    // break;
    // case 2:
    // if (SA.getRegisteredCourseIndex().isEmpty()) {
    // System.out.println("No Course Taken");
    // break;
    // }
    // String courseToDrop;
    // System.out.println("Enter Course to Drop: ");
    // while (true) {
    // courseToDrop = sc.next();
    // if (SA.takingCourse(courseToDrop)) {
    // break;
    // }
    // System.out.println("Course Not Taken");
    // System.out.println("Please Re-enter Course to Drop: ");
    // }

    // studentList.remove(SA);

    // CourseIndex toDrop = SA.getCourseIndex(courseToDrop);
    // courseList.remove(toDrop);
    // CourseIndex droppedCourse = addDropCtrl.dropCourse(SA, courseToDrop);

    // ArrayList<String> indexWaitList = toDrop.getIndexWaitList().getWaitList();
    // if (!indexWaitList.get(0).equals("null")) {
    // String indexWaitListMatricNo = indexWaitList.remove(0);
    // System.out.println(indexWaitList);
    // StudentAcc waitingStudent = studentDBManager
    // .getStudentByMatricNo(indexWaitListMatricNo);
    // studentList.remove(waitingStudent);
    // addDropCtrl.addCourse(waitingStudent, droppedCourse);
    // studentList.add(waitingStudent);
    // // send notification
    // notificationManager.sendEmail(waitingStudent.getUserName(),
    // waitingStudent.getName(),
    // droppedCourse);

    // }

    // studentList.add(SA);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // courseList.add(droppedCourse);
    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);

    // SA.getTimetable().printTimetable();
    // System.out.println("");
    // break;
    // case 3:
    // SA.getTimetable().printTimetable();
    // System.out.println("");
    // break;
    // case 4:
    // if (SA.getRegisteredCourseIndex().isEmpty()) {
    // System.out.println("No Course Taken");
    // break;
    // }
    // ChangeIndexCtrl cic = new ChangeIndexCtrl();
    // System.out.println("Enter Course Code to Change Index: ");
    // String courseToChange;
    // while (true) {
    // courseToChange = sc.next();
    // if (SA.takingCourse(courseToChange)) {
    // break;
    // }
    // System.out.println("Course Not Taken");
    // System.out.println("Please Re-enter Course Code to Change Index: ");
    // }

    // CourseIndex indexToDrop = SA.getCourseIndex(courseToChange);
    // int newCourseIndex = cic.displayValidCourseToChange(courseToChange,
    // indexDBManager, SA);
    // CourseIndex indexToChangeTo =
    // indexDBManager.getCourseIndexInfo(courseToChange,
    // newCourseIndex);

    // studentList.remove(SA);
    // courseList.remove(indexToDrop);
    // courseList.remove(indexToChangeTo);

    // ArrayList<String> IndexWaitList =
    // indexToDrop.getIndexWaitList().getWaitList();
    // StudentAcc waitingStudent = null;
    // if (!IndexWaitList.get(0).equals("null")) {
    // String indexWaitListMatricNo = IndexWaitList.get(0);
    // waitingStudent =
    // studentDBManager.getStudentByMatricNo(indexWaitListMatricNo);
    // }

    // ArrayList<CourseIndex> oldNewCourseIndex = cic.changeIndex(indexToChangeTo,
    // SA, indexToDrop,
    // indexDBManager, addDropCtrl);
    // // check old course if SA is still inside
    // // if no, add waitingStudent into old course
    // if
    // (!oldNewCourseIndex.get(0).getRegisteredStudentMatricNo().contains(SA.getMatricNo())
    // && waitingStudent != null) {
    // studentList.remove(waitingStudent);
    // oldNewCourseIndex.get(0).getIndexWaitList().removeStudent(waitingStudent.getMatricNo());
    // addDropCtrl.addCourse(waitingStudent, oldNewCourseIndex.get(0));
    // // send notification
    // studentList.add(waitingStudent);
    // }

    // CourseIndex toChange_Drop = oldNewCourseIndex.get(0);
    // CourseIndex toChange_Add = oldNewCourseIndex.get(1);

    // studentList.add(SA);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // courseList.add(toChange_Drop);
    // courseList.add(toChange_Add);
    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);

    // SA.getTimetable().printTimetable();
    // System.out.println("");
    // break;
    // case 5:
    // if (SA.getRegisteredCourseIndex().isEmpty()) {
    // System.out.println("No Course Taken");
    // break;
    // }
    // StudentAcc student2 = studentLogin.login(studentList);
    // studentList.remove(student2);
    // studentList.remove(SA);

    // System.out.println("Enter Course Code to Swap: ");
    // String courseToSwap;
    // while (true) {
    // courseToSwap = sc.next();
    // if (SA.takingCourse(courseToSwap) && student2.takingCourse(courseToSwap)) {
    // break;
    // }
    // System.out.println("Course Not Taken by Both Students");
    // System.out.println("Please Re-enter Course Code to Swap: ");
    // }
    // CourseIndex SACourse = SA.getCourseIndex(courseToSwap);
    // CourseIndex student2Course = student2.getCourseIndex(courseToSwap);

    // courseList.remove(student2Course);
    // courseList.remove(SACourse);

    // SwapIndexCtrl sic = new SwapIndexCtrl();
    // ArrayList<CourseIndex> courseIndexes = sic.swapIndex(SA, student2, SACourse,
    // student2Course,
    // addDropCtrl);

    // studentList.add(SA);
    // studentList.add(student2);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // courseList.add(courseIndexes.get(0));
    // courseList.add(courseIndexes.get(1));
    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);

    // System.out.println("");
    // System.out.println("Student 1: ");
    // SA.getTimetable().printTimetable();
    // System.out.println("");
    // System.out.println("Student 2: ");
    // student2.getTimetable().printTimetable();
    // System.out.println("");
    // break;
    // case 6:
    // System.out.println("Enter Course Code to Check: ");
    // String ccCheck = sc.next();
    // System.out.println("Enter Index Number to Check: ");
    // while (!sc.hasNextInt()) {
    // System.out.println("Only Integers Allowed");
    // sc.next();
    // }
    // int iCheck = sc.nextInt();
    // CheckVacancyCtrl cvc = new CheckVacancyCtrl();
    // int vacancy = cvc.getVacancies(ccCheck, iCheck, indexDBManager);
    // if (vacancy == -1) {
    // System.out.println("Invalid Course/Index");
    // } else {
    // System.out.println("Number of Vacancies: " + vacancy);
    // }
    // System.out.println("");
    // break;
    // case 7:
    // ReclassifyCtrl reclassifyCtrl = new ReclassifyCtrl();
    // System.out.println("Enter Course Code to Reclassify:");
    // String userInput = sc.next();

    // studentList.remove(SA);

    // reclassifyCtrl.reclassifyCourse(userInput, SA);

    // studentList.add(SA);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // SA.getTimetable().printTimetable();
    // System.out.println("");
    // break;
    // case 8:
    // studentList.remove(SA);
    // System.out.println("Enter your new password");
    // String newPassword = sc.next();
    // System.out.println("Re-enter your new password");
    // String renewPassword = sc.next();
    // if (renewPassword.equals(newPassword)) {
    // SA.setPassword(newPassword);
    // studentList.add(SA);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);
    // } else {
    // System.out.println("Password Chanage Unsuccessful");
    // }
    // break;
    // case 9:
    // System.out.println("Bye bye!");
    // login_access_student = false;
    // break;
    // default:
    // System.out.println("Invalid Option, Try Again! ");
    // break;

    // }

    // }
    // }
    // } else if (userChoice == 2) {
    // StaffLogin staffLogin = new StaffLogin();
    // StA = staffLogin.login();
    // if (StA != null) {
    // boolean login_access_staff = true;
    // while (login_access_staff) {

    // System.out.println("");
    // System.out.println("=================MAIN MENU=================");
    // System.out.println("Choose option:");
    // System.out.println("1. Create New Student Account"); // EH,CC done
    // System.out.println("2. Change Student Access period"); // EH,CC done
    // System.out.println("3. Change Vacancies"); // EH,CC done
    // System.out.println("4. Print students by Index Number"); // EH done, no CC
    // needed
    // System.out.println("5. Print students by Course"); // EH done, no CC needed
    // System.out.println("6. Add Course Code"); // EH, CC done
    // System.out.println("7. Change Course Code");// EH, CC done
    // System.out.println("8. Change School");// EH, CC done
    // System.out.println("9. Add index number");// CC done, EH not done
    // System.out.println("10. Change index number");
    // System.out.println("11. Logout"); // done
    // System.out.println("===========================================");
    // System.out.println("\n");

    // userChoice = sc.nextInt();

    // AddDropCtrl addDropCtrl = new AddDropCtrl();
    // StaffAddDrop addDropStaff = new StaffAddDrop();
    // ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

    // switch (userChoice) {
    // case 1:
    // StaffAddStudentCtrl addStu = new StaffAddStudentCtrl();
    // StudentAcc newStudent = addStu.AddStudent();

    // studentList.add(newStudent);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // break;

    // case 2:
    // String newAccessDate = "01/01/2020";

    // System.out.println("Enter Student Matric No to change access period:");
    // String studentMatric = sc.next();
    // StudentAcc studentChangeAccess =
    // studentDBManager.getStudentByMatricNo(studentMatric);

    // if (studentChangeAccess != null) {
    // studentList.remove(studentChangeAccess);
    // System.out.println("Enter the new access date in the format dd/MM/YYYY");
    // newAccessDate = sc.next();
    // StaffChangeAccessPeriodCtrl scapc = new StaffChangeAccessPeriodCtrl();
    // studentChangeAccess = scapc.changeAccessPeriod(studentChangeAccess,
    // newAccessDate);
    // studentList.add(studentChangeAccess);
    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);
    // } else {
    // System.out.println("This student does not exist!");
    // }

    // break;

    // case 3:
    // indexDB.print();

    // // To find the course
    // System.out.println("Enter course to change vacancy");
    // String courseToChange = sc.next().toUpperCase();
    // System.out.println("Enter index of course to change vacancies");
    // int courseIndexToChange = sc.nextInt();
    // CourseIndex courseToChangeVacancy =
    // indexDBManager.getCourseIndexInfo(courseToChange,
    // courseIndexToChange);
    // // If the course exists
    // if (courseToChangeVacancy != null) {
    // System.out.println("Value to change to:");
    // int vacancy = sc.nextInt();

    // courseList.remove(courseToChangeVacancy);
    // StaffChangeVacancyCtrl staffChangeVacancyCtrl = new StaffChangeVacancyCtrl();
    // staffChangeVacancyCtrl.changeVacancies(courseToChangeVacancy, vacancy);

    // // This is the look for anyone that is on the waitlist and add them into the
    // // courseIndex
    // IndexWaitList iwl = courseToChangeVacancy.getIndexWaitList();
    // if (iwl.getWaitList().size() != 0) {
    // for (int i = 0; i < vacancy; i++) {

    // String matricNo = iwl.getWaitList().get(0);
    // StudentAcc student = studentDBManager.getStudentByMatricNo(matricNo);
    // studentList.remove(student);
    // boolean boolCheck = addDropCtrl.addCourse(student, courseToChangeVacancy);
    // if (boolCheck) {
    // notificationManager.sendEmail(student.getUserName(), student.getName(),
    // courseToChangeVacancy);
    // } else {
    // System.out.println("Add fail");
    // }

    // // Send email here
    // studentList.add(student);

    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);
    // }
    // }

    // } else {
    // System.out.println("This course and index number does not exist.");
    // }
    // break;

    // case 4:
    // // Display all courseIndexes
    // indexDB.print();

    // System.out.println("Enter Course");
    // String course1 = sc.next().toUpperCase();
    // System.out.println("Enter Index");
    // int index1 = sc.nextInt();
    // CourseIndex courseIndex = indexDBManager.getCourseIndexInfo(course1, index1);
    // if (courseIndex != null) {
    // ArrayList<String> mat = courseIndex.getRegisteredStudentMatricNo();
    // for (int i = 0; i < mat.size(); i++) {
    // StudentAcc temp = studentDBManager.getStudentByMatricNo(mat.get(i));
    // System.out.println(temp);
    // }
    // } else {
    // System.out.println("This course and index number does not exist");
    // }
    // break;

    // case 5:
    // // Display all courseIndexes
    // indexDB.print();

    // System.out.println("Enter Course");
    // String course2 = sc.next();
    // ArrayList<CourseIndex> courseIndexToPrint =
    // indexDBManager.getCourseIndexInfoArray(course2);
    // if (courseIndexToPrint != null) {
    // ArrayList<String> mat1 = new ArrayList<String>();
    // for (int i = 0; i < courseIndexToPrint.size(); i++) {
    // for (int j = 0; j < courseIndexToPrint.get(i).getRegisteredStudentMatricNo()
    // .size(); j++) {
    // if (!courseIndexToPrint.get(i).getRegisteredStudentMatricNo().get(j)
    // .equals("null"))
    // mat1.add((courseIndexToPrint.get(i).getRegisteredStudentMatricNo().get(j)));
    // }
    // }
    // for (int z = 0; z < mat1.size(); z++) {
    // StudentAcc temp = studentDBManager.getStudentByMatricNo(mat1.get(z));
    // System.out.print("Index: " + temp.getCourseIndex(course2).getIndexNo());
    // System.out.println(" | " + temp);
    // }
    // } else {
    // System.out.println("This course code does not exist");
    // }
    // break;

    // case 6:
    // System.out.println("Enter the course code you would like to create: ");
    // String courseCodeToCreate = sc.next().toUpperCase();
    // ArrayList<CourseIndex> checkIfCourseExist = indexDBManager
    // .getCourseIndexInfoArray(courseCodeToCreate);

    // if (checkIfCourseExist.isEmpty()) { // The course code does not exist yet
    // StaffCreateCourseCtrl staffCreateCourseCtrl = new StaffCreateCourseCtrl();
    // ArrayList<CourseIndex> newCourseIndexes = staffCreateCourseCtrl
    // .createCourse(courseCodeToCreate);

    // if (newCourseIndexes != null) { // This is to check if the courseCode was
    // returned
    // for (CourseIndex newCourse : newCourseIndexes) {
    // courseList.add(newCourse);
    // }
    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);
    // } else {
    // System.out.println("No new course created");
    // }
    // } else {
    // System.out.println("This course already exists!");
    // }

    // break;

    // case 7:

    // indexDBManager.printCourses();
    // // Getting user input for the course to chagnge
    // System.out.println("Enter the course code for the course to change");
    // String courseSelected = sc.next();
    // ArrayList<CourseIndex> courses =
    // indexDBManager.getCourseIndexInfoArray(courseSelected);

    // // If the course does not exist, the arrayList will be empty and enter the
    // else
    // if (!courses.isEmpty()) {
    // System.out.println("Enter new Course Code");
    // String newCourseCode = sc.next();

    // // Check whether this course code already exists
    // ArrayList<CourseIndex> courseCheck = indexDBManager
    // .getCourseIndexInfoArray(newCourseCode);
    // if (!courseCheck.isEmpty()) {
    // // Course code already exists
    // System.out.println("ERROR: This coursecode already exists!");
    // break;
    // }

    // // ArrayList that will store all affected students
    // ArrayList<StudentAcc> studentAffected = new ArrayList<>();
    // for (CourseIndex c : courses) {
    // ArrayList<String> studentsInCourse = c.getRegisteredStudentMatricNo();
    // for (String students : studentsInCourse) {
    // StudentAcc droppingStudents =
    // studentDBManager.getStudentByMatricNo(students);
    // studentList.remove(droppingStudents);
    // studentAffected.add(droppingStudents);
    // }
    // }

    // ChangeCourseCodeCtrl ccc = new ChangeCourseCodeCtrl();
    // courseList = ccc.changeCourseCode(courseList, courses, courseSelected,
    // newCourseCode);

    // for (StudentAcc studentToAdd : studentAffected) {
    // if (studentToAdd != null) {
    // studentToAdd.updateCourseHash(newCourseCode, courseSelected);
    // studentList.add(studentToAdd);
    // }
    // }

    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);
    // System.out.println("Course code change successful!");

    // } else {
    // System.out.println("Course does not exist");
    // }

    // break;

    // case 8:
    // indexDBManager.printCourses();
    // System.out.println("Enter Course to change school");
    // String course = sc.next();
    // ArrayList<CourseIndex> c = indexDBManager.getCourseIndexInfoArray(course);
    // // Get the course that the user would like to change the school
    // if (!c.isEmpty()) {
    // System.out.println("Enter new school");
    // String newSchool = sc.next();

    // ChangeSchCtrl csc = new ChangeSchCtrl();
    // courseList = csc.changeSchool(courseList, c, newSchool, course);

    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);
    // System.out.println("School updated!");
    // } else {
    // System.out.println("ERROR: Course does not exist!");
    // }
    // break;

    // case 9:
    // System.out.println("Input the course code to add an index to:");
    // String courseCodeToAddIndex = sc.next().toUpperCase();

    // // Checking if the course exists
    // ArrayList<CourseIndex> doesCourseExist = indexDBManager
    // .getCourseIndexInfoArray(courseCodeToAddIndex);
    // if (doesCourseExist.isEmpty()) {
    // System.out.println("ERROR: Course does not exist");
    // break;
    // }
    // // Getting the new course index number to add
    // System.out.println("Enter the indexNo you would like to add:");
    // while (!sc.hasNextInt()) {
    // System.out.println("ERROR: Course indexes are integers!");
    // sc.next();
    // }
    // int indexToAdd = sc.nextInt();

    // // Check if the course index already exists
    // CourseIndex courseIndexToAdd =
    // indexDBManager.getCourseIndexInfo(courseCodeToAddIndex,
    // indexToAdd);
    // if (courseIndexToAdd != null) {
    // System.out.println("Course index already exists");
    // break;
    // }

    // StaffCreateIndex staffCreateIndex = new StaffCreateIndex();
    // CourseIndex newIndex = staffCreateIndex.createIndex(indexDBManager,
    // courseCodeToAddIndex,
    // indexToAdd);
    // if (newIndex != null) {
    // courseList.add(newIndex);
    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);
    // }
    // break;

    // case 10:
    // // Getting the course
    // System.out.println("Change Index Number");
    // System.out.println("Enter the course code:");
    // String courseCode = sc.next().toUpperCase();

    // ArrayList<CourseIndex> courseToChangeIndexNo = indexDBManager
    // .getCourseIndexInfoArray(courseCode);
    // // Check if the courseCode exists
    // if (courseToChangeIndexNo.isEmpty()) {
    // System.out.println("ERROR: Course does not exist!");
    // break;
    // }

    // System.out.println("Enter the index no:");
    // while (!sc.hasNextInt()) {
    // System.out.println("ERROR: Course indexes are integers!");
    // sc.next();
    // }
    // int indexToChange = sc.nextInt();

    // CourseIndex courseIndexToChangeIndexNo =
    // indexDBManager.getCourseIndexInfo(courseCode,
    // indexToChange);
    // // Check if the course exists, if it does not, break
    // if (courseIndexToChangeIndexNo == null) {
    // System.out.println("ERROR: Course Index does not exist!");
    // break;
    // }
    // // Drop the course index from courseList
    // courseList.remove(courseIndexToChangeIndexNo);
    // // Get the new index no.
    // System.out.println("Enter the new index no:");
    // while (!sc.hasNextInt()) {
    // System.out.println("ERROR: Course indexes are integers!");
    // sc.next();
    // }
    // int newIndexNo = sc.nextInt();
    // // Check if the new index already exists
    // CourseIndex courseIndextoChangeTo =
    // indexDBManager.getCourseIndexInfo(courseCode,
    // newIndexNo);
    // if (courseIndextoChangeTo != null) {
    // System.out.println("This index already exists!");
    // break;
    // }

    // // Get all students that are affected by the change
    // ArrayList<String> matricAffected = new ArrayList<>();
    // matricAffected = courseIndexToChangeIndexNo.getRegisteredStudentMatricNo();
    // ArrayList<StudentAcc> affectedStudents = new ArrayList<>();
    // for (String matricNo : matricAffected) {
    // StudentAcc affected = studentDBManager.getStudentByMatricNo(matricNo);
    // // Remove the students from studentList
    // studentList.remove(affected);
    // affectedStudents.add(affected);
    // }

    // StaffChangeIndexCtrl staffChangeIndexCtrl = new StaffChangeIndexCtrl();

    // CourseIndex newlyChangedCourseIndex = staffChangeIndexCtrl
    // .courseIndexChangeIndex(courseIndexToChangeIndexNo, newIndexNo);
    // // courseIndexToChangeIndexNo.setIndexNo(newIndexNo);

    // if (newlyChangedCourseIndex == null) {
    // System.out.println("ERROR!");
    // courseList.add(courseIndexToChangeIndexNo);
    // } else {
    // courseList.add(newlyChangedCourseIndex);
    // }

    // indexDBManager.updateDatabase(courseList, indexDB);
    // courseIndexWriter.writeFile(indexDBManager);

    // for (StudentAcc student : affectedStudents) {
    // studentList.add(student);
    // }

    // studentDBManager.updateDatabase(studentList, studentDB);
    // studentWriter.writeFile(studentDBManager);

    // break;

    // case 11:
    // System.out.println("Logout");
    // login_access_staff = false;
    // break;
    // }
    // }
    // }
    // }
    // }

}

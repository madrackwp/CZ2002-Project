package UserInterfaces;

import java.util.ArrayList;
import java.util.Scanner;

import CourseController.AddDropCtrl;
import CourseIndex.CourseIndex;
import CourseIndex.IndexWaitList;
import DatabaseManager.CourseIndexDBManager;
import DatabaseManager.StudentDBManager;
import LocalDatabase.CourseIndexDB;
import LocalDatabase.StudentDB;
import Login.StaffLogin;
import Notification.NotificationManager;
import ReadWriteFile.*;
import StaffDuties.ChangeCourseCodeCtrl;
import StaffDuties.ChangeSchCtrl;
import StaffDuties.StaffAddStudentCtrl;
import StaffDuties.StaffChangeAccessPeriodCtrl;
import StaffDuties.StaffChangeIndexCtrl;
import StaffDuties.StaffChangeVacancyCtrl;
import StaffDuties.StaffCreateCourseCtrl;
import StaffDuties.StaffCreateIndex;
import Users.StaffAcc;
import Users.StudentAcc;

/**
 * This is the staff's implementataion of UserUI
 * 
 * @version 1.0
 * @since 2020-11-25
 */
public class StaffUI implements UserUI {
    /**
     * This creates the StaffUI object
     */
    public StaffUI() {
    }

    /**
     * This will run the StaffAcc's instance of the user interface
     */
    public void runUI() {
        int userChoice;
        StaffAcc StA = null;

        CourseIndexReader CIR = new CourseIndexReader();
        ArrayList<CourseIndex> courseList = CIR.ReadFile();
        CourseIndexDB indexDB = new CourseIndexDB(courseList);
        CourseIndexDBManager indexDBManager = new CourseIndexDBManager(indexDB);

        StudentReader ur = new StudentReader();
        ArrayList<StudentAcc> studentList = ur.ReadFile(indexDBManager);

        StaffReader staffReader = new StaffReader();
        ArrayList<StaffAcc> staffList = staffReader.ReadFile();
        StudentDB studentDB = new StudentDB(studentList);
        StudentDBManager studentDBManager = new StudentDBManager(studentDB);
        StudentWriter studentWriter = new StudentWriter();
        CourseIndexWriter courseIndexWriter = new CourseIndexWriter();

        NotificationManager notificationManager = new NotificationManager();
        Scanner sc = new Scanner(System.in);

        StaffLogin staffLogin = new StaffLogin();
        StA = staffLogin.login(staffList);
        if (StA != null) {
            boolean login_access_staff = true;
            while (login_access_staff) {
                System.out.println("");
                System.out.println("=================MAIN MENU=================");
                System.out.println("Choose option:");
                System.out.println("1. Create New Student Account"); // EH,CC done
                System.out.println("2. Change Student Access period"); // EH,CC done
                System.out.println("3. View course index vacancy"); // EH,CC done
                System.out.println("4. Change Vacancies"); // EH,CC done
                System.out.println("5. Print students by Index Number"); // EH done, no CC needed
                System.out.println("6. Print students by Course"); // EH done, no CC needed
                System.out.println("7. Add Course Code"); // EH, CC done
                System.out.println("8. Change Course Code");// EH, CC done
                System.out.println("9. Change School");// EH, CC done
                System.out.println("10. Add index number");// CC done, EH not done
                System.out.println("11. Change index number");
                System.out.println("12. Logout"); // done
                System.out.println("===========================================");
                System.out.println("");

                userChoice = sc.nextInt();

                AddDropCtrl addDropCtrl = new AddDropCtrl();
                switch (userChoice) {
                    case 1:
                        StaffAddStudentCtrl addStu = new StaffAddStudentCtrl();
                        StudentAcc newStudent = addStu.AddStudent();

                        StudentAcc studentCheck = studentDBManager.getStudentByMatricNo(newStudent.getMatricNo());
                        if (studentCheck != null) {
                            System.out.println("Student already exists!");
                            break;
                        }

                        studentList.add(newStudent);
                        studentDBManager.updateDatabase(studentList, studentDB);
                        studentWriter.writeFile(studentDBManager);

                        studentDBManager.printAllStudents();

                        break;

                    case 2:
                        String newAccessDate = "01/01/2020";

                        System.out.println("Enter Student Matric No to change access period:");
                        String studentMatric = sc.next().toUpperCase();
                        StudentAcc studentChangeAccess = studentDBManager.getStudentByMatricNo(studentMatric);

                        if (studentChangeAccess != null) {
                            studentList.remove(studentChangeAccess);
                            System.out.println("Enter the new access date in the format dd/MM/YYYY");
                            newAccessDate = sc.next();
                            StaffChangeAccessPeriodCtrl scapc = new StaffChangeAccessPeriodCtrl();
                            studentChangeAccess = scapc.changeAccessPeriod(studentChangeAccess, newAccessDate);
                            studentList.add(studentChangeAccess);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);
                        } else {
                            System.out.println("This student does not exist!");
                        }

                        break;

                    case 3:
                        indexDBManager.printIndexes();
                        // System.out.println("Enter Course Code to Check: ");
                        // String ccCheck = sc.next();
                        // System.out.println("Enter Index Number to Check: ");
                        // while (!sc.hasNextInt()) {
                        // System.out.println("Only Integers Allowed");
                        // sc.next();
                        // }
                        // int iCheck = sc.nextInt();
                        // CheckVacancyCtrl cvc = new CheckVacancyCtrl();
                        // int vacancyCheck = cvc.getVacancies(ccCheck, iCheck, indexDBManager);
                        // if (vacancyCheck == -1) {
                        // System.out.println("Invalid Course/Index");
                        // } else {
                        // System.out.println("Number of Vacancies: " + vacancyCheck);
                        // }
                        // System.out.println("");
                        break;

                    case 4:
                        indexDB.print();

                        // To find the course
                        System.out.println("Enter course to change vacancy");
                        String courseToChange = sc.next().toUpperCase();
                        System.out.println("Enter index of course to change vacancies");
                        int courseIndexToChange;
                        while (true) {
                            while (!sc.hasNextInt()) {
                                System.out.println("ERROR: Course indexNo are integers");
                                sc.next();
                            }
                            courseIndexToChange = sc.nextInt();
                            if (courseIndexToChange < 0) {
                                System.out.println("ERROR: Course indexNo are positive");
                            } else {
                                break;
                            }
                        }

                        CourseIndex courseToChangeVacancy = indexDBManager.getCourseIndexInfo(courseToChange,
                                courseIndexToChange);
                        // System.out.println("DEBUG: " + courseToChangeVacancy);
                        // If the course exists
                        int vacancy;
                        if (courseToChangeVacancy != null) {
                            System.out.println("Value to change to:");

                            while (true) {
                                while (!sc.hasNextInt()) {
                                    System.out.println("ERROR: Vacancy must be an integer");
                                    sc.next();
                                }
                                vacancy = sc.nextInt();
                                if (vacancy < 0) {
                                    System.out.println("ERROR: Vacancies can only be positive");
                                } else {
                                    break;
                                }
                            }

                            courseList.remove(courseToChangeVacancy);
                            StaffChangeVacancyCtrl staffChangeVacancyCtrl = new StaffChangeVacancyCtrl();
                            CourseIndex updatedCourseIndex = staffChangeVacancyCtrl
                                    .changeVacancies(courseToChangeVacancy, vacancy);

                            // This is the look for anyone that is on the waitlist and add them into the
                            // courseIndex
                            IndexWaitList iwl = updatedCourseIndex.getIndexWaitList();
                            // if (iwl.getWaitList().get(0).equals("null")) { // There is no one in the
                            // waitList
                            // courseList.add(updatedCourseIndex);
                            // indexDBManager.updateDatabase(courseList, indexDB);
                            // courseIndexWriter.writeFile(indexDBManager);
                            // break;
                            // }
                            for (int i = 0; i < vacancy; i++) {
                                if (iwl.getWaitList().size() == 0) {
                                    break;
                                }

                                String matricNo = iwl.getWaitList().get(0);
                                StudentAcc student = studentDBManager.getStudentByMatricNo(matricNo);
                                if (student == null) {
                                    break;
                                }
                                studentList.remove(student);

                                boolean boolCheck = addDropCtrl.addCourse(student, updatedCourseIndex);
                                if (boolCheck) {
                                    notificationManager.sendEmail(student.getUserName(), student.getName(),
                                            updatedCourseIndex);
                                } else {
                                    System.out.println("Add fail");
                                }

                                // Send email here
                                studentList.add(student);

                            }
                            courseList.add(updatedCourseIndex);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);

                        } else {
                            System.out.println("This course code/index number does not exist.");
                        }
                        break;

                    case 5:
                        // Display all courseIndexes
                        indexDB.print();

                        System.out.println("Enter Course");
                        String course1 = sc.next().toUpperCase();
                        System.out.println("Enter Index");

                        int index1;
                        while (true) {
                            while (!sc.hasNextInt()) {
                                System.out.println("ERROR: Course indexNo are integers");
                                sc.next();
                            }
                            index1 = sc.nextInt();
                            if (index1 < 0) {
                                System.out.println("ERROR: Course indexNo are positive");
                            } else {
                                break;
                            }
                        }

                        CourseIndex courseIndex = indexDBManager.getCourseIndexInfo(course1, index1);
                        if (courseIndex != null) {
                            ArrayList<String> mat = courseIndex.getRegisteredStudentMatricNo();
                            for (int i = 0; i < mat.size(); i++) {
                                StudentAcc temp = studentDBManager.getStudentByMatricNo(mat.get(i));
                                System.out.println(temp);
                            }
                        } else {
                            System.out.println("This course/index number does not exist");
                        }
                        break;

                    case 6:
                        // Display all courseIndexes
                        indexDB.print();

                        System.out.println("Enter Course");
                        String course2 = sc.next();
                        ArrayList<CourseIndex> courseIndexToPrint = indexDBManager.getCourseIndexInfoArray(course2);
                        if (!courseIndexToPrint.isEmpty()) {
                            ArrayList<String> mat1 = new ArrayList<String>();
                            for (int i = 0; i < courseIndexToPrint.size(); i++) {
                                for (int j = 0; j < courseIndexToPrint.get(i).getRegisteredStudentMatricNo()
                                        .size(); j++) {
                                    if (!courseIndexToPrint.get(i).getRegisteredStudentMatricNo().get(j).equals("null"))
                                        mat1.add((courseIndexToPrint.get(i).getRegisteredStudentMatricNo().get(j)));
                                }
                            }
                            for (int z = 0; z < mat1.size(); z++) {
                                StudentAcc temp = studentDBManager.getStudentByMatricNo(mat1.get(z));
                                System.out.print("Index: " + temp.getCourseIndex(course2).getIndexNo());
                                System.out.println(" | " + temp);
                            }
                        } else {
                            System.out.println("This course code does not exist");
                        }
                        break;

                    case 7:
                        System.out.println("Enter the course code you would like to create: ");
                        String courseCodeToCreate = sc.next().toUpperCase();
                        ArrayList<CourseIndex> checkIfCourseExist = indexDBManager
                                .getCourseIndexInfoArray(courseCodeToCreate);

                        if (checkIfCourseExist.isEmpty()) { // The course code does not exist yet
                            StaffCreateCourseCtrl staffCreateCourseCtrl = new StaffCreateCourseCtrl();
                            ArrayList<CourseIndex> newCourseIndexes = staffCreateCourseCtrl
                                    .createCourse(courseCodeToCreate);

                            if (newCourseIndexes != null) { // This is to check if the courseCode was returned
                                for (CourseIndex newCourse : newCourseIndexes) {
                                    courseList.add(newCourse);
                                }
                                indexDBManager.updateDatabase(courseList, indexDB);
                                courseIndexWriter.writeFile(indexDBManager);
                            } else {
                                System.out.println("No new course created");
                            }
                        } else {
                            System.out.println("This course already exists!");
                        }

                        break;

                    case 8:

                        indexDBManager.printCourses();
                        // Getting user input for the course to chagnge
                        System.out.println("Enter the course code for the course to change");
                        String courseSelected = sc.next();
                        ArrayList<CourseIndex> courses = indexDBManager.getCourseIndexInfoArray(courseSelected);

                        // If the course does not exist, the arrayList will be empty and enter the else
                        if (!courses.isEmpty()) {
                            System.out.println("Enter new Course Code");
                            String newCourseCode = sc.next();

                            // Check whether this course code already exists
                            ArrayList<CourseIndex> courseCheck = indexDBManager.getCourseIndexInfoArray(newCourseCode);
                            if (!courseCheck.isEmpty()) {
                                // Course code already exists
                                System.out.println("ERROR: This coursecode already exists!");
                                break;
                            }

                            // ArrayList that will store all affected students
                            ArrayList<StudentAcc> studentAffected = new ArrayList<>();
                            for (CourseIndex c : courses) {
                                ArrayList<String> studentsInCourse = c.getRegisteredStudentMatricNo();
                                for (String students : studentsInCourse) {
                                    StudentAcc droppingStudents = studentDBManager.getStudentByMatricNo(students);
                                    studentList.remove(droppingStudents);
                                    studentAffected.add(droppingStudents);
                                }
                            }

                            ChangeCourseCodeCtrl ccc = new ChangeCourseCodeCtrl();
                            courseList = ccc.changeCourseCode(courseList, courses, courseSelected, newCourseCode);

                            for (StudentAcc studentToAdd : studentAffected) {
                                if (studentToAdd != null) {
                                    studentToAdd.updateCourseHash(newCourseCode, courseSelected);
                                    studentList.add(studentToAdd);
                                }
                            }

                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);
                            System.out.println("Course code change successful!");

                        } else {
                            System.out.println("Course does not exist");
                        }
                        indexDBManager.printCourses();

                        break;

                    case 9:
                        indexDBManager.printCourses();
                        System.out.println("Enter Course to change school");
                        String course = sc.next();
                        ArrayList<CourseIndex> c = indexDBManager.getCourseIndexInfoArray(course);
                        // Get the course that the user would like to change the school
                        if (!c.isEmpty()) {
                            System.out.println("Enter new school");
                            String newSchool = sc.next();

                            ChangeSchCtrl csc = new ChangeSchCtrl();
                            courseList = csc.changeSchool(courseList, c, newSchool, course);

                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);
                            System.out.println("School updated!");
                        } else {
                            System.out.println("ERROR: Course does not exist!");
                        }
                        indexDBManager.printCourses();
                        break;

                    case 10:
                        System.out.println("Input the course code to add an index to:");
                        String courseCodeToAddIndex = sc.next().toUpperCase();

                        // Checking if the course exists
                        ArrayList<CourseIndex> doesCourseExist = indexDBManager
                                .getCourseIndexInfoArray(courseCodeToAddIndex);
                        if (doesCourseExist.isEmpty()) {
                            System.out.println("ERROR: Course does not exist");
                            break;
                        }
                        // Getting the new course index number to add
                        System.out.println("Enter the indexNo you would like to add:");
                        int indexToAdd;
                        while (true) {
                            while (!sc.hasNextInt()) {
                                System.out.println("ERROR: Course indexNo are integers");
                                sc.next();
                            }
                            indexToAdd = sc.nextInt();
                            if (indexToAdd < 0) {
                                System.out.println("ERROR: Course indexNo are positive");
                            } else {
                                break;
                            }
                        }

                        // Check if the course index already exists
                        CourseIndex courseIndexToAdd = indexDBManager.getCourseIndexInfo(courseCodeToAddIndex,
                                indexToAdd);
                        if (courseIndexToAdd != null) {
                            System.out.println("Course index already exists");
                            break;
                        }

                        StaffCreateIndex staffCreateIndex = new StaffCreateIndex();
                        CourseIndex newIndex = staffCreateIndex.createIndex(indexDBManager, courseCodeToAddIndex,
                                indexToAdd);
                        if (newIndex != null) {
                            courseList.add(newIndex);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);
                        }
                        indexDBManager.printIndexes();
                        break;

                    case 11:
                        // Getting the course
                        System.out.println("Change Index Number");
                        System.out.println("Enter the course code:");
                        String courseCode = sc.next().toUpperCase();

                        ArrayList<CourseIndex> courseToChangeIndexNo = indexDBManager
                                .getCourseIndexInfoArray(courseCode);
                        // Check if the courseCode exists
                        if (courseToChangeIndexNo.isEmpty()) {
                            System.out.println("ERROR: Course does not exist!");
                            break;
                        }

                        System.out.println("Enter the index no:");
                        int indexToChange;
                        while (true) {
                            while (!sc.hasNextInt()) {
                                System.out.println("ERROR: Course indexNo are integers");
                                sc.next();
                            }
                            indexToChange = sc.nextInt();
                            if (indexToChange < 0) {
                                System.out.println("ERROR: Course indexNo are positive");
                            } else {
                                break;
                            }
                        }

                        CourseIndex courseIndexToChangeIndexNo = indexDBManager.getCourseIndexInfo(courseCode,
                                indexToChange);
                        // Check if the course exists, if it does not, break
                        if (courseIndexToChangeIndexNo == null) {
                            System.out.println("ERROR: Course Index does not exist!");
                            break;
                        }
                        // Drop the course index from courseList
                        courseList.remove(courseIndexToChangeIndexNo);
                        // Get the new index no.
                        System.out.println("Enter the new index no:");
                        while (!sc.hasNextInt()) {
                            System.out.println("ERROR: Course indexes are integers!");
                            sc.next();
                        }
                        int newIndexNo = sc.nextInt();
                        // Check if the new index already exists
                        CourseIndex courseIndextoChangeTo = indexDBManager.getCourseIndexInfo(courseCode, newIndexNo);
                        if (courseIndextoChangeTo != null) {
                            System.out.println("This index already exists!");
                            break;
                        }

                        // Get all students that are affected by the change
                        ArrayList<String> matricAffected = new ArrayList<>();
                        matricAffected = courseIndexToChangeIndexNo.getRegisteredStudentMatricNo();
                        ArrayList<StudentAcc> affectedStudents = new ArrayList<>();
                        for (String matricNo : matricAffected) {
                            StudentAcc affected = studentDBManager.getStudentByMatricNo(matricNo);
                            // Remove the students from studentList
                            studentList.remove(affected);
                            affectedStudents.add(affected);
                        }

                        StaffChangeIndexCtrl staffChangeIndexCtrl = new StaffChangeIndexCtrl();

                        CourseIndex newlyChangedCourseIndex = staffChangeIndexCtrl
                                .courseIndexChangeIndex(courseIndexToChangeIndexNo, newIndexNo);
                        // courseIndexToChangeIndexNo.setIndexNo(newIndexNo);

                        if (newlyChangedCourseIndex == null) {
                            System.out.println("ERROR!");
                            courseList.add(courseIndexToChangeIndexNo);
                        } else {
                            courseList.add(newlyChangedCourseIndex);
                        }

                        indexDBManager.updateDatabase(courseList, indexDB);
                        courseIndexWriter.writeFile(indexDBManager);

                        for (StudentAcc student : affectedStudents) {
                            studentList.add(student);
                        }

                        studentDBManager.updateDatabase(studentList, studentDB);
                        studentWriter.writeFile(studentDBManager);

                        indexDBManager.printIndexes();

                        break;

                    case 12:
                        System.out.println("Bye bye! ");
                        login_access_staff = false;
                        break;
                }
            }
        }
    }
}

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import CourseController.*;
import CourseIndex.CourseIndex;
import CourseIndex.IndexWaitList;
import CourseIndex.Lesson;
import CourseIndex.ModType;
import DatabaseManager.CourseIndexDBManager;
import DatabaseManager.StudentDBManager;
import LocalDatabase.*;
import Login.StaffLogin;
import Login.StudentLogin;
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
            StudentLogin studentLogin = new StudentLogin();
            SA = studentLogin.login(studentList);
            // System.out.println(SA.getUserName());
            if (SA != null) {
                boolean login_access_student = true;
                while (login_access_student) {
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

                    userChoice = sc.nextInt();
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
                            // SA.getTimetable().printTimetable();
                            String courseToDrop;
                            System.out.println("Enter Course to Drop: ");
                            while (true){
                                courseToDrop = sc.next();
                                if (SA.takingCourse(courseToDrop)){
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
                            System.out.println("Enter Course Code to Change Index: ");
                            String courseToChange;
                            while (true){
                                courseToChange = sc.next();
                                if (SA.takingCourse(courseToChange)){
                                    break;
                                }
                                System.out.println("Course Not Taken");
                                System.out.println("Please Re-enter Course Code to Change Index: ");
                            }

                            CourseIndex indexToDrop = SA.getCourseIndex(courseToChange);
                            int newCourseIndex = cic.displayValidCourseToChange(courseToChange, indexDBManager, SA);
                            CourseIndex indexToChangeTo = indexDBManager.getCourseIndexInfo(courseToChange,
                                    newCourseIndex);

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
                            StudentAcc student2 = studentLogin.login(studentList);
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
                                System.out.println("Unsucessful");
                            }
                            break;
                        case 9:
                            System.out.println("Bye bye!");
                            login_access_student = false;
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
            StaffLogin staffLogin = new StaffLogin();
            StA = staffLogin.login();
            if (StA != null) {
                boolean login_access_staff = true;
                while (login_access_staff) {
                    System.out.println("Choose option:");
                    System.out.println("1. Create New Student Account");
                    System.out.println("2. Change Student Access period"); // done
                    System.out.println("3. Change Vacancies"); // done
                    System.out.println("4. Print students by Index Number"); // done
                    System.out.println("5. Print students by Course"); // done
                    System.out.println("6. Add Course Code"); // done
                    System.out.println("7. Change Course Code"); // done
                    System.out.println("8. Change School"); // done
                    System.out.println("9. Add index number");// done
                    System.out.println("10. Change index number");
                    System.out.println("11. Logout"); // done
                    System.out.println("===========================================");

                    userChoice = sc.nextInt();

                    AddDropCtrl addDropCtrl = new AddDropCtrl();
                    StaffAddDrop addDropStaff = new StaffAddDrop();
                    ShowAllCoursesCtrl showAllCoursesCtrl = new ShowAllCoursesCtrl();

                    switch (userChoice) {
                        case 1:
                            StaffAddStudentCtrl addStu = new StaffAddStudentCtrl();
                            StudentAcc newStudent = addStu.AddStudent();
                            studentList.add(newStudent);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);
                            break;
                        case 2:
                            // // SA.getTimetable().printTimetable();
                            // System.out.println("Enter course to drop");
                            // String courseToDrop = sc.next();
                            // addDropCtrl.dropCourse(SA, courseToDrop);
                            // SA.getTimetable().printTimetable();
                            // System.out.println("");
                            System.out.println("Enter Student Matric No to change access period:");
                            String studentMatric = sc.next();
                            StudentAcc studentChangeAccess = studentDBManager.getStudentByMatricNo(studentMatric);

                            studentList.remove(studentChangeAccess);
                            System.out.println("Enter the new access date in the format dd/MM/YYYY");
                            String newAccessDate = sc.next();
                            studentChangeAccess.setAccessDate(newAccessDate);

                            studentList.add(studentChangeAccess);
                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

                            break;

                        case 3:
                            indexDB.print();
                            System.out.println("Enter course to change vacancy");
                            String courseToChange = sc.next();
                            System.out.println("Enter index of course to change vacancies");
                            int courseIndexToChange = sc.nextInt();
                            CourseIndex courseToChangeVacancy = indexDBManager.getCourseIndexInfo(courseToChange,
                                    courseIndexToChange);

                            System.out.println("Value to change to:");
                            int vacancy = sc.nextInt();

                            courseList.remove(courseToChangeVacancy);
                            StaffChangeVacancyCtrl staffChangeVacancyCtrl = new StaffChangeVacancyCtrl();
                            staffChangeVacancyCtrl.changeVacancies(courseToChangeVacancy, vacancy);

                            IndexWaitList iwl = courseToChangeVacancy.getIndexWaitList();
                            for (int i = 0; i < vacancy; i++) {

                                String matricNo = iwl.getWaitList().get(0);
                                StudentAcc student = studentDBManager.getStudentByMatricNo(matricNo);
                                studentList.remove(student);
                                addDropCtrl.addCourse(student, courseToChangeVacancy);
                                System.out.println("Student" + i);
                                // Send email here\
                                studentList.add(student);

                            }

                            courseList.add(courseToChangeVacancy);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);

                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);
                            break;

                        case 4:
                            System.out.println("Enter Course");
                            String course1 = sc.next();
                            System.out.println("Enter Index");
                            int index1 = sc.nextInt();
                            CourseIndex courseIndex = indexDBManager.getCourseIndexInfo(course1, index1);
                            ArrayList<String> mat = courseIndex.getRegisteredStudentMatricNo();
                            for (int i = 0; i < mat.size(); i++) {
                                StudentAcc temp = studentDBManager.getStudentByMatricNo(mat.get(i));
                                System.out.println(temp);
                            }
                            break;

                        case 5:
                            System.out.println("Enter Course");
                            String course2 = sc.next();
                            ArrayList<CourseIndex> courseIndex1 = indexDBManager.getCourseIndexInfoArray(course2);
                            ArrayList<String> mat1 = new ArrayList<String>();
                            for (int i = 0; i < courseIndex1.size(); i++) {
                                // System.out.println(courseIndex1.size());
                                for (int j = 0; j < courseIndex1.get(i).getRegisteredStudentMatricNo().size(); j++) {
                                    if (!courseIndex1.get(i).getRegisteredStudentMatricNo().get(j).equals("null"))
                                        mat1.add((courseIndex1.get(i).getRegisteredStudentMatricNo().get(j)));
                                }
                            }
                            for (int z = 0; z < mat1.size(); z++) {
                                StudentAcc temp = studentDBManager.getStudentByMatricNo(mat1.get(z));
                                System.out.print("Index: " + temp.getCourseIndex(course2).getIndexNo());
                                System.out.println(" | " + temp);
                                // temp.print();
                            }
                            break;

                        case 6:

                            StaffCreateCourseCtrl staffCreateCourseCtrl = new StaffCreateCourseCtrl();
                            ArrayList<CourseIndex> newCourseIndexes = staffCreateCourseCtrl.createCourse();

                            for (CourseIndex newCourse : newCourseIndexes) {
                                courseList.add(newCourse);
                            }

                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);
                            break;

                        case 7:
                            System.out.println("Enter Course to change");
                            String course4 = sc.next();
                            ArrayList<CourseIndex> courseIndex4 = indexDBManager.getCourseIndexInfoArray(course4);

                            System.out.println("Enter new Course Code");
                            String courseCode4 = sc.next();

                            for (int i = 0; i < courseIndex4.size(); i++) {
                                // System.out.println(courseIndex1.size());
                                courseList.remove(courseIndex4.get(i));
                                if (courseIndex4.get(i).getCourseCode().equals(course4))
                                    courseIndex4.get(i).setCourseCode(courseCode4);
                                courseList.add(courseIndex4.get(i));
                            }
                            System.out.println(courseIndex4);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);
                            break;

                        case 8:
                            System.out.println("Enter Course");
                            String course3 = sc.next();
                            ArrayList<CourseIndex> courseIndex3 = indexDBManager.getCourseIndexInfoArray(course3);

                            System.out.println("Enter School");
                            String school3 = sc.next();

                            for (int i = 0; i < courseIndex3.size(); i++) {
                                // System.out.println(courseIndex1.size());
                                courseList.remove(courseIndex3.get(i));
                                if (courseIndex3.get(i).getCourseCode().equals(course3))
                                    courseIndex3.get(i).setSchool(school3);
                                courseList.add(courseIndex3.get(i));
                            }
                            System.out.println(courseIndex3);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);
                            break;

                        case 9:
                            StaffCreateIndex staffCreateIndex = new StaffCreateIndex();
                            CourseIndex newIndex = staffCreateIndex.createIndex(indexDBManager);
                            courseList.add(newIndex);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);
                            break;

                        case 10:
                            System.out.println("Change Index Number");
                            System.out.println("Enter the course code:");
                            String courseCode = sc.next();
                            System.out.println("Enter the index no:");
                            int indexToChange = sc.nextInt();

                            System.out.println("Enter the new index no:");
                            int newIndexNo = sc.nextInt();

                            CourseIndex courseIndexToChangeIndexNo = indexDBManager.getCourseIndexInfo(courseCode,
                                    indexToChange);

                            courseList.remove(courseIndexToChangeIndexNo);

                            ArrayList<String> matricAffected = new ArrayList<>();
                            matricAffected = courseIndexToChangeIndexNo.getRegisteredStudentMatricNo();

                            ArrayList<StudentAcc> affectedStudents = new ArrayList<>();
                            for (String matricNo : matricAffected) {
                                StudentAcc affected = studentDBManager.getStudentByMatricNo(matricNo);
                                studentList.remove(affected);
                                affectedStudents.add(affected);
                            }

                            courseIndexToChangeIndexNo.setIndexNo(newIndexNo);

                            courseList.add(courseIndexToChangeIndexNo);
                            indexDBManager.updateDatabase(courseList, indexDB);
                            courseIndexWriter.writeFile(indexDBManager);

                            for (StudentAcc student : affectedStudents) {
                                studentList.add(student);
                            }

                            studentDBManager.updateDatabase(studentList, studentDB);
                            studentWriter.writeFile(studentDBManager);

                            break;

                        case 11:
                            System.out.println("Logout");
                            login_access_staff = false;
                            break;
                    }
                }
            }
        }
    }

}

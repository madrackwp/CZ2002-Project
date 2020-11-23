package CourseController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import Users.StudentAcc;
import DatabaseManager.CourseIndexDBManager;
import Timetable.Timetable;

public class ChangeIndexCtrl {
    public ChangeIndexCtrl() {

    }

    public int displayValidCourseToChange(String course, CourseIndexDBManager dbManager, StudentAcc student) {
        CourseIndex currentCourse = student.getCourseIndex(course);
        ArrayList<CourseIndex> validCourseIndexes = new ArrayList<>();
        List <Integer> listOfIndexes = new ArrayList<Integer>();

        for (CourseIndex i : dbManager.getCourseIndexes()) {
            if (i.getCourseCode().equals(course) && (!i.equals(currentCourse))) {
                validCourseIndexes.add(i);
            }
        }
        System.out.println("");
        System.out.println("Other Course Indexes: ");
        for (CourseIndex j : validCourseIndexes) {
            System.out.println(j.getIndexNo());
            listOfIndexes.add(j.getIndexNo());
        }
        System.out.println("Enter New Course Index: ");
        int newCourseIndex;
        while (true){
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                System.out.println("Only Integers Accepted");
                sc.next();
            }
            newCourseIndex = sc.nextInt();
            if (listOfIndexes.contains(newCourseIndex)){
                break;
            }
            System.out.println("Invalid Course Index");
            System.out.println("Please Re-enter New Course Index: ");
        }
        return newCourseIndex;
    }

    public ArrayList<CourseIndex> changeIndex(CourseIndex newCourseIndex, StudentAcc student,
            CourseIndex oldCourseIndex, CourseIndexDBManager courseIndexDBManager, AddDropCtrl addDropCtrl) {
        ArrayList<CourseIndex> courseIndexes = new ArrayList<CourseIndex>();

        addDropCtrl.dropCourse(student, oldCourseIndex.getCourseCode());
        Timetable timetable = student.getTimetable();
        if (timetable.checkEmptySlot(newCourseIndex)) {
            addDropCtrl.addCourse(student, newCourseIndex);
            System.out.println("Change Successful");
        } else {
            System.out.println("Timing Clash, Change Unsuccessful");
            addDropCtrl.addCourse(student, oldCourseIndex);
        }
        courseIndexes.add(oldCourseIndex);
        courseIndexes.add(newCourseIndex);
        return courseIndexes;
    }
}

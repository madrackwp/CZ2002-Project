package CourseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import Users.StudentAcc;
import DatabaseManager.CourseIndexDBManager;
import Timetable.Timetable;

/**
 * Controller that changes the course index for a student
 * @author Chong Jing Hong
 * @since 25/11/2020
 */

public class ChangeIndexCtrl {

    /**
     * Creates the ChangeIndex controller
     */

    public ChangeIndexCtrl() {

    }

    /**
     * Displays the valid courses that can be changed to
     * @param course The course code of the course index to be changed
     * @param dbManager The CourseIndexDBManager
     * @param student The student account that wants to change his/her course index
     * @return The desired course index
     */

    public int displayValidCourseToChange(String course, CourseIndexDBManager dbManager, StudentAcc student) {
        CourseIndex currentCourse = student.getCourseIndex(course);
        ArrayList<CourseIndex> validCourseIndexes = new ArrayList<>();
        List<Integer> listOfIndexes = new ArrayList<Integer>();

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
        while (true) {
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                System.out.println("Only Integers Accepted");
                sc.next();
            }
            newCourseIndex = sc.nextInt();
            if (listOfIndexes.contains(newCourseIndex)) {
                break;
            }
            System.out.println("Invalid Course Index");
            System.out.println("Please Re-enter New Course Index: ");
        }
        return newCourseIndex;
    }

    /**
     * Changes the course index of the student
     * @param newCourseIndex the desired course index to change to
     * @param student the student account that wants to change the course index
     * @param oldCourseIndex the course index the student is currently holding
     * @param courseIndexDBManager The CourseIndexDBManager
     * @param addDropCtrl The add/drop controller
     * @return Updated array list of CourseIndex
     */

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

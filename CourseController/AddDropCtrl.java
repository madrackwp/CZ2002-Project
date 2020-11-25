package CourseController;

import CourseIndex.*;
import Timetable.Timetable;
import Users.*;

/**
 * Controller that adds and drops courses for a student
 * @author Chong Jing Hong
 * @since 25/11/2020
 */

public class AddDropCtrl {

    /**
     * Creates the add/drop controller
     */

    public AddDropCtrl() {
    }

    /**
     * Registers a student for a course
     * @param student The student to be registered
     * @param courseToAdd The course to be added to the student account
     * @return whether or not the adding is successful
     */

    public boolean addCourse(StudentAcc student, CourseIndex courseToAdd) {
        Timetable timetable = student.getTimetable();

        if (timetable.checkEmptySlot(courseToAdd)) {
            if (courseToAdd.getVacancies() == 0) {
                courseToAdd.addToWaitList(student.getMatricNo());
                System.out.println("No Vacancies, Added to Waitlist");
                return false;
            }
            student.addNewCourse(courseToAdd);
            courseToAdd.addStudent(student.getMatricNo());
            return true;
        } else {
            System.out.println("Time Clash, Add Unsuccessful! ");
            return false;
        }
    }

    /**
     * Drops a course for a student
     * @param student the student account to drop a course
     * @param courseToDrop the course code to be dropped
     * @return the CourseIndex object that has been updated
     */

    public CourseIndex dropCourse(StudentAcc student, String courseToDrop) {
        CourseIndex droppingCourse = student.getCourseIndex(courseToDrop);
        if (courseToDrop != null) {
            student.removeCourse(droppingCourse);
            droppingCourse.removeStudent(student.getMatricNo());
            return droppingCourse;
        }
        return null;
    }

}

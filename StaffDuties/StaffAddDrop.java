package StaffDuties;

import CourseIndex.*;
import Timetable.Timetable;
import Users.*;

/**
 * Controller used by Staff to add or drop courses for a student
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class StaffAddDrop {
    /**
     * Constructor
     */
    public StaffAddDrop() {
    }

    /**
     * Method for staff to add course for student
     * 
     * @param student     StudentAcc to add
     * @param courseToAdd CourseIndex to add student to
     * @return True is added successfully, else false
     */
    public boolean addCourse(StudentAcc student, CourseIndex courseToAdd) {
        Timetable timetable = student.getTimetable();
        for (CourseIndex courseIndex : student.getRegisteredCourseIndex()) {
            if (courseIndex.getCourseCode().equals(courseToAdd.getCourseCode())) {
                System.out.println("Already taking course");
                return false;
            }
        }
        if (timetable.checkEmptySlot(courseToAdd)) {
            System.out.println("Overwrite System vacancies");
            student.addNewCourse(courseToAdd);
            courseToAdd.addStudentEvenIfZero(student.getMatricNo());
            return true;

        } else {
            System.out.println("Time clash!");
            return false;
        }
    }

}

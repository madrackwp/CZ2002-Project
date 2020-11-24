package CourseController;

import CourseIndex.*;
import Timetable.Timetable;
import Users.*;

public class AddDropCtrl {
    public AddDropCtrl() {
    }

    public boolean addCourse(StudentAcc student, CourseIndex courseToAdd) {
        Timetable timetable = student.getTimetable();
        // for (CourseIndex courseIndex : student.getRegisteredCourseIndex()) {
        // if (courseIndex.getCourseCode().equals(courseToAdd.getCourseCode())) {
        // System.out.println("Already taking course");
        // return false;
        // }
        // }
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

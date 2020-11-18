package CourseController;

import java.util.Scanner;

import CourseIndex.*;
import Timetable.Timetable;
import Users.*;

public class AddDropController {
    public AddDropController() {
    }

    public boolean addCourse(StudentAcc student, CourseIndex courseToAdd) {
        Timetable timetable = student.getTimetable();
        System.out.println(courseToAdd.getVacancies());
        for (CourseIndex courseIndex : student.getRegisteredCourseIndex()) {
            // System.out.println("Inside for loop " + courseIndex);
            // System.out.println("Watashi is here!");
            if (courseIndex.getCourseCode().equals(courseToAdd.getCourseCode())) {
                System.out.println("Already taking course");
                return false;
            }
        }
        if (timetable.checkEmptySlot(courseToAdd)) {
            if (courseToAdd.getVacancies() == 0) {
                System.out.println("No vacancies, added to waitlist");
                return false;
            }
            student.addNewCourse(courseToAdd);
            courseToAdd.addStudent(student.getMatricNo());
            return true;
        } else {
            System.out.println("Time clash!");
            return false;
        }
    }

    public boolean dropCourse(StudentAcc student, String courseToDrop) {
        CourseIndex droppingCourse = student.getCourseIndex(courseToDrop);
        if (droppingCourse != null) {
            student.removeCourse(droppingCourse);
            droppingCourse.removeStudent(student.getMatricNo());
            return true;
        }

        return false;
    }

}
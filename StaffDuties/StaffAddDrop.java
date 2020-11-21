package StaffDuties;

import CourseIndex.*;
import DatabaseManager.CourseIndexDBManager;
import LocalDatabase.CourseIndexDB;
import ReadWriteFile.CourseIndexReader;
import Timetable.Timetable;
import Users.*;

public class StaffAddDrop {
    public boolean addCourse(StudentAcc student, CourseIndex courseToAdd) {
        Timetable timetable = student.getTimetable();
        for (CourseIndex courseIndex : student.getRegisteredCourseIndex()) {
            if (courseIndex.getCourseCode().equals(courseToAdd.getCourseCode())) {
                System.out.println("Already taking course");
                return false;
            }
        }
        if (timetable.checkEmptySlot(courseToAdd)) {
            if (courseToAdd.getVacancies() == 0) {
                System.out.println("Overwrite System vacancies");
                student.addNewCourse(courseToAdd);
                courseToAdd.addStudentEvenIfZero(student.getMatricNo());
                return true;
            }
            student.addNewCourse(courseToAdd);
            courseToAdd.addStudent(student.getMatricNo());
            return true;
        } else {
            System.out.println("Time clash!");
            return false;
        }
    }

    public boolean changeVacancies(CourseIndexDB courseToChange, String course, int index, int vacancies) {
        for (CourseIndex courseIndex: courseToChange.getCourseIndexes()) {
            courseIndex.changeVacancies(course, index, vacancies);
                return true;
        }
        return false;
    }
}

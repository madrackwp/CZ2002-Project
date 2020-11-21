package CourseController;

import java.util.ArrayList;
import CourseIndex.CourseIndex;
import Users.StudentAcc;
import DatabaseManager.CourseIndexDBManager;
import Timetable.Timetable;

public class ChangeIndexCtrl {
    public ChangeIndexCtrl() {

    }

    public void displayValidCourseToChange(String course, CourseIndexDBManager dbManager, StudentAcc student) {
        CourseIndex currentCourse = student.getCourseIndex(course);
        ArrayList<CourseIndex> validCourseIndexes = new ArrayList<>();

        for (CourseIndex i : dbManager.getCourseIndexes()) {
            if (i.getCourseCode().equals(course) && (!i.equals(currentCourse))) {
                validCourseIndexes.add(i);
            }
        }
        for (CourseIndex j : validCourseIndexes) {
            System.out.println(j.getIndexNo());
        }
    }

    public ArrayList<CourseIndex> changeIndex(CourseIndex newCourseIndex, StudentAcc student,
            CourseIndex oldCourseIndex, CourseIndexDBManager courseIndexDBManager, AddDropCtrl addDropCtrl) {
        ArrayList<CourseIndex> courseIndexes = new ArrayList<CourseIndex>();

        addDropCtrl.dropCourse(student, oldCourseIndex.getCourseCode());
        Timetable timetable = student.getTimetable();
        if (timetable.checkEmptySlot(newCourseIndex)) {
            addDropCtrl.addCourse(student, newCourseIndex);
            System.out.println("Change successful");
        } else {
            System.out.println("Timing Clash");
            addDropCtrl.addCourse(student, oldCourseIndex);
        }
        courseIndexes.add(oldCourseIndex);
        courseIndexes.add(newCourseIndex);
        return courseIndexes;
    }
}

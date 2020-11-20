package CourseController;

import CourseIndex.CourseIndex;
import Users.StudentAcc;

public class SwapIndexCtrl {
    public SwapIndexCtrl() {

    }

    public boolean swapIndex(StudentAcc student1, StudentAcc student2, String courseCode, AddDropCtrl addDropCtrl) {
        if (student1.takingCourse(courseCode) && student2.takingCourse(courseCode)) {
            // SWAP HERE
            CourseIndex tempCourseIndex1 = student1.getCourseIndex(courseCode);
            // System.out.println(tempCourseIndex1);
            CourseIndex tempCourseIndex2 = student2.getCourseIndex(courseCode);
            // System.out.println(tempCourseIndex2);
            addDropCtrl.dropCourse(student1, courseCode);
            // System.out.println(tempCourseIndex1);
            addDropCtrl.dropCourse(student2, courseCode);
            // System.out.println(tempCourseIndex2);

            if (student1.getTimetable().checkEmptySlot(tempCourseIndex2)
                    && student2.getTimetable().checkEmptySlot(tempCourseIndex1)) {
                addDropCtrl.addCourse(student1, tempCourseIndex2);
                addDropCtrl.addCourse(student2, tempCourseIndex1);
                System.out.println("Swap success!");
                return true;
            } else {
                System.out.println("Cannot swap");
                addDropCtrl.addCourse(student1, tempCourseIndex1);
                addDropCtrl.addCourse(student2, tempCourseIndex2);
                return false;
            }

        } else {
            System.out.println("No common course code");
            return false;
        }
    }

}

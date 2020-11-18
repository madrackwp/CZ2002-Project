package CourseController;

import CourseIndex.CourseIndex;
import Users.StudentAcc;

public class SwapIndexController {
    public SwapIndexController() {

    }

    public boolean swapIndex(StudentAcc student1, StudentAcc student2, String courseCode,
            AddDropController addDropController) {
        if (student1.takingCourse(courseCode) && student2.takingCourse(courseCode)) {
            // SWAP HERE
            CourseIndex tempCourseIndex1 = student1.getCourseIndex(courseCode);
            System.out.println(tempCourseIndex1);
            CourseIndex tempCourseIndex2 = student2.getCourseIndex(courseCode);
            System.out.println(tempCourseIndex2);
            addDropController.dropCourse(student1, courseCode);
            System.out.println(tempCourseIndex1);
            addDropController.dropCourse(student2, courseCode);
            System.out.println(tempCourseIndex2);

            if (student1.getTimetable().checkEmptySlot(tempCourseIndex2)
                    && student2.getTimetable().checkEmptySlot(tempCourseIndex1)) {
                addDropController.addCourse(student1, tempCourseIndex2);
                addDropController.addCourse(student2, tempCourseIndex1);
                return true;
            } else {
                System.out.println("Cannot swap");
                addDropController.addCourse(student1, tempCourseIndex1);
                addDropController.addCourse(student2, tempCourseIndex2);
                return false;
            }

        } else {
            System.out.println("No common course code");
            return false;
        }
    }

}

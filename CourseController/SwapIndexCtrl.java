package CourseController;

import java.util.ArrayList;

import CourseIndex.CourseIndex;
import Users.StudentAcc;

public class SwapIndexCtrl {
    public SwapIndexCtrl() {

    }

    public ArrayList<CourseIndex> swapIndex(StudentAcc student1, StudentAcc student2, CourseIndex student1CourseIndex,
            CourseIndex student2CourseIndex, AddDropCtrl addDropCtrl) {

        ArrayList<CourseIndex> courseIndexes = new ArrayList<>();
        if ((student1CourseIndex.getCourseCode()).equals(student2CourseIndex.getCourseCode())) {

            // SWAP HERE

            // System.out.println(tempCourseIndex2);
            addDropCtrl.dropCourse(student1, student1CourseIndex.getCourseCode());
            // System.out.println(tempCourseIndex1);
            addDropCtrl.dropCourse(student2, student2CourseIndex.getCourseCode());
            // System.out.println(tempCourseIndex2);

            if (student1.getTimetable().checkEmptySlot(student2CourseIndex)
                    && student2.getTimetable().checkEmptySlot(student1CourseIndex)) {
                addDropCtrl.addCourse(student1, student2CourseIndex);
                addDropCtrl.addCourse(student2, student1CourseIndex);
                System.out.println("Swap success!");
                courseIndexes.add(student1CourseIndex);
                courseIndexes.add(student2CourseIndex);
                return courseIndexes;
            } else {
                System.out.println("Cannot swap");
                addDropCtrl.addCourse(student1, student1CourseIndex);
                addDropCtrl.addCourse(student2, student2CourseIndex);
                courseIndexes.add(student1CourseIndex);
                courseIndexes.add(student2CourseIndex);
                return courseIndexes;
            }

        } else {
            System.out.println("No common course code");
            courseIndexes.add(student1CourseIndex);
            courseIndexes.add(student2CourseIndex);
            return courseIndexes;
        }
    }

}

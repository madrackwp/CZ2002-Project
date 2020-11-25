package CourseController;

import java.util.ArrayList;

import CourseIndex.CourseIndex;
import Users.StudentAcc;

/**
 * Controller that swaps indexes between 2 students
 * @author Chong Jing Hong
 * @since 25/11/2020
 */

public class SwapIndexCtrl {

    /**
     * Creates the SwapIndex Controller
     */

    public SwapIndexCtrl() {

    }

    /**
     * Swaps course index between 2 students
     * @param student1 The account of student1
     * @param student2 The account of student2
     * @param student1CourseIndex The course index student1 holds
     * @param student2CourseIndex The course index student1 holds
     * @param addDropCtrl The addDropCtrl object
     * @return The updated course indexes
     */

    public ArrayList<CourseIndex> swapIndex(StudentAcc student1, StudentAcc student2, CourseIndex student1CourseIndex,
            CourseIndex student2CourseIndex, AddDropCtrl addDropCtrl) {

        ArrayList<CourseIndex> courseIndexes = new ArrayList<>();
        if ((student1CourseIndex.getCourseCode()).equals(student2CourseIndex.getCourseCode())) {
            addDropCtrl.dropCourse(student1, student1CourseIndex.getCourseCode());
            addDropCtrl.dropCourse(student2, student2CourseIndex.getCourseCode());

            if (student1.getTimetable().checkEmptySlot(student2CourseIndex)
                    && student2.getTimetable().checkEmptySlot(student1CourseIndex)) {
                addDropCtrl.addCourse(student1, student2CourseIndex);
                addDropCtrl.addCourse(student2, student1CourseIndex);
                System.out.println("Swap Success!");
                courseIndexes.add(student1CourseIndex);
                courseIndexes.add(student2CourseIndex);
                return courseIndexes;
            } else {
                System.out.println("Cannot Swap!");
                addDropCtrl.addCourse(student1, student1CourseIndex);
                addDropCtrl.addCourse(student2, student2CourseIndex);
                courseIndexes.add(student1CourseIndex);
                courseIndexes.add(student2CourseIndex);
                return courseIndexes;
            }

        } else {
            System.out.println("No Common Course Code!");
            courseIndexes.add(student1CourseIndex);
            courseIndexes.add(student2CourseIndex);
            return courseIndexes;
        }
    }

}

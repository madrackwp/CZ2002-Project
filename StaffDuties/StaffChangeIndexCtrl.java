package StaffDuties;

import CourseIndex.CourseIndex;

/**
 * Controller used by Staff to change an index number of an existing CourseIndex
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */

public class StaffChangeIndexCtrl {
    /**
     * Constructor
     */
    public StaffChangeIndexCtrl() {
    }

    /**
     * Method to change index number of an existing CourseIndex
     * 
     * @param courseToChange CourseIndex to be changed
     * @param newIndex       New index number
     * @return New CourseIndex with new index number
     */
    public CourseIndex courseIndexChangeIndex(CourseIndex courseToChange, int newIndex) {
        try {
            courseToChange.setIndexNo(newIndex);
            return courseToChange;
        } catch (Exception e) {
            System.out.println("ERROR: Could not change index");
            return null;
        }

    }

}

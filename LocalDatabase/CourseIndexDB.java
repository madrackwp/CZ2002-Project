package LocalDatabase;

import java.util.ArrayList;

import CourseIndex.*;

/** Used to store information of CourseIndex objects
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public class CourseIndexDB implements Database {

    /**
     * Array list containing all CourseIndex information
     */

    private ArrayList<CourseIndex> courseIndexes;

    /**
     * Creates a new CourseIndexDB object with an array list of CourseIndex objects
     * @param courseList Array list of CourseIndex objects
     */

    public CourseIndexDB(ArrayList<CourseIndex> courseList) {
        this.courseIndexes = courseList;
    }

    /**
     * Prints the information the CourseIndex objects in the array list
     */

    public void print() {
        for (CourseIndex courseIndex : this.courseIndexes) {
            System.out.println(courseIndex.getSchool() + " " + courseIndex.getCourseCode() + " "
                    + courseIndex.getIndexNo() + " " + courseIndex.getVacancies());
        }
    }

    /**
     * Gets the array list of CourseIndex objects
     * @return the array list of CourseIndex objects
     */

    public ArrayList<CourseIndex> getCourseIndexes() {
        return this.courseIndexes;
    }

    /**
     * Sets the array list of CourseIndex objects
     * @return whether the setting was successful or not
     */

    public boolean setCourseIndexes(ArrayList<CourseIndex> courseToSet) {
        try {
            this.courseIndexes = courseToSet;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
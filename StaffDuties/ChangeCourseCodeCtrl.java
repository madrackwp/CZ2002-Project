package StaffDuties;

import java.util.ArrayList;

import CourseIndex.CourseIndex;

/**
 * Controller used by Staff to change an existing course's code
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class ChangeCourseCodeCtrl {
    /**
     * Constructor
     */
    public ChangeCourseCodeCtrl() {
    }

    /**
     * Method to change a current course's code
     * 
     * @param courseList     ArrayList of the current CourseIndexes
     * @param courseToChange ArrayList of CourseIndex that are affected by the
     *                       change of course code as course code is an attribute of
     *                       more than one CourseIndex
     * @param oldCourseCode  The old course code
     * @param newCourseCode  The new course code
     * @return The new ArrayList of modified CourseIndex
     */
    public ArrayList<CourseIndex> changeCourseCode(ArrayList<CourseIndex> courseList,
            ArrayList<CourseIndex> courseToChange, String oldCourseCode, String newCourseCode) {
        for (int i = 0; i < courseToChange.size(); i++) {
            // System.out.println(courseIndex1.size());
            courseList.remove(courseToChange.get(i));
            if (courseToChange.get(i).getCourseCode().equals(oldCourseCode))
                courseToChange.get(i).setCourseCode(newCourseCode);
            courseList.add(courseToChange.get(i));
        }
        return courseList;
    }

}

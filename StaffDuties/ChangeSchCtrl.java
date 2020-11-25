package StaffDuties;

import java.util.ArrayList;

import CourseIndex.CourseIndex;

/**
 * Controller used by Staff to change an existing course's school
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class ChangeSchCtrl {
    /**
     * Constructor
     */
    public ChangeSchCtrl() {
    }

    /**
     * Method to change the school of an existing course
     * 
     * @param courseList      ArrayList of all the CourseIndex in the sytem
     * @param coursesToChange ArrayList of all the affected CourseIndex
     * @param school          New school to change to
     * @param course          The course of the school that is changing
     * @return The newly modified ArrayList of CourseIndexes
     */
    public ArrayList<CourseIndex> changeSchool(ArrayList<CourseIndex> courseList,
            ArrayList<CourseIndex> coursesToChange, String school, String course) {
        for (int i = 0; i < coursesToChange.size(); i++) {
            courseList.remove(coursesToChange.get(i));
            if (coursesToChange.get(i).getCourseCode().equals(course))
                coursesToChange.get(i).setSchool(school);
            courseList.add(coursesToChange.get(i));
        }
        return courseList;
    }

}

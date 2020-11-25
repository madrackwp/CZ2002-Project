package DatabaseManager;

import LocalDatabase.*;

import java.util.ArrayList;

import CourseIndex.*;
import Users.*;

/** This class implements the DatabaseManager interface to manage the CourseIndex objects
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public class CourseIndexDBManager implements DatabaseManager {

    /**
     * The CourseIndex data to be managed
     */

    private ArrayList<CourseIndex> courseIndexes;

    /**
     * Creates a new CourseIndexDBManager object with a courseIndexDB object
     * @param courseIndexDB the course index database to be managed
     */

    public CourseIndexDBManager(CourseIndexDB courseIndexDB) {
        this.courseIndexes = courseIndexDB.getCourseIndexes();
    };

    /**
     * This method adds a CourseIndex object into the local CourseIndex database
     * @param courseIndex the CourseIndex object to be added
     * @return whether the adding is successful or not
     */

    @Override
    public boolean addEntry(Object courseIndex) {
        try {
            courseIndexes.add((CourseIndex) courseIndex);
            return true;
        } catch (Exception e) {
            // System.out.println("FOR DEBUGGING: " + e.getMessage());
            return false;
        }

    }

    /**
     * This method removes a CourseIndex object into the local CourseIndex database
     * @param courseIndex the CourseIndex object to be removed
     * @return whether the removal is successful or not
     */

    @Override
    public boolean removeEntry(Object courseIndex) {
        if (this.courseIndexes.contains((CourseIndex) courseIndex)) {
            this.courseIndexes.remove(courseIndex);
            return true;
        } else {
            System.out.println("Entry does not exist");
            return false;
        }
    }

    /**
     * Adds a student into a course index
     * @param student the student to be added
     * @param indexNo the course index to add a student
     * @param courseCode the course code of the course index
     * @return whether or not the student was added successfully into the course index
     */

    public boolean addStudentToIndex(StudentAcc student, int indexNo, String courseCode) {
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getIndexNo() == indexNo && courseIndex.getCourseCode() == courseCode) {
                courseIndex.addStudent(student.getMatricNo());
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a student from a course index
     * @param student the student to be removed
     * @param indexNo the course index to remove a student
     * @param courseCode the course code of the course index
     * @return whether or not the student was removed successfully into the course index
     */

    public boolean removeStudentFromIndex(StudentAcc student, int indexNo, String courseCode) {
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getIndexNo() == indexNo && courseIndex.getCourseCode() == courseCode) {
                courseIndex.removeStudent(student.getMatricNo());
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a course index
     * @param courseCode the course code of the course index
     * @param indexNo the course index
     * @return
     */

    public CourseIndex getCourseIndexInfo(String courseCode, int indexNo) {
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode) && courseIndex.getIndexNo() == indexNo) {
                return courseIndex;
            }
        }
        return null;
    }

    /**
     * Gets an array list of course index
     * @param courseCode the course code of the course indexes
     * @return array list of course indexes
     */

    public ArrayList<CourseIndex> getCourseIndexInfoArray(String courseCode) {
        ArrayList<CourseIndex> temp = new ArrayList<CourseIndex>();
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode)) {
                temp.add(courseIndex);
            }
        }
        return temp;
    }

    /**
     * Get the CourseIndex objects
     * @return array list of CourseIndex
     */

    public ArrayList<CourseIndex> getCourseIndexes() {
        return this.courseIndexes;
    }

    /**
     * This method updates the local course index database
     * @param arrayListOfCourse the updated array list of course indexes
     * @param dataBaseForCourse database of course indexes to be updated
     * @return whether the updating is successful or not
     */

    @Override
    public boolean updateDatabase(Object arrayListOfCourse, Object dataBaseForCourse) {
        try {
            ArrayList<CourseIndex> updatedCourses = (ArrayList<CourseIndex>) arrayListOfCourse;
            ((CourseIndexDB) dataBaseForCourse).setCourseIndexes(updatedCourses);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Prints all the course codes in the course database
     */

    public void printCourses() {
        ArrayList<String> printedCourses = new ArrayList<>();
        for (CourseIndex courseIndex : this.courseIndexes) {
            String courseCodeToPrint = courseIndex.getCourseCode();
            if (!printedCourses.contains(courseCodeToPrint)) {
                printedCourses.add(courseCodeToPrint);
                System.out.println("CourseCode: " + courseCodeToPrint + " | School: " + courseIndex.getSchool()
                        + " | AUs: " + courseIndex.getAcademicUnits());
            }
        }
    }

    /**
     * Prints all the course indexes all the course codes
     */

    public void printIndexes() {
        for (CourseIndex courseIndex : this.courseIndexes) {
            System.out.println("CourseCode: " + courseIndex.getCourseCode() + " | School: " + courseIndex.getSchool()
                    + " | IndexNo: " + courseIndex.getIndexNo() + " | Vacancy: " + courseIndex.getVacancies());
        }
    }

}

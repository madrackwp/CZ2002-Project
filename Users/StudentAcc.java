package Users;

import java.util.ArrayList;
import java.util.HashMap;

import CourseIndex.CourseIndex;
import CourseIndex.ModType;
import Timetable.Timetable;

/**
 * This represents a student that has access to the STARS system One student can
 * be enrolled into many courses
 * 
 * @version 1.0
 * @since 2020-11-25
 */
public class StudentAcc extends UserAcc {
    /**
     * matricNo uniquely identifies a student and his account
     */
    private String matricNo;
    /**
     * yearOfStudy shows what year the student is currently in
     */
    private int yearOfStudy;
    /**
     * This is to show when the student is allowed to access the STARS system to fix
     * his mods It MUST BE in the format dd/MM/yyyy
     */
    private String accessDate;
    /**
     * This will be a record of all the courses that the student will be taking
     */
    private ArrayList<CourseIndex> registeredCourseIndexes;
    /**
     * This will be the student's timetable for the semester
     */
    private Timetable timetable;
    /**
     * This will store what the student will be taking certain mods as
     * (CORE,GERPE_LA,GERPE_STS,GERPE_BM,UE or MPE)
     */
    private HashMap<String, ModType> courseHash;

    /**
     * This creates the studentAcc
     * 
     * @param userName                This StudentAcc's userName
     * @param password                This StudentAcc's hashed password
     * @param name                    This StudentAcc's name
     * @param school                  This StudentAcc's school/faculty
     * @param matricNo                This StudentAcc's matriculation number
     * @param yearOfStudy             This StudentAcc's year of study
     * @param accessDate              This StudentAcc's access date to STARS
     * @param registeredCourseIndexes This is all the StudentAcc's registered
     *                                courses
     * @param courseHash              This will store the StudentAcc's record of
     *                                what the mod is taken as
     */
    public StudentAcc(String userName, String password, String name, String school, String matricNo, int yearOfStudy,
            String accessDate, ArrayList<CourseIndex> registeredCourseIndexes, HashMap<String, ModType> courseHash) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
        this.accessDate = accessDate;
        this.registeredCourseIndexes = registeredCourseIndexes;
        this.timetable = new Timetable(registeredCourseIndexes, courseHash);
        this.courseHash = courseHash;
    }

    /**
     * This overrides the parent's toString method for printing each StudentAcc
     */
    @Override
    public String toString() {
        return "Name: " + super.getName() + " | School: " + super.getSchool() + " | MatricNo: " + this.matricNo
                + " | YearOfStudy: " + this.yearOfStudy;
    }

    /**
     * Gets the matriculation number of this student
     * 
     * @return this StudentAcc's matriculation number
     */
    public String getMatricNo() {
        return matricNo;
    }

    /**
     * Gets the student's modtype
     * 
     * @return this StudentAcc's modtype
     */
    public HashMap<String, ModType> getCourseHash() {
        return courseHash;
    }

    /**
     * Gets the student's current year of study
     * 
     * @return this StudentAcc's yearOfStudy
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    /**
     * Gets the student's access date for the STARS system
     * 
     * @return this StudentAcc's accessDate
     */
    public String getAccessDate() {
        return this.accessDate;
    }

    /**
     * Gets the student's registered courses as an Arraylist of CourseIndex objects
     * 
     * @return this StudentAcc's registered courses as ArrayList<CourseIndex>
     */
    public ArrayList<CourseIndex> getRegisteredCourseIndex() {
        return this.registeredCourseIndexes;
    }

    /**
     * Gets the student's specific CourseIndex object by taking in a course code as
     * a parameter
     * 
     * @param courseCode Uses this string to find the CourseIndex object
     * @return The CourseIndex of the student with the courseCode inputted if it
     *         exists. Else, return null
     */

    public CourseIndex getCourseIndex(String courseCode) {
        for (CourseIndex courseIndex : this.registeredCourseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode)) {
                return courseIndex;
            }
        }
        return null;
    }

    /**
     * Retrieves the student's timetable object
     * 
     * @return this StudentAcc's timetable object
     */
    public Timetable getTimetable() {
        return this.timetable;
    }

    /**
     * Sets the student's access date for STARS system Must be in dd/MM/yyyy format
     * 
     * @param accessDate This is the StudentAcc's new accessDate
     */
    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    /**
     * This is to convert the entire StudentAcc object that will be written into the
     * text file
     * 
     * @return a string that is the text format required to read and write
     *         successfully in the program
     */
    public String toWrite() {

        String temp = super.getUserName() + " " + super.getPassword() + " " + super.getName() + " " + super.getSchool()
                + " " + this.getMatricNo() + " " + Integer.toString(this.getYearOfStudy()) + " " + this.getAccessDate();

        if (this.registeredCourseIndexes.isEmpty()) {
            temp += " null";
        } else {
            for (CourseIndex courseIndex : this.registeredCourseIndexes) {
                // if(this.registeredCourseIndexes.isEmpty()){
                // String modType = "";
                // String coursecode = "";
                // break;
                // }
                String modType = courseHash.get(courseIndex.getCourseCode()).toString();
                String courseInfo = courseIndex.getCourseCode() + "," + Integer.toString(courseIndex.getIndexNo()) + ","
                        + modType;
                temp = temp + " ";
                temp = temp + courseInfo;
            }
        }

        return temp;
    }

    /**
     * Increment the student's year of study
     */
    public void incrementYearOfStudy() {
        this.yearOfStudy++;
    }

    /**
     * Adds a new CourseIndex to the student's registered courses
     * 
     * @param courseIndex CourseIndex object that will be added to student's
     *                    registered courses
     */
    public void addNewCourse(CourseIndex courseIndex) {
        this.registeredCourseIndexes.add(courseIndex);
        this.courseHash.put(courseIndex.getCourseCode(), courseIndex.getDefaultModType());
    }

    /**
     * Removes a CourseIndex from the student's registered courses
     * 
     * @param courseIndex CourseIndex object that will be removed from the student's
     *                    registered courses
     */
    public void removeCourse(CourseIndex courseIndex) {
        this.registeredCourseIndexes.remove(courseIndex);
    }

    /**
     * This method is to find out if the student is taking a particular course
     * through taking in a CourseIndex object courseCode attribute Returns true if
     * course is taken, else false
     * 
     * @param courseCode String attribute of CourseIndex to look for
     * @return true is StudentAcc is taking this particular course, else false
     */
    public boolean takingCourse(String courseCode) {
        for (CourseIndex courseIndex : this.registeredCourseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Changing what the student is taking a particular courseIndex as
     * (CORE,GERPE_LA,GERPE_STS,GERPE_BM,UE or MPE)
     * 
     * @param courseCode Course code of the courseIndex taken by the student to
     *                   change the mod type
     * @param newModType new ModType to change to
     * @return True if successful, else false
     */
    public boolean editModType(String courseCode, ModType newModType) {
        try {
            this.courseHash.put(courseCode, newModType);
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Edit the courseHash when a edit the course code of an existing course
     * 
     * @param newCourseCode The new course code
     * @param oldCourseCode The old course code
     * @return True is update is succesful, else false
     */
    public boolean updateCourseHash(String newCourseCode, String oldCourseCode) {
        ModType temp = this.courseHash.get(oldCourseCode);
        this.courseHash.remove(oldCourseCode);
        this.courseHash.put(newCourseCode, temp);
        return true;
    }

}
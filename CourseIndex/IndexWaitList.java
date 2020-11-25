package CourseIndex;

import java.util.ArrayList;

/**
 * This class holds all the students who are waiting to enroll in a course that
 * is currently full
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class IndexWaitList {
    /**
     * The students who are on the wait list is stored as an arraylist of String of
     * their matriculation numbers
     */
    ArrayList<String> waitList;

    /**
     * Constructor to create the IndexWaitList object
     * 
     * @param waitList This is the arraylist of matriculation number strings that
     *                 are currently on the wait list
     */
    public IndexWaitList(ArrayList<String> waitList) {
        this.waitList = waitList;
    }

    /**
     * Adds a student to the waitlist
     * 
     * @param student The student's matriculation number to be added to the waitlist
     */
    public void addStudent(String student) {
        this.waitList.add(student);
    }

    /**
     * Removes a student from the waitlist
     * 
     * @param student The student's matriculation number to be removed from the
     *                waitlist
     */
    public void removeStudent(String student) {
        boolean check = this.waitList.remove(student);
        if (check) {
            // System.out.println("StudentAcc removed!");
        } else {
            // System.out.println("Removal unsuccessful");
        }
    }

    /**
     * Gets the waitlist
     * 
     * @return The entire waitlist of student's matriculation number
     */
    public ArrayList<String> getWaitList() {
        return this.waitList;
    }

}

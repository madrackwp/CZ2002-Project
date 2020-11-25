package DatabaseManager;

import CourseIndex.CourseIndex;
import LocalDatabase.StudentDB;
import Users.StudentAcc;

import java.util.ArrayList;

/** This class implements the DatabaseManager interface to manage the StudentAcc objects
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public class StudentDBManager implements DatabaseManager {

    /**
     * The StudentAcc data to be managed
     */

    private ArrayList<StudentAcc> studentAccs;

    /**
     * Creates a new StudentDBManager object with a StudentDB object
     * @param studentDB the student database to be managed
     */

    public StudentDBManager(StudentDB studentDB) {
        this.studentAccs = studentDB.getStudentDB();
    }

    /**
     * This method adds a StudentAcc object into the local student database
     * @param studentAcc the StudentAcc object to be added
     * @return whether the adding is successful or not
     */


    @Override
    public boolean addEntry(Object studentAcc) {
        try {
            studentAccs.add((StudentAcc) studentAcc);
            return true;
        } catch (Exception e) {
            // System.out.println("FOR DEBUGGING: " + e.getMessage());
            return false;
        }
    }

    /**
     * This method removes a StudentAcc object into the local student database
     * @param studentAcc the StudentAcc object to be removed
     * @return whether the removal is successful or not
     */

    @Override
    public boolean removeEntry(Object studentAcc) {
        if (this.studentAccs.contains((CourseIndex) studentAcc)) {
            this.studentAccs.remove(studentAcc);
            return true;
        } else {
            System.out.println("Entry does not exist");
            return false;
        }
    }

    /**
     * Get the StudentAcc objects
     * @return array list of StudentAcc
     */

    public ArrayList<StudentAcc> getStudentAccs() {
        return this.studentAccs;
    }

    /**
     * This method updates the local student database
     * @param studentAccs the updated student data
     * @param studentDB the student database to be updated
     * @return whether the updating is successful or not
     */

    @Override
    public boolean updateDatabase(Object studentAccs, Object studentDB) {
        try {
            ArrayList<StudentAcc> list = (ArrayList<StudentAcc>) studentAccs;
            ((StudentDB) studentDB).setStudentDB(list);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Get StudentAcc objects by their matriculation number
     * @param matricNo matriculation number of the student
     * @return the student account
     */

    public StudentAcc getStudentByMatricNo(String matricNo) {
        for (StudentAcc sa : studentAccs) {
            if (sa.getMatricNo().equals(matricNo)) {
                return sa;
            }
        }
        // System.out.println("No such matric no in the database!");
        return null;
    }

    /**
     * Print all students in the database
     */

    public void printAllStudents() {
        for (StudentAcc student : this.studentAccs) {
            System.out.println(student);
        }
    }

}

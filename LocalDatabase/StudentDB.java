package LocalDatabase;

import Users.StudentAcc;

import java.util.ArrayList;

/** Used to store information of StudentAcc objects
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public class StudentDB implements Database {

    /**
     * Array list containing all StaffAcc information
     */

    private ArrayList<StudentAcc> studentDB;

    /**
     * Creates a new StudentDB object with an array list of StudentAcc objects
     * @param sdb Array list of StudentAcc objects
     */

    public StudentDB(ArrayList<StudentAcc> sdb) {
        this.studentDB = sdb;
    }

    // print username, name, matricNo, school, year
    /**
     * Prints the information the StudentAcc objects in the array list
     */

    @Override
    public void print() {
        for (int i = 0; i < this.studentDB.size(); i++) {
            System.out.println(studentDB.get(i).getUserName() + " " + studentDB.get(i).getName() + " "
                    + studentDB.get(i).getMatricNo() + " " + studentDB.get(i).getSchool() + " "
                    + studentDB.get(i).getYearOfStudy());
        }
    }

    /**
     * Gets the array list of StudentAcc objects
     * @return the array list of StudentAcc objects
     */

    public ArrayList<StudentAcc> getStudentDB() {
        return studentDB;
    }

    /**
     * Sets the array list of StudentAcc objects
     * @return whether the setting was successful or not
     */

    public boolean setStudentDB(ArrayList<StudentAcc> studentAccs) {
        try {
            this.studentDB = studentAccs;
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}

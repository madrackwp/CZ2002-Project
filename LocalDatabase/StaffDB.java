package LocalDatabase;

import Users.StaffAcc;

import java.util.ArrayList;

/** Used to store information of StaffAcc objects
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public class StaffDB implements Database{

    /**
     * Array list containing all StaffAcc information
     */

    private ArrayList<StaffAcc> staffDB;

    /**
     * Creates a new StaffDB object with an array list of StaffAcc objects
     * @param stdb Array list of StaffAcc objects
     */

    public StaffDB(ArrayList<StaffAcc> stdb){
        this.staffDB = stdb;
    }

    //print username, name, staffID, school

    /**
     * Prints the information the StaffAcc objects in the array list
     */
    @Override
    public void print() {
        for(int i=0; i<this.staffDB.size(); i++){
            System.out.println(staffDB.get(i).getUserName() + " " + staffDB.get(i).getName() + " " + staffDB.get(i).getStaffIDNo()
                    + " " + staffDB.get(i).getSchool());
        }
    }

    /**
     * Gets the array list of StaffAcc objects
     * @return the array list of StaffAcc objects
     */
    public ArrayList<StaffAcc> getStaffDB() {
        return staffDB;
    }
}

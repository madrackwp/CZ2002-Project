package DatabaseManager;

import CourseIndex.CourseIndex;
import LocalDatabase.StaffDB;
import Users.StaffAcc;
import java.util.ArrayList;

/** This class implements the DatabaseManager interface to manage the StaffAcc objects
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public class StaffDBManager implements DatabaseManager {

    /**
     * The StaffAcc data to be managed
     */

    private ArrayList<StaffAcc> staffAccs;

    /**
     * Creates a new StaffDBManager object with a StaffDB object
     * @param staffDB
     */

    public StaffDBManager(StaffDB staffDB) {
        this.staffAccs = staffDB.getStaffDB();
    }

    /**
     * This method adds a StaffAcc object into the local staff database
     * @param staffAcc the StaffAcc object to be added
     * @return whether the adding is successful or not
     */

    @Override
    public boolean addEntry(Object staffAcc) {
        try {
            staffAccs.add((StaffAcc) staffAcc);
            return true;
        } catch (Exception e) {
            // System.out.println("FOR DEBUGGING: " + e.getMessage());
            return false;
        }
    }

    /**
     * This method removes a StaffAcc object into the local staff database
     * @param staffAcc the StaffAcc object to be removed
     * @return whether the removal is successful or not
     */

    @Override
    public boolean removeEntry(Object staffAcc) {
        if (this.staffAccs.contains((CourseIndex) staffAcc)) {
            this.staffAccs.remove(staffAcc);
            return true;
        } else {
            System.out.println("Entry does not exist");
            return false;
        }
    }

    /**
     * This method updates the local database
     * @param arrayList the updated data
     * @param dataBase the database to be updated
     * @return whether the updating is successful or not
     */

    @Override
    public boolean updateDatabase(Object arrayList, Object dataBase) {
        // TODO Auto-generated method stub
        return false;
    }
}

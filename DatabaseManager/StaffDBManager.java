package DatabaseManager;

import CourseIndex.CourseIndex;
import LocalDatabase.StaffDB;
import Users.StaffAcc;
import java.util.ArrayList;

public class StaffDBManager implements DatabaseManager {
    private ArrayList<StaffAcc> staffAccs;

    public StaffDBManager(StaffDB staffDB) {
        this.staffAccs = staffDB.getStaffDB();
    }

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

    @Override
    public boolean updateDatabase(Object arrayList, Object dataBase) {
        // TODO Auto-generated method stub
        return false;
    }
}

package LocalDatabase;

import Users.StaffAcc;

import java.util.ArrayList;

public class StaffDB implements Database{
    private ArrayList<StaffAcc> staffDB;

    public StaffDB(ArrayList<StaffAcc> stdb){
        this.staffDB = stdb;
    }

    //print username, name, staffID, school
    @Override
    public void print() {
        for(int i=0; i<this.staffDB.size(); i++){
            System.out.println(staffDB.get(i).getUserName() + " " + staffDB.get(i).getName() + " " + staffDB.get(i).getStaffIDNo()
                    + " " + staffDB.get(i).getSchool());
        }
    }
}

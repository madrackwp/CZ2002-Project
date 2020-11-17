package DatabaseManager;

import LocalDatabase.StudentDB;
import Users.StudentAcc;

import java.util.ArrayList;

public class StudentDBManager implements DatabaseManager{
    private ArrayList<StudentAcc> studentAccs;

    @Override
    public boolean addEntry(Object object) {
        return false;
    }

    @Override
    public boolean removeEntry(Object object) {
        return false;
    }
}

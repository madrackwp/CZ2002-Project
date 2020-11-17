package DatabaseManager;

import CourseIndex.CourseIndex;
import LocalDatabase.StudentDB;
import Users.StudentAcc;

import java.util.ArrayList;

public class StudentDBManager implements DatabaseManager{
    private ArrayList<StudentAcc> studentAccs;

    public StudentDBManager(StudentDB studentDB){
        this.studentAccs = studentDB.getStudentDB();
    }

    @Override
    public boolean addEntry(Object studentAcc) {
        try {
            studentAccs.add((StudentAcc) studentAcc);
            return true;
        } catch (Exception e) {
            System.out.println("FOR DEBUGGING: " + e.getMessage());
            return false;
        }
    }

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
}

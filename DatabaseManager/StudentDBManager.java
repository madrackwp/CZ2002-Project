package DatabaseManager;

import CourseIndex.CourseIndex;
import LocalDatabase.StudentDB;
import ReadWriteFile.StudentWriter;
import Users.StudentAcc;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentDBManager implements DatabaseManager {
    private ArrayList<StudentAcc> studentAccs;

    public StudentDBManager(StudentDB studentDB) {
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

    public ArrayList<StudentAcc> getStudentAccs() {
        return this.studentAccs;
    }

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

    // public boolean changeAccess(String matricNo){
    // for(int i=0; i<this.studentAccs.size(); i++){
    // if(matricNo.equals(studentAccs.get(i).getMatricNo())){
    // System.out.print("Enter new access year: ");
    // Scanner sc = new Scanner(System.in);
    // int year = sc.nextInt();
    // studentAccs.get(i).setAccessYear(year);
    // System.out.print("Enter new access month: ");
    // int month = sc.nextInt();
    // studentAccs.get(i).setAccessMonth(month);
    // System.out.print("Enter new access date: ");
    // int date = sc.nextInt();
    // studentAccs.get(i).setAccesDate(date);
    // return true;
    // }
    // }
    // return false;
    // }
}

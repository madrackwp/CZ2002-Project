package LocalDatabase;

import Users.StudentAcc;

import java.util.ArrayList;

public class StudentDB implements Database{
    private ArrayList<StudentAcc> studentDB;


    public StudentDB(ArrayList<StudentAcc> sdb){
        this.studentDB = sdb;
    }


    //print username, name, matricNo, school, year
    @Override
    public void print() {
        for(int i=0; i<this.studentDB.size(); i++){
            System.out.println(studentDB.get(i).getUserName() + " " + studentDB.get(i).getName() + " " + studentDB.get(i).getMatricNo()
                    + " " + studentDB.get(i).getSchool() + " " + studentDB.get(i).getYearOfStudy());
        }
    }

    public ArrayList<StudentAcc> getStudentDB() {
        return studentDB;
    }

    //test
    public static void main(String[] args) {
        ArrayList<StudentAcc> s = new ArrayList<>();
        StudentAcc s1 = new StudentAcc("hello", "hello", "hello", "hello",
                "hello", 1);
        s.add(s1);
        StudentDB ss = new StudentDB(s);
        ss.print();

    }


}

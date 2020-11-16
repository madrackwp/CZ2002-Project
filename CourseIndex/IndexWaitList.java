package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class IndexWaitList {
    ArrayList<StudentAcc> waitList;

    public IndexWaitList(ArrayList<StudentAcc> w){
        this.waitList = w;
    }

    public void addStudent(StudentAcc s){
        this.waitList.add(s);
        System.out.println("StudentAcc added successfully!");
    }

    public void removeStudent(StudentAcc s){
        boolean check = this.waitList.remove(s);
        if(check){
            System.out.println("StudentAcc removed!");
        }
        else{
            System.out.println("Removal unsuccessful");
        }
    }
}

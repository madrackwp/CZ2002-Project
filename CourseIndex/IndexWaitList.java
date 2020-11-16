package CourseIndex;

import Users.Student;

import java.util.ArrayList;

public class IndexWaitList {
    ArrayList<Student> waitList;

    public IndexWaitList(ArrayList<Student> w){
        this.waitList = w;
    }

    public void addStudent(Student s){
        this.waitList.add(s);
        System.out.println("Student added successfully!");
    }

    public void removeStudent(Student s){
        boolean check = this.waitList.remove(s);
        if(check){
            System.out.println("Student removed!");
        }
        else{
            System.out.println("Removal unsuccessful");
        }
    }
}

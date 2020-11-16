package CourseIndex;

import Users.Student;

import java.util.ArrayList;

public class CourseIndex {
    int indexNo;
    int capacity;
    int vacancies;
    ArrayList<Student> registeredStudent;
    String tutorialDateTime;
    String lectureDateTime;
    String courseCode;
    String school;

    public CourseIndex(int i, int c, int v, ArrayList<Student> r, String t, String l, String cc, String s){
        this.indexNo = i;
        this.capacity = c;
        this.vacancies = v;
        this.registeredStudent = r;
        this.tutorialDateTime = t;
        this.lectureDateTime = l;
        this.courseCode = cc;
        this.school = s;
//        IndexWaitList indexWaitList = new IndexWaitList();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getVacancies() {
        return vacancies;
    }

    public ArrayList<Student> getRegisteredStudent() {
        return registeredStudent;
    }
}

package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class CourseIndex {
    int indexNo;
    int capacity;
    int vacancies;
    ArrayList<StudentAcc> registeredStudent;
    String tutorialDateTime;
    String lectureDateTime;
    String courseCode;
    String school;

    public CourseIndex(int i, int c, int v, ArrayList<StudentAcc> r, String t, String l, String cc, String s) {
        this.indexNo = i;
        this.capacity = c;
        this.vacancies = v;
        this.registeredStudent = r;
        this.tutorialDateTime = t;
        this.lectureDateTime = l;
        this.courseCode = cc;
        this.school = s;
        // IndexWaitList indexWaitList = new IndexWaitList();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getVacancies() {
        return vacancies;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public int getIndexNo() {
        return this.indexNo;
    }

    public ArrayList<StudentAcc> getRegisteredStudent() {
        return this.registeredStudent;
    }

    public boolean addStudent(StudentAcc student) {
        if (vacancies == 0) {
            return false;
        } else {
            registeredStudent.add(student);
            vacancies--;
            return true;
        }
    }

    public boolean removeStudent(StudentAcc student) {
        if (registeredStudent.contains(student)) {
            registeredStudent.remove(student);
            vacancies++;
            return true;
        } else {
            return false;
        }
    }
}

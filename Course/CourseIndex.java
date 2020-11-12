package Course;

import java.util.ArrayList;
import Users.Student;

public class CourseIndex {
    private int indexNo;
    private int capacity;
    private int vacancies;
    private String courseCode, school;
    // private ArrayList<Student> waitList;
    // private ArrayList<Student> registeredStudents;

    public CourseIndex(String courseCode, int indexNo, String school, int capacity, int vacancies) {
        this.indexNo = indexNo;
        this.courseCode = courseCode;
        this.school = school;
        this.capacity = capacity;
        this.vacancies = vacancies;
    }

    public int getIndexNo() {
        return indexNo;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getVacancies() {
        return this.vacancies;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getSchool() {
        return this.school;
    }

    public String toString() {
        return this.courseCode + " " + this.indexNo + " " + this.school + " " + this.capacity + " " + this.vacancies;
    }

    // public ArrayList<Student> getWaitList() {
    // return this.waitList;
    // }

    // public ArrayList<Student> getRegisteredStudents() {
    // return this.registeredStudents;
    // }

    // public boolean registerStudent(Student student) {
    // if (getVacancies() <= 0) {
    // waitList.add(student);
    // return false;
    // } else {
    // registeredStudents.add(student);
    // return true;
    // }
    // }

}

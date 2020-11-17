package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class CourseIndex {
    int indexNo;
    int capacity;
    int vacancies;
    ArrayList<String> registeredStudent;
    String tutorialDateTime;
    String lectureDateTime;
    String courseCode;
    String school;

    public CourseIndex(int i, int v, ArrayList<String> r, String t, String l, String cc, String s) {

        this.indexNo = i;
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

    public boolean addStudent(String studentMatricString) {
        if (vacancies == 0) {
            return false;
        } else {
            registeredStudent.add(studentMatricString);
            vacancies--;
            return true;
        }
    }

    public boolean removeStudent(String studentMatricString) {
        if (registeredStudent.contains(studentMatricString)) {
            registeredStudent.remove(studentMatricString);
            vacancies++;
            return true;
        } else {
            return false;
        }
    }

    public String getLectureDateTime() {
        return lectureDateTime;
    }

    public String getTutorialDateTime() {
        return tutorialDateTime;
    }

    public String getSchool() {
        return school;
    }

}

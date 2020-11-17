package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class CourseIndex {
    int indexNo;
    int capacity;
    int vacancies;
    ArrayList<String> registeredStudentMatricNo;
    String tutorialDateTime;
    String lectureDateTime;
    String courseCode;
    String school;

    public CourseIndex(int i, int v, ArrayList<String> r, String t, String l, String cc, String s) {
<<<<<<< HEAD
=======

>>>>>>> db0d92824bb672d00293fbbb927321eca05a30cb
        this.indexNo = i;
        this.vacancies = v;
        this.registeredStudentMatricNo = r;
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

<<<<<<< HEAD
    public ArrayList<String> getRegisteredStudent() {
        return this.registeredStudent;
=======
    public ArrayList<String> getRegisteredStudentMatricNo() {

        return this.registeredStudentMatricNo;
>>>>>>> db0d92824bb672d00293fbbb927321eca05a30cb
    }

    public boolean addStudent(String studentMatricString) {
        if (vacancies == 0) {
            return false;
        } else {
            registeredStudentMatricNo.add(studentMatricString);
            vacancies--;
            return true;
        }
    }

    public boolean removeStudent(String studentMatricString) {
        if (registeredStudentMatricNo.contains(studentMatricString)) {
            registeredStudentMatricNo.remove(studentMatricString);
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

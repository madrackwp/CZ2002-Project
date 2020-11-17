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

<<<<<<< HEAD
    public int getIndexNo() {
        return this.indexNo;
    }

=======
>>>>>>> 5b07c331498f03651c73d2fe42b2d8c91aee05af
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

    public String getLectureDateTime() {
        return lectureDateTime;
    }

    public String getTutorialDateTime() {
        return tutorialDateTime;
    }

    public String getSchool() {
        return school;
    }

    public int getIndexNo() {
        return indexNo;
    }
}

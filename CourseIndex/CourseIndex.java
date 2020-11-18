package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class CourseIndex {
    int indexNo;
    int capacity;
    int vacancies;
    ArrayList<Lesson> lessons;
    String courseCode;
    String school;
    ArrayList<String> registeredStudentMatricNo;

    public CourseIndex(int indexNo, int vacancies, String courseCode, String school,
                       ArrayList<String> registeredStudentMatricNo, ArrayList<Lesson> lessons) {
        this.indexNo = indexNo;
        this.vacancies = vacancies;
        this.lessons = lessons;
        this.courseCode = courseCode;
        this.school = school;
        this.registeredStudentMatricNo = registeredStudentMatricNo;
        // IndexWaitList indexWaitList = new IndexWaitList();
    }

    public String toString() {
        return Integer.toString(this.indexNo) + " " + this.courseCode;
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

    public ArrayList<String> getRegisteredStudentMatricNo() {
        return this.registeredStudentMatricNo;
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

    public ArrayList<Lesson> getLessons() {
     return this.lessons;
    }

    public String getSchool() {
        return school;
    }

    public String toWrite() {
        String result = this.courseCode + " " + Integer.toString(this.indexNo) + " " + this.school + " "
                + Integer.toString(this.capacity) + " " + Integer.toString(this.vacancies);

        for (String matricNo : this.registeredStudentMatricNo) {
            result += " " + matricNo;
        }
        // result += "\n";
        return result;
    }

}

package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class CourseIndex {
    int indexNo;
    int capacity;
    int vacancies;
<<<<<<< HEAD
    ArrayList<String> registeredStudentMatricNo;
    String lectureDay;
    String lectureStartTime;
    String lectureEndTime;
    String tutorialDay;
    String tutorialStartTime;
    String tutorialEndTime;
=======
    // ArrayList<Lesson> lessons;
>>>>>>> 72c9b41121fb762c19a1c7ddfc5122a1774a6deb
    String courseCode;
    String school;
    ArrayList<String> registeredStudentMatricNo;

<<<<<<< HEAD
    public CourseIndex(int i, int v, ArrayList<String> r, String t, String t1, String t2,
                        String l, String l1, String l2, String cc, String s) {
        this.indexNo = i;
        this.vacancies = v;
        this.registeredStudentMatricNo = r;
        this.tutorialDay = t;
        this.tutorialStartTime = t1;
        this.tutorialEndTime = t2;
        this.lectureDay = l;
        this.lectureStartTime = l1;
        this.lectureEndTime = l2;
        this.courseCode = cc;
        this.school = s;
=======
    public CourseIndex(int indexNo, int vacancies,
            // String tutorialStartTime, String tutorialEndTime, Day tutorialDay, String
            // lectureStartTime,
            // String lectureEndTime, Day lectureDay,
            String courseCode, String school, ArrayList<String> registeredStudentMatricNo) {
        this.indexNo = indexNo;
        this.vacancies = vacancies;
        this.courseCode = courseCode;
        this.school = school;
        this.registeredStudentMatricNo = registeredStudentMatricNo;
>>>>>>> 72c9b41121fb762c19a1c7ddfc5122a1774a6deb
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

<<<<<<< HEAD
    public String getLectureDay() {
        return lectureDay;
    }

    public String getLectureStartTime(){
        return lectureStartTime;
    }

    public String getLectureEndTime(){
        return lectureEndTime;
    }

    public String getTutorialDay() {
        return tutorialDay;
    }

    public String getTutorialStartTime(){
        return tutorialStartTime;
    }

    public String getTutorialEndTime(){
        return tutorialEndTime;
    }
=======
    // public ArrayList<Lesson> getLessons() {
    // return this.lessons;
    // }
>>>>>>> 72c9b41121fb762c19a1c7ddfc5122a1774a6deb

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

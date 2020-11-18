package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class CourseIndex {
    String courseCode;
    int indexNo;
    String school;
    int capacity;
    int vacancies;
    ArrayList<String> registeredStudentMatricNo;
    ArrayList<Lesson> lessons;

    public CourseIndex(String courseCode, int indexNo, String school, int capacity, int vacancies,
                       ArrayList<String> registeredStudentMatricNo, ArrayList<Lesson> lessons) {
        this.courseCode = courseCode;
        this.indexNo = indexNo;
        this.school = school;
        this.capacity = capacity;
        this.vacancies = vacancies;
        this.registeredStudentMatricNo = registeredStudentMatricNo;
        this.lessons = lessons;
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
        result += " ";

        for(int i=0; i<registeredStudentMatricNo.size(); i++){
            if(i==registeredStudentMatricNo.size()-1){
                result += registeredStudentMatricNo.get(i);
            }else{
                result += registeredStudentMatricNo.get(i) + ",";
            }
        }
        result += " ";
        for(Lesson l: this.lessons){
            result += l.getStartTime() + "," + l.getEndTime() + "," + l.getTypeToString() + "," + l.getDayToString() + " ";
        }
        // result += "\n";
        return result;
    }

}

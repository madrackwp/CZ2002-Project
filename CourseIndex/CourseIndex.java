package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

public class CourseIndex {
    String courseCode;
    int indexNo;
    int academicUnits;
    String school;
    ArrayList<ModType> allowedModType;
    int capacity;
    int vacancies;
    ArrayList<String> registeredStudentMatricNo;
    ArrayList<Lesson> lessons;

    public CourseIndex(String courseCode, int indexNo, int academicUnits, String school,
            ArrayList<ModType> allowedModType, int capacity, int vacancies, ArrayList<String> registeredStudentMatricNo,
            ArrayList<Lesson> lessons) {
        this.courseCode = courseCode;
        this.indexNo = indexNo;
        this.academicUnits = academicUnits;
        this.school = school;
        this.allowedModType = allowedModType;
        this.capacity = capacity;
        this.vacancies = vacancies;
        this.registeredStudentMatricNo = registeredStudentMatricNo;
        this.lessons = lessons;

        // IndexWaitList indexWaitList = new IndexWaitList();
    }

    public String toString() {
        return this.courseCode + " " + this.school + " " + Integer.toString(this.indexNo) + " "
                + Integer.toString(this.vacancies);
    }

    // public String fullDetails() {
    // return this.courseCode + " " + this.indexNo + " " + this.school + " " +
    // this.modType.toString() + " "
    // + Integer.toString(this.capacity) + " " + Integer.toString(this.vacancies) +
    // " "
    // + this.registeredStudentMatricNo + " " + this.lessons;
    // }

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

    public boolean addStudent(String studentMatricNo) {
        if (vacancies == 0) {
            return false;
        } else {
            if (registeredStudentMatricNo.get(0).equals("null")) {
                registeredStudentMatricNo.remove(0);
            }
            registeredStudentMatricNo.add(studentMatricNo);
            vacancies--;
            return true;
        }
    }

    public boolean removeStudent(String studentMatricNo) {
        if (registeredStudentMatricNo.contains(studentMatricNo)) {
            registeredStudentMatricNo.remove(studentMatricNo);
            vacancies++;
            if (registeredStudentMatricNo.isEmpty()) {
                registeredStudentMatricNo.add("null");
            }
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
        String result = this.courseCode + " " + Integer.toString(this.indexNo) + " "
                + Integer.toString(this.academicUnits) + " " + this.school + " ";

        for (int i = 0; i < allowedModType.size(); i++) {
            result += allowedModType.get(i).toString();
            if (i != allowedModType.size() - 1) {
                result += ",";
            } else {
                result += " ";
            }
        }

        result = result + Integer.toString(this.capacity) + " " + Integer.toString(this.vacancies);
        result += " ";

        if (!this.registeredStudentMatricNo.isEmpty()) {
            for (int i = 0; i < registeredStudentMatricNo.size(); i++) {
                if (i == registeredStudentMatricNo.size() - 1) {
                    result += registeredStudentMatricNo.get(i);
                } else {
                    result += registeredStudentMatricNo.get(i) + ",";
                }
            }
            result += " ";
        }

        for (Lesson l : this.lessons) {
            result += l.getStartTime() + "," + l.getEndTime() + "," + l.getTypeToString() + "," + l.getDayToString()
                    + " ";
        }
        // result += "\n";
        return result;
    }

    public int getAcademicUnits() {
        return this.academicUnits;
    }

    public ModType getDefaultModType() {
        return this.allowedModType.get(0);
    }

    public ArrayList<ModType> getAllowedModTypes() {
        return this.allowedModType;
    }

}

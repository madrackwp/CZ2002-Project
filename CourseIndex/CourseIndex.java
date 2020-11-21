package CourseIndex;

import Users.StudentAcc;

import java.util.ArrayList;

import javax.print.attribute.standard.RequestingUserName;

public class CourseIndex {
    String courseCode;
    int indexNo;
    int academicUnits;
    String school;
    ArrayList<ModType> allowedModType;
    // int capacity;
    IndexWaitList indexWaitList;
    int vacancies;
    ArrayList<String> registeredStudentMatricNo;
    ArrayList<Lesson> lessons;

    public CourseIndex(String courseCode, int indexNo, int academicUnits, String school,
            ArrayList<ModType> allowedModType, IndexWaitList indexWaitList, int vacancies,
            ArrayList<String> registeredStudentMatricNo, ArrayList<Lesson> lessons) {
        this.courseCode = courseCode;
        this.indexNo = indexNo;
        this.academicUnits = academicUnits;
        this.school = school;
        this.allowedModType = allowedModType;
        // this.capacity = capacity;
        this.indexWaitList = indexWaitList;
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
            if (this.indexWaitList.getWaitList().isEmpty()) {
                this.indexWaitList.addStudent("null");
            }
            registeredStudentMatricNo.add(studentMatricNo);
            vacancies--;
            return true;
        }
    }

    // public boolean addStudentEvenIfZero(String studentMatricNo) {
    // registeredStudentMatricNo.add(studentMatricNo);
    // return true;
    // }

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

        for (int i = 0; i < this.indexWaitList.getWaitList().size(); i++) {
            if (i == indexWaitList.getWaitList().size() - 1) {
                result += indexWaitList.getWaitList().get(i);
            } else {
                result += indexWaitList.getWaitList().get(i) + ",";
            }
            result += " ";
        }

        result = result + Integer.toString(this.vacancies);
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

    public void setVacancy(int vacancy) {
        this.vacancies = vacancy;
    }

    public boolean addToWaitList(String matricNo) {
        try {
            if (this.indexWaitList.getWaitList().get(0).equals("null")) {
                this.indexWaitList.getWaitList().remove(0);
            }

            this.indexWaitList.addStudent(matricNo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public IndexWaitList getIndexWaitList() {
        return this.indexWaitList;
    }
}

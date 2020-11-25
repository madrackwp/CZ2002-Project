package CourseIndex;

import java.util.ArrayList;

/**
 * CourseIndex is an object used to store all the indexes within the STARS
 * system
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class CourseIndex {
    /**
     * The course code uniquely defines the each course available to be taken in the
     * STARS system
     */
    String courseCode;
    /**
     * Each course index will have multiple unique course index numbers
     */
    int indexNo;
    /**
     * Each course will have a specific number of academic units
     */
    int academicUnits;
    /**
     * Each course will have a school that is in charge of
     */
    String school;
    /**
     * Each course will have a fixed number of allowed mod types it can be
     * classified as (CORE, UE, GERPE_LA, GERPE_BM, GERPE_STS, MPE)
     */
    ArrayList<ModType> allowedModType;
    /**
     * Each course index will have a wait list to store students who are interested
     * in taking the course but that particular index they are interested is full
     */
    IndexWaitList indexWaitList;
    /**
     * Each index number will have a number that stores the vacancies available
     */
    int vacancies;
    /**
     * Each index will have a set of registered students
     */
    ArrayList<String> registeredStudentMatricNo;
    /**
     * Each index will have a set of lessons such as Tutorials, Lectures or Labs
     */
    ArrayList<Lesson> lessons;

    /**
     * Constructor to create the CourseIndex object
     * 
     * @param courseCode                The Course code of the course
     * @param indexNo                   The index number of the course
     * @param academicUnits             The number of Academic units
     * @param school                    The school of the CourseIndex
     * @param allowedModType            An ArrayList of ModTypes that this
     *                                  particular course can be classified as
     * @param indexWaitList             An arraylist of student matriculation
     *                                  numbers who are interested to take a
     *                                  particular course index but vacancy is 0
     * @param vacancies                 The number of vacancies for the particular
     *                                  course index
     * @param registeredStudentMatricNo An ArrayList of student matriculation
     *                                  numbers who are currently enrolled
     * @param lessons                   An ArrayList of lessons that this course
     *                                  index has
     */
    public CourseIndex(String courseCode, int indexNo, int academicUnits, String school,
            ArrayList<ModType> allowedModType, IndexWaitList indexWaitList, int vacancies,
            ArrayList<String> registeredStudentMatricNo, ArrayList<Lesson> lessons) {
        this.courseCode = courseCode;
        this.indexNo = indexNo;
        this.academicUnits = academicUnits;
        this.school = school;
        this.allowedModType = allowedModType;
        this.indexWaitList = indexWaitList;
        this.vacancies = vacancies;
        this.registeredStudentMatricNo = registeredStudentMatricNo;
        this.lessons = lessons;
    }

    /**
     * Format to print a course index object
     */
    public String toString() {
        return this.courseCode + " " + this.school + " " + Integer.toString(this.indexNo) + " "
                + Integer.toString(this.vacancies);
    }

    /**
     * Gets the vacancies for this course index object
     * 
     * @return This CourseIndex's vacancies
     */
    public int getVacancies() {
        return vacancies;
    }

    /**
     * Gets the course code for this course index object
     * 
     * @return This CourseIndex's courseCode
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * Gets the index number of this course index object
     * 
     * @return This CourseIndex's indexNo
     */
    public int getIndexNo() {
        return this.indexNo;
    }

    /**
     * Gets all currently registered students
     * 
     * @return This CourseIndex's ArrayList of registered student's matriculation
     *         numbers
     */
    public ArrayList<String> getRegisteredStudentMatricNo() {
        return this.registeredStudentMatricNo;
    }

    /**
     * Gets all lessons under this course and index number
     * 
     * @return This CourseIndex's ArrayList of Lessons
     */
    public ArrayList<Lesson> getLessons() {
        return this.lessons;
    }

    /**
     * Gets the school in charge of this course
     * 
     * @return This CourseIndex's school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Gets this course and index number's waiting list
     * 
     * @return This CourseIndex's ArrayList of String of student's matriculation
     *         number
     */
    public IndexWaitList getIndexWaitList() {
        return this.indexWaitList;
    }

    /**
     * Gets this course's number of academic units
     * 
     * @return This CourseIndex's academic units
     */
    public int getAcademicUnits() {
        return this.academicUnits;
    }

    /**
     * Gets the default mod type this course will be classified as
     * 
     * @return This Course's default ModType
     */
    public ModType getDefaultModType() {
        return this.allowedModType.get(0);
    }

    /**
     * Gets the list of what this course can be taken as
     * 
     * @return This Course ArrayList of allowed ModTypes
     */
    public ArrayList<ModType> getAllowedModTypes() {
        return this.allowedModType;
    }

    /**
     * Set the vacancy of this course code and index number
     * 
     * @param vacancy The number of vacancy to set to
     */
    public void setVacancy(int vacancy) {
        this.vacancies = vacancy;
    }

    /**
     * Change the school that is incharge of this course code and index number
     * 
     * @param school The new school to change to
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Change the course code
     * 
     * @param courseCode The new course code to set to
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Change the index number of a particular course code's index number
     * 
     * @param newIndex The new index number to change to
     */
    public void setIndexNo(int newIndex) {
        this.indexNo = newIndex;
    }

    /**
     * Add a student that is enrolled under this course
     * 
     * @param studentMatricNo The matriculation number of the student to add
     * @return True if add was successful, else false
     */
    public boolean addStudent(String studentMatricNo) {
        if (vacancies == 0) {
            return false;
        } else {
            if (registeredStudentMatricNo.get(0).equals("null")) {
                registeredStudentMatricNo.remove(0);
            }
            if (this.indexWaitList.getWaitList().isEmpty()) {
                this.indexWaitList.addStudent("null");
            } else if (!this.indexWaitList.getWaitList().isEmpty()) {
                this.indexWaitList.getWaitList().remove(0);
                if (this.indexWaitList.getWaitList().size() == 0) {
                    this.indexWaitList.getWaitList().add("null");
                }
            }

            registeredStudentMatricNo.add(studentMatricNo);
            vacancies--;
            return true;
        }
    }

    /**
     * Override the vacancy number and add a student For Staff to use only
     * 
     * @param studentMatricNo The matriculation number of the student to add
     * @return True if added successfully, else false;
     */
    public boolean addStudentEvenIfZero(String studentMatricNo) {
        registeredStudentMatricNo.add(studentMatricNo);
        return true;
    }

    /**
     * Removes a student from the list of registered student
     * 
     * @param studentMatricNo The matriculation number of the student to remove
     */
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

    /**
     * Adding a student to the wait list when the trying to add the course but has
     * no vacancy
     * 
     * @param matricNo Matriculation number of the student to add to the waitlist
     * @return True is add to waitlist is succesful, else false
     */
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

    /**
     * Used to format the CourseIndex object into one that can be written to the
     * data CourseData.txt file
     * 
     * @return Exact CourseIndex entry in the CourseData.txt file
     */
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

}

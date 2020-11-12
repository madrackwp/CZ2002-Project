package Course;

import java.util.ArrayList;

public class Course {
    private String courseCode, school;
    private ArrayList<CourseIndex> indexes;

    public Course(String courseCode, String school) {
        this.courseCode = courseCode;
        this.school = school;
        this.indexes = new ArrayList<CourseIndex>();
    };

    public boolean createIndeOx() {
        // CALL CREATE INDEX METHOD AND APPEND TO ARRAY
        return true;
    }

    public boolean dropIndex(int indexNo) {
        if (indexes.removeIf(courseIndex -> courseIndex.getIndexNo() == indexNo)) {
            return true;
        } else {
            return false;
        }
    }

    public void editCourse(String courseCode) {

    }

    public void checkCourseAvail() {
    }

    public void printStudentsByIndex() {
    }

    public void printStudentsByCourse() {
    }

    public String getcourseCode() {
        return this.courseCode;
    }

    public String getSchool() {
        return this.school;
    }

    public String getIndexes() {
        return this.indexes.toString();
    }

    @Override
    public String toString() {
        return this.courseCode + " " + this.school;

    }

}
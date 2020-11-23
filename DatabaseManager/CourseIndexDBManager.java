package DatabaseManager;

import LocalDatabase.*;

import java.util.ArrayList;

import CourseIndex.*;
import Users.*;

public class CourseIndexDBManager implements DatabaseManager {
    private ArrayList<CourseIndex> courseIndexes;

    public CourseIndexDBManager(CourseIndexDB courseIndexDB) {
        this.courseIndexes = courseIndexDB.getCourseIndexes();
    };

    @Override
    public boolean addEntry(Object courseIndex) {
        try {
            courseIndexes.add((CourseIndex) courseIndex);
            return true;
        } catch (Exception e) {
            System.out.println("FOR DEBUGGING: " + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean removeEntry(Object courseIndex) {
        if (this.courseIndexes.contains((CourseIndex) courseIndex)) {
            this.courseIndexes.remove(courseIndex);
            return true;
        } else {
            System.out.println("Entry does not exist");
            return false;
        }
    }

    public boolean addStudentToIndex(StudentAcc student, int indexNo, String courseCode) {
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getIndexNo() == indexNo && courseIndex.getCourseCode() == courseCode) {
                courseIndex.addStudent(student.getMatricNo());
                return true;
            }
        }
        return false;
    }

    public boolean removeStudentFromIndex(StudentAcc student, int indexNo, String courseCode) {
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getIndexNo() == indexNo && courseIndex.getCourseCode() == courseCode) {
                courseIndex.removeStudent(student.getMatricNo());
                return true;
            }
        }
        return false;
    }

    public CourseIndex getCourseIndexInfo(String courseCode, int indexNo) {
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode) && courseIndex.getIndexNo() == indexNo) {
                return courseIndex;
            }
        }
        // System.out.println("Course with course index do not exist!");
        return null;
    }

    public ArrayList<CourseIndex> getCourseIndexInfoArray(String courseCode) {
        ArrayList<CourseIndex> temp = new ArrayList<CourseIndex>();
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode)) {
                temp.add(courseIndex);
            }
        }
        return temp;
    }

    public ArrayList<CourseIndex> getCourseIndexes() {
        return this.courseIndexes;
    }

    @Override
    public boolean updateDatabase(Object arrayListOfCourse, Object dataBaseForCourse) {
        try {
            ArrayList<CourseIndex> updatedCourses = (ArrayList<CourseIndex>) arrayListOfCourse;
            ((CourseIndexDB) dataBaseForCourse).setCourseIndexes(updatedCourses);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void printCourses() {
        ArrayList<String> printedCourses = new ArrayList<>();
        for (CourseIndex courseIndex : this.courseIndexes) {
            String courseCodeToPrint = courseIndex.getCourseCode();
            if (!printedCourses.contains(courseCodeToPrint)) {
                printedCourses.add(courseCodeToPrint);
                System.out.println("CourseCode: " + courseCodeToPrint + " | School: " + courseIndex.getSchool()
                        + " | AUs: " + courseIndex.getAcademicUnits());
            }
        }
    }

    public void printIndexes() {
        for (CourseIndex courseIndex : this.courseIndexes) {
            System.out.println(courseIndex);
        }
    }

}

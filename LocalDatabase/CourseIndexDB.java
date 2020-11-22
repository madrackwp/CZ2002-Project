package LocalDatabase;

import java.util.ArrayList;

import CourseIndex.*;

public class CourseIndexDB implements Database {

    private ArrayList<CourseIndex> courseIndexes;

    public CourseIndexDB(ArrayList<CourseIndex> courseList) {
        this.courseIndexes = courseList;
    }

    @Override
    public void print() {
        for (CourseIndex courseIndex : this.courseIndexes) {
            System.out.println(courseIndex.getSchool() + " " + courseIndex.getCourseCode() + " "
                    + courseIndex.getIndexNo() + " " + courseIndex.getVacancies());
        }
    }

    public ArrayList<CourseIndex> getCourseIndexes() {
        return this.courseIndexes;
    }

    public boolean setCourseIndexes(ArrayList<CourseIndex> courseToSet) {
        try {
            this.courseIndexes = courseToSet;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
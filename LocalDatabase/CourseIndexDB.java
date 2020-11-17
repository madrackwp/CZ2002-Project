package LocalDatabase;

import java.util.ArrayList;

import CourseIndex.*;

public class CourseIndexDB implements Database {

    private ArrayList<CourseIndex> courseIndexes;

    public CourseIndexDB(ArrayList<CourseIndex> c) {
        this.courseIndexes = c;
    }

    @Override
    public void print() {
        for (CourseIndex courseIndex : this.courseIndexes) {
            System.out.println(courseIndex.getSchool() + " " + courseIndex.getCourseCode() + " "
                    + courseIndex.getIndexNo() + " " + courseIndex.getCapacity() + " " + courseIndex.getVacancies());
        }
    }

    public ArrayList<CourseIndex> getCourseIndexes() {
        return this.courseIndexes;
    }
}
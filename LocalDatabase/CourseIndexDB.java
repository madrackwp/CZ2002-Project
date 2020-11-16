package LocalDatabase;

import java.util.ArrayList;

import Course.*;

public class CourseIndexDB implements Database {

    private ArrayList<CourseIndex> courseIndexes;

    @Override
    public boolean addToDB(Object courseIndex) {
        try {
            courseIndexes.add((CourseIndex) courseIndex);
            return true;
        } catch (Exception e) {
            System.out.println("FOR DEBUGGING PURPOSE " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeFromDB(Object courseIndex) {
        try {
            this.courseIndexes.remove((CourseIndex) courseIndex);
            return true;
        } catch (Exception e) {
            System.out.println("FOR DEBUGGING PURPOSE " + e.getMessage());
            return false;
        }
    }

    @Override
    public void print() {
        for (CourseIndex courseIndex : this.courseIndexes) {
            System.out.println(courseIndex);
        }

    }

    @Override
    public boolean updateEntry(Object updatedCourseIndex) {
        for (CourseIndex courseIndex : this.courseIndexes) {
            if (courseIndex.getIndexNo() == ((CourseIndex) updatedCourseIndex).getIndexNo()) {
                int index = courseIndexes.indexOf(updatedCourseIndex);
                courseIndexes.set(index, (CourseIndex) updatedCourseIndex);
                return true;
            }
        }
        return false;
    }

}
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
    // @Override
    // public boolean addToDB(Object courseIndex) {
    // try {
    // courseIndexes.add((CourseIndex) courseIndex);
    // return true;
    // } catch (Exception e) {
    // System.out.println("FOR DEBUGGING PURPOSE " + e.getMessage());
    // return false;
    // }
    // }

    // @Override
    // public boolean removeFromDB(Object courseIndex) {
    // try {
    // this.courseIndexes.remove((CourseIndex) courseIndex);
    // return true;
    // } catch (Exception e) {
    // System.out.println("FOR DEBUGGING PURPOSE " + e.getMessage());
    // return false;
    // }
    // }

    // @Override
    // public boolean updateEntry(Object updatedCourseIndex) {
    // for (CourseIndex courseIndex : this.courseIndexes) {
    // if (courseIndex.getIndexNo() == ((CourseIndex)
    // updatedCourseIndex).getIndexNo()) {
    // int index = courseIndexes.indexOf(updatedCourseIndex);
    // courseIndexes.set(index, (CourseIndex) updatedCourseIndex);
    // return true;
    // }
    // }
    // return false;
    // }

}
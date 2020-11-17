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
                courseIndex.addStudent(student.getName());
                return true;
            }
        }
        return false;
    }

    public boolean removeStudentFromIndex(StudentAcc student, int indexNo, String courseCode) {
        for (CourseIndex courseIndex : courseIndexes) {
            if (courseIndex.getIndexNo() == indexNo && courseIndex.getCourseCode() == courseCode) {
                courseIndex.removeStudent(student.getName());
                return true;
            }
        }
        return false;
    }

}
<<<<<<< HEAD
=======

//package DatabaseManager;
//
//import LocalDatabase.*;
//
//public class CourseIndexDBManager implements DatabaseManager {
//    private ArrayList<CourseIndex> courseIndexes;
//
//    public CounrseIndexDBManager(CourseIndexDB courseIndexDB){
//        this.courseIndexes = courseIndexDB.getCourseIndexes();
//    };
//
//    public boolean addEntry(CourseIndex courseIndex) {
//        try {
//            courseIndexDB.add(courseIndex);
//            return true;
//        } catch (Exception e) {
//            System.out.println("FOR DEBUGGING: " + e.getMessage());
//            return false;
//        }
//
//    }
//
//    public boolean removeEntry(CourseIndex courseIndex) {
//        if (this.courseIndexes.contains(courseIndex)) {
//            this.courseIndexes.remove(courseIndex);
//            return true;
//        } else {
//            System.out.println("Entry does not exist");
//            return false;
//        }
//
//    }
//
//    public boolean addStudentToIndex(StudentAcc student, int indexNo, String courseCode){
//        for (CourseIndex courseIndex : courseIndexes){
//            if (courseIndex.)
//        }
//    }
//
//}

>>>>>>> 3890f2bbfab6e2cad152ac7ef3f997cf10347969

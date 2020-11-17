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

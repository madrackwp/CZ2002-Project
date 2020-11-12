//import java.util.ArrayList;
//
//public class CourseIndex extends Course {
//    private int indexNo;
//    private int capacity;
//    private int vacancies;
//    private ArrayList<Student> waitList;
//    private ArrayList<Student> registeredStudents;
//
//    public CourseIndex(String courseCode, String school, String description, int indexNo) {
//        super(courseCode, school, description);
//        this.indexNo = indexNo;
//    }
//
//    public int getIndexNo() {
//        return indexNo;
//    }
//
//    public int getCapacity() {
//        return this.capacity;
//    }
//
//    public int getVacancies() {
//        return this.vacancies;
//    }
//
//    public ArrayList<Student> getWaitList() {
//        return this.waitList;
//    }
//
//    public ArrayList<Student> getRegisteredStudents() {
//        return this.registeredStudents;
//    }
//
//    public boolean registerStudent(Student student) {
//        if (getVacancies() <= 0) {
//            waitList.add(student);
//            return false;
//        } else {
//            registeredStudents.add(student);
//            return true;
//        }
//    }
//
//}

import java.util.ArrayList;

public class CourseIndex extends Course {
    private int indexNo;
    private int capacity;
    private int vacancies;
    private ArrayList<Student> waitList;
    private ArrayList<Student> registeredStudents;

    public int getVacancies() {
        return this.vacancies;
    }

    public boolean registerStudent(Student student) {
        if (getVacancies() <= 0) {
            waitList.add(student);
            return false;
        } else {
            registeredStudents.add(student);
            return true;
        }
    }

}

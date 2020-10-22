import java.util.*;

public class Student extends User {
    private String name;
    private String matricNo;
    private String school;
    private int yearOfStudy;
    private ArrayList<CourseIndex> courses;

    public Student(String userName, String password, String name, String matricNo, String school, int yearOfStudy, ArrayList<CourseIndex> courses){
        super(userName, password);
        this.name = name;
        this.matricNo = name;
        this.school = school;
        this.yearOfStudy = yearOfStudy;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public ArrayList<CourseIndex> getCourses() {
        return courses;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public boolean login(){
        System.out.print("Enter username: ");
        Scanner sc = new Scanner(System.in);
        String userN = sc.next();
        System.out.print("Enter password: ");
        String passW = sc.next();

        if(userN.equals(this.getUsername())  && passW.equals(this.getPassword())) {
            System.out.println("Login successful");
            return true;
        }

        System.out.println("Invalid username or password");
        return false;
    }



}
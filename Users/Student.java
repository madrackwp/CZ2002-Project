public class Student extends User{
    private String matricNo;
    private int yearOfStudy;

    public Student(String userName, String password, String name, String school, String matricNo, int yearOfStudy) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
    }


}
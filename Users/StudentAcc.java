package Users;

public class StudentAcc extends UserAcc {
    private String matricNo;
    private int yearOfStudy;

    public StudentAcc(String userName, String password, String name, String school, String matricNo, int yearOfStudy) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
    }
}
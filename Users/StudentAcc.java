package Users;

public class StudentAcc extends UserAcc {
    private String matricNo;
    private int yearOfStudy;

    public StudentAcc(String userName, String password, String name, String school, String matricNo, int yearOfStudy) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getMatricNo() {
        return matricNo;
    }

    @Override
    public String getSchool() {
        return super.getSchool();
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }
}
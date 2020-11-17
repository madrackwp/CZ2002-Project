package Users;

public class StudentAcc extends UserAcc {
    private String matricNo;
    private int yearOfStudy;
    private int accessYear;
    private int accessMonth;
    private int accesDate;

    public StudentAcc(String userName, String password, String name, String school, String matricNo, int yearOfStudy,
    int accessYear, int month, int date) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
        this.accessYear = accessYear;
        this.accessMonth = month;
        this.accesDate = date;
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

    public int getAccessYear() {
        return accessYear;
    }

    public int getAccessMonth() {
        return accessMonth;
    }

    public int getAccesDate() {
        return accesDate;
    }

    public void setAccessYear(int accessYear) {
        this.accessYear = accessYear;
    }

    public void setAccessMonth(int accessMonth) {
        this.accessMonth = accessMonth;
    }

    public void setAccesDate(int accesDate) {
        this.accesDate = accesDate;
    }
}
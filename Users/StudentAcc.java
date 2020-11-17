package Users;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentAcc extends UserAcc {
    private String matricNo;
    private int yearOfStudy;
    private String accessDate;

    public StudentAcc(String userName, String password, String name, String school, String matricNo, int yearOfStudy,
            String accessDate) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
        this.accessDate = accessDate;

        // SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        // try {
        // this.accessDate = sdf
        // .parse(Integer.toString(date) + " " + Integer.toString(month) + " " +
        // Integer.toString(accessYear));
        // } catch (Exception e) {

        // }
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    public String getPassword() {
        return super.getPassword();
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

    public String getAccessDate() {
        return this.accessDate;
    }

    public void setAccessDate() {

    }

    // public int getAccessYear() {
    // return accessYear;
    // }

    // public int getAccessMonth() {
    // return accessMonth;
    // }

    // public int getAccesDate() {
    // return accesDate;
    // }

    // public void setAccessYear(int accessYear) {
    // this.accessYear = accessYear;
    // }

    // public void setAccessMonth(int accessMonth) {
    // this.accessMonth = accessMonth;
    // }

    // public void setAccesDate(int accesDate) {
    // this.accesDate = accesDate;
    // }
}
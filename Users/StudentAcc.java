package Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import CourseIndex.CourseIndex;

public class StudentAcc extends UserAcc {
    private String matricNo;
    private int yearOfStudy;
    private String accessDate;
    private ArrayList<CourseIndex> registeredCourseIndexes;

    public StudentAcc(String userName, String password, String name, String school, String matricNo, int yearOfStudy,
            String accessDate, ArrayList<CourseIndex> registeredCourseIndexes) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
        this.accessDate = accessDate;
        this.registeredCourseIndexes = registeredCourseIndexes;

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

    public ArrayList<CourseIndex> getRegisteredCourseIndex() {
        return this.registeredCourseIndexes;
    }

    public void setAccessDate() {

    }

}
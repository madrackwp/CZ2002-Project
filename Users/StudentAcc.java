package Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import CourseIndex.CourseIndex;
import CourseIndex.ModType;
import Timetable.Timetable;

public class StudentAcc extends UserAcc {
    private String matricNo;
    private int yearOfStudy;
    private String accessDate;
    private ArrayList<CourseIndex> registeredCourseIndexes;
    private Timetable timetable;
    private HashMap<String, ModType> courseHash;

    public StudentAcc(String userName, String password, String name, String school, String matricNo, int yearOfStudy,
            String accessDate, ArrayList<CourseIndex> registeredCourseIndexes, HashMap<String, ModType> courseHash) {
        super(userName, password, name, school);
        this.matricNo = matricNo;
        this.yearOfStudy = yearOfStudy;
        this.accessDate = accessDate;
        this.registeredCourseIndexes = registeredCourseIndexes;
        this.timetable = new Timetable(registeredCourseIndexes);
        this.courseHash = courseHash;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public HashMap<String, ModType> getCourseHash() {
        return courseHash;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public String getAccessDate() {
        return this.accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    public ArrayList<CourseIndex> getRegisteredCourseIndex() {
        return this.registeredCourseIndexes;
    }

    public void setAccessDate() {

    }

    public String toWrite() {

        String temp = super.getUserName() + " " + super.getPassword() + " " + super.getName() + " " + super.getSchool()
                + " " + this.getMatricNo() + " " + Integer.toString(this.getYearOfStudy()) + " " + this.getAccessDate();
        for (CourseIndex courseIndex : this.registeredCourseIndexes) {
            String modType = courseHash.get(courseIndex.getCourseCode()).toString();
            String courseInfo = courseIndex.getCourseCode() + "," + Integer.toString(courseIndex.getIndexNo()) + ","
                    + modType;
            temp = temp + " ";
            temp = temp + courseInfo;
        }
        return temp;
    }

    public void incrementYearOfStudy() {
        this.yearOfStudy++;
    }

    public Timetable getTimetable() {
        return this.timetable;
    }

    public void addNewCourse(CourseIndex courseIndex) {
        this.registeredCourseIndexes.add(courseIndex);
    }

    public void removeCourse(CourseIndex courseIndex) {
        this.registeredCourseIndexes.remove(courseIndex);
    }

    public boolean takingCourse(String courseCode) {
        for (CourseIndex courseIndex : this.registeredCourseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public CourseIndex getCourseIndex(String courseCode) {
        for (CourseIndex courseIndex : this.registeredCourseIndexes) {
            if (courseIndex.getCourseCode().equals(courseCode)) {
                return courseIndex;
            }
        }
        return null;
    }

}
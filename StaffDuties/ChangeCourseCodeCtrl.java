package StaffDuties;

import java.util.ArrayList;

import CourseIndex.CourseIndex;

public class ChangeCourseCodeCtrl {
    public ChangeCourseCodeCtrl() {
    }

    public ArrayList<CourseIndex> changeCourseCode(ArrayList<CourseIndex> courseList,
            ArrayList<CourseIndex> courseToChange, String courseCode, String newCourseCode) {
        for (int i = 0; i < courseToChange.size(); i++) {
            // System.out.println(courseIndex1.size());
            courseList.remove(courseToChange.get(i));
            if (courseToChange.get(i).getCourseCode().equals(courseCode))
                courseToChange.get(i).setCourseCode(newCourseCode);
            courseList.add(courseToChange.get(i));
        }
        return courseList;
    }

}

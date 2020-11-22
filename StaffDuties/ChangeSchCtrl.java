package StaffDuties;

import java.util.ArrayList;

import CourseIndex.CourseIndex;

public class ChangeSchCtrl {
    public ChangeSchCtrl() {
    }

    public ArrayList<CourseIndex> changeSchool(ArrayList<CourseIndex> courseList,
            ArrayList<CourseIndex> coursesToChange, String school, String course) {
        for (int i = 0; i < coursesToChange.size(); i++) {
            // System.out.println(courseIndex1.size());
            courseList.remove(coursesToChange.get(i));
            if (coursesToChange.get(i).getCourseCode().equals(course))
                coursesToChange.get(i).setSchool(school);
            courseList.add(coursesToChange.get(i));
        }
        return courseList;
    }

}

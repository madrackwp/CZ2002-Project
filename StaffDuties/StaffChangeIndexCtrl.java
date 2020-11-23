package StaffDuties;

import CourseIndex.CourseIndex;

public class StaffChangeIndexCtrl {
    public StaffChangeIndexCtrl() {
    }

    public CourseIndex courseIndexChangeIndex(CourseIndex courseToChange, int newIndex) {
        try {
            courseToChange.setIndexNo(newIndex);
            return courseToChange;
        } catch (Exception e) {
            System.out.println("ERROR: Could not change index");
            return null;
        }

    }

}

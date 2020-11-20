package CourseController;

import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;

public class CheckVacancyCtrl {
    public CheckVacancyCtrl() {
    }

    public int getVacancies(String courseCode, int indexNum, CourseIndexDBManager courseIndexDBManager) {
        for (CourseIndex i : courseIndexDBManager.getCourseIndexes()) {
            if (i.getCourseCode().equals(courseCode) && i.getIndexNo() == indexNum) {
                return i.getVacancies();
            }
        }
        // System.out.println("Course does not exist!");
        return -1;
    }

}

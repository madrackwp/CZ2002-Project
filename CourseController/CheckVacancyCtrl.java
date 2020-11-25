package CourseController;

import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;

/**
 * Controller that checks the vacancy of all course indexes
 * @author Chong Jing Hong
 * @since 25/11/2020
 */

public class CheckVacancyCtrl {

    /**
     * Creates the CheckVacancy controller
     */

    public CheckVacancyCtrl() {
    }

    /**
     * Gets the vacancies of a specific course index
     * @param courseCode The course code of the course index
     * @param indexNum The course index number
     * @param courseIndexDBManager The CourseIndexDBManager
     * @return The number of vacancies
     */

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

package StaffDuties;

import CourseIndex.CourseIndex;

/**
 * Controller used by staff to change the vacancy of a CourseIndex
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */

public class StaffChangeVacancyCtrl {

    /**
     * Constructor
     */
    public StaffChangeVacancyCtrl() {
    }

    /**
     * Method to change vacancies of a particular CourseIndex
     * 
     * @param courseIndex CourseIndex to have vacancy changed
     * @param vacancies   New number of vacancy
     * @return CourseIndex with new number of vacancies
     */
    public CourseIndex changeVacancies(CourseIndex courseIndex, int vacancies) {
        try {
            courseIndex.setVacancy(vacancies);
            return courseIndex;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

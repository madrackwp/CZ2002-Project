package StaffDuties;

import CourseIndex.CourseIndex;

public class StaffChangeVacancyCtrl {

    public StaffChangeVacancyCtrl() {
    }

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

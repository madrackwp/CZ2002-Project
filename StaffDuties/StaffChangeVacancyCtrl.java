package StaffDuties;

import CourseIndex.CourseIndex;

public class StaffChangeVacancyCtrl {

    public StaffChangeVacancyCtrl() {
    }

    public boolean changeVacancies(CourseIndex courseIndex, int vacancies) {
        try {
            courseIndex.setVacancy(vacancies);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

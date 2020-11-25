package StaffDuties;

import Users.StudentAcc;

/**
 * Controller used by staff to change the access period of a StudentAcc
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class StaffChangeAccessPeriodCtrl {

    /**
     * Constructor
     */
    public StaffChangeAccessPeriodCtrl() {
    }

    /**
     * Method to change access period
     * 
     * @param s       StudentAcc to change access period
     * @param newDate New access period in the format dd/MM/yyyy
     * @return the StudentAcc with the new access period
     */
    public StudentAcc changeAccessPeriod(StudentAcc s, String newDate) {
        s.setAccessDate(newDate);
        return s;
    }

}

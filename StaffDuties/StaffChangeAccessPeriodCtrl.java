package StaffDuties;

import Users.StudentAcc;

public class StaffChangeAccessPeriodCtrl {
    public StaffChangeAccessPeriodCtrl() {
    }

    public StudentAcc changeAccessPeriod(StudentAcc s, String newDate) {
        s.setAccessDate(newDate);
        return s;
    }

}

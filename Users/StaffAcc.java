package Users;

/**
 * Represents a staff account that will perform administrative actions on the
 * system
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class StaffAcc extends UserAcc {
    /**
     * This will be a unique key to identify staff accounts
     */
    private String staffIDNo;

    /**
     * Creates a new StaffAcc
     * 
     * @param userName  This StaffAcc's userName used for login in
     * @param password  This StaffAcc's hashed password
     * @param name      This StaffAcc's name
     * @param school    This StaffAcc's school/faculty
     * @param staffIDNo This StaffAcc's unique staffIDNo
     */
    public StaffAcc(String userName, String password, String name, String school, String staffIDNo) {
        super(userName, password, name, school);
        this.staffIDNo = staffIDNo;
    }

    /**
     * Gets the StaffAcc's unique ID number
     * 
     * @return this StaffAcc's staffIDNo
     */
    public String getStaffIDNo() {
        return this.staffIDNo;
    }
}

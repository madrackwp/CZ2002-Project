package Users;

/**
 * A user parent class that has access to the STARS system
 */

public class UserAcc {
    /**
     * userName is will be used to log into the system name will be the name of the
     * user school will be the school/faculty the user belongs to password here will
     * be a hashed value of the actual password the user inputs
     * 
     * @version 1.0
     * @since 2020-11-25
     */
    private String userName, name, school;
    private String password;

    // constructor
    /**
     * This creates the new user
     * 
     * @param userName This is the user's login userName
     * @param password This will be the hashed version of the user's actual password
     * @param name     This will be the user's name
     * @param school   This will be the user's school/faculty
     */
    public UserAcc(String userName, String password, String name, String school) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.school = school;
    }

    // getters
    /**
     * Gets the user's userName
     * 
     * @return this user's userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Gets the user's name
     * 
     * @return this user's userName
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's school/faculty
     * 
     * @return this user's school/faculty
     */
    public String getSchool() {
        return school;
    }

    /**
     * Gets the user's hashed password Disclaimer: this method is not used but is
     * here for administrative reasons
     * 
     * @return this user's hashed password
     */
    public String getPassword() {
        return this.password;
    }

    // setters
    /**
     * Sets the user's userName if there is a need to
     * 
     * @param userName this is the new userName to change to
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the user's name if there is a need to
     * 
     * @param name this is the new name to change to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the user's school if there is a need to
     * 
     * @param school this is the new school to change to
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Sets the user's password if there is a need to Mainly used to reset the
     * user's password
     * 
     * @param newPassword this is the password the user wants to use
     * @return boolean value, true if successful
     */
    public boolean setPassword(String newPassword) {
        try {
            this.password = Integer.toString(newPassword.hashCode());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

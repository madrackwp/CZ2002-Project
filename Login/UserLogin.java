package Login;

/**
 * This interface is used for abstraction for users to login
 * 
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public interface UserLogin {
    /**
     * This method allows users, students and staff, to login to the STARS system
     * 
     * @param users is the domain of login
     * @return the user object
     * @since 1.0
     */
    public abstract Object login(Object users);
}

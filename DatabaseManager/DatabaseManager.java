package DatabaseManager;

/** This interface is used for abstraction for manager the local databases
 * @author Chong Jing Hong
 * @version 1.0
 * @since 25/11/2020
 */

public interface DatabaseManager {

    /**
     * This method adds an entry into the local database
     * @param object the entry to be added
     * @return whether the adding is successful or not
     */

    public abstract boolean addEntry(Object object);

    /**
     * This method removes an entry into the local database
     * @param object the entry to be removed
     * @return whether the removal is successful or not
     */

    public abstract boolean removeEntry(Object object);

    /**
     * This method updates the local database
     * @param arrayList the updated data
     * @param dataBase the database to be updated
     * @return whether the updating is successful or not
     */

    public abstract boolean updateDatabase(Object arrayList, Object dataBase);

}

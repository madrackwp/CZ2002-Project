package DatabaseManager;

public interface DatabaseManager {
    public abstract boolean addEntry(Object object);

    public abstract boolean removeEntry(Object object);

    public abstract boolean updateDatabase(Object arrayList, Object dataBase);

}

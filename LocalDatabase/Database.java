package LocalDatabase;

public interface Database {
    public boolean addToDB(Object object);

    public boolean removeFromDB(Object object);

    public void print();

    public boolean updateEntry(Object object);

}

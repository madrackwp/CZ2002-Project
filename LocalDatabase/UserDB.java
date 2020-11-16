package LocalDatabase;

import java.util.ArrayList;
import Users.*;

public class UserDB implements Database {
    private ArrayList<User> userData;

    public UserDB() {
        this.userData = new ArrayList<User>();
    }

    @Override
    public boolean addToDB(Object user) {
        try {
            this.userData.add((User) user);
            return true;
        } catch (Exception e) {
            System.out.println("FOR DEBUGGING PURPOSE " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeFromDB(Object user) {
        try {
            this.userData.remove((User) user);
            return true;
        } catch (Exception e) {
            System.out.println("FOR DEBUGGING PURPOSE " + e.getMessage());
            return false;
        }
    }

    @Override
    public void print() {
        for (User user : userData) {
            System.out.println(user);
        }
    }

    @Override
    public boolean updateEntry(Object updatedUser) {
        for (User user : userData) {
            if (user.getName() == ((User) updatedUser).getName()) {
                int index = userData.indexOf(updatedUser);
                userData.set(index, (User) updatedUser);
                return true;
            }
        }
        return false;
    }

}
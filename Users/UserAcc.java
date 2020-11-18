package Users;

public class UserAcc {
    private String userName, name, school;
    private String password;

    // constructor
    public UserAcc(String userName, String password, String name, String school) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.school = school;
    }

    // getters
    public String getUserName() {
        return this.userName;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public String getPassword() {
        return this.password;
    }

    // setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean setPassword(String newPassword) {
        try {
            this.password = Integer.toString(newPassword.hashCode());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package Users;

public class Staff extends User {
    private String staffIDNo;

    public Staff(String userName, String password, String name, String school, String staffIDNo) {
        super(userName, password, name, school);
        this.staffIDNo = staffIDNo;
    }
}

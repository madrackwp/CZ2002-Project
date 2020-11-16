package Users;

public class StaffAcc extends UserAcc {
    private String staffIDNo;

    public StaffAcc(String userName, String password, String name, String school, String staffIDNo) {
        super(userName, password, name, school);
        this.staffIDNo = staffIDNo;
    }
}

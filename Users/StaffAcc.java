package Users;

public class StaffAcc extends UserAcc {
    private String staffIDNo;

    public StaffAcc(String userName, String password, String name, String school, String staffIDNo) {
        super(userName, password, name, school);
        this.staffIDNo = staffIDNo;
    }

    @Override
    public String getSchool() {
        return super.getSchool();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    public String getStaffIDNo() {
        return staffIDNo;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}

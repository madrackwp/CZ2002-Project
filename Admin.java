public class Admin extends User {
    private String name, school;
    private int staffIDNo;

    public Admin(String name, String school, int staffIDNo) {
        this.name = name;
        this.school = school;
        this.staffIDNo = staffIDNo;
    }

}

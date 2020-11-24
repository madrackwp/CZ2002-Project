package CourseIndex;

import java.util.ArrayList;

public class IndexWaitList {
    ArrayList<String> waitList;

    public IndexWaitList(ArrayList<String> w) {
        this.waitList = w;
    }

    public void addStudent(String s) {
        this.waitList.add(s);
        // System.out.println("StudentAcc added successfully!");
    }

    public void removeStudent(String s) {
        boolean check = this.waitList.remove(s);
        if (check) {
            // System.out.println("StudentAcc removed!");
        } else {
            // System.out.println("Removal unsuccessful");
        }
    }

    public ArrayList<String> getWaitList() {
        return this.waitList;
    }

}

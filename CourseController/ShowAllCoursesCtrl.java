package CourseController;

import java.util.ArrayList;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;
import Users.StudentAcc;

public class ShowAllCoursesCtrl {
    public ShowAllCoursesCtrl() {
    }

    // public void showCourses(CourseIndexDBManager dbManager) {
    // ArrayList<CourseIndex> temp = dbManager.getCourseIndexes();
    // int i = 1;
    // for (CourseIndex courseIndex : temp) {

    // System.out.print(i + " " + courseIndex + " \n");
    // i++;

    // }
    // }

    // public CourseIndex selectCourse(CourseIndexDBManager dbManager) {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("Select course to add:");
    // showCourses(dbManager);
    // int input = sc.nextInt();
    // ArrayList<CourseIndex> temp = dbManager.getCourseIndexes();
    // return temp.get(input - 1);
    // }

    public CourseIndex selectCourseThatStudentNotTaking(StudentAcc studentAcc, CourseIndexDBManager dbManager) {
        int k = 1;
        boolean found;
        ArrayList<CourseIndex> temp = new ArrayList<>();
        System.out.println("");
        System.out.println("Courses Available: ");
        for (CourseIndex courseIndex : dbManager.getCourseIndexes()) {
            found = false;
            for (int i = 0; i < studentAcc.getRegisteredCourseIndex().size(); i++) {
                if (studentAcc.getRegisteredCourseIndex().get(i).getCourseCode().equals(courseIndex.getCourseCode())) {
                    found = true;
                    break;
                }

            }
            if (found == false) {
                System.out.println(k + " " + courseIndex);
                temp.add(courseIndex);
                k++;
            }
        }
        int input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Course to Add: ");
        while (true){
            while (!sc.hasNextInt()) {
                System.out.println("Only Integers between 1 and "+(k-1)+" Accepted");
                sc.next();
            }
            input = sc.nextInt();
            if (input > 0 && input < k){
                break;
            }
            System.out.println("Invalid Input (Input should be between 1 and "+(k-1)+")");
            System.out.println("Please Re-select Course to Add: ");
        }
        if (temp.isEmpty()) {
            System.out.println("All Courses Taken!");
            return null;
        } else {
            return temp.get(input - 1);

        }

    }

}

package CourseController;

import java.util.ArrayList;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;

public class ShowAllCourses {
    public ShowAllCourses() {
    }

    public void showCourses(CourseIndexDBManager dbManager) {
        ArrayList<CourseIndex> temp = dbManager.getCourseIndexes();
        int i = 1;
        for (CourseIndex courseIndex : temp) {

            System.out.print(i + " " + courseIndex + " \n");
            i++;

        }
    }

    public CourseIndex selectCourse(CourseIndexDBManager dbManager) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select course to add");
        showCourses(dbManager);
        int input = sc.nextInt();
        ArrayList<CourseIndex> temp = dbManager.getCourseIndexes();
        return temp.get(input - 1);
    }
}

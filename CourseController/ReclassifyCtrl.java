package CourseController;

import DatabaseManager.CourseIndexDBManager;
import Users.StudentAcc;

import java.util.ArrayList;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import CourseIndex.ModType;

public class ReclassifyCtrl {
    public ReclassifyCtrl() {
    }

    public boolean reclassifyCourse(String courseCode, StudentAcc studentAcc) {
        Scanner sc = new Scanner(System.in);
        // studentAcc.getTimetable().printTimetable();
        // System.out.println("=====================================");
        // System.out.println("Select mod to reclassify:");
        // String userInput = sc.next();
        if (!studentAcc.takingCourse(courseCode)) {
            System.out.println("You Are Not Currently Taking This Course");
            return false;
        }
        ArrayList<ModType> allowedModTypes = new ArrayList<ModType>();
        System.out.println("Select Desired Course Type: ");
        int i=1;
        for (CourseIndex courseIndex : studentAcc.getRegisteredCourseIndex()) {
            if (courseIndex.getCourseCode().equals(courseCode)) {
                allowedModTypes = courseIndex.getAllowedModTypes();
                for (ModType modtype : allowedModTypes) {
                    System.out.println(i + " " + modtype.toString());
                    i++;
                }
                break;
            }
        }
        int anotherUserInput;
        while (true){
            while (!sc.hasNextInt()) {
                System.out.println("Only Integers between 1 and "+(i-1)+" Accepted");
                sc.next();
            }
            anotherUserInput = sc.nextInt();
            if (anotherUserInput > 0 && anotherUserInput < i){
                break;
            }
            System.out.println("Invalid Input (Input should be between 1 and "+(i-1)+")");
            System.out.println("Please Re-select Reclassification: ");
        }
        ModType newModType = allowedModTypes.get(anotherUserInput - 1);
        studentAcc.editModType(courseCode, newModType);
        return true;

    }

}

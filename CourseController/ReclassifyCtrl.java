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

    public boolean reclassifyCourse(StudentAcc studentAcc, CourseIndexDBManager courseIndexDBManager) {
        Scanner sc = new Scanner(System.in);
        studentAcc.getTimetable().printTimetable();
        System.out.println("=====================================");
        System.out.println("Select mod to reclassify:");
        String userInput = sc.next();
        if (!studentAcc.takingCourse(userInput)) {
            System.out.println("Invalid input");
            return false;
        }
        ArrayList<ModType> allowedModTypes = new ArrayList<ModType>();
        System.out.println("Select desired mod type: ");
        for (CourseIndex courseIndex : studentAcc.getRegisteredCourseIndex()) {
            if (courseIndex.getCourseCode().equals(userInput)) {
                allowedModTypes = courseIndex.getAllowedModTypes();
                int i = 1;
                for (ModType modtype : allowedModTypes) {
                    System.out.println(i + " " + modtype.toString());
                    i++;
                }
                break;
            }
        }

        int anotherUserInput = sc.nextInt();
        ModType newModType = allowedModTypes.get(anotherUserInput - 1);
        studentAcc.editModType(userInput, newModType);
        return true;

    }

}

package CourseController;
import java.util.ArrayList;
import CourseIndex.CourseIndex;
import Users.StudentAcc;
import DatabaseManager.CourseIndexDBManager;
import java.util.Scanner;

public class ChangeIndexCtrl {
    public ChangeIndexCtrl(){

    }

    public boolean changeIndex(StudentAcc student, String courseCode, CourseIndexDBManager courseIndexDBManager, AddDropCtrl addDropCtrl){
        Scanner sc = new Scanner(System.in);
        CourseIndex currentCourse = student.getCourseIndex(courseCode);
        if (!student.takingCourse(courseCode)){
            System.out.println("Invalid input");
            return false;
        }
        ArrayList<CourseIndex> validCourseIndexes = new ArrayList<CourseIndex>();
        for (CourseIndex i : courseIndexDBManager.getCourseIndexes()){
            if (i.getCourseCode().equals(courseCode) && (!i.equals(currentCourse))){
                    validCourseIndexes.add(i);
                }
        }
        System.out.println("Select desired index number: ");
        int k = 1;
        for (CourseIndex j : validCourseIndexes){
            System.out.println(k + ": " + j.getIndexNo());
            k++;
        }
        int userInput = sc.nextInt();
        addDropCtrl.dropCourse(student, courseCode);
        if (student.getTimetable().checkEmptySlot(validCourseIndexes.get(userInput-1))){
            addDropCtrl.addCourse(student, validCourseIndexes.get(userInput-1));
            System.out.println("Change successful");
        }
        else{
            System.out.println("Timing Clash");
            addDropCtrl.addCourse(student, currentCourse);
        }
        return true;
    }
}

package StaffDuties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Users.StudentAcc;
import CourseIndex.CourseIndex;
import CourseIndex.ModType;

public class StaffAddStudentCtrl {
    public StaffAddStudentCtrl() {
    }

    public StudentAcc AddStudent() {
        Scanner sc = new Scanner(System.in);

        String defaultPassword = Integer.toString("Welcome@123".hashCode());
        String accessDate = "01/01/2020";

        ArrayList<CourseIndex> emptyArr = new ArrayList<>();
        HashMap<String, ModType> emptyHash = new HashMap<>();
        
        System.out.println("Enter username of student");
        String username = sc.next();
        System.out.println("Enter name of student");
        String name = sc.next();
        System.out.println("Enter school of student");
        String school = sc.next().toUpperCase();
        System.out.println("Enter matric no. of student");
        String matNo = sc.next().toUpperCase();
        System.out.println("Enter year of study of student");
        int yearOfStudy = sc.nextInt();
        
        while (true) {
            System.out.println("Enter access date of student (format dd/mm/yyyy)");
            accessDate = sc.next();
            String[] tokens = accessDate.split("/");
            if ((tokens.length == 3) && (tokens[0].length() == 2) && (tokens[1].length() == 2) && (tokens[2].length() == 4)) 
                break;
            else 
                System.out.println("Invalid date input");
        }

        StudentAcc student = new StudentAcc(username, defaultPassword, name, school, matNo, yearOfStudy, accessDate,
                emptyArr, emptyHash);
                
        return student;
    }
}

package StaffDuties;

import java.util.ArrayList;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import CourseIndex.Day;
import CourseIndex.IndexWaitList;
import CourseIndex.Lesson;
import CourseIndex.ModType;
import CourseIndex.Type;
import DatabaseManager.CourseIndexDBManager;

/**
 * Controller used by staff to create indexes for existing course codes
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 25-11-25
 */
public class StaffCreateIndex {
    /**
     * Constructor
     */
    public StaffCreateIndex() {
    }

    /**
     * Method to create the new index for existing course codes
     * 
     * @param indexDBManager The instance of the CourseIndexDB manager used in the
     *                       program
     * @param courseCode     The course code to create the new index
     * @param newIndexNo     The new index number
     * @return The newly created CourseIndex for the selected newIndexNo and
     *         existing courseCode
     */
    public CourseIndex createIndex(CourseIndexDBManager indexDBManager, String courseCode, int newIndexNo) {
        Scanner sc = new Scanner(System.in);

        CourseIndex courseToRef = indexDBManager.getCourseIndexInfoArray(courseCode).get(0);
        int noOfAUS = courseToRef.getAcademicUnits();
        String school = courseToRef.getSchool();
        ArrayList<ModType> allowedModType = courseToRef.getAllowedModTypes();
        ArrayList<String> nullList = new ArrayList<>();
        nullList.add("null");
        ArrayList<String> registeredMatNo = nullList;
        IndexWaitList indexWaitList = new IndexWaitList(nullList);

        System.out.println("Enter number of vacancies: ");
        int vacancy = sc.nextInt();

        ArrayList<Lesson> lessonsInCourse = new ArrayList<>();
        lessonsInCourse = courseToRef.getLessons();
        ArrayList<Type> lessonType = new ArrayList<>();

        for (Lesson lesson : lessonsInCourse) {
            lessonType.add(lesson.getType());
        }

        ArrayList<Lesson> lessonsToAdd = new ArrayList<>();

        for (int x = 0; x < lessonType.size(); x++) {
            if (lessonType.get(x) == Type.valueOf("Lecture")) {
                String startTime = courseToRef.getLessons().get(x).getStartTime();
                String endTime = courseToRef.getLessons().get(x).getEndTime();
                Day day = courseToRef.getLessons().get(x).getDay();
                Lesson lesson = new Lesson(startTime, endTime, lessonType.get(x), day);
                lessonsToAdd.add(lesson);
            } else {
                System.out.print("For " + lessonType.get(x).toString());
                System.out.println(" enter day");
                Day day = Day.valueOf(sc.next().toUpperCase());

                System.out.println("Enter start time in format HH:MM in 24Hr clock");
                String startTime = sc.next();

                System.out.println("Enter end time in format HH:MM in 24Hr clock");
                String endTime = sc.next();

                Lesson lesson = new Lesson(startTime, endTime, lessonType.get(x), day);
                lessonsToAdd.add(lesson);
            }

        }

        CourseIndex newIndex = new CourseIndex(courseCode, newIndexNo, noOfAUS, school, allowedModType, indexWaitList,
                vacancy, registeredMatNo, lessonsToAdd);
        System.out.println("Course index created!");
        return newIndex;
    }

}

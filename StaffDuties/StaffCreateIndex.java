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

public class StaffCreateIndex {
    public StaffCreateIndex() {
    }

    public CourseIndex createIndex(CourseIndexDBManager indexDBManager) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter course code to add index: ");
        String courseCode = sc.next();
        System.out.println("Enter new index to add: ");
        int index = sc.nextInt();

        // to check if course index already exists inside the database
        CourseIndex courseToCheck = indexDBManager.getCourseIndexInfo(courseCode, index);
        if (courseToCheck == null) {
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

            CourseIndex newIndex = new CourseIndex(courseCode, index, noOfAUS, school, allowedModType, indexWaitList,
                    vacancy, registeredMatNo, lessonsToAdd);
            System.out.println("Course index created!");

            return newIndex;
        } else {
            System.out.println("ERROR: Course code does not exist, create new course!");
            return null;
        }
    }

}

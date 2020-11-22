package StaffDuties;

import java.util.ArrayList;
import java.util.Scanner;

import CourseIndex.CourseIndex;
import CourseIndex.Day;
import CourseIndex.IndexWaitList;
import CourseIndex.Lesson;
import CourseIndex.ModType;
import CourseIndex.Type;

public class StaffCreateCourseCtrl {
    public StaffCreateCourseCtrl() {
    }

    public ArrayList<CourseIndex> createCourse() {
        ArrayList<CourseIndex> newCourseIndexes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Add Course");
        String courseCode = sc.next();

        System.out.println("Course AU:");
        int noOfAUs = sc.nextInt();

        System.out.println("Course School");
        String school = sc.next();

        System.out.println("How many allowed mod types?");
        int noOfModTypes = sc.nextInt();

        ArrayList<ModType> allowedModTypes = new ArrayList<ModType>();
        for (int i = 0; i < noOfModTypes; i++) {
            System.out.println("Course Type: CORE, UE, GERPE_LA, GERPE_BM, GERPE_STS, MPE");
            ModType modType = ModType.valueOf(sc.next());
            if (allowedModTypes.contains(modType)) {
                System.out.println("Mod Type already added!");
                i--;
            }
            allowedModTypes.add(modType);
        }

        System.out.println("How many different lesson types?");
        int noOfLessons = sc.nextInt();

        ArrayList<Type> lessonTypesInCourse = new ArrayList<>();
        for (int x = 0; x < noOfLessons; x++) {
            System.out.println("Enter lesson types that this course has");
            Type lessonType = Type.valueOf(sc.next());
            if (lessonTypesInCourse.contains(lessonType)) {
                System.out.println("Lesson Type already added!");
                x--;
            }
            lessonTypesInCourse.add(lessonType);
        }

        System.out.println("How many indexes would you like to create?");
        int noOfIndexes = sc.nextInt();

        boolean in = false;
        Lesson lectureLesson = null;
        String lessonStartTime, lessonEndTime;
        Day lessonDay;
        for (int j = 0; j < noOfIndexes; j++) {
            System.out.println("Enter the index no to create: ");
            int indexNo = sc.nextInt();
            ArrayList<Lesson> lessons = new ArrayList<>();

            System.out.println("No of vacancies for this index");
            int vacancies = sc.nextInt();

            for (Type type : lessonTypesInCourse) {
                if ((type.toString().equals("Lecture")) && (in == false)) {
                    System.out.print("For " + type.toString());
                    System.out.println(" enter day");
                    lessonDay = Day.valueOf(sc.next().toUpperCase());
                    System.out.println("Enter start time in format HH:MM in 24Hr clock");
                    lessonStartTime = sc.next();
                    System.out.println("Enter end time in format HH:MM in 24Hr clock");
                    lessonEndTime = sc.next();
                    lectureLesson = new Lesson(lessonStartTime, lessonEndTime, type, lessonDay);
                    lessons.add(lectureLesson);
                    in = true;
                } else if ((type.toString().equals("Lecture")) && (in == true)) {
                    System.out.print(lectureLesson);
                    lessons.add(lectureLesson);
                } else {
                    System.out.print("For " + type.toString());
                    System.out.println(" enter day");
                    Day day = Day.valueOf(sc.next().toUpperCase());

                    System.out.println("Enter start time in format HH:MM in 24Hr clock");
                    String startTime = sc.next();

                    System.out.println("Enter end time in format HH:MM in 24Hr clock");
                    String endTime = sc.next();

                    Lesson lesson = new Lesson(startTime, endTime, type, day);
                    lessons.add(lesson);
                }
            }
            ArrayList<String> nullList = new ArrayList<>();
            nullList.add("null");

            IndexWaitList indexWaitList = new IndexWaitList(nullList);

            CourseIndex newCourseIndex = new CourseIndex(courseCode, indexNo, noOfAUs, school, allowedModTypes,
                    indexWaitList, vacancies, nullList, lessons);
            newCourseIndexes.add(newCourseIndex);
        }

        return newCourseIndexes;

    }
}

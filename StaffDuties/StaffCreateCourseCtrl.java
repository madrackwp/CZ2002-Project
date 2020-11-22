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
        boolean checker4ModType = true;

        System.out.println("Add Course");
        String courseCode = sc.next().toUpperCase();

        System.out.println("Course AU:");
        int noOfAUs = sc.nextInt();

        System.out.println("Course School");
        String school = sc.next().toUpperCase();
        
        System.out.println("How many allowed mod types? (Max 6)");
        int noOfModTypes = sc.nextInt();

        ArrayList<ModType> allowedModTypes = new ArrayList<ModType>();
        for (int i = 0; i < noOfModTypes; i++) {
            try {
                System.out.println("Course Type: CORE, UE, GERPE_LA, GERPE_BM, GERPE_STS, MPE");
                ModType modType = ModType.valueOf(sc.next().toUpperCase());
                if (allowedModTypes.contains(modType)) {
                    System.out.println("Mod Type already added!");
                    i--;
                }
                allowedModTypes.add(modType);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Mod Input");
                        i--;
                }
        }

        System.out.println("How many different lesson types? (Max 3)");
        int noOfLessons = sc.nextInt();

        ArrayList<Type> lessonTypesInCourse = new ArrayList<>();
        for (int x = 0; x < noOfLessons; x++) {
            try {
                System.out.println("Enter lesson types that this course has: Lecture, Tutorial or Lab");
                Type lessonType = Type.valueOf(sc.next());
                if (lessonTypesInCourse.contains(lessonType)) {
                    System.out.println("Lesson Type already added!");
                    x--;
                } 
                lessonTypesInCourse.add(lessonType);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Lesson Type");
                x--;
            }
        }
            
        System.out.println("How many indexes would you like to create?");
        int noOfIndexes = sc.nextInt();

        boolean in = false;
        Lesson lectureLesson = null;
        String lessonStartTime = "08:30", lessonEndTime = "08:30", startTime = "08:30", endTime = "08:30";
        Day lessonDay = Day.MONDAY, day = Day.MONDAY;

        for (int j = 0; j < noOfIndexes; j++) {
            System.out.println("Enter the index no to create: ");
            int indexNo = sc.nextInt();
            ArrayList<Lesson> lessons = new ArrayList<>();

            System.out.println("No of vacancies for this index");
            int vacancies = sc.nextInt();

            for (Type type : lessonTypesInCourse) {
                if ((type.toString().equals("Lecture")) && (in == false)) {
                    boolean inside = true;
                    while (inside == true) {
                        try {
                            System.out.print("For " + type.toString());
                            System.out.println(" enter day");
                            lessonDay = Day.valueOf(sc.next().toUpperCase());
                            inside = false;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Day");
                        }
                    boolean inside1 = true;
                    while (inside1 == true) {
                        System.out.println("Enter start time in format HH:MM in 24Hr clock");
                        lessonStartTime = sc.next();
                        String tokens[] = lessonStartTime.split(":");
                        if ((tokens.length == 2)) 
                            inside1 = false;
                        else
                            System.out.println("Invalid time format");
                    }
                    boolean inside2 = true;
                    while (inside2 == true) {
                        System.out.println("Enter end time in format HH:MM in 24Hr clock");
                        lessonEndTime = sc.next();
                        String[] tokeners = lessonEndTime.split(":");
                        if ((tokeners.length == 2)) 
                            inside2 = false;
                        else
                            System.out.println("Invalid time format");
                    }
                        lectureLesson = new Lesson(lessonStartTime, lessonEndTime, type, lessonDay);
                        lessons.add(lectureLesson);
                        in = true;
                    }
                } else if ((type.toString().equals("Lecture")) && (in == true)) {
                    lessons.add(lectureLesson);
                } else {
                    boolean inside3 = true;
                    while (inside3 == true) {
                        try {
                            System.out.print("For " + type.toString());
                            System.out.println(" enter day");
                            day = Day.valueOf(sc.next().toUpperCase());
                            inside3 = false;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Day");
                        }
                    }

                    boolean inside4 = true;
                    while (inside4 == true) {
                        System.out.println("Enter start time in format HH:MM in 24Hr clock");
                        startTime = sc.next();
                        String tokens[] = startTime.split(":");
                        if ((tokens.length == 2)) 
                            inside4 = false;
                        else
                            System.out.println("Invalid time format");
                    }

                    boolean inside5 = true;
                    while (inside5 == true) {
                        System.out.println("Enter end time in format HH:MM in 24Hr clock");
                        endTime = sc.next();
                        String[] tokeners = endTime.split(":");
                        if ((tokeners.length == 2)) 
                            inside5 = false;
                        else
                            System.out.println("Invalid time format");
                    }
                    
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

package StaffDuties;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import CourseIndex.CourseIndex;
import CourseIndex.Day;
import CourseIndex.IndexWaitList;
import CourseIndex.Lesson;
import CourseIndex.ModType;
import CourseIndex.Type;

/**
 * Controller used by Staff to create a new course
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class StaffCreateCourseCtrl {
    /**
     * Constructor
     */
    public StaffCreateCourseCtrl() {
    }

    /**
     * Method to check if time input is in the correct format
     * 
     * @param time input time String must be in format HH:MM from 08:30 to 23:30
     * @return true if valid input, else false
     */
    public static boolean isValidTime(String time) {

        // Regex to check valid time in 24-hour format.
        String regex = "(0[8-9]|1[0-9]|2[0-3]):30";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the time is empty
        // return false
        if (time == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given time
        // and regular expression.
        Matcher m = p.matcher(time);

        // Return if the time
        // matched the ReGex
        return m.matches();
    }

    /**
     * Method to create course
     * 
     * @param courseCodeToCreate Course Code that staff would like to create
     * @return ArrayList of CourseIndex objects with the new course code
     */
    public ArrayList<CourseIndex> createCourse(String courseCodeToCreate) {
        ArrayList<CourseIndex> newCourseIndexes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Getting the number of AUs for this course
        System.out.println("Course AU, integers only");
        while (!sc.hasNextInt()) {
            System.out.println("Integers only");
            sc.next();
        }
        int noOfAUs = sc.nextInt();

        // Getting the school for this course
        System.out.println("Course School");
        String school = sc.next().toUpperCase();

        // Getting the number of modtypes
        System.out.println("No. of allowed mod types, integers only (CORE, UE, GERPE_LA, GERPE_BM, GERPE_STS, MPE)");
        int noOfModTypes;
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.println("ERROR: Integers of at most 6 only");
                sc.next();
            }
            noOfModTypes = sc.nextInt();
            if (noOfModTypes >= 7 || noOfModTypes < 0) {
                System.out.println("ERROR: Positive integers of at most 6 only");
            } else {
                break;
            }
        }

        // Getting the modtypes
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
                System.out.println("Invalid Mod Input, follow examples above!");
                i--;
            }
        }

        // Getting the number of different lesson types
        System.out.println("No. of different lesson types, integers only (Lecture, Tutorial, Lab)");

        int noOfLessons;
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.println("ERROR: Positive integers of at most 3 only");
                sc.next();
            }
            noOfLessons = sc.nextInt();
            if (noOfLessons > 3 || noOfLessons < 1) {
                System.out.println("ERROR: Positive integers of at most 3 only");
            } else {
                break;
            }
        }

        // Getting the lessontypes
        ArrayList<Type> lessonTypesInCourse = new ArrayList<>();
        for (int x = 0; x < noOfLessons; x++) {
            try {
                System.out.println("Enter lesson types that this course has: Lecture, Tutorial or Lab");
                // This is to check that only the first letter is capitalised
                String lessonInput = sc.next();
                String lessonInputFormatted = lessonInput.substring(0, 1).toUpperCase() + lessonInput.substring(1);
                Type lessonType = Type.valueOf(lessonInputFormatted);
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
        while (!sc.hasNextInt()) {
            System.out.println("Integers only");
            sc.next();
        }
        int noOfIndexes = sc.nextInt();

        boolean in = false;
        Lesson lectureLesson = null;
        String lessonStartTime = "08:30", lessonEndTime = "08:30", startTime = "08:30", endTime = "08:30";
        Day lessonDay = Day.MONDAY, day = Day.MONDAY;

        for (int j = 0; j < noOfIndexes; j++) {
            System.out.println("Enter the index no to create: ");
            while (!sc.hasNextInt()) {
                System.out.println("Integers only");
                sc.next();
            }
            int indexNo = sc.nextInt();
            ArrayList<Lesson> lessons = new ArrayList<>();

            System.out.println("No of vacancies for this index");
            while (!sc.hasNextInt()) {
                System.out.println("Integers only");
                sc.next();
            }
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
                    }
                    boolean inside1 = true;
                    while (inside1 == true) {
                        System.out.println("Enter start time in format HH:MM in 24Hr clock");
                        lessonStartTime = sc.next();
                        if (isValidTime(lessonStartTime))
                            inside1 = false;
                        else
                            System.out.println("Time should be between 08:30 to 23:30");
                    }
                    boolean inside2 = true;
                    while (inside2 == true) {
                        System.out.println("Enter end time in format HH:MM in 24Hr clock");
                        lessonEndTime = sc.next();
                        if (isValidTime(lessonEndTime))
                            inside2 = false;
                        else
                            System.out.println("Time should be between 08:30 to 23:30");
                    }
                    lectureLesson = new Lesson(lessonStartTime, lessonEndTime, type, lessonDay);
                    lessons.add(lectureLesson);
                    in = true;
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
                        if (isValidTime(startTime))
                            inside4 = false;
                        else
                            System.out.println("Time should be between 08:30 to 23:30");
                    }

                    boolean inside5 = true;
                    while (inside5 == true) {
                        System.out.println("Enter end time in format HH:MM in 24Hr clock");
                        endTime = sc.next();
                        if (isValidTime(endTime))
                            inside5 = false;
                        else
                            System.out.println("Time should be between 08:30 and 23:30");
                    }

                    Lesson lesson = new Lesson(startTime, endTime, type, day);
                    lessons.add(lesson);
                }
            }
            ArrayList<String> nullList = new ArrayList<>();
            nullList.add("null");

            IndexWaitList indexWaitList = new IndexWaitList(nullList);

            CourseIndex newCourseIndex = new CourseIndex(courseCodeToCreate, indexNo, noOfAUs, school, allowedModTypes,
                    indexWaitList, vacancies, nullList, lessons);
            newCourseIndexes.add(newCourseIndex);
        }
        return newCourseIndexes;
    }
}

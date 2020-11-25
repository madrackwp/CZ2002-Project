package Timetable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import CourseIndex.*;

/**
 * An object that will store and display the StudentAcc's registered courses
 * Also used to manage adding courses by checking if the course the student
 * wants to clashes with anything else
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class Timetable {
    /**
     * The Timetable object stores an instance of the courses taken by the student
     * in order to keep track of the registered courses
     */
    private ArrayList<CourseIndex> coursestaken;
    /**
     * This 2D array will manage the time slots of the student for the purpose of
     * adding courses
     */
    private String[][] timetable;
    /**
     * The timetable will also have an instance of the HashMap which stores what
     * this student is taking a particular course as
     */
    private HashMap<String, ModType> courseHash;

    /**
     * This creates the timetable object based on the student's current registered
     * courses The timetable is also created here. It is a 5 by 14 2D String array
     * where each column will be 5 days of the week and the 14 rows are all the
     * available periods in a day
     * 
     * @param c          The ArrayList of courseIndex objects that the student is
     *                   currently taking
     * @param courseHash Stores information on how this student is taking the mod
     *                   (CORE,GERPE_LA,GERPE_STS,GERPE_BM,UE or MPE)
     */
    public Timetable(ArrayList<CourseIndex> c, HashMap<String, ModType> courseHash) {
        this.coursestaken = c;
        this.timetable = new String[5][14];
        this.courseHash = courseHash;
    }

    /**
     * This method is to check if this student's timetable is able to fit the new
     * index to add
     * 
     * @param c The CourseIndex to check if it can be added
     * @return True if the student's timetable can accomodate the new course index,
     *         otherwise false
     */
    public boolean checkEmptySlot(CourseIndex c) {
        this.timetable = new String[5][14];
        for (int i = 0; i < coursestaken.size(); i++) {
            this.addIndex(this.coursestaken.get(i));
        }
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        ArrayList<Lesson> arrayOfLessons = c.getLessons();
        Date dayStart = null;
        try {
            dayStart = format.parse("08:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < arrayOfLessons.size(); i++) {
            Date startTime = null;
            Date endTime = null;
            try {
                startTime = format.parse(arrayOfLessons.get(i).getStartTime());
                endTime = format.parse(arrayOfLessons.get(i).getEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long diff = endTime.getTime() - startTime.getTime();
            long diffHours = diff / (60 * 60 * 1000);
            int exactDiffHours = (int) (diffHours);
            long start = startTime.getTime() - dayStart.getTime();
            long startHours = start / (60 * 60 * 1000);
            int exactStartHours = (int) (startHours);
            Day day = arrayOfLessons.get(i).getDay();
            switch (day) {
                case MONDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        if (timetable[0][exactStartHours] == null) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                    break;
                case TUESDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        if (timetable[1][exactStartHours] == null) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                    break;
                case WEDNESDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        if (timetable[2][exactStartHours] == null) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                    break;
                case THURSDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        if (timetable[3][exactStartHours] == null) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                    break;
                case FRIDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        if (timetable[4][exactStartHours] == null) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                    break;
            }
        }
        return true;
    }

    /**
     * This a CourseIndex to this student's timetable
     * 
     * @param c This is the CourseIndex the student will be adding to the timetable
     */
    public void addIndex(CourseIndex c) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        ArrayList<Lesson> arrayOfLessons = c.getLessons();
        Date dayStart = null;
        try {
            dayStart = format.parse("08:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < arrayOfLessons.size(); i++) {
            Date startTime = null;
            Date endTime = null;
            try {
                startTime = format.parse(arrayOfLessons.get(i).getStartTime());
                endTime = format.parse(arrayOfLessons.get(i).getEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long diff = endTime.getTime() - startTime.getTime();
            long diffHours = diff / (60 * 60 * 1000);
            int exactDiffHours = (int) (diffHours);
            long start = startTime.getTime() - dayStart.getTime();
            long startHours = start / (60 * 60 * 1000);
            int exactStartHours = (int) (startHours);
            Day day = arrayOfLessons.get(i).getDay();
            Type type = arrayOfLessons.get(i).getType();
            switch (day) {
                case MONDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        timetable[0][exactStartHours] = c.getCourseCode() + type;
                    }
                    break;
                case TUESDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        timetable[1][exactStartHours] = c.getCourseCode() + type;
                    }
                    break;
                case WEDNESDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        timetable[2][exactStartHours] = c.getCourseCode() + type;
                    }
                    break;
                case THURSDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        timetable[3][exactStartHours] = c.getCourseCode() + type;
                    }
                    break;
                case FRIDAY:
                    for (int j = 0; j < exactDiffHours; j++, exactStartHours++) {
                        timetable[4][exactStartHours] = c.getCourseCode() + type;
                    }
                    break;
            }
        }
    }

    /**
     * Prints the timetable
     */
    public void printTimetable() {
        int totalAU = 0;
        System.out.println("");
        System.out.println("Timetable");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < coursestaken.size(); i++) {
            totalAU += coursestaken.get(i).getAcademicUnits();
            System.out.print(coursestaken.get(i).getCourseCode() + " ");
            System.out.print(coursestaken.get(i).getIndexNo() + " ");
            System.out.print(coursestaken.get(i).getAcademicUnits() + "AU ");
            System.out.println(courseHash.get(coursestaken.get(i).getCourseCode()));

            for (int j = 0; j < coursestaken.get(i).getLessons().size(); j++) {
                System.out.print(coursestaken.get(i).getLessons().get(j).getType() + " ");
                System.out.print(coursestaken.get(i).getLessons().get(j).getDay() + " ");
                System.out.print(coursestaken.get(i).getLessons().get(j).getStartTime() + "-");
                System.out.print(coursestaken.get(i).getLessons().get(j).getEndTime());
                System.out.println();
            }
            System.out.println("----------------------------------------------");
        }
        System.out.println("Total AUs taken: " + totalAU);
    }
}

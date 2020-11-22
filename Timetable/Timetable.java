package Timetable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import CourseIndex.*;

public class Timetable {
    private ArrayList<CourseIndex> coursestaken;
    private String[][] timetable;
    private HashMap<String, ModType> courseHash;

    public Timetable(ArrayList<CourseIndex> c, HashMap<String, ModType> courseHash) {
        this.coursestaken = c;
        this.timetable = new String[5][14];
        this.courseHash = courseHash;
    }

    public boolean checkEmptySlot(CourseIndex c) {
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

    public void printTimetable() {
        int totalAU = 0;
        System.out.println("");
        System.out.println("Student Timetable");
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

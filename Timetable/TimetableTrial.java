package Timetable;

import java.util.ArrayList;
import CourseIndex.CourseIndex;
import CourseIndex.Day;
import CourseIndex.Type;
import CourseIndex.Lesson;

public class TimetableTrial {
    public static void main(String[] args) {

        ArrayList<String> matricnumber = new ArrayList<String>();
        matricnumber.add("U1921535H");

        ArrayList<Lesson> lessons1 = new ArrayList<Lesson>();
        Lesson lesson11 = new Lesson("09:30", "11:30", Type.Lecture, Day.MONDAY);
        lessons1.add(lesson11);

        ArrayList<Lesson> lessons2 = new ArrayList<Lesson>();
        Lesson lesson21 = new Lesson("11:30", "13:30", Type.Tutorial, Day.MONDAY);
        lessons2.add(lesson21);
        CourseIndex index1 = new CourseIndex("CZ2001", 10001, "SCSE", 20, 20, matricnumber, lessons1);
        CourseIndex index2 = new CourseIndex("CZ2002", 10102, "SCSE", 30, 30, matricnumber, lessons2);

        ArrayList<CourseIndex> courses = new ArrayList<CourseIndex>();
        courses.add(index1);

        Timetable timetable1 = new Timetable(courses);
        System.out.println(timetable1.checkEmptySlot(index2));
        timetable1.printTimetable();
    }
}

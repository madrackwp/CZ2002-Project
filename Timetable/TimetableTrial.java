package Timetable;
import java.util.ArrayList;
import CourseIndex.CourseIndex;

public class TimetableTrial {
    public static void main(String[] args){
        System.out.println("Hello World");
        ArrayList<String> matricnumber = new ArrayList<String>();
        matricnumber.add("U1921535H");
        CourseIndex index1 = new CourseIndex(10001, 20, matricnumber, "Monday", "09:30", "11:30", 
        "Tuesday", "14:30", "17:30", "CZ2001", "SCSE");
        CourseIndex index2 = new CourseIndex(10102, 20, matricnumber, "Wednesday", "08:30", "10:30", 
        "Thursday", "18:30", "21:30", "CZ2002", "SCSE");
        CourseIndex index3 = new CourseIndex(10203, 20, matricnumber, "Monday", "11:30", "12:30", 
        "Thursday", "11:30", "12:30", "CZ2003", "SCSE");

        ArrayList<CourseIndex> courses = new ArrayList<CourseIndex>();
        courses.add(index1);
        courses.add(index2);
        courses.add(index3);

        Timetable timetable1 = new Timetable(courses);
        for(int index=0;index<courses.size();index++){
            timetable1.addIndex(courses.get(index));
        }

        timetable1.printTimetable(timetable1.getTimetable());
    }
}

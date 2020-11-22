
import java.util.ArrayList;

import CourseIndex.CourseIndex;
import CourseIndex.Day;
import CourseIndex.IndexWaitList;
import CourseIndex.Lesson;
import CourseIndex.ModType;
import CourseIndex.Type;
import Notification.NotificationManager;

public class TestEmail {
    public static void main(String[] args) {
        NotificationManager n = new NotificationManager();
        ArrayList<ModType> allowedModType = new ArrayList<>();
        allowedModType.add(ModType.valueOf("CORE"));
        ArrayList<String> nullList = new ArrayList<>();
        nullList.add("null");
        IndexWaitList i = new IndexWaitList(nullList);
        ArrayList<Lesson> l = new ArrayList<>();
        Lesson newLesson = new Lesson("08:30", "09:30", Type.valueOf("Lecture"), Day.valueOf("MONDAY"));
        l.add(newLesson);
        CourseIndex c = new CourseIndex("CZ1234", 1234, 3, "SCSE", allowedModType, i, 10, nullList, l);

        n.sendEmail("student1", "bobtan", c);
    }
}

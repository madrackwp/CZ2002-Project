package Notification;

import java.util.Scanner;

import CourseIndex.CourseIndex;

public class NotificationManager {
    public NotificationManager() {
    }
    // public static void notify(){

    // }

    public boolean sendEmail(String studentUserName, String studentName, CourseIndex courseIndex) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.sendNotification(studentUserName, studentName, courseIndex);
        return true;
    }

}

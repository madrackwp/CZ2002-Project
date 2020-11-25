package Notification;

import CourseIndex.CourseIndex;

/**
 * The notification manager will handle notifications sent out to Users
 */

public class NotificationManager {

    /**
     * Constructor
     */
    public NotificationManager() {
    }

    /**
     * Method to send an email about successful transfer from wait list to
     * registered
     * 
     * @param studentUserName userName of student to be notified
     * @param studentName     name of the student to be notified
     * @param courseIndex     CourseIndex which the student will be tranfered from
     *                        the waitlist to the registered list
     * @return true if successful email sent, else print error message and return
     *         false
     */
    public boolean sendEmail(String studentUserName, String studentName, CourseIndex courseIndex) {
        try {
            EmailNotification emailNotification = new EmailNotification();
            emailNotification.sendNotification(studentUserName, studentName, courseIndex);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}

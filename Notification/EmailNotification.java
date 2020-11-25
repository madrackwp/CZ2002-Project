package Notification;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import CourseIndex.CourseIndex;

/**
 * Email notification class to send emails to students
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class EmailNotification {
    /**
     * Constructor
     */
    public EmailNotification() {
    }

    /**
     * Method to send notification
     * 
     * @param username    userName of receipient
     * @param studentName name of receipient
     * @param courseIndex CourseIndex where receipient is added to the registered
     *                    courses from the wait list
     */
    public static void sendNotification(String username, String studentName, CourseIndex courseIndex) {
        String courseCode = courseIndex.getCourseCode();
        int indexNo = courseIndex.getIndexNo();
        String toemail = username + studentName + "@gmail.com";
        Mailer.send("staffaccntustar@gmail.com", "P@ssword12345", toemail, "Notification: Message from STARS Admin",
                " You have been added to a course on your wait list.\nCourse Code: " + courseCode + "\nIndex: "
                        + Integer.toString(indexNo));
    }
}

/**
 * API to send email
 */
class Mailer {

    /**
     * Constructor
     */
    public Mailer() {
    }

    /**
     * 
     * @param from     Email sender
     * @param password Password of Sender
     * @param to       Email of recipient
     * @param sub      Message subject
     * @param msg      Message body
     */
    public static void send(String from, String password, String to, String sub, String msg) {
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            // send message
            Transport.send(message);
            // System.out.println("Notification sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}

package Notification;

import java.util.Scanner;

public class NotificationManager {
    // public static void notify(){
        
    // }

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the student's username: ");
        String username=sc.nextLine();
        EmailNotification.sendNotification(username); 

    }

    
}

import java.util.Scanner;

import UserInterfaces.StaffUI;
import UserInterfaces.StudentUI;

/**
 * The main user interface that will run the entire program
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class STARSUI {
    public static void main(String[] args) {
        boolean run = true;
        Scanner sc = new Scanner(System.in);

        while (run) {
            System.out.println("========================");
            System.out.println("====Welcome to STARS====");
            System.out.println("Login domain:");
            System.out.println("1. Student");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.println("========================");

            int userChoice;
            while (true) {
                while (!sc.hasNextInt()) {
                    System.out.println("Integers only!");
                    sc.next();
                }
                userChoice = sc.nextInt();
                if (userChoice > 3 || userChoice <= 0) {
                    System.out.println("Input 1-3 only");
                } else {
                    break;
                }
            }

            switch (userChoice) {
                case 1:
                    StudentUI studentUI = new StudentUI();
                    studentUI.runUI();
                    break;
                case 2:
                    StaffUI staffUI = new StaffUI();
                    staffUI.runUI();
                    break;
                case 3:
                    System.out.println("Logging off~");
                    run = false;
                    break;
                default:
                    break;
            }

        }
    }

}

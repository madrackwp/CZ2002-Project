//import java.util.ArrayList;
//import java.util.Scanner;
//
//import LocalDatabase.UserDB;
//import Users.*;
//
//public class UserInterface {
//
//     public UserInterface() {
//         int userChoice;
//         System.out.println("Welcome to STARS");
//         System.out.println("Select login: 1. StudentAcc 2. StaffAcc");
//         Scanner sc = new Scanner(System.in);
//         userChoice = sc.nextInt();
//         if(userChoice == 1){
//             this.studentLogin();
//         }
//         else if(userChoice == 2){
//             this.staffLogin();
//         }
//     }
//
//     public StudentAcc studentLogin(){
//         UserReader ur = new UserReader();
//         ArrayList<StudentAcc> sl = ur.ReadFile();
//        if(!sl.isEmpty()){
//            UserDB udb = new UserDB();
//
//
//        }
//     }
//
//    public static void main(String[] args) {
//        UserReader r = new UserReader();
//        ArrayList<StudentAcc> s = r.ReadFile();
//        System.out.println(s);
//        StudentAcc s1 = s.get(0);
//        System.out.println(s1.getSchool());
//    }
//}

package ReadWriteFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Course.Course;
import Users.Student;

public class Reader {

	public Reader() {
	}

	// Student stuff
	public Student studentLogin() {
		String line;
		boolean userBool = false;

		// read input
		System.out.print("Enter student username: ");
		Scanner sc = new Scanner(System.in);
		String userID = sc.next();
		System.out.print("Enter student password: ");
		int passID = sc.next().hashCode();
		String hashedPassword = Integer.toString(passID);

		try {
			BufferedReader reader = new BufferedReader(new FileReader("studentdata.txt"));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				if ((tokens[0].equals(userID)) && tokens[1].equals(hashedPassword)) {
					System.out.println("Login Successful");
					Student s = new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
							Integer.parseInt(tokens[5]));
					return s;
				} else if (tokens[0].equals(userID))
					userBool = true;
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {
			// null exception handle by contain() function
			// for when account don't match
			if (userBool == false) {
				System.out.println("Invalid Username");
			} else {
				System.out.println("Invalid Password");
			}
		}
		return null;
	}

	public String getSchool(String username) {
		String line;

		try {
			BufferedReader reader = new BufferedReader(new FileReader("studentdata.txt"));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				if (tokens[0].equals(username)) {
					reader.close();
					return tokens[3];
				}
			} while (line != null);
			reader.close();
			return "";
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return "";
		} catch (NullPointerException n) {
			System.out.println("Username not found");
			return "";
		}
	}

	public Course getCourseInformation(String searchedCourse) {
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"C:\\Users\\madra\\OneDrive - Nanyang Technological University\\Y2S1\\CZ2002\\CZ2002-Project\\ReadWriteFile\\courseData.txt"));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				if (searchedCourse.equals(tokens[0])) {

					// System.out.println("Index: " + tokens[1]);
					// System.out.println("Vacancy: " + tokens[2]);
					// System.out.println("Waitlist: " + tokens[3]);
					Course course = new Course(tokens[0], tokens[2]);
					reader.close();
					return course;
				}
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		} catch (NullPointerException n) {
			return null;
		}
		return null;
	}

	public ArrayList<Course> getAllCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();

		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"C:\\Users\\madra\\OneDrive - Nanyang Technological University\\Y2S1\\CZ2002\\CZ2002-Project\\ReadWriteFile\\courseData.txt"));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				Course course = new Course(tokens[0], tokens[2]);
				courses.add(course);
				// reader.close()
			} while (line != null);
			return courses;
			// reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		} catch (NullPointerException n) {
			// System.out.println(n.getMessage());
			return courses;
		}
	}

	public int getVacancy(String course, String index) {
		String line;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\bghx9\\eclipse-workspace\\STARS\\course.txt"));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				if (course.equals(tokens[0]) && index.equals(tokens[1])) {
					reader.close();
					return Integer.parseInt(tokens[2]);
				}
			} while (line != null);
			reader.close();
			return 0;
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return 0;
		} catch (NullPointerException n) {
			System.out.println("Invalid Course or Index");
			return 0;
		}
	}

	// Staff stuff
	public boolean staffLogin() {
		String line;
		boolean userBool = false;

		// read input
		System.out.print("Enter staff username: ");
		Scanner sc = new Scanner(System.in);
		String userID = sc.next();
		System.out.print("Enter staff password: ");
		int passID = sc.next().hashCode();
		String hashedPassword = Integer.toString(passID);

		try {
			BufferedReader reader = new BufferedReader(new FileReader("staffdata.txt"));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				if ((tokens[0].equals(userID)) && tokens[1].equals(hashedPassword)) {
					System.out.println("Login Successful");
					return true;
				} else if (tokens[0].equals(userID))
					userBool = true;
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {
			// null exception handle by contain() function
			// for when account don't match
			if (userBool == false) {
				System.out.println("Invalid Username");
			} else {
				System.out.println("Invalid Password");
			}
		}
		return false;
	}

}

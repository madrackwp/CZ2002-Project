package ReadWriteFile;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Writer {

	private String username, hashedPassword, matricNo, school;

	public Writer() {
	}

	public void getAccount(String username, String hashedPassword, String matricNo, String school) {
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.matricNo = matricNo;
		this.school = school;
	}

	public void createStudentAccount() {
		String line;
		String temp = "\n" + username + " " + hashedPassword + " " + matricNo + " " + school;
		boolean found = false;

		try {
			BufferedReader reader = new BufferedReader(new FileReader("studentdata.txt"));
			do {
				line = reader.readLine();
				if (line.contains(username))
					found = true;
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {
			// null exception handle by contain() function
			try {
				if (found == false) {
					FileOutputStream writer = new FileOutputStream("studentdata.txt", true);
					writer.write(temp.getBytes());
					writer.close();
					System.out.println("Account Created");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			if (found != false)
				System.out.println("Existing Account Error");
		}
	}

	public void writeInformation(String course, String index, String school, String vacancy, String waitlist) {
		String line;
		String temp = course + " " + index;
		String in = "\n" + course + " " + index + " " + school + " " + vacancy + " " + waitlist;
		boolean found = false;

		try {
			BufferedReader reader = new BufferedReader(new FileReader("student.txt"));
			do {
				line = reader.readLine();
				System.out.println(line.contains(temp));
				if (line.contains(temp))
					found = true;
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {
			// null exception handle by contain() function
			try {
				if (found == false) {
					FileOutputStream writer = new FileOutputStream("studentdata.txt", true);
					writer.write(in.getBytes());
					writer.close();
					System.out.println("Information Added");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			if (found != false)
				System.out.println("Existing Information Error");
		}
	}
}

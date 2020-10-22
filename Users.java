import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Users {
	
	private String username; 
	private String hashedPassword;
	
	public Users () {}
	
	public void createAccount (String username, String hashedPassword) {
		this.username = username;
		this.hashedPassword = hashedPassword;
	}
	
	public void writeFile() {
		String temp = "\n"+username+" "+hashedPassword;
		try {
			FileOutputStream writer = new FileOutputStream("C:\\Users\\bghx9\\eclipse-workspace\\STARS\\users.txt", true);
			writer.write(temp.getBytes());
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public void logIn (String username, String hashedPassword) {
		String temp = username+" "+hashedPassword;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\bghx9\\eclipse-workspace\\STARS\\users.txt"));
			String line = reader.readLine();
			while (line != null) {
				line = reader.readLine();
				if (line.equals(temp)) {
					System.out.println("Welcome to STARS");
					break;
				}
			}
			reader.close();
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		catch (NullPointerException g) {
			// For when account don't match
			System.out.println("Invalid Account");
		} 
	}
}

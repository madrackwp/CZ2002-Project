package Users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class UserReader extends Reader{
	
	public UserReader() {}

	public ArrayList<Student> ReadFile() {
		ArrayList<Student> tempArr2 = new ArrayList<Student>();
		
		String line;
		String path = "ReadWriteFile\\studentdata.txt";
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				Student s = new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]));
//				ArrayList<String> tempArr1 = new ArrayList<>();
//				tempArr1.add(tokens[0]);
//				tempArr1.add(tokens[1]);
//				tempArr1.add(tokens[2]);
//				tempArr1.add(tokens[3]);
				tempArr2.add(s);
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {}
		return tempArr2;
	}
}

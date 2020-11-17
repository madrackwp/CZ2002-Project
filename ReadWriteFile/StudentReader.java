package ReadWriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Users.StudentAcc;

public class StudentReader extends Reader {
	
	public StudentReader() {}

	public ArrayList<StudentAcc> ReadFile() {
		ArrayList<StudentAcc> studentArr = new ArrayList<StudentAcc>();
		
		String line;
		String path = "ReadWriteFile\\studentdata.txt";
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				StudentAcc s = new StudentAcc(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]));
				studentArr.add(s);
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {}
		return studentArr;
	}
}

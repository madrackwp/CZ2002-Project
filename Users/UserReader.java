package Users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserReader {
    private ArrayList<ArrayList<String>> tempArr2 = new ArrayList<>();
	
	public UserReader() {}
	
	public void ReadFile() {
		
		String line;
		String path = "C:\\Users\\bghx9\\eclipse-workspace\\STARS\\users.txt";
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				ArrayList<String> tempArr1 = new ArrayList<>();
				tempArr1.add(tokens[0]);
				tempArr1.add(tokens[1]);
				tempArr1.add(tokens[2]);
				tempArr1.add(tokens[3]);
				tempArr2.add(tempArr1);
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {}
	}
	
	public ArrayList<ArrayList<String>> getArray() {
		return tempArr2;
	}
}

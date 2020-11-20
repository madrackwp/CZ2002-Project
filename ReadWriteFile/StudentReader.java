package ReadWriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import CourseIndex.CourseIndex;
import CourseIndex.ModType;
import DatabaseManager.CourseIndexDBManager;
import Users.StudentAcc;

public class StudentReader extends TextFileReader {

	public StudentReader() {
	}

	public ArrayList<StudentAcc> ReadFile(Object courseIndexDBManager) {
		ArrayList<StudentAcc> studentArr = new ArrayList<StudentAcc>();

		String line;
		String path = "ReadWriteFile\\studentdata.txt";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));

			do {
				line = reader.readLine();
				String[] tokens = line.split(" ");
				ArrayList<CourseIndex> registeredCourseIndexes = new ArrayList<CourseIndex>();
				HashMap<String, ModType> modHash = new HashMap<String, ModType>();
				for (int i = 7; i < tokens.length; i++) {
					String[] indexInfo = tokens[i].split(",");
					// System.out.println(indexInfo[0] + indexInfo[1]);
					try {
						CourseIndex tempCourseIndex = ((CourseIndexDBManager) courseIndexDBManager)
								.getCourseIndexInfo(indexInfo[0], Integer.parseInt(indexInfo[1]));
						registeredCourseIndexes.add(tempCourseIndex);
						modHash.put(indexInfo[0], ModType.valueOf(indexInfo[2]));
					} catch (NullPointerException e) {
						System.out.println("The course does not exist");
						break;
					}
				}
				StudentAcc s = new StudentAcc(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
						Integer.parseInt(tokens[5]), (tokens[6]), registeredCourseIndexes, modHash);
				studentArr.add(s);
			} while (line != null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NullPointerException n) {
		}
		return studentArr;
	}

	@Override
	public Object ReadFile() {
		// TODO Auto-generated method stub
		return null;
	}

}

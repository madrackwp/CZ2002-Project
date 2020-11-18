package ReadWriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import CourseIndex.CourseIndex;
import DatabaseManager.CourseIndexDBManager;
import Users.StudentAcc;

public class StudentReader extends Reader {

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
				for (int i = 7; i < tokens.length; i++) {
					String[] indexInfo = tokens[i].split(",");
					// System.out.println(indexInfo[0] + indexInfo[1]);
					try {
						CourseIndex tempCourseIndex = ((CourseIndexDBManager) courseIndexDBManager)
								.getCourseIndexInfo(indexInfo[0], Integer.parseInt(indexInfo[1]));
						registeredCourseIndexes.add(tempCourseIndex);
					} catch (NullPointerException e) {
						System.out.println("The course does not exist");
						break;
					}
				}
				StudentAcc s = new StudentAcc(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
						Integer.parseInt(tokens[5]), (tokens[6]), registeredCourseIndexes);
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

}

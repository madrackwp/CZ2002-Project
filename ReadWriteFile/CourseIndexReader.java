package ReadWriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import CourseIndex.CourseIndex;

public class CourseIndexReader extends Reader {

    public ArrayList<CourseIndex> ReadFile() {
        ArrayList<String> matricArr = new ArrayList<String>();
        ArrayList<CourseIndex> courseIndexes = new ArrayList<CourseIndex>();

        String line;
        String path = "ReadWriteFile\\courseData.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            do {
                line = reader.readLine();
                String[] tokens = line.split(" ");
                for (int i = 7; i < tokens.length; i++) {
                    matricArr.add(tokens[i]);
                }
                CourseIndex c = new CourseIndex(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[3]), matricArr, tokens[5], tokens[6], tokens[0], tokens[2]);
                courseIndexes.add(c);
            } while (line != null);
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (NullPointerException n) {}
        return courseIndexes;
   }
}

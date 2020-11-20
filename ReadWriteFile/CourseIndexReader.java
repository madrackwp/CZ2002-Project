package ReadWriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import CourseIndex.CourseIndex;
import CourseIndex.*;

public class CourseIndexReader extends TextFileReader {

    public ArrayList<CourseIndex> ReadFile() {

        ArrayList<CourseIndex> courseIndexes = new ArrayList<CourseIndex>();

        String line;
        String path = "ReadWriteFile\\courseData.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            do {
                line = reader.readLine();
                String[] tokens = line.split(" ");
                String[] matNo = tokens[5].split(",");
                ArrayList<String> matricArr = new ArrayList<>();
                for (int i = 0; i < matNo.length; i++) {
                    matricArr.add(matNo[i]);
                }
                ArrayList<Lesson> lessonArrayList = new ArrayList<>();
                for (int i = 6; i < tokens.length; i++) {
                    String[] lessonDetails = tokens[i].split(",");
                    Lesson l = new Lesson(lessonDetails[0], lessonDetails[1], Type.valueOf(lessonDetails[2]),
                            Day.valueOf(lessonDetails[3]));
                    lessonArrayList.add(l);
                }
                CourseIndex c = new CourseIndex(tokens[0], Integer.parseInt(tokens[1]), tokens[2],
                        ModType.valueOf(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), matricArr,
                        lessonArrayList);
                courseIndexes.add(c);
            } while (line != null);
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (NullPointerException n) {
        }
        return courseIndexes;
    }

}

package ReadWriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import CourseIndex.CourseIndex;
import CourseIndex.*;

/**
 * This will read the courseData.txt file and return an arraylist of CourseIndex
 * items
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-25-11
 */
public class CourseIndexReader implements TextFileReader {

    /**
     * This creates the CourseIndexReader object
     */
    public CourseIndexReader() {
    }

    /**
     * This reads the file specified in the path variable and returns an arraylist
     * of CourseIndex items
     */
    public ArrayList<CourseIndex> ReadFile() {

        ArrayList<CourseIndex> courseIndexes = new ArrayList<CourseIndex>();

        String line;
        String path = "ReadWriteFile\\courseData.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            do {
                line = reader.readLine();
                String[] tokens = line.split(" ");

                ArrayList<ModType> allowedModTypes = new ArrayList<ModType>();
                String[] modTypes = tokens[4].split(",");
                for (String modType : modTypes) {
                    allowedModTypes.add(ModType.valueOf(modType));
                }

                String[] waitListMatric = tokens[5].split(",");
                ArrayList<String> waitList = new ArrayList<>();
                if (!waitListMatric[0].equals("null")) {
                    for (String matricNo : waitListMatric) {
                        waitList.add(matricNo);
                    }
                } else {
                    waitList.add("null");
                }
                IndexWaitList indexWaitList = new IndexWaitList(waitList);

                String[] matNo = tokens[7].split(",");
                ArrayList<String> matricArr = new ArrayList<>();
                if (!matNo[0].equals("null")) {
                    for (int i = 0; i < matNo.length; i++) {
                        matricArr.add(matNo[i]);
                    }
                } else {
                    matricArr.add("null");
                }

                ArrayList<Lesson> lessonArrayList = new ArrayList<>();
                for (int i = 8; i < tokens.length; i++) {
                    String[] lessonDetails = tokens[i].split(",");
                    Lesson l = new Lesson(lessonDetails[0], lessonDetails[1], Type.valueOf(lessonDetails[2]),
                            Day.valueOf(lessonDetails[3]));
                    lessonArrayList.add(l);
                }

                CourseIndex c = new CourseIndex(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
                        tokens[3], allowedModTypes, indexWaitList, Integer.parseInt(tokens[6]), matricArr,
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

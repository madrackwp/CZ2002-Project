package ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import CourseIndex.CourseIndex;
import DatabaseManager.*;

/**
 * This class implements the TextFileWriter interface in order to write the data
 * from the CourseIndex database manager into the data .txt file
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */

public class CourseIndexWriter implements TextFileWriter {

    /**
     * Creates the CourseIndexWriter class
     */
    public CourseIndexWriter() {
    }

    /**
     * Method used the write the data in the CourseIndexDBManager into the data .txt
     * file
     */
    public void writeFile(Object courseIndexDBManager) {
        ArrayList<CourseIndex> courseIndexes = ((CourseIndexDBManager) courseIndexDBManager).getCourseIndexes();

        String path = "ReadWriteFile\\courseData.txt";

        try {
            FileOutputStream writer = new FileOutputStream(path, false);

            for (int i = 0; i < courseIndexes.size(); i++) {
                writer.write(courseIndexes.get(i).toWrite().getBytes());
                writer.write("\n".getBytes());
            }
            writer.close();
            // System.out.println("BRYAYFASYDFDHLJFALSDKJFASLFJSD");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

package ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import CourseIndex.CourseIndex;
import LocalDatabase.CourseIndexDB;
import DatabaseManager.*;

public class CourseIndexWriter extends TextFileWriter {

    public CourseIndexWriter() {
    }

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
            System.out.println("BRYAYFASYDFDHLJFALSDKJFASLFJSD");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // public void WriteDirect() {

    // }

    // public static void main(String[] args) {
    // CourseIndexReader cr1 = new CourseIndexReader();
    // ArrayList<CourseIndex> courseIndexes = cr1.ReadFile();
    // CourseIndexDB db = new CourseIndexDB(courseIndexes);
    // CourseIndexWriter writer = new CourseIndexWriter();
    // writer.writeFile(db);
    // }
}

package ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import CourseIndex.CourseIndex;
import LocalDatabase.CourseIndexDB;

public class CourseIndexWriter extends Writer {

    public CourseIndexWriter() {
    }

    public void writeFile(Object courseIndexDB) {
        ArrayList<CourseIndex> courseIndexes = ((CourseIndexDB) courseIndexDB).getCourseIndexes();

        String path = "ReadWriteFile\\courseData.txt";

        try {
            FileOutputStream writer = new FileOutputStream(path, false);

            for (int i = 0; i < courseIndexes.size(); i++) {
                System.out.println(courseIndexes.get(i).toString());
                writer.write(courseIndexes.get(i).toString().getBytes());
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

    public static void main(String[] args) {
        CourseIndexReader cr1 = new CourseIndexReader();
        ArrayList<CourseIndex> courseIndexes = cr1.ReadFile();
        CourseIndexDB db = new CourseIndexDB(courseIndexes);
        CourseIndexWriter writer = new CourseIndexWriter();
        writer.writeFile(db);
    }
}

package ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import CourseIndex.CourseIndex;

public class CourseIndexWriter extends Writer{
    
    public void WriteFile() {
        CourseIndexReader reader = new CourseIndexReader();
        String path = "ReadWriteFile\\courseData.txt";

        try {
            reader.ReadFile();
            FileOutputStream writer = new FileOutputStream(path, false);

            for (int i = 0; i < reader.ReadFile().size(); i++){
                writer.write(reader.ReadFile().get(i).toString().getBytes());
                writer.write("\n".getBytes());
            }
            writer.close();

        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void WriteDirect() {

    }
}

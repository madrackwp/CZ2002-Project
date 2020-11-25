package ReadWriteFile;

import java.io.FileOutputStream;
import java.util.ArrayList;

import Users.StudentAcc;
import DatabaseManager.*;

/**
 * This class implements the TextFileWriter interface in order to write the data
 * from the Student database manager into the data .txt file
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class StudentWriter implements TextFileWriter {

    /**
     * Creates the StudentWriter class
     */
    public StudentWriter() {
    }

    /**
     * Method used the write the data in the StudentDBManager object into the data
     * .txt file
     */
    @Override
    public void writeFile(Object studentDBManager) {
        String path = "ReadWriteFile\\studentdata.txt";
        ArrayList<StudentAcc> studentAccs = ((StudentDBManager) studentDBManager).getStudentAccs();

        try {
            FileOutputStream writer = new FileOutputStream(path, false);
            for (StudentAcc studentAcc : studentAccs) {
                writer.write(studentAcc.toWrite().getBytes());
                writer.write("\n".getBytes());
            }
            writer.close();

        } catch (Exception e) {

        }
    }
}

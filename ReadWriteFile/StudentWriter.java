package ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import CourseIndex.CourseIndex;
import Users.StudentAcc;
import DatabaseManager.*;
import LocalDatabase.CourseIndexDB;
import LocalDatabase.StudentDB;

public class StudentWriter extends Writer {

    StudentReader reader = new StudentReader();

    public StudentWriter() {
    }

    // public void writeFile() {
    // String path = "ReadWriteFile\\studentdata.txt";

    // try {
    // reader.ReadFile();
    // FileOutputStream writer = new FileOutputStream(path, false);
    // for (int i = 0; i < reader.ReadFile().size(); i++) {
    // writer.write(reader.ReadFile().get(i).toString().getBytes());
    // writer.write(" ".getBytes());
    // }
    // writer.write("\n".getBytes());
    // writer.close();
    // } catch (IOException e) {
    // System.out.println("An error occurred.");
    // e.printStackTrace();
    // }
    // }

    public void WriteDirect() {

    }

    @Override
    void writeFile(Object studentDBManager) {
        String path = "ReadWriteFile\\studentdata.txt";
        // TODO Auto-generated method stub
        ArrayList<StudentAcc> studentAccs = ((StudentDBManager) studentDBManager).getStudentAccs();

        try {
            FileOutputStream writer = new FileOutputStream(path, false);
            for (StudentAcc studentAcc : studentAccs) {
                writer.write(studentAcc.toWrite().getBytes());
                writer.write("\n".getBytes());
            }
            writer.close();
            System.out.println("Writing done");
        } catch (Exception e) {
            System.out.println("An error occured when writing students to txt file");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CourseIndexReader CIR = new CourseIndexReader();
        ArrayList<CourseIndex> temp = CIR.ReadFile();
        CourseIndexDB indexDB = new CourseIndexDB(temp);
        CourseIndexDBManager indexDBManager = new CourseIndexDBManager(indexDB);

        StudentReader ur = new StudentReader();
        ArrayList<StudentAcc> studentList = ur.ReadFile(indexDBManager);
        StudentDB studentDB = new StudentDB(studentList);

        // ArrayList<CourseIndex> testing = new ArrayList<CourseIndex>();
        // StudentAcc toBeInserted = new StudentAcc("INSERTED", 123123123, "something",
        // "CEE", "U1921799J", 2, 01/01/2020, )

        StudentDBManager studentDBManager = new StudentDBManager(studentDB);

        StudentWriter studentWriter = new StudentWriter();
        studentWriter.writeFile(studentDBManager);

    }
}

//package ReadWriteFile;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import Users.UserAcc;
//import Course.*;
//
//public class CourseIndexReader extends TextFileReader {
//
//    @Override
//    public ArrayList<CourseIndex> ReadFile() {
//        ArrayList<CourseIndex> courseIndexes = new ArrayList<CourseIndex>();
//
//        String line;
//        try {
//            BufferedReader reader = new BufferedReader(new TextFileReader("ReadWriteFile\\courseData.txt"));
//            do {
//                line = reader.readLine();
//                if (line == null)
//                    return courseIndexes;
//                String[] tokens = line.split(" ");
//                CourseIndex courseIndex = new CourseIndex(tokens[0], Integer.parseInt(tokens[1]), tokens[2],
//                        Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
//                courseIndexes.add(courseIndex);
//            } while (line != null);
//            return courseIndexes;
//            // reader.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//            return null;
//        } catch (NullPointerException n) {
//            System.out.println("An error occurred.");
//            n.printStackTrace();
//            return null;
//        }
//    }
//}

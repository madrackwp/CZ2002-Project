package ReadWriteFile;

import Users.StaffAcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This will read the staffdata.txt file and return an arraylist of StaffAcc
 * objects
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public class StaffReader implements TextFileReader {

    /**
     * Creates the StaffReader class
     */
    public StaffReader() {
    }

    /**
     * This reads the file specified in the path variable and returns an arraylist
     * of StaffAcc objects
     */
    public ArrayList<StaffAcc> ReadFile() {
        ArrayList<StaffAcc> staffArr = new ArrayList<>();
        String line;
        String path = "ReadWriteFile\\staffdata.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            do {
                line = reader.readLine();
                String[] tokens = line.split(" ");
                StaffAcc s = new StaffAcc(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                staffArr.add(s);
            } while (line != null);
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (NullPointerException n) {
        }
        return staffArr;
    }
}

package ReadWriteFile;

import Users.StaffAcc;
import Users.StudentAcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StaffReader extends Reader {
    public StaffReader() {

    }

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

    @Override
    Object ReadFile(Object object) {
        // TODO Auto-generated method stub
        return null;
    }
}

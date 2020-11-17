package ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;

public class StudentWriter extends Writer {
    
    StudentReader reader = new StudentReader();
    
    public StudentWriter() {}

    public void WriteFile() {
		String path = "ReadWriteFile\\studentdata.txt";
        
        try {
			reader.ReadFile();
			FileOutputStream writer = new FileOutputStream(path, false);
            for (int i = 0; i < reader.ReadFile().size(); i++) {
		        writer.write(reader.ReadFile().get(i).toString().getBytes());
		        writer.write(" ".getBytes());
		        }
	        	writer.write("\n".getBytes());
	        writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
    }

    public void WriteDirect() {
        
    }
}

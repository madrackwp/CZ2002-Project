package ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;

public class UserWriter extends Writer {
    
    UserReader reader = new UserReader();
    
    public UserWriter() {}

    public void WriteFile() {
        
        try {
			reader.ReadFile();
			FileOutputStream writer = new FileOutputStream("C:\\Users\\bghx9\\eclipse-workspace\\STARS\\users.txt", false);
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

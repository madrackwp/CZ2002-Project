package ReadWriteFile;

/**
 * An interface that will contain the necessary methods to read text files
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public interface TextFileReader {

	/**
	 * ReadFile method will read the text file with data
	 * 
	 * @return This will return an ArrayList of objects inside the respective data
	 *         .txt files
	 */
	public abstract Object ReadFile();

}
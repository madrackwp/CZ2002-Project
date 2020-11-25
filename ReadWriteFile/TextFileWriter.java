package ReadWriteFile;

/**
 * An interface that will contain the necessary methods to write to the data
 * .txt files
 * 
 * @author Goh Wei Pin
 * @version 1.0
 * @since 2020-11-25
 */
public interface TextFileWriter {
	/**
	 * This is the method used to write to the data .txt files
	 * 
	 * @param object DatabaseManager object that will contain the latest information
	 *               that will be written to the data .txt files
	 */
	abstract void writeFile(Object object);
}

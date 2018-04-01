/**
*Name and ID: Felix Morin, 40063253
*Assignment 3
*BibCreator2 Class
*Due Date: March 19th 2018
*
*The goal of this program is to open files containing information about articles and to translate these informations into references of three different types
*(acm, ieee, nj) each into a respective file
**/

/**
 * This is the FileInvalidException class
 */

//TASK 2

public class FileInvalidException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public FileInvalidException()
	{
		super("Input file cannot be parsed due to missing information. Consequently, the file will be deleted.");
	}
	
	/**
	 * Constructor with a string parameter
	 */
	public FileInvalidException(String problem)
	{
		super("Input file cannot br parsed due to missing information ("+problem+"). Consequently, the file will be deleted.");
	}
	
	/**
	 * getter method for message
	 * @returns message
	 */
	public String getMessage()
	{
		return super.getMessage();
	}
	
}

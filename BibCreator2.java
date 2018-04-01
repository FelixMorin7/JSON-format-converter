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
 * Necessary packages
 **/
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
import java.nio.file.*;

/**
 * This is the BibCreator2 class
 */
public class BibCreator2 {

/** Open, close, and ask the user for the file to be displayed */
	public static void main(String[] args) {
		
		/**Number of files to be opened*/
		int fileNum = 10;
		
		/** Create a Scanner array to read from the files and 3 PrintWriter to create and write to the reference files */
		Scanner original[] = new Scanner[fileNum];
		PrintWriter acm[] = new PrintWriter[fileNum];
		PrintWriter ieee[] = new PrintWriter[fileNum];
		PrintWriter nj[] = new PrintWriter[fileNum];
		
		/**Declare the files to read from and the files to be created*/
		String originalFiles[] = {"Latex1.bib", "Latex2.bib", "Latex3.bib", "Latex4.bib", "Latex5.bib", "Latex6.bib", "Latex7.bib", "Latex8.bib", "Latex9.bib", "Latex10.bib"};
		String IEEEFiles[] = {"IEEE1.json", "IEEE2.json", "IEEE3.json", "IEEE4.json", "IEEE5.json", "IEEE6.json", "IEEE7.json", "IEEE8.json", "IEEE9.json", "IEEE10.json"};
		String ACMFiles[] = {"ACM1.json", "ACM2.json", "ACM3.json", "ACM4.json", "ACM5.json", "ACM6.json", "ACM7.json", "ACM8.json", "ACM9.json", "ACM10.json"};
		String NJFiles[] ={"NJ1.json", "NJ2.json", "NJ3.json", "NJ4.json", "NJ5.json", "NJ6.json", "NJ7.json", "NJ8.json", "NJ9.json", "NJ10.json"};
		
	
		//TASK 3
		
		/**Declare variable to be used in the loop and in the catch method*/
		int i = 0;
		
		/**Try to open/create all the files*/
		try {
			
			for(;i<fileNum; i++)
			{
				original[i] = new Scanner(new FileInputStream(originalFiles[i]));
			}
			
		}
		
		/**If a file cannot be opened, close all the input files that were created, then end the program*/ 
		catch(FileNotFoundException e)
		{
			System.out.print("Could not open input file #" + (i+1) + " for reading. Make sure it exists. All files opened for writing will be deleted and then"
					+ " the program will terminate.");
			
			for(;i>=0; i--)
			{
				original[i].close();
			}
			
			System.exit(0);
		}
		
		
		//TASK 4
		
		int j = 0;
		
		try {
			
			for(;j<fileNum; j++)
			{
				ieee[j] = new PrintWriter(new FileOutputStream(IEEEFiles[j]));
				acm[j] = new PrintWriter(new FileOutputStream(ACMFiles[j]));
				nj[j] = new PrintWriter(new FileOutputStream(NJFiles[j]));
			}
			
		}
		
		/**If a file cannot be opened, delete all the output files that were created, then end the program*/ 
		catch(FileNotFoundException e)
		{
			System.out.print("File #" + (j+1) + " cannot be created. Make sure it exists. All files opened for writing will be deleted and the"
					+ "program will end.");
			
			File a;
			File b;
			File c;
			
			for(;j>=0; j--)  
			{
				ieee[j].close();
				acm[j].close();
				nj[j].close();
				
				a = new File(IEEEFiles[j]);
				a.delete();
				b = new File(ACMFiles[j]);
				b.delete();
				c = new File(NJFiles[j]);
				c.delete();
			}
			
		}
		
	
	/** Declare variable to be used in the loop and in the catch method */
	int k = 0;
		
	for(;k<fileNum; k++)
	{
		processFilesForValidation(original[k], ieee[k], acm[k], nj[k], originalFiles[k], IEEEFiles[k], ACMFiles[k], NJFiles[k]);
	}
		
	/**Close all files*/
	for(int i1 =0;i1<fileNum; i1++)
	{
		original[i1].close();
		ieee[i1].close();
		acm[i1].close();
		nj[i1].close();
	}
	
	/**Create BufferedReader to read from a file*/
	BufferedReader display = null;
	/**Create Scanner to read from user*/
	Scanner key = new Scanner(System.in);
	/**Variables to be used during the reading from file*/
	String request = "";
	int attempt = 0;
	boolean valid = false;
	
	/** Try to read a file name from user and give 2 attempt to enter a valid name */
	while(!valid)
	{
	try
	{
		/**Read from user*/
		System.out.println("Enter the name of the file you want to display: ");
		request = key.next();
		
		/**Try to create file*/
		display = new BufferedReader(new FileReader(request));
		valid = true;
	}
	/**Let the user retry if attempt < 2, otherwise terminate the program*/
	catch(FileNotFoundException e)
	{
		System.out.println("The file you entered does not exist.");
		attempt++;
		if(attempt>=2)
		{
			System.out.println("Second attempt expired. The program will terminate.");
			System.exit(0);
		}
		System.out.println("You have one more chance to try to enter a valid name:");
	}
	}
	
	System.out.println("\nHere is the content of the file " + request + ": ");
	
	int x;
	
	/**Read the file using BufferedReader and output the result to the user*/
	
	try {
	
	x = display.read();
	while(x != -1) 
	{
		System.out.print((char)x);		
		x = display.read();		
	}
	key.close();
	display.close();
	}
	/**catch if any weird input has occurred and terminate program*/
	catch(IOException e)
	{
		System.out.print("An error has occured while reading the file. The program will terminate");
		System.exit(0);
	}
	
	}

	
	/** method that reads the input files and stores the information into separate variables */
	public static void processFilesForValidation(Scanner a, PrintWriter b, PrintWriter c, PrintWriter d, String original, String ieee, String acm, String nj)
	{
		/**Variables to be used while reading the input file*/
		String s = "";
		char boundary = '-';
		String t = "";
		
		/** Variables used to store respective information about the file  */
		String author = "";
		String journal = "";
		String title = "";
		String year = "";
		String volume = "";
		String number = "";
		String pages = "";
		String keywords = "";
		String doi = "";
		String ISSN = "";
		String month = "";
		
		/**Try to process the files for validation and create references*/
		try {
		
		/** Do until the end of the file */
		while(a.hasNext())
		{
		
		s = a.next();
		
		/** Repeat for each article in the file */
		if(s.equals("@ARTICLE{"))
		{
			/** Repeat until the end of the article */
			while(boundary!='}')
			{
			
			s = a.nextLine();
			
			if(s.length()!=0)
				boundary = s.charAt(0);
			
			if(s.length()!=0)
			{
			StringTokenizer e = new StringTokenizer(s, "={");
			t = e.nextToken();
			}
			
			/** Depending on the information name at the beginning of each line, store the following information into the respective variable
			 * If a field is empty, throw an FileInvalidException with the name of the empty information */
			switch(t)
			{
			case "author":
				if(s.length()<11) //test
					continue;
				s = s.substring(8, s.length()-3);
				author = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "title":
				if(s.length()<10) //test
					continue;
				s = s.substring(7, s.length()-3);
				title = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "journal":
				if(s.length()<12) //test
					continue;
				s = s.substring(9, s.length()-3);
				journal = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "year":
				if(s.length()<9) //test
					continue;
				s = s.substring(6, s.length()-3);
				year = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "month":
				if(s.length()<9) //test
					continue;
				s = s.substring(7, s.length()-2);
				month = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "pages":
				if(s.length()<10) //test
					continue;
				s = s.substring(7, s.length()-3);
				pages = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "volume":
				if(s.length()<11) //test
					continue;
				s = s.substring(8, s.length()-3);
				volume = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "keywords":
				if(s.length()<13) //test
					continue;
				s = s.substring(10, s.length()-3);
				keywords = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "number":
				if(s.length()<11) //test
					continue;
				s = s.substring(8, s.length()-3);
				number = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "doi":
				if(s.length()<8) //test
					continue;
				s = s.substring(5, s.length()-3);
				doi = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			case "ISSN":
				if(s.length()<9) //test
					continue;
				s = s.substring(6, s.length()-3);
				ISSN = s;
				if(s.equals(""))
					throw new FileInvalidException(t);
				break;
			}	
			
			}
		
			/** Send the information to 3 different methods formating the information into the different reference formats */
			b.println("\n" + ieeeFormat(author, journal, title, year, volume, number, pages, keywords, doi, ISSN, month));
			c.println("\n" + acmFormat(author, journal, title, year, volume, number, pages, keywords, doi, ISSN, month));
			d.println("\n" + njFormat(author, journal, title, year, volume, number, pages, keywords, doi, ISSN, month));
		
			/** Empty all the variable to be ready for the next article in the file */
			
			author = "";
			journal = "";
			title = "";
			year = "";
			volume = "";
			number = "";
			pages = "";
			keywords = "";
			doi = "";
			ISSN = "";
			month = "";
			
			boundary = '-';
		}
		
		}
		
		}
		/**If a file has an empty field, inform the user and delete the respective files?*/
		catch(FileInvalidException e)
		{
			System.out.println("The file " + original + " is invalid.");
			System.out.println(e.getMessage() + "\n");
			
			b.close();
			c.close();
			d.close();
			
			File x = new File(ieee);
			x.delete();
			File y = new File(acm);
			y.delete();
			File z = new File(nj);
			z.delete();
		}
	}
	
	/** Method that formats the input file information according to the ieee model */
	public static String ieeeFormat(String author, String journal, String title, String year, String volume, String number, String pages, String keywords, String doi, String ISSN, String month)
	{
		String format = "";
		
		String[] a = author.split(" and "); 
		
		author = "";
		
		for(int i=0; i<a.length; i++)
		{
			author += a[i] + ", ";
		}
		
		author = author.substring(0, author.length()-2);
		
		format = author + ". \"" + title + "\", " + journal + ", vol. " + volume + ", no. " + number + ", p. " + pages + ", " + month + " " + year + ".";  
		
		return format;
	}
	
	/** Method that formats the input file information according to the acm model */
	public static String acmFormat(String author, String journal, String title, String year, String volume, String number, String pages, String keywords, String doi, String ISSN, String month)
	{
		String format = "";
		
		String[] a = author.split(" and "); 
		
		author = "";
		
		author += a[0];
		
		format = author + " et al. " + year + ". " + title + ". " + journal + ". " + volume + ", " + number + " (" + year + "), " + pages + ". DOI:" + doi + "."; 
		
		return format;
	}
	
	/** Method that formats the input file information according to the nj model */
	public static String njFormat(String author, String journal, String title, String year, String volume, String number, String pages, String keywords, String doi, String ISSN, String month)
	{
		String format = "";
		
		String[] a = author.split(" and "); 
		
		author = "";
		
		for(int i=0; i<a.length; i++)
		{
			author += a[i] + " & ";
		}
		
		author = author.substring(0, author.length()-3);
		
		format = author + ". " + title + ". " + journal + ". " + volume + ", " + pages + "(" + year + ")."; 
		
		return format;
	}
}

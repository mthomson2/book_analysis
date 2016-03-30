import java.io.*;
import java.util.*;

/**
 *  Some practice with file I/O.
 */
class IOPractice {
	/**
	 *  An example main method to test functionality.
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		if(args.length != 2) {
			System.out.println("Two command line argument required: (1) input file name (2) output file name");
		}
		else {
			String result1 = IOPractice.readFromFile(args[0]);
			IOPractice.writeToFile(args[1], result1);
			String result2 = IOPractice.readFromFile(args[1]);
			if(result1.equals(result2)) {
				System.out.println("Win!");
			}
			else {
				System.out.println("Not working yet :(");
			}
			
		}
	}


	public static String readFromFile(String firstFile){
		//creates scanner object and makes sure it exists
		try {
			Scanner sc = new Scanner(new File(firstFile));
			//wholeConvo will be the returned String
			String wholeConvo = "";
			//Will print each token to wholeConvo thats in the firstFile.
			while (sc.hasNextLine() != false) {
				wholeConvo += sc.nextLine();
			}
			sc.close();
			//System.out.println(wholeConvo);
			return wholeConvo;
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found catch.  " + firstFile);
			return null;
		}
	}

	public static boolean writeToFile(String secondFile, String wholeConvoParam)  {
		//creates PrintWriter object to print to the secondFile. 
		try {
			PrintWriter pw = new PrintWriter(secondFile);
			//Uses the parameter to print the wholeConvo from the readFromFile method
			pw.println(wholeConvoParam);
			//pw.println("hello");
			pw.close();
			return true;
		}
		catch (FileNotFoundException e) {
			System.out.println("In the catch");
			return false;
		}
		
	}
}


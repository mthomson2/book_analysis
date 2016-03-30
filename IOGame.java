import java.io.*;
import java.util.*;

/**
 *  This class supports a Mad-lib like interactive game.
 */
public class IOGame {
	
	//Makes a fileName instance variable to use in the whole class
	private String fileName;
	/**
	 *  Constructs a new game from a template contained in a file.
	 *  
	 *  @param fileName the name of the file containing the tempalte
	 *  @throws GameFileNotFoundException if the file in question can not be located
	 */
	
	//Constructor: Initializes fileName and checks to see if the file is found. 
	public IOGame(String fileName) throws GameFileNotFoundException {
		/* YOUR CODE HERE */
		this.fileName = fileName;
		try {
			readInTemplate(fileName);
		}
		
		//this is how I catch the exceptions throughout this IOGame class.
		catch (FileNotFoundException e){
			throw new GameFileNotFoundException();
		}
	}
	
	/**
	 *  Reads in a new template from a file.
	 *  
	 *  @param fileName the name of the file containing the tempalte
	 * @throws FileNotFoundException 
	 */
	public void readInTemplate(String fileName) throws GameFileNotFoundException {
		//Initializes fileName again so fileName is consistant with the methods using it.
		this.fileName = fileName;
		try {
			Scanner sc = new Scanner(new File(fileName));	
			sc.close();
		//System.out.println(templateFile);
		} catch (FileNotFoundException e) {
			throw new GameFileNotFoundException();
		}
	}
	
	//Loops through the text file seeking the characters '<' and '>'. 
	public int getNumPlaceHolders() throws GameFileNotFoundException {
		try {
			Scanner sc = new Scanner(new File(fileName));
			int placeHolders = 0;
			while (sc.hasNext() != false) {
				String token = sc.next();
				//uses charAt method to find beginning and ending of a token. A place holder token must have 
				//its first character a '<' and its last character a '>'.
				if (token.charAt(0) == '<' & token.charAt(token.length()-1) == '>') {
					placeHolders++;
				}
			}
			sc.close();
			return placeHolders;
		} catch (FileNotFoundException e) {
			throw new GameFileNotFoundException();
		}
		
	}
	
	/**
	 *  Prompts the user for replacements to the template. This method only
	 *  prints the prompt and does not actually get the user input.
	 * @throws FileNotFoundException 
	 */
	
	//Prompts the user for input in a numbered list. 
	public void promptUser()  {
		try {
			System.out.println("Please enter " + getNumPlaceHolders() + " replacements:");
			int numHolders = getNumPlaceHolders();
			// Creates array replacers that will hold the place holders. Replacers will have the length of the number
			// of place holders in the file (determined by getNumPlaceHolders()).
			String[] replacers = new String[numHolders];
			Scanner sc = new Scanner(new File(fileName));
			int n = 0;
			while (sc.hasNext() != false) {
				String token = sc.next();
				//If it is a place holder, it will be put in the array at the given index.
				if (token.charAt(0) == '<' & token.charAt(token.length()-1) == '>') {
					replacers[n] = token;
					//Checks to make sure the method doesn't try to create more spaces in the array.
					if (n < (numHolders - 1)) {
						n++;
					}
				}
			}
			//prints the actualy prompt 
			for (int i = 0; i<numHolders; i++) {
				System.out.print(i + 1 + ". ");
				System.out.println(replacers[i].substring(1, replacers[i].length() - 1).replace('-', ' '));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			

		}
	}
	
	/**
	 *  Gets the user input for replacements to the template
	 *  
	 *  @return an array containing each replacement to the template
	 * @throws FileNotFoundException 
	 */
	
	//Gets user input by putting the input acquired by a Scanner object into an array.
	public String[] getUserInput() throws FileNotFoundException {
		/* YOUR CODE HERE */
		String[] arrayInput = new String[getNumPlaceHolders()];
		int inputAllowed = 0;
		Scanner sc = new Scanner(System.in);
		//Goes through the user's input and puts the input into the array at the given index.
		while (inputAllowed < getNumPlaceHolders()) {
			String userInput = sc.next();
			arrayInput[inputAllowed] = userInput;
			inputAllowed++;
		}
		sc.close();
		return arrayInput;
	}
	
	/**
	 *  Gets placeholder replacements from a file
	 *  
	 *  @param fileName the name of the file containing the replacements
	 *  @return an array containing each replacement to the template
	 * @throws FileNotFoundException 
	 */
	
	// Uses Scanner and File objects to open and read the selected file. The contents read in will be put into an array
	public String[] getFileInput(String fileName) {
		/* YOUR CODE HERE */
		Scanner sc = null;
		String[] inputList = null;
		try{
			sc = new Scanner(new File(fileName));
			inputList = new String[getNumPlaceHolders()];
			int counter = 0;
			//Puts the read-in lines into the array at the given index.
			while(sc.hasNextLine() != false) {
				inputList[counter] = sc.nextLine();
				counter++;
			}
		} catch (FileNotFoundException e){
		}
		
		sc.close();
		return inputList;
	}
	
	/**
	 *  Replaces placeholders in the template. If there are not enough
	 *  replacements, then the remaining tokens are displayed without
	 *  being replaced. If there are too many tokens, then the extra
	 *  replacements are ignored.
	 *  
	 *  @param replacements an array of replacements to the template
	 *  @return the template with placeholders replaced
	 * @throws FileNotFoundException 
	 */
	
	public String replace(String[] replacements) throws FileNotFoundException {
		/* YOUR CODE HERE */
		Scanner sc = new Scanner(new File(fileName));
		//wholeFile is instantiated here. Will be returned at end of method. 
		String wholeFile = "";
		int counter = 0;
		while (sc.hasNext() != false) {
			String token = sc.next();
			//Checks to see if a token is a placeholder. 
			if (token.charAt(0) == '<' & token.charAt(token.length()-1) == '>') {
				//if token is a placeholder, it will be replaced with the String from the indexed array. 
				token = token.replace(token, replacements[counter]);
				counter++;
				wholeFile += token + " ";
			//print every other character as well. Don't just print the place holder tokens. 
			} else {
				wholeFile += token + " ";
			}
		}
		sc.close();
		return wholeFile;
	}
	
	/**
	 *  <p>Replaces placeholders in the template. If there are not enough
	 *  replacements, then the remaining tokens are displayed without
	 *  being replaced. If there are too many tokens, then the extra
	 *  replacements are ignored.</p>
	 *  
	 *  <p>After replacement, writes result to a file.</p>
	 *  
	 *  @param replacements an array of replacements to the template
	 *  @param fileName the name of the file to write to
	 *  @throws OutputFileNotFoundException if the file in question can not be located
	 */
	public void replaceAndWrite(String[] replacements, String fileName) throws OutputFileNotFoundException {
		//opens up a PrintWriter object to write to the given file
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e){
			throw new OutputFileNotFoundException();
		}
		// s will be the printed statement with everything in the file. 
		String s = "";
		
		//calls replace with the replacements parameter. Will return the String wholeFile with everything replaces
		try {
			s = replace(replacements);
		} catch (FileNotFoundException e) {	
		}


		//System.out.println("THIS IS S " + s);
		pw.print(s);
		pw.close();
	}

	/**
	 *  An example main method that runs a small game.
	 *  
	 *  @param args input to the program from the command line
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//IOGame game = new IOGame(args[0]);
		// game.promptUser();
		// String[] replacements = game.getUserInput();
		// String result = game.replace(replacements);
		// System.out.println("Result: " + result);
		
		//String[] replacements = game.getFileInput(args[1]);
		//game.replaceAndWrite(replacements, args[2]);
		
		
		/* Below are some code snippets you may find useful when testing your program:
		
		IOGame game = new IOGame("file name goes in here");
		game.promptUser();
		String[] replacements = game.getUserInput();
		String result = game.replace(replacements);
		System.out.println("Result: " + result);
		
		IOGame game = new IOGame("file name goes in here");
		String[] replacements = game.getFileInput("file name goes in here");
		game.replaceAndWrite(replacements, "file name goes in here");
		
		System.err.println("Error: Could not find requested template file.");
		System.err.println("Error: Could not find requested template file or answer file.");
		System.err.println("Error: Could not write to output file.");
		System.err.println("You must call this program in one of the following ways:\njava IOGame templateFile\njava IOGame templateFile replacementsFile outputFile");
		*/
	}
}


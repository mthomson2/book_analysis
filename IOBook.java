import java.io.*;
import java.util.*;


public class IOBook {
	
	//Instance variables to help throughout the program
	private String database;
	private String fileName;
	private int howBigCounter;
	private String secondFile;
	
	// Constructor for IOBook. Initializes args[0] which is the database.txt file.  
	public IOBook(String database){
		this.database = database;
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File(database));
		} catch (FileNotFoundException e) {
			System.out.println("The file was not find. Please make sure the database text file is in your working directory. \n");
		}
		System.out.println("\n");
		sc.close();
	}
	
	
	//getFileName() prompts the user to pick a book from the database.
	public void getFileName() throws FileNotFoundException {
		//Create new Scanner object for file input
		Scanner sc = new Scanner(new File(database));
		System.out.println("Please pick a book. Type in the full name.");
		//while there are still tokens to read, it will print the tokens out
		while (sc.hasNext() != false) {
			String token = sc.next();
			System.out.println(token);
		}
		System.out.print(">> ");
		//asks user to pick which book they want to evaluate
		Scanner scan = new Scanner(System.in);
		this.fileName = scan.nextLine();
		System.out.println(fileName);
		sc.close();
		scan.close();
		System.out.println("\n");
	}
	
	//will list the database when needed. 
	public void listDatabase() throws FileNotFoundException {
		Scanner scanList = new Scanner(new File(database));
		System.out.println("Here is the list of books in the database: ");
		while (scanList.hasNext() != false) {
			String token = scanList.next();
			System.out.println("--  " + token);
		}
		scanList.close();
		System.out.println("\n");
	}
	
	//Prints the number of books that are in the database. Increments the counterBooks after there is
	//found to be a line determined by hasNextLine()
	public int numberOfBooks() throws FileNotFoundException {
		Scanner scanBooks = new Scanner(new File(database));
		int counterBooks = 0;
		// while loop that ensures the .nextLine() command will not exceed the lines on the database.
		while (scanBooks.hasNextLine() != false) {
			scanBooks.nextLine();
			counterBooks++;
		}
		scanBooks.close();
		//prints the number of books in the database. 
		if (counterBooks == 1) {
			System.out.println("There is 1 book in the database.");
		} else {
		System.out.println("There are "+ counterBooks + " books in the database.");
		}
		System.out.println("\n");
		return counterBooks;
	}
	
	//Database method: Will print the number of .txt files in the database
	public int txtFile() throws FileNotFoundException {
		Scanner txtSc = new Scanner(new File(database));
		int cTxt = 0;
		//Starts while loop to ensure nothing gets an OutOfRange Error
		while (txtSc.hasNextLine() != false) {
			String token = txtSc.nextLine();
			//Check to see if the last 4 letters are '.txt'
			if ((token.substring(token.length()-4,token.length())).equals(".txt")) {
				cTxt++;
			} 
		}
		txtSc.close();
		System.out.println("Number of .txt files in the database: " + cTxt);
		System.out.println("\n");
		return cTxt;
	}
	
	//Determines how many tokens are in the text. 
	public int bookChars() throws FileNotFoundException {
		Scanner scBook = new Scanner(new File(fileName));
		int counter = 0;
		//While loop to determine when to stop incrementing
		while (scBook.hasNext() != false) {
			scBook.next();
			counter++;
		}
		scBook.close();
		System.out.println("There are " + counter + " words in this text.");
		//instance variable set here to help with other methods
		howBigCounter = counter;
		System.out.println("\n");
		return counter;
	}
	//uses the 'howBigCounter' instance variable to determine how large the file is. 
	public void howBig() throws FileNotFoundException {
		//Uses if/elseif/else statements to check conditions. 
		if (howBigCounter < 5000) {
			System.out.println("This text file is less than 5,000 characters.");
		} else if (howBigCounter < 20000) {
			System.out.println("This text file is less than 20,000 characters.");
		} else if (howBigCounter < 80000) {
			System.out.println("This text file is less than 80,000 characters.");
		} else if (howBigCounter < 150000) {
			System.out.println("This text file is less than 150,000 charachters.");
		} else {
			System.out.println("This book is greater than 150,000 characters. It is way too long.");
		}
		System.out.println("\n");
	}
	
	//Counts the number of "the"s in a given file. 
	public void numThe() throws FileNotFoundException {
		Scanner scanThe = new Scanner(new File(fileName));
		int counterThe = 0;
		while (scanThe.hasNext() != false) {
			//initializes the String token to be used without confusion in the method.
			String token = scanThe.next();
			//and instance of "the" or "The" will be counted in the final count.
			if (token.equals("the") || token.equals("The")) {
				counterThe++;
				scanThe.next();
			}
		}
		System.out.println("The text has the word 'the' in it " + counterThe + " times.");
		System.out.println("\n");
		scanThe.close();
	}
	
	//Prints out the number of vowels that stand alone. 
	public void vowels() throws FileNotFoundException {
		Scanner vScan = new Scanner(new File(fileName));
		int countA = 0;
		int countE = 0;
		int countI = 0;
		int countO = 0;
		int countU = 0;
		while (vScan.hasNext() != false) {
			//Uses .equalsIgnoreCase(String) in case some of the characters were uppercase or lowercase. 
			String token = vScan.next();
			if (token.equalsIgnoreCase("a")) {
				countA++;
			} if (token.equalsIgnoreCase("e")) {
				countE++;
			} if (token.equalsIgnoreCase("i")) {
				countI++;
			} if (token.equalsIgnoreCase("o")) {
				countO++;
			} if (token.equalsIgnoreCase("u")) {
				countU++;
			}
		}
		System.out.println("Number of vowels in this text (standing alone):\nA - " + countA + "\nE - " + countE + "\nI - " + countI + "\nO - " + countO + "\nU - " + countU);
		System.out.println("\n");
		vScan.close();
	}
	

	
	//main method for testing and implementing 
	public static void main(String[] args) throws FileNotFoundException {
		IOBook book1 = new IOBook(args[0]);
		book1.getFileName();
		book1.bookChars();
		book1.listDatabase();
		book1.howBig();
		book1.numThe();
		book1.numberOfBooks();
		book1.txtFile();
		book1.vowels();
	}
}

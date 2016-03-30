Molly Thomson
mthomso2
G00892755
Lecture: H01
Lab: 2H1

For the honors section:

***READ THIS FIRST*** The database file MUST have a new line at the end
	of the file. So if the database file has 2 books in it, it will
	be comprised of 3 lines. This must be true 

Welcome to my IOBook program!

To run the IOBook java program, you must compile the program and then
run it with one argument. The argument is the database file. 

The database file is included in the text file in the text folder. I did not
remove this folder when submitting just for this purpose so you have access
to database.txt, fairytales.txt, and fairytales2.txt.

The default database file that I use which is located in the text folder
is "database.txt". It contains the books "fairytales.txt" and "fairytales2.txt"

For example, after you compile it, it might look like:
>>  java IOBook database.txt

You will then be prompted to input a file name. You can choose one from the
list that is provided on the prompt. 

From there, you should see all the methods at work. I have already put them
in my main method to run automatically. 

My IOBook class has several differnet statistical methods for databases and
books located in the database. 

The methods are as follows:
 - getFileName() 
	It prompts the user to pick a book in the list and then assigns that
	book to the fileName. 

 - listDatabase()
	Will print an ordered list of the database.

 - numberOfBooks()
	Prints the number of books that are in the database.

 - txtFile()
	Prints how many .txt files are in the database.

 - bookChars()
	Will print the number of tokens that are in a whole book. 

 - howBig()
	Will determine how big the book is based on how many tokens the
	book has. It will determine if it is less than the next one. 

 - numThe()
	Counts how many times the word "the" is in the book.

 - vowels()
	Will show how many times a token is equal to one of the vowels. Will
	display each vowel's counter at the end in comparison to each others.
	
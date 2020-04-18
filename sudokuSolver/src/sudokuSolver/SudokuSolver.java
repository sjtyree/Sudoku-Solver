package sudokuSolver;
//import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver 
{
	private static Scanner keyBoard = new Scanner(System.in);

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		SudokuSquare[][] sudokuPuzzle = new SudokuSquare[9][9];
		int choice = getUserChoice();
		populatePuzzle(sudokuPuzzle, choice);
		System.out.println("Here is the entered puzzle:");
		printPuzzle(sudokuPuzzle);
		System.out.println("The program will now attempt to solve the puzzle. Press enter to continue.");
		keyBoard.nextLine();
	    boolean success = solvePuzzleR(sudokuPuzzle, 0, 0); //call recursive function, starting at 0,0 (first square)
	    if (success)
		{
			System.out.println("Here is the completed puzzle:");
			printPuzzle(sudokuPuzzle);
		}
		else
		{
			System.out.println("The puzzle could not be solved.");
			printPuzzle(sudokuPuzzle);
		}
		
		//System.out.println(choice);
		
		keyBoard.close();
	}



	private static int getUserChoice() 
	{
		// TODO Auto-generated method stub
		//Scanner keyBoard = new Scanner(System.in);
		int choice = 0;
		do
		{
			try
			{
				System.out.println("Enter 1 to manually enter in sudoku numbers");
				System.out.println("Enter 2 to read sudoku numbers from a file");
				choice = Integer.parseInt(keyBoard.nextLine());
				if (choice != 1 && choice != 2)
					System.out.println("Please enter a valid choice");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid number");
			}
			
		}
		while(choice != 1 && choice != 2);
		
//		keyBoard.close();
//		System.out.println("Scanner closed");
		return choice;
	}
	
	/**
	 * 
	 * @param sudokuPuzzle
	 * @param choice
	 */
	private static void populatePuzzle(SudokuSquare[][] sudokuPuzzle, int choice) 
	{
		//manually enter numbers
		if (choice == 1)
		{
			getNumbersFromUser(sudokuPuzzle);
		}
		//read from file
		else if(choice == 2)
		{
			getNumbersFromFile(sudokuPuzzle);
		}
		//add more choices if necessary
		
		
		
	}


	private static void getNumbersFromUser(SudokuSquare[][] sudokuPuzzle)
	{
//		Scanner keyBoard = new Scanner(System.in);
		String line;
		char sudokuChar;
		System.out.println("Starting with the top row, enter each line of the puzzle, followed by enter.");
		System.out.println("Please enter the lines/rows one at a time, pressing enter at the end of each line.");
		System.out.println("Enter 0 for blank spaces.");
		for(int x = 0; x < 9; x++)
		{
			line = keyBoard.nextLine();
			System.out.println("You entered " + line);
			for (int y = 0; y < 9; y++)
			{
				sudokuChar = line.charAt(y);
				if (sudokuChar == '0')
					sudokuPuzzle[x][y] = new SudokuSquare(sudokuChar, false); //create new sudoku square, set fixed value to false
				else if (validNumber(sudokuChar))
					sudokuPuzzle[x][y] = new SudokuSquare(sudokuChar, true); //create new sudoku square, set fixed value to true 
				else
					System.out.println(sudokuChar + " is not a valid number.");
			}
		}
//		keyBoard.close();
	}
	
	private static void getNumbersFromFile(SudokuSquare[][] sudokuPuzzle)
	{
		System.out.println("Reading from file at location: SudokuInput\\SudokuInput.txt");
		Scanner sudokuFile;
		String line; 
		char sudoku_char;
		try
		{
			sudokuFile = new Scanner(new File("SudokuInput\\SudokuInput.txt"));
			for(int x = 0; x < 9; x++)
			{
				line = sudokuFile.nextLine(); //grab the puzzle line by line
				for (int y = 0; y < 9; y++)
				{
					sudoku_char = line.charAt(y); //get the next character in the line
					if (sudoku_char == '0')
						sudokuPuzzle[x][y] = new SudokuSquare(sudoku_char, false); //create new sudoku square, set fixed value to false
					else if (validNumber(sudoku_char))
						sudokuPuzzle[x][y] = new SudokuSquare(sudoku_char, true); //create new sudoku square, set fixed value to true 
					else
						System.out.println(sudoku_char + " is not a valid number.");
				}
//				sudokuFile.nextLine();
				
			}
			sudokuFile.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found at SudokuInput\\\\SudokuInput.txt");
		}
		
		
		
	}
	
	/**
	 * Checks a character to see if it is a valid sudoku number (1-9)
	 * @param sudoku_char The character to be checked.
	 * @return true if the character is a valid sudoku number (1-9). False otherwise. 
	 */
	private static boolean validNumber(char sudoku_char)
	{
		return (sudoku_char == '1' || sudoku_char == '2' || sudoku_char == '3' ||
				sudoku_char == '4' || sudoku_char == '5' || sudoku_char == '6' ||
				sudoku_char == '7' || sudoku_char == '8' || sudoku_char == '9');
	}

	/**
	 * The main recursive function that is used to solve the Sudoku puzzle
	 * @param sudokuPuzzle The sudoku puzzle to be solved
	 * @param x The puzzle's x coordinate that we are checking.
	 * @param y The puzzle's y coordinate that we are checking.
	 * @return True if we have successfully reached the end of the solved puzzle. False if we have tried every number for the current square, and need to move back one square to try another number. Also returns false if the puzzle cannot be solved for some reason.3
	 */
	private static boolean solvePuzzleR(SudokuSquare[][] sudokuPuzzle, int x, int y)
	{
		//base case, reached the end of the puzzle
		if (x == 9 && y == 0)
			return true;
		SudokuSquare square = sudokuPuzzle[x][y];
		int nextX, nextY; //the next x and y coordinates we will go to after this call 
		if (y == 8) //if we're at the end of the row, move onto the next row
		{
			nextX = x + 1;
			nextY = 0;
		}
		else //we are not at the end of the row, keep moving right 
		{
			nextX = x;
			nextY = y + 1;
		}
		if (!square.getFixed()) //if number is not fixed, try to put a number in.
		{
			for (int prospect = 1; prospect <= 9; prospect++) //iterate through the numbers 1-9 to determine which number goes there
			{
				if (checkRow(sudokuPuzzle, x, y, (char)(prospect+'0')) && checkColumn(sudokuPuzzle, x, y, (char)(prospect+'0')) && checkSquare(sudokuPuzzle, x, y, (char)(prospect+'0')))
				{//If number is not in the row or column or square, put the number there and move on to the next square
					square.setValue((char)(prospect+'0')); //trick to convert an integer to a char. 
					sudokuPuzzle[x][y] = square;
					if(solvePuzzleR(sudokuPuzzle, nextX, nextY)) //return true and break the loop if puzzle is complete. Otherwise, continue looping
						return true;
				}
			}
			square.setValue('0'); //set value back to 0 if we can't find a prospective number
			sudokuPuzzle[x][y] = square;
			return false; //return false and try a different number on the previous square
		}
		else //if number is fixed, move on to the next square
		{
			return solvePuzzleR(sudokuPuzzle, nextX, nextY);

		}
	}

	

	/*
	 * Checks to see if the prospective number already exists in the current row.
	 * @param sudokuPuzzle The sudoku puzzle
	 * @param x the x coordinate of the sudoku puzzle that we're checking
	 * @param y the y coordinate of the sudoku puzzle that we're checking
	 * @param prospect The number that we are checking
	 * @returns true if the number does not exist in the current row. False otherwise
	 */
	public static boolean checkRow(SudokuSquare[][] sudokuPuzzle, int x, int y, char prospect)
	{
		for (int num = 0; num < 9; num++)
		{
			if (y != num)
				if (sudokuPuzzle[x][num].getValue() == prospect )
					return false;
		}
		return true;
	}

	/*
	 * Checks to see if the prospective number already exists in the current column.
	 * @param sudokuPuzzle The sudoku puzzle
	 * @param x The x coordinate of the sudoku puzzle that we're checking
	 * @param y The y coordinate of the sudoku puzzle that we're checking
	 * @param prospect The number that we are checking
	 * @returns true if the number does not exist in the current column. False otherwise
	 */
	public static boolean checkColumn(SudokuSquare[][] sudokuPuzzle, int x, int y, char prospect)
	{
		for (int num = 0; num < 9; num++)
		{
			if (x != num)
				if (sudokuPuzzle[num][y].getValue() == prospect )
					return false;
		}
		return true;
	}

	/*
	 * Checks to see if the prospective number already exists in the current 9x9 square.
	 * @param sudokuPuzzle The sudoku puzzle
	 * @param x The x coordinate of the sudoku puzzle that we're checking
	 * @param y The y coordinate of the sudoku puzzle that we're checking
	 * @param prospect The number that we are checking
	 * @returns true if the number does not exist in the current 9x9 square. False otherwise
	 */
	public static boolean checkSquare(SudokuSquare[][] sudokuPuzzle, int x, int y, char prospect)
	{
		if (x % 3 == 0 && y % 3 == 0) //we're at the top left of the square
		{
			//check squares
			return  sudokuPuzzle[x+1][y+1].getValue() != prospect &&
					sudokuPuzzle[x+1][y+2].getValue() != prospect &&
					sudokuPuzzle[x+2][y+1].getValue() != prospect &&
					sudokuPuzzle[x+2][y+2].getValue() != prospect;
			
		}
		else if (x % 3 == 0 && y % 3 == 1) //we're at the top middle of the square
		{
			//check squares
			return  sudokuPuzzle[x+1][y-1].getValue() != prospect &&
					sudokuPuzzle[x+1][y+1].getValue() != prospect &&
					sudokuPuzzle[x+2][y-1].getValue() != prospect &&
					sudokuPuzzle[x+2][y+1].getValue() != prospect;
		}
		else if (x % 3 == 0 && y % 3 == 2) //we're at the top right of the square
		{
			//check squares\
			return  sudokuPuzzle[x+1][y-2].getValue() != prospect &&
					sudokuPuzzle[x+1][y-1].getValue() != prospect &&
					sudokuPuzzle[x+2][y-2].getValue() != prospect &&
					sudokuPuzzle[x+2][y-1].getValue() != prospect;
		}
		else if (x % 3 == 1 && y % 3 == 0) //we're at the middle left of the square
		{
			//check squares 
			return  sudokuPuzzle[x-1][y+1].getValue() != prospect &&
					sudokuPuzzle[x-1][y+2].getValue() != prospect &&
					sudokuPuzzle[x+1][y+1].getValue() != prospect &&
					sudokuPuzzle[x+1][y+2].getValue() != prospect;
		}
		else if (x % 3 == 1 && y % 3 == 1) //we're at the middle middle of the square
		{
			//check squares 
			return  sudokuPuzzle[x-1][y-1].getValue() != prospect &&
					sudokuPuzzle[x-1][y+1].getValue() != prospect &&
					sudokuPuzzle[x+1][y-1].getValue() != prospect &&
					sudokuPuzzle[x+1][y+1].getValue() != prospect;
		}
		else if (x % 3 == 1 && y % 3 == 2) //we're at the middle right of the square
		{
			//check squares 
			return  sudokuPuzzle[x-1][y-2].getValue() != prospect &&
					sudokuPuzzle[x-1][y-1].getValue() != prospect &&
					sudokuPuzzle[x+1][y-2].getValue() != prospect &&
					sudokuPuzzle[x+1][y-1].getValue() != prospect;
		}
		else if (x % 3 == 2 && y % 3 == 0) //we're at the bottom left of the square
		{
			//check squares 
			return  sudokuPuzzle[x-2][y+1].getValue() != prospect &&
					sudokuPuzzle[x-2][y+2].getValue() != prospect &&
					sudokuPuzzle[x-1][y+1].getValue() != prospect &&
					sudokuPuzzle[x-1][y+2].getValue() != prospect; 
		}
		else if (x % 3 == 2 && y % 3 == 1) //we're at the bottom middle of the square
		{
			//check squares 
			return  sudokuPuzzle[x-2][y-1].getValue() != prospect &&
					sudokuPuzzle[x-2][y+1].getValue() != prospect &&
					sudokuPuzzle[x-1][y-1].getValue() != prospect &&
					sudokuPuzzle[x-1][y+1].getValue() != prospect; 
		}
		else if (x % 3 == 2 && y % 3 == 2) //we're at the bottom right of the square
		{
			//check squares 
			return  sudokuPuzzle[x-2][y-2].getValue() != prospect &&
					sudokuPuzzle[x-2][y-1].getValue() != prospect &&
					sudokuPuzzle[x-1][y-2].getValue() != prospect &&
					sudokuPuzzle[x-1][y-1].getValue() != prospect; 
		} 
		return false; //return false if no conditions above are met
		
	}

	private static void printPuzzle(SudokuSquare[][] sudokuPuzzle)
	{
		for(int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				System.out.print(sudokuPuzzle[x][y]);
				if (y == 2 || y == 5 )
					System.out.print('|'); //print a pipe character if we're at the right side of a square 
			}
			System.out.println();
			if (x == 2 || x == 5) //print a line of hyphens if we're at the bottom of a square
			{
				System.out.print("-----------");
				System.out.println();
			}
		}
	}
}

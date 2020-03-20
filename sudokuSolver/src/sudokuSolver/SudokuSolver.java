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
		printPuzzle(sudokuPuzzle);
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
		int enteredValue;
		System.out.println("Starting with the top row, left to write, please enter each number, followed by enter.");
		System.out.println("Please enter the numbers one at a time, pressing enter after each number.");
		System.out.println("Enter 0 for blank spaces.");
		for(int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				enteredValue = Integer.parseInt(keyBoard.nextLine());
				System.out.println("You entered " + enteredValue);
				sudokuPuzzle[x][y] = new SudokuSquare(enteredValue, true); //create new sudoku square, set fixed value to true 
			}
		}
//		keyBoard.close();
	}
	
	private static void getNumbersFromFile(SudokuSquare[][] sudokuPuzzle)
	{
		System.out.println("Reading from file at location: SudokuInput\\SudokuInput.txt");
		Scanner sudokuFile;
		try
		{
			sudokuFile = new Scanner(new File("SudokuInput\\SudokuInput.txt"));
			for(int x = 0; x < 9; x++)
			{
				for (int y = 0; y < 9; y++)
				{
					sudokuPuzzle[x][y] = new SudokuSquare(sudokuFile.nextInt(), true); //create new sudoku square, set fixed value to true 
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
	
	private static void printPuzzle(SudokuSquare[][] sudokuPuzzle)
	{
		for(int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				System.out.print(sudokuPuzzle[x][y].getValue());
			}
			System.out.println();
		}
	}
}

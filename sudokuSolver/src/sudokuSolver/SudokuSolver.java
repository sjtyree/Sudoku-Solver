package sudokuSolver;
//import java.io.*;

import java.util.Scanner;

public class SudokuSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SudokuSquare[][] sudokuPuzzle = new SudokuSquare[9][9];
		int choice = getUserChoice();
		populatePuzzle(sudokuPuzzle, choice);
		//System.out.println(choice);

	}


	private static int getUserChoice() 
	{
		// TODO Auto-generated method stub
		Scanner keyBoard = new Scanner(System.in);
		int choice = 0;
		do
		{
			try
			{
				System.out.println("Enter 1 to manually enter in sudoku numbers");
				System.out.println("Enter 2 to read sudoku numbers from a file");
				choice = Integer.parseInt(keyBoard.nextLine());
				if (choice != 1 || choice != 2)
					System.out.println("Please enter a valid choice");
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid number");
			}
			
		}
		while(choice != 1 && choice != 2);
		
		keyBoard.close();
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
		// TODO Auto-generated method stub
	}
	
	private static void getNumbersFromFile(SudokuSquare[][] sudokuPuzzle)
	{
		System.out.println("Reading from file at location: SudokuInput\\SudokuInput.txt");
		Scanner sudokuFile = new Scanner("SudokuInput\\SudokuInput.txt");
		
	}


	

}

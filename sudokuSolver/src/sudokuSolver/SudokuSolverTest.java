package sudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SudokuSolverTest
{

	@Test
	void testCheckRow()
	{
		SudokuSquare[][]sudokuTest = new SudokuSquare[1][9];
		for (int y = 0; y < 9; y++)
			sudokuTest[0][y] = new SudokuSquare();
		sudokuTest[0][1].setValue('1');
		assertFalse(SudokuSolver.checkRow(sudokuTest, 0, 0, '1'));
		assertTrue(SudokuSolver.checkRow(sudokuTest, 0, 0, '2'));
		sudokuTest[0][1].setValue('0');
		
		
	}

	@Test
	void testCheckColumn() 
	{
		SudokuSquare[][]sudokuTest = new SudokuSquare[9][1];
		for (int x = 0; x < 9; x++)
			sudokuTest[x][0] = new SudokuSquare();
		sudokuTest[1][0].setValue('1');
		assertFalse(SudokuSolver.checkColumn(sudokuTest, 0, 0, '1'));
		assertTrue(SudokuSolver.checkColumn(sudokuTest, 0, 0, '2'));
	}
	
	@Test
	void testCheckSquare()
	{
		SudokuSquare[][]sudokuTest = new SudokuSquare[3][3];
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				sudokuTest[x][y] = new SudokuSquare();
		
		//test top left square
		sudokuTest[1][1].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 0, 0, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 0, 0, '2'));
		sudokuTest[1][1].setValue('0');
		
		//test top middle square
		sudokuTest[1][0].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 0, 1, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 0, 1, '2'));
		sudokuTest[1][1].setValue('0');
		
		//test top right square
		sudokuTest[1][1].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 0, 2, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 0, 2, '2'));
		sudokuTest[1][1].setValue('0');
		
		//test middle left square
		sudokuTest[0][1].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 1, 0, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 1, 0, '2'));
		sudokuTest[1][1].setValue('0');
		
		//test middle middle square
		sudokuTest[0][0].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 1, 1, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 1, 1, '2'));
		sudokuTest[1][1].setValue('0');
				
		//test middle right square
		sudokuTest[0][1].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 1, 2, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 1, 2, '2'));
		sudokuTest[1][1].setValue('0');
		
		//test bottom left square
		sudokuTest[0][2].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 2, 0, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 2, 0, '2'));
		sudokuTest[1][1].setValue('0');
		
		//test bottom middle square
		sudokuTest[1][2].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 2, 1, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 2, 1, '2'));
		sudokuTest[1][1].setValue('0');
		
		//test bottom right square
		sudokuTest[1][1].setValue('1');
		assertFalse(SudokuSolver.checkSquare(sudokuTest, 2, 2, '1'));
		assertTrue(SudokuSolver.checkSquare(sudokuTest, 2, 2, '2'));
		sudokuTest[1][1].setValue('0');
	}
}

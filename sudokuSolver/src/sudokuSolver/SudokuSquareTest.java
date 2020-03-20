package sudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SudokuSquareTest
{

	@Test
	void testSudokuSquare()
	{
		SudokuSquare testSquare = new SudokuSquare();
		assertEquals(testSquare.getValue(), 0);
		assertEquals(testSquare.getFixed(), false);
	}

	@Test
	void testSudokuSquareInt()
	{
		int testValue = 1;
		SudokuSquare testSquare = new SudokuSquare(testValue);
		assertEquals(testSquare.getValue(), 1);
		assertEquals(testSquare.getFixed(), false);
	}

	@Test
	void testSudokuSquareIntBoolean()
	{
		int testValue = 1;
		boolean testFixed = true;
		SudokuSquare testSquare = new SudokuSquare(testValue, testFixed);
		assertEquals(testSquare.getValue(), 1);
		assertEquals(testSquare.getFixed(), true);
	}

	@Test
	void testGetValue()
	{
		//fail("Not yet implemented");
	}

	@Test
	void testSetValue()
	{
		
		int initialValue = 0, setValue = 1; 
		SudokuSquare testSquare = new SudokuSquare(initialValue);
		testSquare.setValue(setValue);
		assertEquals(testSquare.getValue(), 1);
		
	}

	@Test
	void testGetPossibleValues()
	{
		//fail("Not yet implemented");
	}

	@Test
	void testSetPossibleValue()
	{
		int possibleValue1 = 1, possibleValue2 = 2, possibleValue3 = 3;
		SudokuSquare testSquare = new SudokuSquare();
		testSquare.setPossibleValue(possibleValue1);
		ArrayList<Integer> testList = testSquare.getPossibleValues();
		assertTrue(testList.contains(1));
		testSquare.setPossibleValue(possibleValue2);
		testSquare.setPossibleValue(possibleValue3);
		assertTrue(testList.contains(2));
		assertTrue(testList.contains(3));
		assertEquals(testList.size(), 3);
	}

	@Test
	void testRemovePossibleValue()
	{
		int possibleValue1 = 1, possibleValue2 = 2, possibleValue3 = 3;
		SudokuSquare testSquare = new SudokuSquare();
		testSquare.setPossibleValue(possibleValue1);
	}

}

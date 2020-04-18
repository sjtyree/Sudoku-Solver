/**
 * 
 */
package sudokuSolver;

import java.util.ArrayList;



/**
 * @author styree
 *
 */
public class SudokuSquare 
{

	private char value; //actual value of the square
	private boolean fixedNum; //number is fixed and cannot be changed ie was one of the numbers present in the original puzzle
	private ArrayList<Integer> possibleValues; //list of possible values that this square could contain
	
	/**
	 * 
	 */
	public SudokuSquare() 
	{
		this.value = 0;
		this.fixedNum = false;
		this.possibleValues = new ArrayList<Integer>(9);
	}
	
	public SudokuSquare(char value) 
	{
		this.value = value;
		this.fixedNum = false;
		this.possibleValues = new ArrayList<Integer>(9);
	}
	
	public SudokuSquare(char value, boolean fixedNum) 
	{
		this.value = value;
		this.fixedNum = fixedNum;
		this.possibleValues = new ArrayList<Integer>(9);
	}
	
	public char getValue()
	{
		return this.value;
	}
	
	public void setValue(char value)
	{
		this.value = value;
	}
	
	public void setFixed(boolean isFixed)
	{
		this.fixedNum = isFixed;
	}
	
	public boolean getFixed()
	{
		return this.fixedNum;
	}
	
	public ArrayList<Integer> getPossibleValues()
	{
		return this.possibleValues; 
	}
	
	public void setPossibleValue(int value)
	{
		//if value is not in List, add it and then sort
		if (possibleValues.indexOf(value) == -1)
		{
			this.possibleValues.add(value); 
			this.possibleValues.sort(null);
		}
	}
	
	public void removePossibleValue(Integer value)
	{
		if (possibleValues.indexOf(value) != -1)
		{
			this.possibleValues.add(value); 
		}
	}
	
	@Override
	public String toString()
	{
		return value + "";//convert char to string
	}

}

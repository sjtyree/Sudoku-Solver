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

	int value;
	ArrayList<Integer> possibleValues = new ArrayList(9);
	
	/**
	 * 
	 */
	public SudokuSquare() 
	{
		this.value = 0;
	}
	
	public SudokuSquare(int value) 
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
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

}

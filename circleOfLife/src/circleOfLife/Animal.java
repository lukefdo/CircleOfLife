package circleOfLife;

/**
 *  
 * @author
 *
 */

/*
 * This class is to be extended by the Deer, Jaguar, and Puma classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 

	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		// TODO 
		return age; 
	}
}

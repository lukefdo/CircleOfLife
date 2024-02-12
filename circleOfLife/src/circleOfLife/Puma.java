package circleOfLife;

/**
 *  
 * @author
 *
 */

/**
 * A puma eats deers and competes against a jaguar. 
 */
public class Puma extends Animal 
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Puma (Jungle j, int r, int c, int a) 
	{
		// TODO 
		jungle = j;
		row = r;
		column = c;
		age = a;
	}
		
	/**
	 * A puma occupies the square. 	 
	 */
	@Override
	public State who()
	{ 
		return State.PUMA; 
	}
	
	/**
	 * A puma dies of old age or hunger, or from attack by a jaguar. 
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	@Override
	public Living next(Jungle wNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See Section 2.2 in the project description for the survival rules for a puma. 
		if (this.age == 4) {
			return wNew.grid[row][column] = new Empty(wNew, row, column);
		}
		int pop[] = new int[NUM_LIFE_FORMS];
		this.census(pop);
		if (pop[JAGUAR] > pop[PUMA]) {
			return wNew.grid[row][column] = new Jaguar(wNew, row, column,0);
		}
		else if (pop[JAGUAR] + pop[PUMA] > pop[DEER]) {
			return wNew.grid[row][column] = new Empty(wNew, row, column);
		}
		else {
			this.age++;
			jungle = wNew;
			return wNew.grid[row][column] = this;
		}
		
		
	}
}

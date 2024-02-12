package circleOfLife;

/**
 *  
 * @author
 *
 */

/**
 * A jaguar eats a deer and competes against a puma. 
 */
public class Jaguar extends Animal
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Jaguar (Jungle j, int r, int c, int a) 
	{
		jungle = j;
		row = r;
		column = c;
		age = a;
	}
	
	/**
	 * A jaguar occupies the square. 	 
	 */
	@Override
	public State who()
	{ 
		return State.JAGUAR; 
	}
	
	/**
	 * A jaguar dies of old age or hunger, from isolation and attack by more numerous pumas.
	 *  
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	@Override
	public Living next(Jungle jNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See Section 2.1 in the project description for the survival rules for a jaguar. 
		if (this.age == 5) {
			return jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		if (pop[JAGUAR]*2 <= pop[PUMA]) {
			return jNew.grid[row][column] = new Puma(jNew, row, column, 0);
		}
		else if (pop[JAGUAR] + pop[PUMA] > pop[DEER]) {
			return jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else {
			this.age++;
			jungle = jNew;
			return jNew.grid[row][column] = this; 
		}
		 
	}
}

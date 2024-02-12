package circleOfLife;

/**
 *  
 * @author
 *
 */

/*
 * A deer eats grass and lives no more than six years.
 */
public class Deer extends Animal 
{	
	/**
	 * Creates a Deer object.
	 * @param j: jungle  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Deer (Jungle j, int r, int c, int a) 
	{
		jungle = j;
		row = r;
		column = c;
		age = a;
	}
		
	// Deer occupies the square.
	@Override
	public State who()
	{
		return State.DEER; 
	}
	
	/**
	 * @param jNew     jungle in the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	@Override
	public Living next(Jungle jNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See Section 2.3 in the project description for the survival rules for a deer. 
		if (this.age == 6) {
			return jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		if (pop[GRASS] == 0) {
			return jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else if (pop[PUMA] + pop[JAGUAR] > pop[DEER] && pop[PUMA] >= pop[JAGUAR]*2) {
			return jNew.grid[row][column] = new Puma(jNew, row, column, 0);
		}
		else if (pop[PUMA] + pop[JAGUAR] > pop[DEER] && pop[JAGUAR] >= pop[PUMA]) {
			return jNew.grid[row][column] = new Jaguar(jNew, row, column, 0);
		}
		else {
			this.age++;
			jungle = jNew;
			return jNew.grid[row][column] = this;
		} 
	}
}

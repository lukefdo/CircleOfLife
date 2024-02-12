package circleOfLife;

/**
 *  
 * @author
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Jungle j, int r, int c) 
	{
		jungle = j;
		row = r;
		column = c;
	}
	
	@Override
	public State who()
	{
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Deer, Grass, Jaguar, or Puma, or 
	 * remain empty. 
	 * @param jNew     jungle in the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	@Override
	public Living next(Jungle jNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See Section 2.5 in the project description for corresponding survival rules. 
		//
		//Deer, if more than one neighboring Deer;
		//otherwise, Puma, if 2 or more neighboring Puma;
		//otherwise, Jaguar, if more than one neighboring Jaguar;
		//otherwise, Grass, if at least one neighboring Grass;
		//otherwise, Empty.

		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		if(pop[DEER] >= 2) {
			return jNew.grid[row][column] = new Deer(jNew, row, column, 0);
		}
		else if (pop[PUMA] >= 2) {
			return jNew.grid[row][column] = new Puma(jNew, row, column, 0);
		}
		else if (pop[JAGUAR] >= 2) {
			return jNew.grid[row][column] = new Jaguar(jNew, row, column, 0);
		}
		else if (pop[GRASS] >= 1) {
			return jNew.grid[row][column] = new Grass(jNew, row, column);
		}
		else {
			jungle = jNew;
			return jNew.grid[row][column] = this;
		}
	}
}

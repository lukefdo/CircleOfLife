package circleOfLife;

/**
 *  
 * @author
 *
 */

/**
 * Grass may be eaten out or taken over by deers. 
 *
 */
public class Grass extends Living 
{
	public Grass (Jungle j, int r, int c) 
	{
		// Done
		jungle = j;
		row = r;
		column = c;
	}
	
	@Override
	public State who()
	{
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many deers in the neighborhood. Deers may also 
	 * multiply fast enough to take over Grass. 
	 * 
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	@Override
	public Living next(Jungle jNew)
	{
		// TODO 
		// a) Empty if at least three times as many Deers as Grasses in the neighborhood;
		// b) otherwise, Deer if there are at least four Deers in the neighborhood;
		// c) otherwise,Grass.
		// See Living.java for an outline of the function. 
		// See Section 2.4 in the project description for the survival rules for grass. 
		
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		if(pop[DEER] >= pop[GRASS]*3) {
			return jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else if (pop[DEER] >= 4) {
			return jNew.grid[row][column] = new Deer(jNew, row, column, 0);
		}
		else {
			jungle = jNew;
			return jNew.grid[row][column] = this; 
		}
	}
}

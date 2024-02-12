package circleOfLife;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Luke Fernando
 *
 */

/**
 * 
 * The CircleOfLife class performs simulation over a grid jungle 
 * with squares occupied by deers, jaguars, pumas, grass, or none. 
 *
 */
public class CircleOfLife 
{
	/**
	 * Update the new jungle from the old jungle in one cycle. 
	 * @param jOld  old jungle
	 * @param jNew  new jungle 
	 */
	public static void updateJungle(Jungle jOld, Jungle jNew)
	{
		// TODO 
		// 
		// For every life form in the grid jOld, generate  
		// a Living object in the grid jNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
		
		for (int r = 0; r < jOld.getWidth(); r++) {
			for (int c = 0; c < jOld.getWidth(); c++) {
				jOld.grid[r][c].next(jNew);
			}
		}
	}
	
	/**
	 * Repeatedly generates jungles either randomly or from reading files. 
	 * Over each jungle, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// Generate CircleOfLife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random jungle, 2 to read a jungle from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two jungles even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the jungle 
		//    odd from the jungle even; in an odd numbered cycle, generate even 
		//    from odd. 
		// 4. Print out initial and final jungles only.  No intermediate jungles should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate jungles.)
		// 
		// 5. You may save some randomly generated jungles as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated. 
		
		Jungle even;   				 // the jungle after an even number of cycles 
		Jungle odd;                  // the jungle after an odd number of cycles
		int trial = 1;
		int in = 0;
		
		System.out.println("Circle of Life in the Amazon Jungle");
		System.out.println("keys: 1 (random jungle)  2 (file input)  3 (exit)");
		
		Scanner reader = new Scanner(System.in);
		
		while (in != 3) {
			
			System.out.println("Trail " + trial);
			in = reader.nextInt();
			if ( in == 3) {
				System.exit(0);
			}
			
			if (in == 1) {
				System.out.println("Random jungle");
				System.out.println("Enter grid width:");
				int width = reader.nextInt();
				System.out.println("Enter the number of cycles:");
				
				int cycles = reader.nextInt();
				int cur = 0; //current cycle
				
				
				even = new Jungle(width); //construct both even and odd, add random animals to even
				even.randomInit();
				System.out.println("Initial jungle: \n" + even.toString());
				odd = new Jungle(width);
				
				while (cur < cycles) {
					if (cur % 2 == 0) {
						updateJungle(even, odd);
					}
					else {
						updateJungle(odd, even);
					}
					cur++;
				}
				
				if ( cycles % 2 == 0) {
					// return even
					System.out.println("Final jungle \n" + even.toString());
				}
				else {
					//return odd
					System.out.println("Final jungle \n" + odd.toString());
				}
			}
			
			if (in == 2) {
				//need to finish
				System.out.println("Jungle input from a file \nFile name:"); 
				String f = reader.next();
				System.out.println("Enter the number of cycles:");
				
				int cycles = reader.nextInt();
				int cur = 0; //current cycle
				
				even = new Jungle(f);
				System.out.println("Initial jungle: \n" + even.toString());
				int width = even.getWidth();
				odd = new Jungle(width);
				
				while (cur < cycles) {
					if (cur % 2 == 0) {
						updateJungle(even, odd);
					}
					else {
						updateJungle(odd, even);
					}
					cur++;
				}
				
				if ( cycles % 2 == 0) {
					// return even
					System.out.println("Final jungle \n" + even.toString());
				}
				else {
					//return odd
					System.out.println("Final jungle \n" + odd.toString());
				}
			}
			trial++;
		}
	}
}

package circleOfLife;



/**
 *  
 * @author
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The jungle is represented as a square grid of size width X width. 
 *
 */
public class Jungle 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Jungle(String inputFileName) throws FileNotFoundException
	{		
        // 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid jungle in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done. 
		
		File file = new File(inputFileName);
		
		Scanner getWidth = new Scanner(file);
		int counter = 0;
		while (getWidth.hasNextLine()) {
			String l = getWidth.nextLine();
			if (!l.equals("")) {
				counter++;
			}
		}
		int w = counter;
		getWidth.close();
		width = w;
		grid = new Living[w][w];
		Scanner input = new Scanner(file);
		
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				String temp = input.next();
				
				if (temp.equals("E")) {
					grid[i][j] = new Empty(this, i, j); 
				}
				else if (temp.equals("G")) {
					grid[i][j] = new Grass(this, i, j);
				}
				else {
					char[] tempArr = temp.toCharArray();
					char ani = tempArr[0];
					char age = tempArr[1];
					int iAge = Character.getNumericValue(age);
					
					if( ani == 'D') {
						grid[i][j] = new Deer(this, i, j, iAge);
					}
					else if (ani == 'J') {
						grid[i][j] = new Jaguar(this, i, j, iAge);
					}
					else if (ani == 'P') {
						grid[i][j] = new Puma(this, i, j, iAge);
					}
				}
			}
		}
		input.close();
	}
	
	/**
	 * Constructor that builds a w X w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Jungle(int w)
	{
		width = w;
		grid = new Living[w][w]; 
	}
	
	
	public int getWidth()
	{
		return width;  // to be modified 
	}
	
	/**
	 * Initialize the jungle by randomly assigning to every square of the grid  
	 * one of Deer, Empty, Grass, Jaguar, or Puma.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random random = new Random(); 
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < width; col++) {
				int i = random.ints(0, 4).findFirst().getAsInt();
				if (i == Living.DEER) {
					grid[row][col] = new Deer(this, col, row, 0);
				}
				if (i == Living.EMPTY) {
					grid[row][col] = new Empty(this, col, row);
				}
				if (i == Living.GRASS) {
					grid[row][col] = new Grass(this, col, row);
				}
				if (i == Living.JAGUAR) {
					grid[row][col] = new Jaguar(this, col, row, 0);
				}
				if (i == Living.PUMA) {
					grid[row][col] = new Puma(this, col, row, 0);
				}
			}
		}
	}
	
	
	/**
	 * Output the jungle grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	@Override
	public String toString()
	{
		String s = "";
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.width; j++) {
				Living temp = this.grid[i][j];
				State state = null;
				try {
					state = temp.who();
				} catch (Exception e) {
					s = s + "null ";
				}
				
				if (state == State.DEER) {
					Deer d = (Deer) temp;
					String a = String.valueOf(d.age);
					s = s + "D" + a +" ";
				}
				else if (state == State.EMPTY) {
					s = s + "E  ";
				}
				else if (state == State.GRASS) {
					s = s + "G  ";
				}
				else if (state == State.JAGUAR) {
					Jaguar ja = (Jaguar) temp;
					String a = String.valueOf(ja.age);
					s = s + "J" + a + " ";
				}
				else if (state == State.PUMA) {
					Puma p = (Puma) temp;
					String a = String.valueOf(p.age);
					s = s + "P" + a + " ";
				}
			}
			if (i != this.width-1) 
			{
				s = s + "\n";
			}
		}
		return s; 
	}
	

	/**
	 * Write the jungle grid to an output file.  Also useful for saving a randomly 
	 * generated jungle for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    D, E, G, J, P. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
		try {
		FileWriter myWriter = new FileWriter(outputFileName);
		for (Living[] eachrow: this.grid) {
			for (Living j: eachrow) {
				State s = j.who();
				if (s == State.DEER) {
					Deer d = (Deer) j;
					int age = d.myAge();
					myWriter.write("D" + age + " " );
				}
				if (s == State.EMPTY) {
					myWriter.write("E  ");;
				}
				if (s == State.GRASS) {
					myWriter.write("G  ");
				}
				if (s == State.JAGUAR) {
					Jaguar ja = (Jaguar) j;
					int age = ja.myAge();
					myWriter.write("J" + age + " ");
				}
				if (s == State.PUMA) {
					Puma p = (Puma) j;
					int age = p.myAge();
					myWriter.write("P" + age + " ");
				}
			}
			myWriter.write(System.lineSeparator());
		}
		myWriter.close();
		}	catch (FileNotFoundException e) {
		System.out.println("File name not found");
		e.printStackTrace();
		}	catch (IOException e1) {
			System.out.println("IO Exception error");
			e1.printStackTrace();
		}
	}	
}

package jUnitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

import circleOfLife.Jungle;

public class JungleTest {

	@Test
	public void testJungleString() throws FileNotFoundException {
		//used testToString to test constructor
	}
	
	@Test
	public void testRandomInit() {
		Jungle j = new Jungle(5);
		
		j.randomInit();
		System.out.println("First random jungle \n" +
						    j.toString() + "\n");
		
		j.randomInit();
		System.out.println("Second random jungle \n" +
							j.toString() + "\n");
		
		j.randomInit();
		System.out.println("Third random jungle \n" +
							j.toString() + "\n");
	}

	@Test
	public void testToString() throws FileNotFoundException {
		String f = "1-3x3.txt";
		Jungle j = new Jungle(f);
		String js = j.toString();
		System.out.println("Given string from method");
		System.out.print(j.toString());
		String expect = 
				  "G  J0 G  \n"
				+ "J0 D0 P0 \n"
				+ "J0 D0 E  ";
		System.out.println();
		System.out.println();
		System.out.println("Expected string");
		System.out.println(expect);
		System.out.println();
		assertEquals(expect, js);	}

	@Test
	public void testWrite() throws FileNotFoundException {
		String f = "1-3x3.txt";
		Jungle j = new Jungle(f);
		j.write("output.txt");
		
	}

}

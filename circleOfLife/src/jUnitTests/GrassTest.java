package jUnitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;

import circleOfLife.*;

public class GrassTest {

	@Test
	public void test() throws FileNotFoundException {
		Jungle j = new Jungle("1-3x3.txt");
		Jungle j1 = new Jungle(3);
		Grass g = (Grass) j.grid[0][0];
		int[] pop = new int[5];
		g.census(pop);
		System.out.println(Arrays.toString(pop));
		
		g.next(j1);
		System.out.print(j1);
	}

}

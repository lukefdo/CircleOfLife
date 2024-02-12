package jUnitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;

import circleOfLife.*;

public class LivingTest {

	@Test
	public void testCensus() throws FileNotFoundException {
		String f = "1-3x3.txt";
		Jungle j = new Jungle(f);
		Grass ani = (Grass) j.grid[0][2];
		int[] pop = new int[5];
		ani.census(pop);
		System.out.print(Arrays.toString(pop));
				
	}
}

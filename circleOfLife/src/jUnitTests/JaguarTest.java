package jUnitTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import circleOfLife.*;

public class JaguarTest {

	@Test
	public void livingNextTest() throws FileNotFoundException {
		String f = "1-3x3.txt";
		Jungle j = new Jungle(f);
		Jaguar jag = (Jaguar) j.grid[0][1];
		Jungle jNew = new Jungle(3);
		Living next = jag.next(jNew);
		System.out.println(next.who());
		String s = jNew.toString();
		System.out.print(s);
		
		Jungle test = new Jungle(3);
		test.grid[0][1] = new Empty(test, 0, 1);
		String s1 = test.toString();
		assertEquals(s, s1);
	}

}

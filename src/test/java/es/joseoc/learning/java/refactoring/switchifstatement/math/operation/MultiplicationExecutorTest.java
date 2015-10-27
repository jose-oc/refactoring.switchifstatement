package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

import static org.junit.Assert.*;


import org.junit.Test;


public class MultiplicationExecutorTest {

	@Test
	public void testExecute() throws Exception {
		MultiplicationExecutor executor = new MultiplicationExecutor(10, 2);
		int actual = executor.execute();
		assertEquals(20, actual);
	}

	@Test
	public void testCommutative() throws Exception {
		MultiplicationExecutor executor = new MultiplicationExecutor(10, 2);
		int result1 = executor.execute();
		executor = new MultiplicationExecutor(2, 10);
		int result2 = executor.execute();
		assertEquals(result1, result2);
	}
}

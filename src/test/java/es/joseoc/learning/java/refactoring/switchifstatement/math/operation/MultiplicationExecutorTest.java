package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

import static org.junit.Assert.*;


import org.junit.Test;


public class MultiplicationExecutorTest {

	@Test
	public void testExecute() throws Exception {
		MultiplicationExecutor executor = new MultiplicationExecutor();
		int actual = executor.execute(10, 2);
		assertEquals(20, actual);
	}

	@Test
	public void testCommutative() throws Exception {
		MultiplicationExecutor executor = new MultiplicationExecutor();
		int result1 = executor.execute(10, 2);
		executor = new MultiplicationExecutor();
		int result2 = executor.execute(2, 10);
		assertEquals(result1, result2);
	}
}

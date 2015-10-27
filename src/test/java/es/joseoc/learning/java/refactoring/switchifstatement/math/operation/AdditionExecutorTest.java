package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AdditionExecutorTest {

	@Test
	public void testExecute() throws Exception {
		AdditionExecutor executor = new AdditionExecutor(10, 0);
		int actual = executor.execute();
		assertEquals(10, actual);
	}

	@Test
	public void testCommutative() throws Exception {
		AdditionExecutor executor = new AdditionExecutor(10, 0);
		int result1 = executor.execute();
		executor = new AdditionExecutor(0, 10);
		int result2 = executor.execute();
		assertEquals(result1, result2);
	}
}

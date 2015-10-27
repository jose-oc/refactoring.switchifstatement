package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AdditionExecutorTest {

	@Test
	public void testExecute() throws Exception {
		AdditionExecutor executor = new AdditionExecutor();
		int actual = executor.execute(10, 0);
		assertEquals(10, actual);
	}

	@Test
	public void testCommutative() throws Exception {
		AdditionExecutor executor = new AdditionExecutor();
		int result1 = executor.execute(10, 0);
		executor = new AdditionExecutor();
		int result2 = executor.execute(0, 10);
		assertEquals(result1, result2);
	}
}

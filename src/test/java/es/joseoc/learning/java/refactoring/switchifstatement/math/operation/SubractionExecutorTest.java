package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubractionExecutorTest {

	@Test
	public void testExecute() throws Exception {
		SubractionExecutor executor = new SubractionExecutor(10, 2);
		int actual = executor.execute();
		assertEquals(8, actual);
	}
	
	@Test
	public void testExecuteWhenNegativeExpected() throws Exception {
		SubractionExecutor executor = new SubractionExecutor(10, 20);
		int actual = executor.execute();
		assertEquals(-10, actual);
	}
}

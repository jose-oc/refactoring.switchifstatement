package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubractionExecutorTest {

	@Test
	public void testExecute() throws Exception {
		SubractionExecutor executor = new SubractionExecutor();
		int actual = executor.execute(10, 2);
		assertEquals(8, actual);
	}
	
	@Test
	public void testExecuteWhenNegativeExpected() throws Exception {
		SubractionExecutor executor = new SubractionExecutor();
		int actual = executor.execute(10, 20);
		assertEquals(-10, actual);
	}
}

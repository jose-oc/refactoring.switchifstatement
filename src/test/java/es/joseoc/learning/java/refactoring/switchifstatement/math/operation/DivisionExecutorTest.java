package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class DivisionExecutorTest {
	
	@Test
	public void testExecute() throws Exception {
		DivisionExecutor executor = new DivisionExecutor();
		int actual = executor.execute(10, 2);
		assertEquals(5, actual);
	}
	
	@Test
	public void testExecuteWhenDivisorSmaller() throws Exception {
		DivisionExecutor executor = new DivisionExecutor();
		int actual = executor.execute(10, 20);
		assertEquals(0, actual);
	}

	@Test(expected = ArithmeticException.class)
	public void testExecuteWhenDivisorZero() throws Exception {
		DivisionExecutor executor = new DivisionExecutor();
		int actual = executor.execute(10, 0);
		assertEquals(0, actual);
	}

}

package es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory;

import static org.junit.Assert.*;


import org.junit.Test;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.AdditionExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory.BinaryOperationClassNotFoundException;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory.BinaryOperationCreateNewInstanceException;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory.UnsupportedBinaryOperationException;

public class BinaryOperationFactoryTest {

	@Test
	public void whenSupportedOperationShouldGetInstance() throws Exception {
		BinaryOperation actual = BinaryOperationFactory.getInstance(Operation.ADDITION);
		assertEquals(AdditionExecutor.class, actual.getClass());
	}

	@Test(expected = UnsupportedBinaryOperationException.class)
	public void whenUnsupportedOperationShouldRaiseException() throws Exception {
		BinaryOperationFactory.getInstance(Operation.MULTIPLICATION);
	}
	
	@Test(expected = BinaryOperationClassNotFoundException.class)
	public void whenInvalidOperationClassShouldRaiseException() throws Exception {
		BinaryOperationFactory.getInstance(Operation.SUBTRACTION);
	}
	
	@Test(expected = BinaryOperationCreateNewInstanceException.class)
	public void whenClassNonInstantiableShouldRaiseException() throws Exception {
		BinaryOperationFactory.getInstance(Operation.DIVISION);
	}

	public static abstract class AbstractMockClass {
		
	}

}

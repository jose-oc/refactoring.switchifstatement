package es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.AdditionExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperationExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory.BinaryOperationCreateNewInstanceException;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory.UnsupportedBinaryOperationException;

public class BinaryOperationFactoryTest {

	@Test
	public void testGetSupportedOperations() throws Exception {
		Set<Operation> supportedOperations = BinaryOperationFactory.getSupportedOperations();
		Set<Operation> expected = new HashSet<>( Arrays.asList( Operation.values() ) );
		assertEquals(expected, supportedOperations);
	}

	@Test
	public void whenSupportedOperationShouldGetInstance() throws Exception {
		BinaryOperation actual = BinaryOperationFactory.getInstance(Operation.ADDITION);
		assertEquals(AdditionExecutor.class, actual.getClass());
	}

	@Test(expected = UnsupportedBinaryOperationException.class)
	@Ignore
	public void whenUnsupportedOperationShouldRaiseException() throws Exception {
		//BinaryOperationFactory.getInstance(Operation.ANOTHER_OPERATION);
		/*
		 * to test this case I'll need to add a new item to the Operation enum and not to write any 
		 * implementation for that operation.
		 */
	}
	
	@Test(expected = BinaryOperationCreateNewInstanceException.class)
	public void whenClassNonInstantiableShouldRaiseException() throws Exception {
		BinaryOperationFactory.getInstance(Operation.DIVISION);
	}

	@BinaryOperationExecutor(type=Operation.DIVISION)
	public static abstract class AbstractMockClass {
		
	}

}

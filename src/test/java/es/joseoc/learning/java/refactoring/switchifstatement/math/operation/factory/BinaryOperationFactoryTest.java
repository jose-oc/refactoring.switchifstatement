package es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.AdditionExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory.BinaryOperationCreateNewInstanceException;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory.UnsupportedBinaryOperationException;

public class BinaryOperationFactoryTest {
	
	@Before
	public void initialize() {
		BinaryOperationFactory.clearCache();
	}

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
	public void whenNullOperationShouldRaiseException() throws Exception {
		BinaryOperationFactory.getInstance(null);
	}

	@Test(expected = BinaryOperationCreateNewInstanceException.class)
	public void whenClassNonInstantiableShouldRaiseException() throws Exception {
		Reflections classScanner = new Reflections("es.joseoc.learning.java.refactoring.switchifstatement.math.operation.mock");
		BinaryOperationFactory.getInstance(Operation.DIVISION, classScanner);
	}

	@Test(expected = UnsupportedBinaryOperationException.class)
	public void whenUnsupportedOperationShouldRaiseException() throws Exception {
		Reflections classScanner = new Reflections("es.joseoc.learning.java.refactoring.switchifstatement.math.operation.mock");
		BinaryOperationFactory.getInstance(Operation.MULTIPLICATION, classScanner);
	}
		
}

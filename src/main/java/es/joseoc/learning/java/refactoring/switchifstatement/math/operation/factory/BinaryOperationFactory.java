package es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.AdditionExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.DivisionExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.MultiplicationExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.SubractionExecutor;

public final class BinaryOperationFactory {

	public static BinaryOperation getInstance(Operation operation) {
		if (operation.equals(Operation.ADDITION)) 
		{
			return new AdditionExecutor();
		} else if (operation.equals(Operation.SUBTRACTION)) 
		{
			return new SubractionExecutor();
		} else if (operation.equals(Operation.MULTIPLICATION)) 
		{
			return new MultiplicationExecutor();
		} else if (operation.equals(Operation.DIVISION)) 
		{
			return new DivisionExecutor();
		} else 
		{
			throw new InvalidOperatorException(operation);
		}
	}


	public static final class InvalidOperatorException extends RuntimeException 
	{
		private static final long serialVersionUID = -4559154624432136312L;

		public InvalidOperatorException(Operation operation) 
		{
			super("Invalid operation: " + operation);
		}
	}
}

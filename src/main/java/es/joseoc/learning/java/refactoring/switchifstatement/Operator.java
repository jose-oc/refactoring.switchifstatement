package es.joseoc.learning.java.refactoring.switchifstatement;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.AdditionExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.DivisionExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.MultiplicationExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.SubractionExecutor;

/**
 * This class contains an example of Builder Pattern.
 * This is a class (inner class in this case) to build another one. 
 * The built class is immutable.
 *
 */
public final class Operator 
{
	private final int operand1;
	private final int operand2;
	private final Operation operation;


	private Operator(int operand1, int operand2, Operation operation) 
	{
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operation = operation;
	}

	private Operator(OperatorBuilder builder) 
	{
		this.operand1 = builder.operand1;
		this.operand2 = builder.operand2;
		this.operation = builder.operation;
	}
	
	public static OperatorBuilder of() 
	{
		return new OperatorBuilder();
	}


	public int performOperation() 
	{
		if (operation.equals(Operation.ADDITION)) 
		{
			AdditionExecutor adder = new AdditionExecutor(operand1, operand2);
			return adder.execute();
		} else if (operation.equals(Operation.SUBTRACTION)) 
		{
			SubractionExecutor subtracter = new SubractionExecutor(operand1, operand2);
			return subtracter.execute();
		} else if (operation.equals(Operation.MULTIPLICATION)) 
		{
			MultiplicationExecutor multiplier = new MultiplicationExecutor(operand1, operand2);
			return multiplier.execute();
		} else if (operation.equals(Operation.DIVISION)) 
		{
			DivisionExecutor div = new DivisionExecutor(operand1, operand2);
			return div.execute();
		} else 
		{
			throw new InvalidOperatorException(this);
		}
	}

	public static final class OperatorBuilder
	{
		private int operand1;
		private int operand2;
		private Operation operation;
		
		public OperatorBuilder operandA(int operand) 
		{
			this.operand1 = operand;
			return this;
		}

		public OperatorBuilder operandB(int operand) 
		{
			this.operand2 = operand;
			return this;
		}

		public OperatorBuilder operation(Operation operation) 
		{
			this.operation = operation;
			return this;
		}

		public Operator prepareOperation() 
		{
			return new Operator(this);
		}
	}
	
	public static final class InvalidOperatorException extends RuntimeException 
	{
		private static final long serialVersionUID = -4559154624432136312L;

		public InvalidOperatorException(Operator operator) 
		{
			super("Invalid operation: " + operator.operation);
		}
	}
}

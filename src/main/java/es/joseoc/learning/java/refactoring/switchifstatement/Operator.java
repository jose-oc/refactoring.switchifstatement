package es.joseoc.learning.java.refactoring.switchifstatement;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory.BinaryOperationFactory;

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
		BinaryOperation executor = BinaryOperationFactory.getInstance(operation);
		return executor.execute(operand1, operand2);
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
}

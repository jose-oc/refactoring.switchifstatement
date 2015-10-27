package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

public final class MultiplicationExecutor implements BinaryOperation 
{
	@Override
	public int execute(int operand1, int operand2) {
		return operand1 * operand2;
	}

}

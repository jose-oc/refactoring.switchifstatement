package es.joseoc.learning.java.refactoring.switchifstatement.math.operation;

public final class MultiplicationExecutor 
{
	private final int operand1;
	private final int operand2;

	public MultiplicationExecutor(int theOperand1, int theOperand2) {
		this.operand1 = theOperand1;
		this.operand2 = theOperand2;
	}

	public int execute() {
		return operand1 * operand2;
	}

	public int getOperand1() {
		return operand1;
	}

	public int getOperand2() {
		return operand2;
	}

}
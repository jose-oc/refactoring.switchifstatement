package es.joseoc.learning.java.refactoring.switchifstatement.math.operation.mock;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperationExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;

@BinaryOperationExecutor(type=Operation.DIVISION)
public abstract class DivisionExecutorMocked implements BinaryOperation {

}

package es.joseoc.learning.java.refactoring.switchifstatement;

import static org.apache.commons.lang3.EnumUtils.isValidEnum;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

import java.util.Arrays;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;

public class App 
{
	private static final int NUM_ARGUMENTS = 3;

	public static void main( String[] args )
    {
        if (!validateArguments( args )) {
        	return;
        }
        
        Operator operator = Operator.of()
        		.operandA(toInt(args[1]))
        		.operandB(toInt(args[2]))
        		.operation(Operation.valueOf(args[0]))
        		.prepareOperation();
        int result = operator.performOperation();
        
        System.out.println( "Result: " + result );
    }

	private static boolean validateArguments( String[] args ) 
	{
		if (!validateNumArguments(args)) {
			return false;
		}
		
		boolean valid = true;
		valid = valid && validateOperation(args);
		valid = valid && validateOperands(args);
		return valid;
	}

	private static boolean validateOperands(String[] args) {
		boolean valid = true;
		for(int i = 1; i < NUM_ARGUMENTS; i++) {
			if (!isDigits(args[i])) {
				System.err.println( "Operand " + i + " must be an integer" );
				valid = false;
			}
		}
		return valid;
	}

	private static boolean validateOperation(String[] args) {
		if (!isValidEnum(Operation.class, args[0])) {
			System.err.println( "Operation " + args[0] + " not valid. Operations valid: " + Arrays.toString( Operation.values() ) );
			return false;			
		}
		return true;
	}

	private static boolean validateNumArguments(String[] args) {
		if (args.length < NUM_ARGUMENTS) {
			System.err.println( usage() );
			return false;
		}
		return true;
	}

	private static String usage() {
		return "Usage: App operation operand1 operand2 \n"
				+ "Being:\n"
				+ "\toperation is one of these symbols: + - * / \n"
				+ "\toperands are integers";
	}
}

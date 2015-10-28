# refactoring.switchifstatement
This is an example of how we can refactor a switch-case or if statement when each case do the same thing with different classes applying a factory.

## Step 1
The project works properly using an if statement like this one:
```java
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
```

We'll work on doing the same with a better code.

At this moment we're using a Builder Pattern on `Operator` class using an inner class as the builder.

## Step 2
The if shown above has been moved to a factory class in order to create the object which executes the operation. These objects implement a new interface in order to the factory returns the same type of object. 

## Step 3
Use a properties file to specify which class implements each operation. This way we can change the behaviour without having to compile and deploy, just changing the properties file. 
We can also add new operations just adding it to the properties file. 
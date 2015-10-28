package es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory;

import java.util.Properties;

import es.joseoc.learning.java.refactoring.switchifstatement.io.PropertiesLoader;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;

public final class BinaryOperationFactory {
	
	private static final String PROPERTIES_NAME = "binary_operations.properties";
	
	private final Operation operation;
	private final Properties props;

	private BinaryOperationFactory(Operation operation) {
		this.operation = operation;
		this.props = PropertiesLoader.loadProperties(PROPERTIES_NAME).getProperties();
	}

	public static BinaryOperation getInstance(Operation operation) {
		BinaryOperationFactory factory = new BinaryOperationFactory(operation);
		String clazzName = factory.readClassNameForOperation();
		Class<?> clazz = factory.loadClass(clazzName);
		return factory.createNewInstance(clazz);
	}

	private String readClassNameForOperation() {
		String clazzName = props.getProperty(operation.name());
		if (clazzName == null) {
			throw new UnsupportedBinaryOperationException(operation);
		}

		return clazzName;
	}

	private Class<?> loadClass(String clazzName) {
		try {
			return Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			throw new BinaryOperationClassNotFoundException(operation, clazzName, e);
		}
	}

	private BinaryOperation createNewInstance(Class<?> clazz) {
		try {
			return (BinaryOperation) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BinaryOperationCreateNewInstanceException(operation, clazz, e);
		}
	}
	
	public static final class UnsupportedBinaryOperationException extends RuntimeException 
	{
		private static final long serialVersionUID = -4559154624432136312L;
		private final Operation operation;

		public UnsupportedBinaryOperationException(Operation operation) 
		{
			super("Unsupported operation: " + operation + "\nTo support it you need to add the operation to the file "
					+ PROPERTIES_NAME + " and associate it with the class which implements "
					+ BinaryOperation.class.getSimpleName());
			this.operation = operation;
		}

		public Operation getOperation() {
			return operation;
		}
	}
	
	public static final class BinaryOperationClassNotFoundException extends RuntimeException 
	{
		private static final long serialVersionUID = -9109575107489500443L;
		private final Operation operation;
		private final String classname;

		public BinaryOperationClassNotFoundException(Operation operation, String classname, Exception e) 
		{
			super("Configuration error: error loading the class " + classname
					+ " which is the implementation defined to use as " + BinaryOperation.class.getSimpleName()
					+ " for the operation " + operation + ". Please, check if the class exists.", e);
			this.operation = operation;
			this.classname = classname;
		}

		public Operation getOperation() {
			return operation;
		}

		public String getClassname() {
			return classname;
		}
	}

	public static final class BinaryOperationCreateNewInstanceException extends RuntimeException 
	{
		private static final long serialVersionUID = 7828333654857238086L;
		private final Operation operation;
		private final Class<?> clazz;

		public BinaryOperationCreateNewInstanceException(Operation operation, Class<?> clazz, Exception e) 
		{
			super("Configuration error: error creating a new instance of the class " + clazz.getCanonicalName()
					+ " which is the implementation defined to use as " + BinaryOperation.class.getSimpleName()
					+ " for the operation " + operation, e);
			this.operation = operation;
			this.clazz = clazz;
		}

		public Operation getOperation() {
			return operation;
		}

		public Class<?> getClazz() {
			return clazz;
		}
	}

}

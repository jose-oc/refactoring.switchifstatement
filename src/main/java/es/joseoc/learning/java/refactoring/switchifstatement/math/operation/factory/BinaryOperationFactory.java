package es.joseoc.learning.java.refactoring.switchifstatement.math.operation.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperation;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.BinaryOperationExecutor;
import es.joseoc.learning.java.refactoring.switchifstatement.math.operation.Operation;

public final class BinaryOperationFactory {
	private static final Logger LOG = LoggerFactory.getLogger(BinaryOperationFactory.class);
	private static final Map<Operation, Class<?>> registeredOperations = new HashMap<>(Operation.values().length);

	private final Operation operation;
	private Reflections classScanner;

	private BinaryOperationFactory(Operation operation) {
		this.operation = operation;
	}

	private BinaryOperationFactory(Operation operation, Reflections classScanner) {
		this.operation = operation;
		this.classScanner = classScanner;
	}

	public static BinaryOperation getInstance(Operation operation) {
		BinaryOperationFactory factory = new BinaryOperationFactory(operation);
		
		factory.init();
		Class<?> binaryOperationClass = registeredOperations.get(operation);
		if (binaryOperationClass != null) {
			return factory.createNewInstance(binaryOperationClass);
		} else {
			throw new UnsupportedBinaryOperationException(operation);
		}
	}

	/**
	 * This method is written for testing purposes
	 */
	protected static BinaryOperation getInstance(Operation operation, Reflections classScanner) {
		BinaryOperationFactory factory = new BinaryOperationFactory(operation, classScanner);

		factory.init();
		Class<?> binaryOperationClass = registeredOperations.get(operation);
		if (binaryOperationClass != null) {
			return factory.createNewInstance(binaryOperationClass);
		} else {
			throw new UnsupportedBinaryOperationException(operation);
		}
	}
	
	/**
	 * This method is written for testing purposes
	 */
	protected static void clearCache() {
		registeredOperations.clear();
	}

	private void init() {
		if (registeredOperations.isEmpty()) {
			scanClasses();
		}
	}

	private void scanClasses() {
		Set<Class<?>> classes = getClassesToExecuteMathOperations();
		for (Class<?> clazz : classes) {
			registerClass(clazz);
		}
	}

	private Set<Class<?>> getClassesToExecuteMathOperations() {
		initializeClassScannerIfNeeded();
		return classScanner.getTypesAnnotatedWith(BinaryOperationExecutor.class);
	}

	private void initializeClassScannerIfNeeded() {
		if (classScanner == null) {
			classScanner = new Reflections("es.joseoc");
		}
	}

	private void registerClass(Class<?> clazz) {
		BinaryOperationExecutor annotation = clazz.getAnnotation(BinaryOperationExecutor.class);
		Operation type = annotation.type();
		if (registeredOperations.containsKey(type)) {
			LOG.warn("BinaryOperation of type " + type.name() + " is already registered with the class "
					+ clazz.getName());
		} else {
			LOG.debug("New BinaryOperation registered. Operation type: " + type.name() + "; Class: " + clazz.getName());
			registeredOperations.put(type, clazz);
		}
	}

	private BinaryOperation createNewInstance(Class<?> clazz) {
		try {
			return (BinaryOperation) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BinaryOperationCreateNewInstanceException(operation, clazz, e);
		}
	}

	public static Set<Operation> getSupportedOperations() {
		BinaryOperationFactory factory = new BinaryOperationFactory(null);
		factory.init();
		return registeredOperations.keySet();
	}

	public static final class UnsupportedBinaryOperationException extends RuntimeException {
		private static final long serialVersionUID = -4559154624432136312L;
		private final Operation operation;

		public UnsupportedBinaryOperationException(Operation operation) {
			super("Unsupported operation: " + operation + "\nTo support it you need to add a new class annotated with "
					+ BinaryOperationExecutor.class.getSimpleName()
					+ " and specify the type of operation it implements.");
			this.operation = operation;
		}

		public Operation getOperation() {
			return operation;
		}
	}

	public static final class BinaryOperationCreateNewInstanceException extends RuntimeException {
		private static final long serialVersionUID = 7828333654857238086L;
		private final Operation operation;
		private final Class<?> clazz;

		public BinaryOperationCreateNewInstanceException(Operation operation, Class<?> clazz, Exception e) {
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

package es.joseoc.learning.java.refactoring.switchifstatement.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesLoader {
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesLoader.class);
	private final String fileName;
	private final String systemPropertyName;
	private final Properties properties = new Properties();
	
	private PropertiesLoader(String fileName, String systemPropertyName) {
		this.fileName = fileName;
		this.systemPropertyName = systemPropertyName;
	}

	public static PropertiesLoader loadProperties(String fileName) {
		return loadProperties(fileName, null);
	}

	public static PropertiesLoader loadProperties(String fileName, String systemPropertyName) {
		PropertiesLoader propUtils = new PropertiesLoader(fileName, systemPropertyName);
		propUtils.load();
		return propUtils;
	}

	private void load() {
		InputStream input = null;

		if (existsConfigDirectory()) {
			input = getInputStreamUsingFile();
		} else {
			input = getInputStreamUsingClassloader();
		}
		
		if (input == null) {
			input = getInputStreamUsingFileInRoot();
		}
		
		loadPropertiesFromInputStream(input);
	}

	private boolean existsConfigDirectory() {
		boolean exists = false;
		if (existsSystemPropertyForConfig()) {
			File dir = new File(System.getProperty(systemPropertyName));
			if (dir.exists() && dir.isDirectory()) {
				exists = true;
				LOG.debug("System property '" + systemPropertyName + "' holds the directory '" + dir
						+ "' which will be used to look for the properties file.");
			} else {
				LOG.warn("The configuration directory specified is not valid: " + System.getProperty(systemPropertyName)
						+ ". This directory is given by a system property called " + systemPropertyName);
			}
		}
		return exists;
	}

	private boolean existsSystemPropertyForConfig() {
		return systemPropertyName != null && System.getProperty(systemPropertyName) != null;
	}

	private InputStream getInputStreamUsingClassloader() {
		return this.getClass().getResourceAsStream(fileName);
	}

	private InputStream getInputStreamUsingFile() {
		File propertyFile = new File(System.getProperty(systemPropertyName), fileName);
		return readInputStreamFromFile(propertyFile);
	}

	private InputStream getInputStreamUsingFileInRoot() {
		InputStream input = null;
		File propertiesFile = new File(this.getClass().getResource("/").getFile(), fileName);
		
		if (propertiesFile.exists() && propertiesFile.isFile()) {
			input = readInputStreamFromFile(propertiesFile);
		} 
		return input;
	}

	private InputStream readInputStreamFromFile(File propertiesFile) {
		InputStream input = null;
		try {
			input = new FileInputStream(propertiesFile);
		} catch (FileNotFoundException e) {
			LOG.warn("File not found. Filename: " + propertiesFile);
		}
		return input;
	}

	private void loadPropertiesFromInputStream(InputStream input) {
		try {
			if (input != null) {
				properties.load(input);
			} else {
				LOG.warn("Properties file " + fileName + " not found.");
			}
		} catch (IOException e) {
			LOG.warn("Error loading properties from inputstream. Filename: " + fileName, e);
		}
	}

	public Properties getProperties() {
		return properties;
	}

}

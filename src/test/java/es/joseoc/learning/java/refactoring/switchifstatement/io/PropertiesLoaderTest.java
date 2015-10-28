package es.joseoc.learning.java.refactoring.switchifstatement.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;


public class PropertiesLoaderTest {

	private static final String VARIABLE_HOLDS_DIRECTORY_NAME = "configuration.dir";
	private static final String PROPERTIES_FILE_NAME = "test.properties";

	@After
	public void clearSystemProperty() throws Exception {
		System.clearProperty(VARIABLE_HOLDS_DIRECTORY_NAME);
	}

	@Test
	public void whenFileExitsOnCustomFolderShouldReturnPropertiesFromDirectory() throws Exception {
		System.setProperty(VARIABLE_HOLDS_DIRECTORY_NAME, this.getClass().getResource("/custom_folder").getFile());
		Properties actual = PropertiesLoader.loadProperties(PROPERTIES_FILE_NAME, VARIABLE_HOLDS_DIRECTORY_NAME).getProperties();

		assertNotNull(actual);
		assertEquals("value1.custom_folder", actual.getProperty("key.1"));
		assertEquals("value2.custom_folder", actual.getProperty("key.2"));
	}

	@Test
	@Ignore
	public void whenFileExitsOnPackageShouldReturnProperties() throws Exception {
		Properties actual = PropertiesLoader.loadProperties(PROPERTIES_FILE_NAME).getProperties();

		assertNotNull(actual);
		assertEquals("value1.package", actual.getProperty("key.1"));
		assertEquals("value2.package", actual.getProperty("key.2"));
	}

	@Test
	public void whenNonExistentConfigDirShouldReturnPropertiesFromRoot() throws Exception {
		System.setProperty(VARIABLE_HOLDS_DIRECTORY_NAME, File.createTempFile("prefix", "sufix").getAbsolutePath());
		Properties actual = PropertiesLoader.loadProperties(PROPERTIES_FILE_NAME, VARIABLE_HOLDS_DIRECTORY_NAME).getProperties();

		assertNotNull(actual);
		assertEquals("value1.root", actual.getProperty("key.1"));
		assertEquals("value2.root", actual.getProperty("key.2"));
	}

	@Test
	public void whenFileExitsInRootShouldReturnPropertiesFromRoot() throws Exception {
		Properties actual = PropertiesLoader.loadProperties(PROPERTIES_FILE_NAME).getProperties();

		assertNotNull(actual);
		assertEquals("value1.root", actual.getProperty("key.1"));
		assertEquals("value2.root", actual.getProperty("key.2"));
	}

	@Test
	public void whenInvalidFilenameShouldReturnEmptyProperties() throws Exception {
		Properties actual = PropertiesLoader.loadProperties("no_exist_file.properties").getProperties();

		assertNotNull(actual);
		assertTrue(actual.isEmpty());
	}

}

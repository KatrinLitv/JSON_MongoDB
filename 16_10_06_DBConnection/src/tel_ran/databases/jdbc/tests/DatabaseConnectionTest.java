package tel_ran.databases.jdbc.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tel_ran.databases.jdbc.DatabaseConnection;

public class DatabaseConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConnection() throws Exception {
		DatabaseConnection databaseConnection = 
				DatabaseConnection.getDataBaseConnection("root", "Paris2005", null, null);
		DatabaseConnection databaseConnection2 = 
				DatabaseConnection.getDataBaseConnection("root", "Paris2005", null, null);
		assertTrue(databaseConnection==databaseConnection2); // class = singletone!!!
		assertTrue(databaseConnection.getConnection()!=null);
	}

}

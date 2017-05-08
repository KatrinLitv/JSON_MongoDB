package tel_ran.databases.mongo.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tel_ran.databases.mongo.MongoConnection;

public class MongoConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMongoConnection() {
		MongoConnection connection1 = MongoConnection.getMongoCollection
				("mongodb://root:Paris2005@ds053126.mlab.com:53126/", "katrin_litv");
		MongoConnection connection2 = MongoConnection.getMongoCollection
				("mongodb://root:Paris2005@ds053126.mlab.com:53126/", "katrin_litv");
		assertTrue(connection1==connection2);
		assertTrue(connection1.getDataBase()!=null);
	}

}

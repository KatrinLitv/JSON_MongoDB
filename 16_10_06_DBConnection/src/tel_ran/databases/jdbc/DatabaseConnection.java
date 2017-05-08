package tel_ran.databases.jdbc;
import java.sql.*;

public class DatabaseConnection {
private static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/sakila";
private static DatabaseConnection databaseConnection;
Connection connection;

private DatabaseConnection(String userName, String password, String url, String driver) throws Exception{
	if (driver==null)
		driver =DEFAULT_DRIVER;
	if (url==null)
		url=DEFAULT_URL;
	Class.forName(driver);
	connection = DriverManager.getConnection(url,userName,password);
}

synchronized public static DatabaseConnection getDataBaseConnection(String userName, String password, String url, String driver) throws Exception{
	if (databaseConnection==null)
		databaseConnection = new DatabaseConnection(userName, password, url, driver);
	return databaseConnection;
}

public Connection getConnection() {
	return connection;
}
}

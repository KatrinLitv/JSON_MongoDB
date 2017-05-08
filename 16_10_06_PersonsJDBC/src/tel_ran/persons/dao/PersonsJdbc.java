package tel_ran.persons.dao;

import java.sql.*;
import java.util.*;

import tel_ran.databases.jdbc.DatabaseConnection;
import tel_ran.persons.entities.Person;

public class PersonsJdbc {
private static final String TABLE_NAME = "persons";
private Statement statement;

public PersonsJdbc() throws Exception{
	DatabaseConnection connection = 
			DatabaseConnection.getDataBaseConnection("root", "Paris2005", null, null);
	statement = connection.getConnection().createStatement();
	
	String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (id INTEGER," +
			"name VARCHAR(254), birthYear INTEGER, PRIMARY KEY(id)," +
			"INDEX(name), INDEX(birthYear))";
	statement.executeUpdate(sql);
}

public boolean addPerson(Person person){
		String sql = String.format(sql="INSERT INTO %s (id,name,birthYear) "+
				"VALUES (%d,'%s',%d)", TABLE_NAME,person.getId(),person.getName(),person.getBirthYear());
		boolean result=true;
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			result=false;
		}
	return result;	
}

public boolean removePerson(int id){
	boolean result=true;
	String sql = String.format(sql="DELETE FROM %s WHERE id=%d ",TABLE_NAME,id);
	try {
		statement.executeUpdate(sql);
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		result=false;
	}
	return result;
}

public Person getPerson(int id){
	Person res = null;
	String sql = String.format("SELECT * FROM %s WHERE id=%d", TABLE_NAME,id);
	ResultSet rs;
	try {
		rs = statement.executeQuery(sql);
		if (rs.next()==true){
			res=getOnePerson(rs);			
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}

	return res;
}

private Person getOnePerson(ResultSet rs) throws Exception {
		String name=rs.getString("name");
		int idRes = rs.getInt("id");
		int birthYear = rs.getInt("birthYear");
	return new Person(idRes, name, birthYear);
}

public Iterable<Person> getPersonsByAge(int ageFrom, int ageTo){
	int yearFrom=getYear(ageTo);
	int yearTo=getYear(ageFrom);
	String sql=String.format("SELECT * FROM %s WHERE birthYear BETWEEN %d AND %d", 
			TABLE_NAME,yearFrom,yearTo);
	return getPersons(sql);
}

private Iterable<Person> getPersons(String sql) {
	List<Person> res = new ArrayList<>();
	try {
		ResultSet rs=statement.executeQuery(sql);
		while (rs.next()){
			res.add(getOnePerson(rs));
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return res;
}

private int getYear(int age) {
	Calendar calendar = Calendar.getInstance();
	return calendar.get(Calendar.YEAR)-age;
}

public Iterable<Person> getPersonsByName(String name){
	String sql=String.format("SELECT * FROM %s WHERE name='%s'", TABLE_NAME,name);
	return getPersons(sql);
}
}

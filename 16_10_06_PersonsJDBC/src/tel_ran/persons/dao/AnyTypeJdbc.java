package tel_ran.persons.dao;

import java.io.IOException;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tel_ran.databases.jdbc.DatabaseConnection;
import tel_ran.persons.entities.Person;

public class AnyTypeJdbc {
//Sql
private static final String TABLE_NAME = "persons";
private static final String TYPE = "type";
private Statement statement;
//Json
static ObjectMapper mapper = new ObjectMapper();

public AnyTypeJdbc() throws Exception {
		DatabaseConnection connection = DatabaseConnection.getDataBaseConnection("root", "Paris2005", null, null);
		statement = connection.getConnection().createStatement();
	}

public boolean add(Object obj){	
	boolean result=true;
	try {
		StringBuffer str[] = getFields(obj);
		String sql = String.format(sql="INSERT INTO %s (%s) VALUES ( %s )", TABLE_NAME,str[0],str[1]);
		statement.executeUpdate(sql);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		result=false;
	}
return result;	
}

private StringBuffer[] getFields(Object obj) throws Exception {
	LinkedHashMap<String,Object> map = getMap(obj);	
	map.put(TYPE,obj.getClass().getName());
	StringBuffer res[] = new StringBuffer[2];
	res[0] = new StringBuffer(50);
	res[1] = new StringBuffer(50);
	for (String str : map.keySet()){
		res[0].append(","+str);	
		if (map.get(str) instanceof String)
			res[1].append(",'").append(map.get(str)).append("'");
		else {
			res[1].append(',');
			res[1].append(map.get(str));
		}
	}
	res[0].deleteCharAt(0);
	res[1].deleteCharAt(0);
	return res;
}

private LinkedHashMap<String, Object> getMap(Object obj) throws Exception {
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	String json=mapper.writeValueAsString(obj);
	LinkedHashMap<String,Object> map = mapper.readValue(json, LinkedHashMap.class);
	
	return map;
}

public Object get(int id){
	Object res = null;
	String sql = String.format("SELECT * FROM %s WHERE id=%d", TABLE_NAME,id);
	ResultSet rs;
	try {
		rs = statement.executeQuery(sql);
		if (rs.next()==true){
			res=getOneObject(rs);	
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}

	return res;
}

private Object getOneObject(ResultSet rs) throws Exception {
	String type=rs.getString("type");
	
	Class c = Class.forName(type); 	
	Object obj = c.newInstance();
	
	Field fields[] = c.getDeclaredFields();
	for (Field field : fields) { 
		String fieldName = field.getName(); 
		try {
			field.set(obj, rs.getString(fieldName));
		} catch (Exception e) {
			field.set(obj, rs.getInt(fieldName));
		} 
	}
	return obj;
//String json =mapper.writeValueAsString(obj);
//	LinkedHashMap<String,Object> map = getMap(obj);
//	for (Entry<String, Object> entity : map.entrySet()){
//		map.put(entity.getKey(), rs.getString(entity.getKey()));
//	}
//	String json =mapper.writeValueAsString(map);
//	System.out.println(json);
//	return mapper.readValue(json,Class.forName((String) map.get(type)));
}


}

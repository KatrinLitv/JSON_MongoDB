package tel_ran.persons.dao;

import java.sql.*;
import java.util.*;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import tel_ran.databases.mongo.MongoConnection;
import tel_ran.persons.entities.Person;

public class PersonsMongo {
private static final String COLLECTION_NAME = "persons";
private static MongoCollection<Document> persons;

public PersonsMongo(String uriStr, String databaseName){
	MongoConnection mongoConnection = MongoConnection.getMongoCollection(uriStr, databaseName);
	persons = mongoConnection.getDataBase().getCollection(COLLECTION_NAME);	
}

public boolean addPerson (Person person){
	persons.insertOne(getDocument(person));
	return true;
}

private Document getDocument(Person person) {
	Document res = new Document();
	res.put("id", person.getId());
	res.put("name", person.getName());
	res.put("birthYear", person.getBirthYear());
	return res;
}

public void drop(){
	persons.drop();
}

public static Person getPerson(int id) {
	Document query = new Document("_id",id);
	FindIterable<Document> resIterable = persons.find(query);
	if (resIterable==null)
		return null;
//	Iterator<Document> it = resIterable.iterator();
//	if (!it.hasNext())
//		return null;
	Document resDocument = resIterable.first();
	if (resDocument==null)
		return null;
	//return getPersonFromDocument(it.next());
	return getPersonFromDocument(resDocument);
}

private static Person getPersonFromDocument(Document resDocument) {	
	return new Person((int)resDocument.get("id"), resDocument.getString("name"), resDocument.getInteger("birthYear"));
}

public Iterable<Person> getPersonsByName(String name) {
	Document query = new Document("name",name);
	FindIterable<Document> resIterable = persons.find(query);
	if (resIterable==null)
		return null;
	return getPersons(resIterable);
}

private Iterable<Person> getPersons(FindIterable<Document> resIterable) {
	ArrayList<Person> res = new ArrayList<>();
	for (Document doc : resIterable){
		if (doc!=null)
			res.add(getPersonFromDocument(doc));
	}
		
	return res;
}

public Iterable<Person> getPersonsByYearAndName(String name, int year) {
	Document query = new Document("name", name).append("birthYear", new Document("$gte",year));
	FindIterable<Document> resIterable = persons.find(query);
	return getPersons(resIterable);
}

public Iterable<Person> getPersonsByNames(String[] names) {
	List<Document> queryList = getQueryList(names);
	Document query = new Document("$or",queryList);
	FindIterable<Document> resIterable = persons.find(query);
	return getPersons(resIterable);
}

private List<Document> getQueryList(String[] names) {
	List<Document> res = new ArrayList<>();
	for (String name : names)
		res.add(new Document("name",name));
	return res;
}
}

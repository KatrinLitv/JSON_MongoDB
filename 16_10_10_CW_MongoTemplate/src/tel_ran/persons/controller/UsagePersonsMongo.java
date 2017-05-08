package tel_ran.persons.controller;

import tel_ran.persons.entities.Person;

import org.bson.Document;

import tel_ran.mongo.MongoTemplate;

public class UsagePersonsMongo {

	public static void main(String[] args) throws Exception {
		MongoTemplate<Person, Integer> personsMongo=new MongoTemplate<>
		("mongodb://root:Paris2005@ds053126.mlab.com:53126/","katrin_litv","MyPersons");
		System.out.println("=====  FIND_ONE  =====");
		System.out.println(personsMongo.findOne(18806));
		
		System.out.println("=====  FIND_MANY  =====");
		String name="nameChild1";
		int year=2000;
		Document query = new Document("name", name).append("birthYear", new Document("$gte",year));
		displayPersons(personsMongo.findMany(query));
	}

	private static void displayPersons(Iterable<Person> personsAge) {
		for (Person person : personsAge){
			System.out.println(person);
		}
	}

}

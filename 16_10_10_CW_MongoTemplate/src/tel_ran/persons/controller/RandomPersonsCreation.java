package tel_ran.persons.controller;

import java.util.*;

import tel_ran.mongo.MongoTemplate;
import tel_ran.persons.entities.Adult;
import tel_ran.persons.entities.Child;
import tel_ran.persons.entities.Person;
import static tel_ran.persons.controller.interfaces.PersonsRandomConstants.*;

public class RandomPersonsCreation {

	public static void main(String[] args) throws Exception {
		MongoTemplate<Person,Integer> personsMongo=new MongoTemplate<>
				("mongodb://root:Paris2005@ds053126.mlab.com:53126/","katrin_litv","MyPersons");
		List<Person> persons=createRandomPersons();
		//personsMongo.saveMany(persons);
		for(Person person : persons){
			personsMongo.saveOne(person);
		}		
	}

	private static List<Person> createRandomPersons() {
		List<Person> res = new ArrayList<>();
		for (int i=0; i<N_PERSONS;i++){
			res.add(createOneRandomPerson());
			res.add(createOneRandomChild());
			res.add(createOneRandomAdult());
		}
		return res;
	}

	private static Person createOneRandomPerson() {
		int id = getRandomNumber(MIN_ID,MAX_ID);
		String name = "name" + getRandomNumber(0, N_NAME);
		int birthYear = getRandomNumber(MIN_BIRTH_YEAR, MAX_BIRTH_YEAR);
		return new Person(id, name, birthYear);
	}

	private static Child createOneRandomChild() {
		int id = getRandomNumber(MIN_ID,MAX_ID);
		String name = "nameChild" + getRandomNumber(0, N_NAME);
		int birthYear = getRandomNumber(MIN_BIRTH_YEAR_CHILD, MAX_BIRTH_YEAR_CHILD);;
		String kindergarten="gan_eladim"+ getRandomNumber(0, N_NAME);;
		return new Child(id, name, birthYear,kindergarten);
	}
	
	private static Adult createOneRandomAdult() {
		int id = getRandomNumber(MIN_ID,MAX_ID);
		String name = "name" + getRandomNumber(0, N_NAME);
		int birthYear = getRandomNumber(MIN_BIRTH_YEAR, MAX_BIRTH_YEAR);
		int salary = getRandomNumber(MIN_SALARY, MAX_SALARY);
		return new Adult(id, name, birthYear,salary);
	}
	
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random()*(max-min+1)+min);
	}

}

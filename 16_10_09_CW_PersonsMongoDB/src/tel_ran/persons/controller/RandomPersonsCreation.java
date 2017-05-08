package tel_ran.persons.controller;

import java.util.*;

import tel_ran.persons.dao.PersonsMongo;
import tel_ran.persons.entities.Person;
//чтобы не указывать имя класс делается такой импорт
import static tel_ran.persons.controller.interfaces.PersonsRandomConstants.*;

public class RandomPersonsCreation {

	public static void main(String[] args) throws Exception {
		PersonsMongo personsMongo=new PersonsMongo
				("mongodb://root:Paris2005@ds053126.mlab.com:53126/","katrin_litv");
		personsMongo.drop();
		List<Person> persons=createRandomPersons();
		for(Person person : persons){
			personsMongo.addPerson(person);
		}
		
	}

	private static List<Person> createRandomPersons() {
		List<Person> res = new ArrayList<>();
		for (int i=0; i<N_PERSONS;i++)
			res.add(createOneRandomPerson());
		return res;
	}

	private static Person createOneRandomPerson() {
		int id = getRandomNumber(MIN_ID,MAX_ID);
		String name = "name" + getRandomNumber(0, N_NAME);
		int birthYear = getRandomNumber(MIN_BIRTH_YEAR, MAX_BIRTH_YEAR);;
		return new Person(id, name, birthYear);
	}

	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random()*(max-min+1)+min);
	}

}
